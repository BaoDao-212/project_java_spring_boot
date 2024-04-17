package com.amigoscode.user;

import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public int registerUser(User user) {
        System.out.println("Registering user: " + user);

        String sql = "INSERT INTO users(username, password, email, fullName, role) VALUES (?, ?, ?, ?, ?)";
        String[] rolesArray = user.getRole().stream()
                .map(Enum::name)
                .toArray(String[]::new);

        return jdbcTemplate.update(
                sql,
                user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName(), rolesArray);
    }

    @Override
    public User findUserByUsername(String username) {
        var sql = """
                SELECT id, username, password, email, fullName, role
                FROM users
                WHERE username = ?
                 """;
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
    }

    @Override
    public User findUserById(Long id) {
        var sql = """
                SELECT id, username, password, email, fullName, role
                FROM users
                WHERE id = ?
                 """;
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

    @Override
    public int updateUser(User user) {
       var sql ="UPDATE users SET email = ?, fullName = ?, role = ? WHERE id = ?";
       String[] rolesArray = user.getRole().stream()
               .map(Enum::name)
               .toArray(String[]::new);

       return jdbcTemplate.update(
           sql,
           user.getEmail(), user.getFullName(), rolesArray, user.getId()
       );
    }

    @Override
    public int updatePasswordUser(User user) {

        var sql = "UPDATE users SET password = ? WHERE id = ?";
        return jdbcTemplate.update(
                sql,
                user.getPassword(), user.getId());
    }

}
