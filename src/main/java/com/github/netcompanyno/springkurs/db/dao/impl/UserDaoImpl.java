package com.github.netcompanyno.springkurs.db.dao.impl;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.netcompanyno.springkurs.db.dao.UserDao;
import com.github.netcompanyno.springkurs.db.rowmapper.UserRowMapper;
import com.github.netcompanyno.springkurs.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
    
    private final NamedParameterJdbcTemplate namedTemplate;
    
    @Autowired
    public UserDaoImpl(final NamedParameterJdbcTemplate namedTemplate) {
        this.namedTemplate = namedTemplate;
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(final long id) {
        return DataAccessUtils.singleResult(
                namedTemplate.query("SELECT * FROM USERS WHERE id = :id",
                        new MapSqlParameterSource().addValue("id", id),
                        new UserRowMapper()));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return namedTemplate.query("SELECT * FROM USERS", new UserRowMapper());
    }

    @Transactional
    @Override
    public long insert(final User user) {
        // Hint: KeyHolder
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

}
