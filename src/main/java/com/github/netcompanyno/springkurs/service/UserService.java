package com.github.netcompanyno.springkurs.service;

import java.util.List;

import com.github.netcompanyno.springkurs.domain.User;

public interface UserService {
    
    User getById(long id);
    
    List<User> getAll();
    
    /**
     * Lagre en bruker
     * @param user Brukeren som lagres
     * @return Brukeren som har blitt lagret med tildelt brukerid
     */
    User insert(User user);

}
