package com.amigoscode.product
;

import java.sql.SQLException;

public interface ProductDao {
    Product findProductById(Long id);
}
