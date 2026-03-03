SET SQL_MODE='IGNORE_SPACE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- =========================
-- CATEGORY DATA (SAFE)
-- =========================

INSERT IGNORE INTO category(name) VALUES
('Fruits'),
('Vegetables'),
('Meat'),
('Fish'),
('Dairy'),
('Bakery'),
('Drinks'),
('Sweets'),
('Electronics');


-- =========================
-- CUSTOMER DATA (SAFE)
-- =========================

INSERT IGNORE INTO customer(address,email,password,role,username) VALUES

('Admin Address',
'admin@gmail.com',
'$2a$12$rw9i5668mEDXSecGu1b6GOIX6P/1.7Eqhl6nBvcuqbBY1IwEqay1q',
'ROLE_ADMIN',
'admin'),

('User Address',
'user@gmail.com',
'$2a$12$rw9i5668mEDXSecGu1b6GOIX6P/1.7Eqhl6nBvcuqbBY1IwEqay1q',
'ROLE_USER',
'user');


-- =========================
-- PRODUCT DATA (SAFE)
-- =========================

INSERT IGNORE INTO product
(description,image,name,price,quantity,weight,category_id)
VALUES

-- Fruits

('Fresh Apple',
'https://via.placeholder.com/150',
'Apple',3,40,100,1),

('Sweet Mango',
'https://via.placeholder.com/150',
'Mango',5,30,200,1),

('Yellow Banana',
'https://via.placeholder.com/150',
'Banana',2,60,120,1),


-- Vegetables

('Organic Tomato',
'https://via.placeholder.com/150',
'Tomato',3,50,150,2),

('Fresh Potato',
'https://via.placeholder.com/150',
'Potato',2,70,250,2),


-- Meat

('Chicken Breast',
'https://via.placeholder.com/150',
'Chicken',12,25,500,3),


-- Fish

('Fresh Fish',
'https://via.placeholder.com/150',
'Fish',15,20,700,4),


-- Dairy

('Milk Pack',
'https://via.placeholder.com/150',
'Milk',4,50,1000,5),

('Cheese Block',
'https://via.placeholder.com/150',
'Cheese',8,30,500,5),


-- Bakery

('Bread Loaf',
'https://via.placeholder.com/150',
'Bread',3,40,400,6),

('Chocolate Cake',
'https://via.placeholder.com/150',
'Cake',10,20,800,6),


-- Drinks

('Orange Juice',
'https://via.placeholder.com/150',
'Juice',5,35,1000,7),

('Soft Drink',
'https://via.placeholder.com/150',
'Cola',2,60,500,7),


-- Sweets

('Ice Cream',
'https://via.placeholder.com/150',
'Ice Cream',6,25,300,8),

('Candy Pack',
'https://via.placeholder.com/150',
'Candy',2,80,200,8),


-- Electronics

('Gaming Laptop',
'https://via.placeholder.com/150',
'Laptop',50000,10,2000,9),

('Android Phone',
'https://via.placeholder.com/150',
'Mobile',20000,20,300,9),

('Wireless Headphones',
'https://via.placeholder.com/150',
'Headphones',3000,30,250,9),

('Gaming Mouse',
'https://via.placeholder.com/150',
'Mouse',1500,40,200,9),

('LED Monitor',
'https://via.placeholder.com/150',
'Monitor',12000,15,3500,9),

('Bluetooth Speaker',
'https://via.placeholder.com/150',
'Speaker',4000,25,900,9),

('USB Cable',
'https://via.placeholder.com/150',
'USB Cable',200,100,50,9),

('Power Bank',
'https://via.placeholder.com/150',
'Power Bank',1800,40,400,9);