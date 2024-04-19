package com.amigoscode.product;

import java.util.List;
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

    @Override
    public int createProduct(Product product) {
       var sql= "INSERT INTO products (product_name, numberproducts) VALUES (?, ?)";
       return jdbcTemplate.update(sql, product.getProduct_name(), product.getNumberProducts());
    }

    @Override
    public int updateProduct(Product product) {
       var sql= "UPDATE products SET product_name = ?, numberproducts = ? WHERE product_id = ?";
         return jdbcTemplate.update(sql, product.getProduct_name(), product.getNumberProducts(), product.getProduct_id());
    }

    @Override
    public int deleteProduct(Long id) {
        var sql= "DELETE FROM products WHERE product_id = ?";
        return jdbcTemplate.update(sql, id);
    }
    @Override 
    public Product findProductByName(String name){
        var sql ="SELECT product_id, product_name, numberproducts FROM products WHERE product_name = ?";
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), name);
    }
    @Override
    public List<Product> findAllProducts() {
        var sql = "SELECT product_id, product_name, numberproducts FROM products";
        return jdbcTemplate.query(sql, new ProductRowMapper()).stream().collect(Collectors.toList());
    }
}
