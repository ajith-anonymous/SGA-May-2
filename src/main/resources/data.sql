-- =============================================
-- Sample Data: 10 Customers and 10 Orders
-- =============================================

-- Insert 10 Customers
INSERT INTO customers (name, email, phone, address) VALUES ('Rajesh Kumar', 'rajesh.kumar@email.com', '9876543210', '12 MG Road, Bangalore');
INSERT INTO customers (name, email, phone, address) VALUES ('Priya Sharma', 'priya.sharma@email.com', '9876543211', '45 Anna Nagar, Chennai');
INSERT INTO customers (name, email, phone, address) VALUES ('Amit Patel', 'amit.patel@email.com', '9876543212', '78 SG Highway, Ahmedabad');
INSERT INTO customers (name, email, phone, address) VALUES ('Sneha Reddy', 'sneha.reddy@email.com', '9876543213', '23 Jubilee Hills, Hyderabad');
INSERT INTO customers (name, email, phone, address) VALUES ('Vikram Singh', 'vikram.singh@email.com', '9876543214', '56 Connaught Place, Delhi');
INSERT INTO customers (name, email, phone, address) VALUES ('Ananya Nair', 'ananya.nair@email.com', '9876543215', '89 Marine Drive, Mumbai');
INSERT INTO customers (name, email, phone, address) VALUES ('Karthik Iyer', 'karthik.iyer@email.com', '9876543216', '34 Koramangala, Bangalore');
INSERT INTO customers (name, email, phone, address) VALUES ('Divya Menon', 'divya.menon@email.com', '9876543217', '67 Vyttila, Kochi');
INSERT INTO customers (name, email, phone, address) VALUES ('Arjun Das', 'arjun.das@email.com', '9876543218', '90 Salt Lake, Kolkata');
INSERT INTO customers (name, email, phone, address) VALUES ('Meera Joshi', 'meera.joshi@email.com', '9876543219', '12 FC Road, Pune');

-- Insert 10 Orders (linked to customers)
INSERT INTO orders (product_name, quantity, total_price, order_date, status, customer_id) VALUES ('Laptop Dell XPS 15', 1, 125000.00, '2026-01-15', 'Delivered', 1);
INSERT INTO orders (product_name, quantity, total_price, order_date, status, customer_id) VALUES ('iPhone 16 Pro', 1, 134900.00, '2026-02-10', 'Shipped', 2);
INSERT INTO orders (product_name, quantity, total_price, order_date, status, customer_id) VALUES ('Samsung Galaxy S25', 2, 159800.00, '2026-02-20', 'Processing', 3);
INSERT INTO orders (product_name, quantity, total_price, order_date, status, customer_id) VALUES ('Sony WH-1000XM5', 3, 29970.00, '2026-03-05', 'Delivered', 4);
INSERT INTO orders (product_name, quantity, total_price, order_date, status, customer_id) VALUES ('MacBook Air M3', 1, 114900.00, '2026-03-12', 'Shipped', 5);
INSERT INTO orders (product_name, quantity, total_price, order_date, status, customer_id) VALUES ('iPad Pro 13 inch', 1, 112900.00, '2026-03-18', 'Processing', 6);
INSERT INTO orders (product_name, quantity, total_price, order_date, status, customer_id) VALUES ('Bose QC Ultra', 2, 49980.00, '2026-04-01', 'Delivered', 7);
INSERT INTO orders (product_name, quantity, total_price, order_date, status, customer_id) VALUES ('Canon EOS R6 Mark II', 1, 175000.00, '2026-04-10', 'Shipped', 8);
INSERT INTO orders (product_name, quantity, total_price, order_date, status, customer_id) VALUES ('Nintendo Switch 2', 2, 69980.00, '2026-04-15', 'Processing', 9);
INSERT INTO orders (product_name, quantity, total_price, order_date, status, customer_id) VALUES ('Kindle Paperwhite', 5, 74950.00, '2026-04-20', 'Delivered', 10);
