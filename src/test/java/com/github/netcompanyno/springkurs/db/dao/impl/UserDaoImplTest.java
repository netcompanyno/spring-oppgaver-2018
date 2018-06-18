package com.github.netcompanyno.springkurs.db.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.netcompanyno.springkurs.db.dao.UserDao;
import com.github.netcompanyno.springkurs.db.dao.impl.UserDaoImpl;
import com.github.netcompanyno.springkurs.domain.User;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = { "flyway.enabled=false" })
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/migration/V1__Create_table_users.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, statements = "DROP TABLE USERS")
})
public class UserDaoImplTest {
    
    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;
    
    private UserDao userDao;
    
    @Before
    public void before() {
        userDao = new UserDaoImpl(namedTemplate);
    }
    
    @Test
    @IfProfileValue(name ="oppgave", value ="del2")
    public void getById_withUserIdThatDoesNotExist_shouldReturnNull() {
        
        // Arrange
        final long idThatdoesNotExist = 999;
        insert(new User("test@test", "fornavn", "etternavn"));
        
        // Act
        final User user = userDao.getById(idThatdoesNotExist);
        
        // Assert
        assertThat(user).isNull();
    }
    
    @Test
    @IfProfileValue(name ="oppgave", value ="del2")
    public void getById_withUserIdThatDoesExist_shouldReturnCorrectUser() {
        // Arrange
        final User expectedUser = new User(1, "test@test", "fornavn", "etternavn");
        insert(expectedUser);
        
        // Act
        final User user = userDao.getById(1);
        
        // Assert
        assertThat(user).isNotNull();
        assertThat(user).isEqualToComparingFieldByField(expectedUser);
    }
    
    @Test
    @IfProfileValue(name ="oppgave", value ="del1")
    public void getAll_whenNoUsers_shouldReturnEmptyList() {
        // Arrange
        // Act
        final List<User> users = userDao.getAll();
        
        
        // Assert
        assertThat(users).isNotNull();
        assertThat(users).isEmpty();
    }
    
    @Test
    @IfProfileValue(name ="oppgave", value ="del1")
    public void getAll_whenUsersExist_shouldReturnAllUsers() {
        
        // Arrange
        final User user1 = new User("test@test", "firstname", "lastname");
        final User user2 = new User("test@test2", "firstname2", "lastname2");
        insert(user1);
        insert(user2);
        
        // Act
        final List<User> users = userDao.getAll();
        
        // Assert
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(2);
    }
    
    private void insert(final User user) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", user.getEmail());
        params.addValue("firstName", user.getFirstname());
        params.addValue("lastName", user.getLastname());
        namedTemplate.update(
                "INSERT INTO USERS(email, firstName, lastName) VALUES(:email, :firstName, :lastName)",
                params);
    }
    

}
