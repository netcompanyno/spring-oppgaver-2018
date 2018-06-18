package com.github.netcompanyno.springkurs.db.dao.impl;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.github.netcompanyno.springkurs.db.dao.UserDao;
import com.github.netcompanyno.springkurs.domain.User;

public class UserDaoImpl implements UserDao {
    
    public UserDaoImpl(final NamedParameterJdbcTemplate namedTemplate) {
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(final long id) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    @Transactional
    @Override
    public long insert(final User user) {
        // Hint: KeyHolder
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

}
