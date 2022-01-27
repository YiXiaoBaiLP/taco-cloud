CREATE TABLE if not exists Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

CREATE TABLE IF NOT EXISTS Taco(
    id identity,
    name varchar(50) not null,
    createAt timestamp not null
);

CREATE TABLE IF NOT EXISTS Taco_Ingredients (
    taco bigint not null,
    ingredient varchar(4) not null
);

ALTER TABLE Taco_Ingredients
    ADD FOREIGN KEY(taco) REFERENCES Taco(id);

ALTER TABLE Taco_Ingredients
    ADD FOREIGN KEY(ingredient) REFERENCES Ingredient(id);

CREATE TABLE IF NOT EXISTS Taco_Order (
    id identity,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(50) not null,
    deliveryZip varchar(50) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placeAt timestamp not null
);

CREATE TABLE IF NOT EXISTS Taco_Order_Tacos (
    tacoOrder bigint not null,
    taco bigint not null
);

ALTER TABLE Taco_Order_Tacos
   add FOREIGN KEY (tacoOrder) REFERENCES Taco_Order(id);

ALTER TABLE Taco_Order_Tacos
    ADD FOREIGN KEY (taco) REFERENCES Taco(id);
