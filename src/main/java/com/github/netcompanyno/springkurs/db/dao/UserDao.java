package com.github.netcompanyno.springkurs.db.dao;

import java.util.List;

import com.github.netcompanyno.springkurs.domain.User;

public interface UserDao {
    
    User getById(long id);
    
    List<User> getAll();
    
    /**
     * Lagre en bruker
     * @param Brukeren som skal lagres
     * @return Tildelt id for brukeren som ble lagret
     */
    long insert(User user);

}
