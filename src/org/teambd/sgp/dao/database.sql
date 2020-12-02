-- Database
DROP DATABASE IF EXISTS product_stock;
CREATE DATABASE product_stock;
USE product_stock;

-- Tables
DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    permission ENUM('admin') NOT NULL,
    is_active BIT(1) DEFAULT 1 NOT NULL,

    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS brand;
CREATE TABLE brand (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) UNIQUE NOT NULL,

    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS category;
CREATE TABLE category (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) UNIQUE NOT NULL,

    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT NULL,
    brand_id_fk INT NOT NULL,
    category_id_fk INT NOT NULL,
    elaboration_date DATE NOT NULL,
    expiration_date DATE NULL,
    gross_price INT NOT NULL DEFAULT 0,
    net_price INT NOT NULL DEFAULT 0,
    stock INT NOT NULL DEFAULT 0,
    is_great BIT(1) NOT NULL DEFAULT 0,
    is_active BIT(1) NOT NULL DEFAULT 1,

    PRIMARY KEY(id),
    FOREIGN KEY(brand_id_fk) REFERENCES brand(id),
    FOREIGN KEY(category_id_fk) REFERENCES category(id)
);

DROP TABLE IF EXISTS price_history;
CREATE TABLE price_history (
    id INT AUTO_INCREMENT NOT NULL,
    product_id_fk INT NOT NULL,
    actual_price INT NOT NULL,
    new_price INT NOT NULL,
    update_date DATE NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(product_id_fk) REFERENCES product(id)
);

-- Function

-- Procedures
DROP PROCEDURE IF EXISTS recursive_eliminator;
DELIMITER //
CREATE PROCEDURE recursive_eliminator_brand(IN _id INT)
BEGIN
    DECLARE have_childs INT;
    SET have_childs = (SELECT COUNT(*) FROM product WHERE brand_id_fk = _id );


    IF have_childs > 0 THEN
        DELETE FROM product WHERE id IN (SELECT id FROM product WHERE brand_id_fk = _id);
        DELETE FROM brand WHERE id = _id;
    ELSE
        DELETE FROM brand WHERE id = _id;
    END IF;
END //
DELIMITER ;

CALL recursive_eliminator_brand(2);


-- Triggers
DROP TRIGGER IF EXISTS apply_net_price;
DELIMITER //
CREATE TRIGGER apply_net_price BEFORE INSERT ON product
    FOR EACH ROW
BEGIN
    SET NEW.net_price = NEW.gross_price * 1.19;
END //
DELIMITER ;


DROP TRIGGER IF EXISTS change_price;
DELIMITER //
CREATE TRIGGER change_price BEFORE UPDATE ON product
    FOR EACH ROW
BEGIN
    IF OLD.gross_price != NEW.gross_price THEN
        INSERT INTO price_history (product_id_fk, actual_price, new_price, update_date)
        VALUES (OLD.id, OLD.gross_price, NEW.gross_price, NOW());
        SET NEW.net_price = NEW.gross_price * 1.19;
    END IF;
END //
DELIMITER ;


-- Data
INSERT INTO user (username, password, permission) VALUES
('ElWazy', SHA2('1324', 512), 'admin'),
('Gaston', SHA2('1234', 512), 'admin'),
('admin', SHA2('admin', 512), 'admin'),
('master', SHA2('master', 512), 'admin');

SELECT * FROM user;


INSERT INTO brand (name) VALUES
("Hellmans"),
("Coca-Cola Company"),
("CCU"),
("Heredia"),
("PF"),
("San Jorge"),
("Colum: toda la magia del sur");

INSERT INTO brand (name) VALUES
("Comafri");

SELECT * FROM brand;


INSERT INTO category (name) VALUES
("Bebestibles"),
("Fiambres"),
("Lacteos"),
("Comida"),
("Electronica");

SELECT * FROM category;


INSERT INTO product
(name, description, brand_id_fk, category_id_fk, elaboration_date, expiration_date,
gross_price, stock, is_great)
VALUES
('Mayo Supreme',           'La raja',                  1, 4, NOW(), NULL, 790,   14, 1),
('Keptchup Supreme',       'existe?',                  1, 4, NOW(), NULL, 990,   14, 0),
('Coca-Cola 3L',           'bebida de fantasia',       2, 1, NOW(), NULL, 2520,  36, 1),
('Sprite 3L',              'bebida de fantasia',       2, 1, NOW(), NULL, 2520,  24, 0),
('Pepsi 3L',               'bebida copia de fantasia', 3, 1, NOW(), NULL, 1720,  36, 1),
('Bilz 3L',                'yo quiero otro mundo',     3, 1, NOW(), NULL, 1720,  24, 0),
('Saco de harina',         '25kg',                     4, 4, NOW(), NULL, 11990, 40, 0),
('2/8 Mortadela de cerdo', 'Turin de cerdo',           5, 2, NOW(), NULL, 1520,  10, 1),
('1/4 Jamon colonial',     'Turin colonial',           6, 2, NOW(), NULL, 2690,  5,  0),
('Leche entera',           '1L',                       7, 3, NOW(), NULL, 990,   10, 0);

UPDATE product SET gross_price = 550 WHERE id = 2;

SELECT * FROM product;
SELECT * FROM price_history;