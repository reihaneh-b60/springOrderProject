INSERT INTO user(user_id, password, role, user_name) VALUES (10,'123', 0, 'ali_1');
INSERT INTO user(user_id, password, role, user_name) VALUES (20,'1232', 1, 'mina_1');
INSERT INTO user(user_id, password, role, user_name) VALUES (30,'1233', 1, 'sara_1');

INSERT INTO orders (order_id, order_code, order_name,status, user_id) VALUES (1, 'ORD001', 'Order One', 1,10);
INSERT INTO orders (order_id, order_code, order_name,status,user_id) VALUES (2, 'ORD002', 'Order Two',0,20);
INSERT INTO orders (order_id, order_code, order_name,status, user_id) VALUES (3, 'ORD003', 'Order Three',2,30);

INSERT INTO order_item (order_id, product_id, order_quantity) VALUES (1, 101, 2);
INSERT INTO order_item (order_id, product_id, order_quantity) VALUES (1, 102, 1);
INSERT INTO order_item (order_id, product_id, order_quantity) VALUES (2, 103, 5);
INSERT INTO order_item (order_id, product_id, order_quantity) VALUES (3, 104, 3);

