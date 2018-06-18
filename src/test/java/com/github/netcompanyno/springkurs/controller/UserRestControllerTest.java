package com.github.netcompanyno.springkurs.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.netcompanyno.springkurs.domain.User;
import com.github.netcompanyno.springkurs.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRestControllerTest {
    
    @MockBean
    private UserService userService;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    @IfProfileValue(name ="oppgave", value ="del1")
    public void getAll_returnsListOfUsersWithHttpStatusOK200() {
        // Arrange
        final List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User("test@test", "fornavn", "etternavn"));
        doReturn(expectedUsers).when(userService).getAll();
        
        // Act
        final ResponseEntity<List<User>> response = restTemplate.exchange(
                "/users", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSameSizeAs(expectedUsers);
    }
    
    @Test
    @IfProfileValue(name ="oppgave", value ="del2")
    public void getById_userExists_returnsExpectedUserWithHttpStatusOK200() {
        
        // Arrange
        final User expectedUser = new User(1,"test@test", "fornavn", "etternavn");
        doReturn(expectedUser).when(userService).getById(anyLong());
        
        // Act
        final ResponseEntity<User> userResponse = restTemplate.getForEntity("/user/1", User.class);
        
        // Assert
        assertThat(userResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userResponse.getBody().equals(expectedUser));
    }
    
    @Test
    @IfProfileValue(name ="oppgave", value ="del2")
    public void getById_userDoesNotExist_returnsHttpStatusNotFound404() {
        
        // Arrange
        doReturn(null).when(userService).getById(anyLong());
        
        // Act
        final ResponseEntity<User> userResponse = restTemplate.getForEntity("/user/1", User.class);
        
        // Assert
        assertThat(userResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
