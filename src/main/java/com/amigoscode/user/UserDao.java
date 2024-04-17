package com.amigoscode.user;

import java.sql.SQLException;

public interface UserDao {
    int registerUser(User user) throws SQLException;
    User findUserByUsername(String username);
    int updatePasswordUser(User user);
    int updateUser(User user);
    User findUserById(Long id);
}
