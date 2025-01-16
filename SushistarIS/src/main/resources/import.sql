INSERT INTO product_category (category_name) VALUES ('Sushi');
INSERT INTO product_category (category_name) VALUES ('Sashimi');
INSERT INTO product_category (category_name) VALUES ('Nigiri');
INSERT INTO product_category (category_name) VALUES ('Maki');
INSERT INTO product_category (category_name) VALUES ('Temaki');
INSERT INTO product_category (category_name) VALUES ('Uramaki');
INSERT INTO product_category (category_name) VALUES ('Ramen');
INSERT INTO product_category (category_name) VALUES ('Donburi');
INSERT INTO product_category (category_name) VALUES ('Tempura');
INSERT INTO product_category (category_name) VALUES ('Yakitori');
INSERT INTO product_category (category_name) VALUES ('Gyoza');
INSERT INTO product_category (category_name) VALUES ('Onigiri');
INSERT INTO product_category (category_name) VALUES ('Okonomiyaki');
INSERT INTO product_category (category_name) VALUES ('Takoyaki');
INSERT INTO product_category (category_name) VALUES ('Mochi');
INSERT INTO product_category (category_name) VALUES ('Katsu');
INSERT INTO product_category (category_name) VALUES ('Bento');

INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Sashimi Mix', 15.99, 'Assortimento di sashimi freschissimo.', 50, 2);

INSERT INTO product (product_name, price, description, amount, product_category)VALUES ('Nigiri Set', 13.99, 'Set di nigiri con diverse varietà di pesce.', 60, 3);

INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Maki Mix', 10.99, 'Assortimento di maki tradizionali.', 80, 4);
INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Spicy Temaki', 8.99, 'Temaki piccante con salmone e avocado.', 40, 5);
INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Dragon Uramaki', 14.99, 'Uramaki con gamberi tempura e avocado.', 70, 6);
INSERT INTO product (product_name, price, description, amount, product_category)VALUES ('Ramen Pork', 12.50, 'Ramen caldo con brodo di maiale e noodles.', 90, 7);
INSERT INTO product (product_name, price, description, amount, product_category)VALUES ('Chicken Donburi', 11.50, 'Ciotola di riso con pollo teriyaki.', 75, 8);
INSERT INTO product (product_name, price, description, amount, product_category)VALUES ('Tempura Shrimp', 9.50, 'Gamberi croccanti in tempura.', 85, 9);
INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Yakitori Skewers', 8.50, 'Spiedini di pollo grigliato.', 95, 10);
INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Gyoza Dumplings', 7.99, 'Ravioli di maiale con salsa di soia.', 100, 11);
INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Onigiri Tuna', 5.99, 'Polpette di riso ripiene di tonno.', 120, 12);
INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Okonomiyaki', 9.99, 'Frittella giapponese con condimenti vari.', 50, 13);
INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Takoyaki', 8.99, 'Palline di pastella ripiene di polpo.', 60, 14);
INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Mochi Ice Cream', 6.50, 'Dolci di riso ripieni di gelato.', 80, 15);
INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Pork Katsu', 11.99, 'Cotoletta di maiale impanata e fritta.', 70, 16);
INSERT INTO product (product_name, price, description, amount, product_category) VALUES ('Bento Box', 13.50, 'Scatola pasto con un assortimento di piatti.', 90, 17);

INSERT INTO user_role (role_tag0) VALUES ('CUSTOMER');
INSERT INTO sushistar_user (username, email, password, firstname, lastname, address, phone_number, birthdate, user_role) VALUES ('testuser','testuser@example.com','hashed_password',    'Mario',   'Rossi', 'Via Roma 123, Milano',  '3331234567', '1990-01-15',1);