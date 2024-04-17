package com.amigoscode.user;


import org.springframework.jdbc.core.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        // System.out.println(resultSet.getString("role"));
        Array sqlArray = resultSet.getArray("role");
        String[] strArray = (String[])sqlArray.getArray();
        List<Role> roles = Arrays.stream(strArray)
        .map(Role::valueOf)
        .collect(Collectors.toList());
        return new User(
               resultSet.getLong("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("email"),
                resultSet.getString("fullName"),
                roles
        );
    }
}
