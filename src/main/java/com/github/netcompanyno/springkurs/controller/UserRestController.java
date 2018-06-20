package com.github.netcompanyno.springkurs.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.netcompanyno.springkurs.domain.User;
import com.github.netcompanyno.springkurs.service.UserService;

@RestController
public class UserRestController {
    
    private final UserService userService;
    
    @Autowired
    public UserRestController(final UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }
    
}
