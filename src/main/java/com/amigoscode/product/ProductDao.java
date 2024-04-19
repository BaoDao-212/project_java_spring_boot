package com.amigoscode.product
;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    Product findProductById(Long id);
    Product findProductByName(String name);
    int createProduct(Product product);
    int updateProduct(Product product);
    int deleteProduct(Long id);
    List<Product> findAllProducts();
}
