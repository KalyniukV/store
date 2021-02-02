DROP DATABASE IF EXISTS store;
DROP USER IF EXISTS `store`@`%`;
CREATE DATABASE IF NOT EXISTS store CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `store`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `store`.* TO `store`@`%`;
FLUSH PRIVILEGES;

use store;

create table orders (
    id      integer auto_increment,
    user_id integer,
    status  varchar(255),
    created_at varchar(255),
    CONSTRAINT pk_orders_id PRIMARY KEY (id)
);

create table products (
    id      integer auto_increment,
    name    varchar(255),
    price   integer,
    status  varchar(255),
    created_at timestamp,
    CONSTRAINT pk_products_id PRIMARY KEY (id)
);


create table order_items (
    order_id integer,
    product_id integer,
    quantity integer,

    CONSTRAINT fk_order_id FOREIGN KEY (order_id)
        REFERENCES orders (id)  ON DELETE CASCADE,
    CONSTRAINT fk_product_id FOREIGN KEY (product_id)
        REFERENCES products (id)  ON DELETE CASCADE
);