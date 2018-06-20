package com.github.netcompanyno.springkurs.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.netcompanyno.springkurs.db.dao.UserDao;
import com.github.netcompanyno.springkurs.domain.User;
import com.github.netcompanyno.springkurs.service.UserService;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    
    @Mock
    private UserDao userDao;
    
    private UserService userService;
    
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userDao);
    }
    
    @Test
    public void getById_withUserIdThatDoesNotExist_shouldReturnNull() {
        
        // Arrange
        doReturn(null).when(userDao).getById((anyLong()));
        
        // Act
        final User user = userService.getById(1);
        
        // Assert
        assertThat(user).isNull();
        
    }
    
    @Test
    @IfProfileValue(name ="oppgave", value ="del2")
    public void getById_withUserIdThatDoesExist_shouldReturnCorrectUser() {
        // Arrange
        final User expectedUser = new User(1, "test@test", "fornavn", "etternavn");
        doReturn(expectedUser).when(userDao).getById((anyLong()));
        
        // Act
        final User user = userService.getById(1);
        
        // Assert
        assertThat(user).isNotNull();
        assertThat(user).isEqualToComparingFieldByField(expectedUser);
    }
    
    @Test
    @IfProfileValue(name ="oppgave", value ="del1")
    public void getAll_whenNoUsers_shouldReturnEmptyList() {
        // Arrange
        doReturn(Collections.emptyList()).when(userDao).getAll();
        
        // Act
        final List<User> users = userService.getAll();
        
        // Assert
        assertThat(users).isNotNull();
        assertThat(users).isEmpty();
    }
    
    @Test
    @IfProfileValue(name ="oppgave", value ="del1")
    public void getAll_whenUsersExist_shouldReturnListOfAllUsers() {
        // Arrange
        final List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User(1, "test@test", "fornavn", "etternavn"));
        expectedUsers.add(new User(2, "test2@test", "fornavn2", "etternavn2"));
        doReturn(expectedUsers).when(userDao).getAll();
        
        // Act
        final List<User> users = userService.getAll();
        
        // Assert
        assertThat(users).isNotNull();
        assertThat(users).isEqualTo(expectedUsers);
    }

}
