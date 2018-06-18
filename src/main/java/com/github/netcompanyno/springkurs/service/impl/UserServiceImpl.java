package com.github.netcompanyno.springkurs.service.impl;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.netcompanyno.springkurs.db.dao.UserDao;
import com.github.netcompanyno.springkurs.domain.User;
import com.github.netcompanyno.springkurs.service.UserService;

public class UserServiceImpl implements UserService {
    
    public UserServiceImpl(final UserDao userDao) {
    }
    
    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    @Override
    public User getById(final long id) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    @Override
    public User insert(final User user) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

}
