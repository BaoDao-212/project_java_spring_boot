package com.amigoscode.product;

import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Product findProductById(Long id) {
        var sql ="SELECT product_id, product_name, numberproducts FROM products WHERE product_id = ?";
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);
    }


    

  
}
