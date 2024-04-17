CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    user_id INT,
    order_date DATE,
    -- Other fields like shipping_address, total_amount, etc.
    FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(100)
    -- Other fields like product_description, price, etc.
);

CREATE TABLE orderItems (
    order_item_id SERIAL PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);
