-- Database
DROP DATABASE poduct_stock;
CREATE DATABASE poduct_stock;
USE product_stock;

-- Tables
CREATE TABLE user (
    id INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    permission ENUM('admin') NOT NULL,
    is_active BIT(1) DEFAULT 1 NOT NULL
);

CREATE TABLE brand (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) UNIQUE NOT NULL,
);

CREATE TABLE category (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) UNIQUE NOT NULL,
);

CREATE TABLE price_history (
    id INT AUTO_INCREMENT NOT NULL,
    product_id_fk INT NOT NULL,
    actual_price INT NOT NULL,
    new_price INT NOT NULL,
    update_date DATE NOT NULL
);

CREATE TABLE product (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT NULL,
    image_path VARCHAR null,
    brand_id_fk INT NOT NULL,
    category_id_fk INT NOT NULL,
    elaboration_date DATE NOT NULL,
    expiration_date DATE NULL,
    gross_price INT NOT NULL DEFAULT 0,
    net_price INT NOT NULL DEFAULT 0,
    stock INT NOT NULL DEFAULT 0,
    is_great BIT(1) NOT NULL DEFAULT 0,
    is_active BIT(1) NOT NULL DEFAULT 1,
);

CREATE TRIGGER change_price
BEFORE UPDATE ON product FOR EACH ROW
INSERT INTO price_history (product_id_fk, actual_price, new_price, update_date) VALUES
(OLD.id, OLD.net_price, NEW.net_price, NOW());

-- Data
INSERT INTO user (username, password, permission) VALUES
('ElWazy', SHA2('1324', 512), 'admin'),
('Gaston', SHA2('1234', 512), 'admin'),
('admin', SHA2('admin', 512), 'admin'),
('master', SHA2('master', 512), 'admin');

INSERT INTO brand (name) VALUES
("Hellmans"),
("Coca-Cola Company"),
("CCU"),
("Heredia"),
("PF"),
("San Jorge"),
("Colum: toda la magia del sur");

INSERT INTO category (name) VALUES
("Bebestibles"),
("Fiambres"),
("Lacteos"),
("Comida"),
("Electronica");

