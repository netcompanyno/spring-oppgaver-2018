package com.github.netcompanyno.springkurs.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.jdbc.core.RowMapper;

import com.github.netcompanyno.springkurs.domain.User;

public class UserRowMapper implements RowMapper<User>{

    @Override
    public User mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

}
