INSERT INTO products (product_name)
VALUES 
    ('Laptop Dell XPS 13'),
    ('Smartphone iPhone 13 Pro'),
    ('Smart TV Samsung QLED 4K'),
    ('Wireless Headphones Sony WH-1000XM4'),
    ('Digital Camera Canon EOS R5'),
    ('Fitness Tracker Fitbit Charge 5'),
    ('Gaming Console PlayStation 5'),
    ('Robot Vacuum Cleaner iRobot Roomba i7+'),
    ('Coffee Machine Nespresso VertuoPlus'),
    ('Electric Toothbrush Oral-B iO Series 9');
-- Thêm 5 đơn hàng vào bảng Order
INSERT INTO orders (user_id, order_date)
VALUES 
    (4, '2024-04-17'),
    (4, '2024-04-18'),
    (4, '2024-04-19'),
    (4, '2024-04-20'),
    (4, '2024-04-21');
-- Thêm các sản phẩm vào bảng OrderItems
-- Lấy order_id của 5 đơn hàng gần nhất
-- INSERT INTO orderItems (order_id, product_id, quantity, price)
-- VALUES
--     (1,1,12,12);
--     (1,2,1,12);
--     (1,3,1,12);
--     (1,4,1,12);
--     (1,5,1,12);
--     (2,6,1,12);
--     (2,7,1,12);
--     (2,8,1,12);
--     (2,9,1,12);
--     (2,10,1,12);
--     (3,1,1,12);
--     (3,2,1,12);
--     (3,3,1,12);
--     (3,4,1,12);
--     (3,5,1,12);
--     (4,6,1,12);
--     (4,7,1,12);
--     (4,8,1,12);
--     (4,9,1,12);
--     (4,10,1,12);
--     (5,1,1,12);
--     (5,2,1,12);
--     (5,3,1,12);
--     (5,4,1,12);
--     (5,5,1,12);