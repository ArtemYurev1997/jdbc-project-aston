CREATE TABLE user_table (
  id INT PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  login VARCHAR(30),
  password VARCHAR(30),
  role VARCHAR(30)
);

CREATE TABLE order_table (
  id INT PRIMARY KEY,
  key_order VARCHAR(50),
  cost_order NUMERIC,
  count_order INT,
  date_order TIMESTAMP,
  status VARCHAR(30),
  user_id INT REFERENCES user_table(id)
);

CREATE TABLE product_table (
  id INT PRIMARY KEY,
  name_product VARCHAR(50),
  code INT,
  price NUMERIC
);

CREATE TABLE basket_table (
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  PRIMARY KEY (order_id, product_id),
  FOREIGN KEY (order_id) REFERENCES order_table(id),
  FOREIGN KEY (product_id) REFERENCES product_table(id)
);

INSERT INTO user_table (id, first_name, last_name, login, password, role)
VALUES (1, 'Anton', 'Antonov', 'Antoha322', 'Anton322', 'Client');

INSERT INTO order_table (id, key_order, cost_order, count_order, date_order, status, user_id)
VALUES (1, 'cola-key', '3', '1', '2024-09-18 14:30:15', 'Unformed', '1');

INSERT INTO product_table (id, name_product, code, price)
VALUES (1, 'Cola', '1234', '2.5');