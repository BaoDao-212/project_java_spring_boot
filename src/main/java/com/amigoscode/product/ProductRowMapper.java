package com.amigoscode.product;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper  implements RowMapper<Product>{
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Product(
                resultSet.getLong("product_id"),
                resultSet.getString("product_name"),
                resultSet.getInt("numberproducts")
                
        );
    }
}
