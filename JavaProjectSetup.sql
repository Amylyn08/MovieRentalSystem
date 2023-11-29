--Amy Nguyen,Bianca Rossetti
DROP TABLE Orders_Products ;
DROP TABLE Stores_Products;
DROP TABLE Warehouses_Products;
DROP TABLE Warehouses_Stores;
DROP TABLE Reviews;
DROP TABLE Orders;
DROP TABLE Customers;
DROP TABLE Warehouses;
DROP TABLE Stores;
DROP TABLE DVDs;
DROP TABLE DigitalMovies;
DROP TABLE MOVIES;
DROP TABLE Moviestore_Customers;
DROP TABLE Products;

CREATE TABLE Stores(
    storeID NUMBER(2) GENERATED ALWAYS AS IDENTITY CONSTRAINT store_pk PRIMARY KEY,
    name VARCHAR2(20) NOT NULL
);

CREATE TABLE Warehouses(
    warehouseID      NUMBER(2)   GENERATED ALWAYS AS IDENTITY CONSTRAINT warehouse_pk PRIMARY KEY,
    name             VARCHAR2(20) NOT NULL,
    streetAddress    VARCHAR2(40),
    city             VARCHAR2(40),
    province         VARCHAR2(40),
    country          VARCHAR2(40)
);

CREATE TABLE Warehouses_Stores(
    warehouseID NUMBER(2)   REFERENCES Warehouses(warehouseID) ON DELETE CASCADE NOT NULL ,
    storeID     NUMBER(2)   REFERENCES Stores(storeID) ON DELETE CASCADE NOT NULL
);

CREATE TABLE Products(
    productID   NUMBER(2)   GENERATED ALWAYS AS IDENTITY CONSTRAINT products_pk PRIMARY KEY,
    name        VARCHAR2(20) NOT NULL,
    category    VARCHAR2(20) NOT NULL
);

CREATE TABLE Warehouses_Products(
    warehouseID     NUMBER(2)   REFERENCES Warehouses(warehouseID) ON DELETE CASCADE NOT NULL,
    productID       NUMBER(2)   REFERENCES Products(productID) ON DELETE CASCADE NOT NULL,
    quantity        NUMBER(6)   NOT NULL
);

CREATE TABLE Stores_Products(
    storeID         NUMBER(2)   REFERENCES Stores(storeID) ON DELETE CASCADE NOT NULL,
    productID       NUMBER(2)   REFERENCES Products(productID) ON DELETE CASCADE NOT NULL,
    price           NUMBER(8,2) NOT NULL
);

CREATE TABLE Customers(
    customerID       NUMBER(2)   GENERATED ALWAYS AS IDENTITY CONSTRAINT customers_pk PRIMARY KEY,
    firstName        VARCHAR2(40) NOT NULL,
    lastName         VARCHAR2(20) NOT NULL,
    email            VARCHAR2(30) NOT NULL,
    streetAddress    VARCHAR2(40),
    city             VARCHAR2(40),
    province         VARCHAR2(40),
    country          VARCHAR2(40)
);

CREATE TABLE Orders(
    orderID         NUMBER(2)   GENERATED ALWAYS AS IDENTITY CONSTRAINT orders_pk PRIMARY KEY,
    customerID      NUMBER(2)   REFERENCES Customers(customerID) ON DELETE CASCADE NOT NULL,
    storeID         NUMBER(2)   REFERENCES Stores(storeID) ON DELETE CASCADE NOT NULL,
    orderDate       DATE
);

CREATE TABLE Orders_Products(
    orderID         NUMBER(2)   REFERENCES Orders(orderID) ON DELETE CASCADE NOT NULL,
    productID       NUMBER(2)   REFERENCES Products(productID) ON DELETE CASCADE NOT NULL,
    quantity        NUMBER(6)   NOT NULL
);

CREATE TABLE Reviews(
    reviewID        NUMBER(2)   GENERATED ALWAYS AS IDENTITY CONSTRAINT reviews_pk PRIMARY KEY,
    productID       NUMBER(2)   REFERENCES Products(productID) ON DELETE CASCADE NOT NULL,
	customerID  	NUMBER(2) 	REFERENCES Customers(customerID) ON DELETE CASCADE NOT NULL,
    star            NUMBER(2, 1),
    flagNums        NUMBER(1),
    description     VARCHAR2(100)
);

CREATE TABLE MovieStore_Customers(
    customerName    VARCHAR(100),
    points          NUMBER(10)
);

CREATE TABLE Movies (
    movieID NUMBER(2) GENERATED ALWAYS AS IDENTITY CONSTRAINT movies_pk PRIMARY KEY,
    title VARCHAR2(50) NOT NULL,
    genre VARCHAR2(20) NOT NULL,
    duration NUMBER(3) NOT NULL,
    summary VARCHAR2(500),
    additionOfRatings NUMBER(4) NOT NULL,
    numRatings NUMBER(3) NOT NULL,
    price NUMBER(6,2) NOT NULL,
    movieURL VARCHAR2(50) NOT NULL,
    productID NUMBER(2) REFERENCES Products(productID) ON DELETE CASCADE NOT NULL
);

CREATE TABLE DVDs(
    movieID NUMBER(2) REFERENCES Movies(movieID) ON DELETE CASCADE NOT NULL,
    stock   NUMBER(3) NOT NULL
);

CREATE TABLE DigitalMovies(
    movieID NUMBER(2) REFERENCES Movies(movieID) ON DELETE CASCADE NOT NULL,
    fileSize NUMBER(4) NOT NULL,
    stock   NUMBER(3) NOT NULL
);

/


/** inserts **/
INSERT INTO Stores (name) 
VALUES ('marche adonis');
INSERT INTO Stores (name) 
VALUES ('marche atwater');
INSERT INTO Stores (name) 
VALUES ('dawson store');
INSERT INTO Stores (name) 
VALUES ('store magic');
INSERT INTO Stores (name) 
VALUES ('movie store');
INSERT INTO Stores (name) 
VALUES ('super rue champlain');
INSERT INTO Stores (name) 
VALUES ('toy r us');
INSERT INTO Stores (name) 
VALUES ('Dealer one');
INSERT INTO Stores (name) 
VALUES ('dealer montreal');
INSERT INTO Stores (name) 
VALUES ('movie start');
INSERT INTO Stores (name) 
VALUES ('star store');

INSERT INTO Warehouses (name, streetAddress, city, province, country) 
VALUES ('Warehouse A', '100 rue William', 'Saint Laurent', 'Quebec', 'Canada');
INSERT INTO Warehouses (name, streetAddress, city, province, country) 
VALUES ('Warehouse B', '304 Rue Francois-Perrault', 'Villeray Saint-Michel', 'Quebec', 'Canada');
INSERT INTO Warehouses (name, streetAddress, city, province, country) 
VALUES ('Warehouse C', '86700 Weston Rd', 'Toronto', 'Ontario', 'Canada');
INSERT INTO Warehouses (name, streetAddress, city, province, country) 
VALUES ('Warehouse D', '170 Sideroad', 'Quebec City', 'Quebec', 'Canada');
INSERT INTO Warehouses (name, streetAddress, city, province, country) 
VALUES ('Warehouse E', '1231 Trudea road', 'Ottawa', 'Ontario', 'Canada');
INSERT INTO Warehouses (name, streetAddress, city, province, country) 
VALUES ('Warehouse F', '16 Whitlock Rd', NULL, 'Alberta', 'Canada');

/*warehouses_stores???*/

INSERT INTO Products (name, category) VALUES ('laptop ASUS 104S', 'Electronics');
INSERT INTO Products (name, category) VALUES ('apple', 'Grocery');
INSERT INTO Products (name, category) VALUES ('SIMS CD', 'Video Games');
INSERT INTO Products (name, category) VALUES ('orange', 'Grocery');
INSERT INTO Products (name, category) VALUES ('Barbie Movie', 'DVD');
INSERT INTO Products (name, category) VALUES ('L''Oreal Normal Hair', 'Health');
INSERT INTO Products (name, category) VALUES ('BMW iX Lego', 'Toys');
INSERT INTO Products (name, category) VALUES ('BMW i6', 'Cars');
INSERT INTO Products (name, category) VALUES ('Truck 500c', 'Vehicle');
INSERT INTO Products (name, category) VALUES ('paper towel', 'Beauty');
INSERT INTO Products (name, category) VALUES ('plum', 'Grocery');
INSERT INTO Products (name, category) VALUES ('Lamborghini Lego', 'Toys');
INSERT INTO Products (name, category) VALUES ('chicken', 'Grocery');
INSERT INTO Products (name, category) VALUES ('PS5', 'Electronics');
INSERT INTO Products (name, category) VALUES ('pasta', 'Grocery');
INSERT INTO Products (name, category) VALUES ('tomato', 'Grocery');
INSERT INTO Products (name, category) VALUES ('Train X745', 'Vehicle');

INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (1, 1, 1000);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (2, 2, 24980);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (3, 3, 103);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (4, 4, 35405);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (5, 5, 40);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (6, 6, 450);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (1, 7, 10);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (1, 8, 6);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (5, 9, 1000);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (6, 10, 3532);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (3, 11, 43242);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (2, 10, 39484);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (4, 11, 6579);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (5, 12, 98765);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (6, 13, 43523);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (1, 15, 2132);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (4, 14, 123);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (1, 16, 352222);
INSERT INTO Warehouses_Products (warehouseID, productID, quantity) VALUES (5, 17, 4543);

INSERT INTO Stores_Products (storeID, productID, price) VALUES (1, 1, 970);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (1, 13, 9.5);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (2, 2, 10);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (3, 3, 50);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (4, 4, 2);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (5, 5, 30);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (6, 6, 10);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (7, 7, 40);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (8, 8, 50000);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (9, 9, 856600);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (10, 10, 50);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (2, 11, 10);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (6, 6, 30);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (7, 12, 80);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (5, 3, 16);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (7, 5, 45);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (1, 13, 9.5);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (2, 15, 13.5);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (11, 14, 200);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (7, 7, 38);
INSERT INTO Stores_Products (storeID, productID, price) VALUES (4, 15, 15);

INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('mahsa', 'sadeghi', 'msadeghi@dawsoncollege.qc.ca', 'Dawson College', 'Montreal', 'Quebec', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('alex', 'brown', 'alex@gmail.com', '090 boul saint laurent', 'Montreal', 'Quebec', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('martin', 'alexandre', 'marting@yahoo.com', NULL, 'Brossard', 'Quebec', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('daneil', 'hanne', 'daneil@yahoo.com', '100 Atwater Street', 'Toronto', 'Ontario', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('martin', 'alexandre', 'marting@yahoo.com', NULL, 'Brossard', 'Quebec', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('John', 'boura', 'bdoura@gmail.com', '100 Young Street', 'Toronto', 'Ontario', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('Ari', 'brown', 'b.a@gmail.com', NULL, NULL, NULL, NULL);
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('Amanda', 'Harry', 'am.harry@yahoo.com', '100 boul saint laurent', 'Montreal', 'Quebec', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('Jack', 'Johnson', 'johnson.a@gmail.com', NULL, 'Calgary', 'Alberta', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('John', 'belle', 'abcd@yahoo.com', '105 Young Street', 'Toronto', 'Ontario', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('martin', 'Li', 'm.li@gmail.com', '87 boul saint laurent', 'Montreal', 'Quebec', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('olivia', 'smith', 'smith@hotmail.com', '76 boul decalthon', 'Laval', 'Quebec', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('Noah', 'Garcia', 'g.noah@yahoo.com', '22222 Happy Street', 'Laval', 'Quebec', 'Canada');
INSERT INTO Customers (firstName, lastName, email, streetAddress, city, province, country) 
VALUES ('mahsa', 'sadeghi', 'ms@gmail.com', '104 Gill Street', 'Toronto', 'Ontario', 'Canada');

INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (1, 1, TO_DATE('4/21/2023', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (2, 2, TO_DATE('10/23/2023', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (3, 3, TO_DATE('10/1/2023', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (4, 4, TO_DATE('10/23/2023', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (2, 5, TO_DATE('10/23/2023', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (3, 6, TO_DATE('10/10/2023', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (1, 7, TO_DATE('10/11/2023', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (6, 8, NULL);
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (7, 9, NULL);
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (8, 10, NULL);
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (9, 2, TO_DATE('5/6/2020', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (3, 6, TO_DATE('9/12/2019', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (1, 7, TO_DATE('10/11/2010', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (1, 2, TO_DATE('5/6/2022', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (14, 7, TO_DATE('10/7/2023', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (10, 8, TO_DATE('8/10/2023', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (2, 5, TO_DATE('10/23/2023', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (2, 7, TO_DATE('10/2/2023', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (11, 1, TO_DATE('4/3/2019', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (12, 2, TO_DATE('12/29/2021', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (13, 11, TO_DATE('1/20/2020', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (1, 7, TO_DATE('10/11/2022', 'MM/DD/YYYY'));
INSERT INTO Orders (customerID, storeID, orderDate) 
VALUES (12, 4, TO_DATE('12/29/2021', 'MM/DD/YYYY'));

INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (1, 1, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (2, 2, 2);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (3, 3, 3);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (4, 4, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (5, 5, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (6, 6, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (7, 7, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (8, 8, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (9, 9, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (10, 10, 3);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (11, 11, 6);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (12, 6, 3);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (13, 12, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (14, 11, 7);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (15, 12, 2);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (16, 8, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (17, 3, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (18, 5, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (19, 13, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (20, 15, 3);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (21, 14, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (22, 7, 1);
INSERT INTO Orders_Products (orderID, productID, quantity) VALUES (23, 15, 3);

INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (4, 0, 'It was affordable.', 1, 1);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (3, 0, 'Quality was not good', 2, 2);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (2, 1, NULL, 3, 3);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (5, 0, 'Highly recommend', 4, 4);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (1, 0, NULL, 5, 2);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (1, 0, 'Did not worth the price',6, 3);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (1, 0, 'Missing some parts',7, 1);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (5, 1, 'Trash', 8, 6);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (2, 0, NULL,9, 7);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (5, 0, NULL,10, 8);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (4, 0, NULL,11, 9);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (3, 0, NULL, 6, 3);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (1, 0, 'Missing some parts', 12, 1);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (4, 0, NULL,11, 1);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (1, 0, 'Great product', 12, 14);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (5, 1, 'Bad quality', 8, 10);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (1, 0, NULL, 3, 2);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (4, 0, NULL, 5, 2);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (4, 0, NULL, 13, 11);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (5, 0, NULL, 15, 12);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (1, 2, 'Worse car I have driven!', 7, 13);
INSERT INTO Reviews (star, flagNums, description, productID, customerID)
VALUES (4, 0, NULL, 15, 12);

INSERT INTO MovieStore_Customers (customerName, points) VALUES ('John Doe', 1500);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Jane Smith', 1200);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('David Johnson', 900);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Emily Davis', 1100);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Michael Wilson', 1350);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Susan Brown', 1000);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Robert Miller', 800);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Olivia Jones', 950);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('William Taylor', 1200);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Emma Anderson', 1050);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Christopher Martinez', 1300);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Ava Jackson', 850);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Brian White', 1100);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Sophia Harris', 1250);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Matthew Clark', 900);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Alice Thomas', 1400);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Andrew Robinson', 1150);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Grace Lee', 950);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Daniel Walker', 1050);
INSERT INTO MovieStore_Customers (customerName, points) VALUES ('Ella Hall', 1250);

INSERT INTO Products (name, category) VALUES ('The Lost City', 'Movies');
INSERT INTO Products (name, category) VALUES ('Infinite Horizons', 'Movies');
INSERT INTO Products (name, category) VALUES ('Summer Breeze', 'Movies');
INSERT INTO Products (name, category) VALUES ('Eclipse of Shadows', 'Movies');
INSERT INTO Products (name, category) VALUES ('Magic Moments', 'Movies');
INSERT INTO Products (name, category) VALUES ('Silent Whispers', 'Movies');
INSERT INTO Products (name, category) VALUES ('The Cosmic Heist', 'Movies');
INSERT INTO Products (name, category) VALUES ('The Haunting Melody', 'Movies');
INSERT INTO Products (name, category) VALUES ('Flight of Fantasy', 'Movies');
INSERT INTO Products (name, category) VALUES ('High Stakes', 'Movies');
INSERT INTO Products (name, category) VALUES ('The Comedy Conundrum', 'Movies');
INSERT INTO Products (name, category) VALUES ('Parallel Realities', 'Movies');
INSERT INTO Products (name, category) VALUES ('Interstellar', 'Movies');
INSERT INTO Products (name, category) VALUES ('The Old Guard', 'Movies');
INSERT INTO Products (name, category) VALUES ('White Chicks', 'Movies');
INSERT INTO Products (name, category) VALUES ('The Grudge', 'Movies');
INSERT INTO Products (name, category) VALUES ('21 Jump Street', 'Movies');
INSERT INTO Products (name, category) VALUES ('The Hangover', 'Movies');
INSERT INTO Products (name, category) VALUES ('Game Night', 'Movies');
INSERT INTO Products (name, category) VALUES ('Elf', 'Movies');

-- INSERT statements for Movies table
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('The Lost City', 'Action-Adventure', 145, 'An archeological expedition races against a rival group to uncover a lost city''s secrets.', 240, 55, 40.50, 'https://www.youtube.com/watch?v=nfKO9rYDmE8', 18);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('Infinite Horizons', 'Sci-Fi', 160, 'Exploring the limitless possibilities of parallel universes and the consequences of choice.', 215, 50, 12.99, 'https://www.youtube.com/watch?v=z3ZXysHLS70', 19);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('Summer Breeze', 'Romance', 110, 'A romantic tale set against the backdrop of a sun-kissed beach and gentle ocean breezes.', 190, 42, 17.49, 'https://www.youtube.com/watch?v=MsW8rXPcnM0', 20);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('Eclipse of Shadows', 'Action', 125, 'A lone hero battles against a dark force threatening to engulf the world in eternal darkness.', 220, 48, 20.99, 'https://www.youtube.com/watch?v=jqyTb6UaeNU', 21);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('Magic Moments', 'Fantasy', 105, 'A heartwarming story of a young magician discovering the true magic of friendship.', 165, 35, 49.99, 'https://www.youtube.com/watch?v=v82p7AgHJFw', 22);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('Silent Whispers', 'Drama', 150, 'In a world where words have power, a mute protagonist unravels a conspiracy through silence.', 205, 45, 21.99, 'https://www.youtube.com/watch?v=PMAkTA6v1Fs', 23);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('The Cosmic Heist', 'Sci-Fi', 140, 'A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.', 230, 52, 32.99, 'https://www.youtube.com/watch?v=8yilea7G6IQ', 24);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('The Haunting Melody', 'Horror', 95, 'A chilling tale of a haunted melody that brings terror and despair to those who hear it.', 160, 30, 31.99, 'https://www.youtube.com/watch?v=CiJf82QyMW4', 25);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('Flight of Fantasy', 'Adventure', 155, 'Embark on a fantastical journey with mythical creatures and epic adventures.', 235, 53, 12.99, 'https://www.youtube.com/watch?v=L8uXekj_i5I', 26);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('High Stakes', 'Action', 120, 'A high-stakes thriller where the protagonist must outsmart cunning adversaries in a game of wits.', 195, 40, 36.99, 'https://www.youtube.com/watch?v=a_Da5ZoFMII', 27);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('The Comedy Conundrum', 'Comedy', 95, 'A comedy of errors ensues when a series of misunderstandings lead to hilarious consequences.', 180, 38, 41.99, 'https://www.youtube.com/watch?v=QJit_DU2XhM', 28);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('Parallel Realities', 'Sci-Fi', 165, 'A mind-bending exploration of parallel realities and the impact of choices on the fabric of existence.', 240, 55, 10.50, 'https://www.youtube.com/watch?v=LTGpg61aSo8', 29);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('Interstellar', 'Sci-Fi', 169, 'In a future where Earth is dying, a team of astronauts embarks on a journey through a wormhole in search of a new habitable planet.', 255, 58, 40.00, 'https://www.youtube.com/watch?v=zSWdZVtXT7E', 30);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('The Old Guard', 'Action', 125, 'A group of immortal mercenaries must protect their secret and fight against a new threat.', 200, 48, 60.00, 'https://www.youtube.com/watch?v=aK-X2d0lJ_s', 31);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('White Chicks', 'Comedy', 109, 'Two FBI agents go undercover as wealthy heiresses in this hilarious comedy of mistaken identity.', 170, 35, 20.00, 'https://www.youtube.com/watch?v=aeVkbNka9HM', 32);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('The Grudge', 'Horror', 93, 'A vengeful spirit haunts a cursed house, terrorizing anyone who enters its dark confines.', 185, 40, 5.99, 'https://www.youtube.com/watch?v=O2NKzO-fxwQ', 33);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('21 Jump Street', 'Action-Comedy', 112, 'Two undercover cops infiltrate a high school to bust a drug ring, leading to unexpected comedic chaos.', 210, 50, 109.99, 'https://www.youtube.com/watch?v=RLoKtb4c4W0', 34);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('The Hangover', 'Comedy', 100, 'A wild bachelor party in Las Vegas leads to a series of outrageous and comedic misadventures.', 198, 45, 9.99, 'https://www.youtube.com/watch?v=tcdUhdOlz9M', 35);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('Game Night', 'Comedy-Mystery', 100, 'A group of friends'' game night turns into a real-life mystery when one of them is kidnapped.', 220, 52, 8.00, 'https://www.youtube.com/watch?v=qmxMAdV6s4U', 36);
INSERT INTO Movies (title, genre, duration, summary, additionOfRatings, numRatings, price, movieURL, productID)
VALUES ('Elf', 'Comedy', 97, 'A human raised by elves at the North Pole embarks on a journey to New York City to find his real father.', 240, 55, 6.99, 'https://www.youtube.com/watch?v=a54yC1etmVc', 37);

INSERT INTO DVDs (movieID, stock) VALUES (1, 3);
INSERT INTO DVDs (movieID, stock) VALUES (2, 5);
INSERT INTO DVDs (movieID, stock) VALUES (3, 2);
INSERT INTO DVDs (movieID, stock) VALUES (4, 2);
INSERT INTO DVDs (movieID, stock) VALUES (5, 8);
INSERT INTO DVDs (movieID, stock) VALUES (6, 8);
INSERT INTO DVDs (movieID, stock) VALUES (7, 5);
INSERT INTO DVDs (movieID, stock) VALUES (8, 5);
INSERT INTO DVDs (movieID, stock) VALUES (9, 3);
INSERT INTO DVDs (movieID, stock) VALUES (10, 8);
INSERT INTO DVDs (movieID, stock) VALUES (11, 5);
INSERT INTO DVDs (movieID, stock) VALUES (12, 2);
INSERT INTO DVDs (movieID, stock) VALUES (13, 8);
INSERT INTO DVDs (movieID, stock) VALUES (14, 2);
INSERT INTO DVDs (movieID, stock) VALUES (15, 2);
INSERT INTO DVDs (movieID, stock) VALUES (16, 5);
INSERT INTO DVDs (movieID, stock) VALUES (17, 7);
INSERT INTO DVDs (movieID, stock) VALUES (18, 3);
INSERT INTO DVDs (movieID, stock) VALUES (19, 2);
INSERT INTO DVDs (movieID, stock) VALUES (20, 5);

INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (1, 1800, 2);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (2, 2000, 8);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (3, 1500, 5);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (4, 1700, 7);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (5, 1600, 5);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (6, 1900, 9);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (7, 1800, 2);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (8, 1400, 2);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (9, 2100, 5);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (10, 2000, 2);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (11, 1850, 8);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (12, 1950, 2);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (13, 1600, 5);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (14, 1750, 2);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (15, 1980, 5);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (16, 1450, 7);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (17, 1700, 2);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (18, 1900, 8);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (19, 2050, 3);
INSERT INTO DigitalMovies (movieID, fileSize, stock) VALUES (20, 1800, 2);

CREATE OR REPLACE PACKAGE loading
AS
    TYPE cursor_table IS REF CURSOR;
    PROCEDURE getCustomers(cursor_c IN OUT cursor_table);
    PROCEDURE getDigitalMovies(cursor_c IN OUT cursor_table);
    PROCEDURE getPhysicalMovies(cursor_c IN OUT cursor_table);
END loading;
/
CREATE OR REPLACE PACKAGE BODY loading
AS
    PROCEDURE getCustomers (cursor_c IN OUT cursor_table)
    AS 
    BEGIN
        OPEN cursor_c FOR
            SELECT * FROM Movies INNER JOIN DVDs USING(movieID);
    END;
    
    PROCEDURE getDigitalMovies (cursor_c IN OUT cursor_table)
    AS 
    BEGIN
        OPEN cursor_c FOR
            SELECT * FROM Movies INNER JOIN DigitalMovies USING(movieID);
    END;
    
    PROCEDURE getPhysicalMovies (cursor_c IN OUT cursor_table)
    AS 
    BEGIN
        OPEN cursor_c FOR
            SELECT * FROM Orders;
    END;
END loading;
/
DROP TABLE Stores_Products;

























