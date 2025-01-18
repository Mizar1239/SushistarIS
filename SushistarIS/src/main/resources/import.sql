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

INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('Nigiri', 15.99, 'Buonissimi nigiri', 50, 2, 'nigiri-600x600.png');

INSERT INTO product (product_name, price, description, amount, product_category, img_path)VALUES ('Maki avocado', 13.99, 'Gustoso maki avocado', 60, 3, 'maki_avocado.png.png');

INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('Nigiri tonno', 10.99, 'Assortimento di maki tradizionali.', 80, 4, 'nigiri_tonno.png.jpg');
INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('Nuggets salmone', 8.99, 'Gustose nuggets di salmone', 40, 5, 'nuggest di salmone.png');
INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('hosomaki', 14.99, 'hosomaki deliziosi', 70, 6, 'hosomaki-600x600.png');
INSERT INTO product (product_name, price, description, amount, product_category, img_path)VALUES ('Sushi pollo', 12.50, 'Ottimo sushi delizioso di pollo gnam gnam', 90, 7, 'SushiLab-Menu-Luglio-2023-©-Marco-Baldassarre-23-300x300.jpg');
INSERT INTO product (product_name, price, description, amount, product_category, img_path)VALUES ('Tonkatsu', 11.50, 'Ottimo tonkatsu', 75, 8, 'SushiLab-Menu-Luglio-2023-©-Marco-Baldassarre-31-300x300.jpg');
INSERT INTO product (product_name, price, description, amount, product_category, img_path)VALUES ('Tempura Shrimp', 9.50, 'Gamberi croccanti in tempura.', 85, 9, 'gambero-tempura-1-600x600.png');
INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('Onigiri fritto', 8.50, 'Onigiri fritto succulento', 95, 10, 'SushiLab-Menu-Luglio-2023-©-Marco-Baldassarre-29-300x300.jpg');
INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('Gyoza Dumplings', 7.99, 'Ravioli di maiale con salsa di soia.', 100, 11, 'SushiLab-Menu-Luglio-2023-©-Marco-Baldassarre-37-300x300 (1).jpg');
INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('Sashimi salmone', 5.99, 'Ottimo sashimi salmone', 120, 12, 'sashimi_salmone.png');
INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('Takoiaky', 9.99, 'Frittella giapponese con condimenti vari.', 50, 13, 'takoyaki.png');
INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('Sushi misto', 8.99, 'Incredibile sushi misto', 60, 14, 'sushi_misto.png');
INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('kataifi', 6.50, 'Gustoso kataifi', 80, 15, 'kataifi-300x300.png');
INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('Tartare', 11.99, 'Tartare incredibile', 70, 16, 'tartare-600x600.jpg');
INSERT INTO product (product_name, price, description, amount, product_category, img_path) VALUES ('Involtini primavera', 13.50, 'Deliziosi involtini primavera', 90, 17, 'involtini_primavera.png');

INSERT INTO user_role (role_tag0) VALUES ('CUSTOMER');
INSERT INTO user_role (role_tag0) VALUES ('ADMIN');
INSERT INTO sushistar_user (username, email, password, firstname, lastname, address, phone_number, birthdate, user_role) VALUES ('testuser','testuser@example.com','hashed_password',    'Mario',   'Rossi', 'Via Roma 123, Milano',  '3331234567', '1990-01-15',1);
INSERT INTO sushistar_user (username, email, password, firstname, lastname, address, phone_number, birthdate, user_role) VALUES ('testAdmin','testAdmin@example.com','hashed_password',    'Furio',   'Terzapi', 'Via Verdi 124, Torino',  '3335558888', '1980-06-22',2);
