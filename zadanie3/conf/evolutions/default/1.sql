# --- !Ups

CREATE TABLE "user" (
    "id"        INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "login"     VARCHAR NOT NULL,
    "email"     VARCHAR NOT NULL,
    "password"  VARCHAR NOT NULL
);

CREATE TABLE "categories" (
    "id"        INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"      VARCHAR NOT NULL,
    "description" VARCHAR NOT NULL,
);

CREATE TABLE "subcategories" (
    "id"        INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"      VARCHAR NOT NULL,
    "description" VARCHAR NOT NULL,
);

CREATE TABLE "products" (
    "id"        INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"      VARCHAR NOT NULL,
);

CREATE TABLE "order" (
    "id"        INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "user"      INTEGER NOT NULL,
    FOREIGN KEY (user) REFERENCES user (id)
);

CREATE TABLE "shoppingcart" (
    "id"        INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "user"      INTEGER NOT NULL,
    "order"     INTEGER NOT NULL,
    FOREIGN KEY (user) REFERENCES user (id),
    FOREIGN KEY (order) REFERENCES order (id)
);

CREATE TABLE "voucher" (
    "id"        INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "user"      INTEGER NOT NULL,
    FOREIGN KEY (user) REFERENCES user (id)
);

CREATE TABLE "clientcard" (
    "id"        INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "user"      INTEGER NOT NULL,
    FOREIGN KEY (user) REFERENCES user (id)
);

CREATE TABLE "transaction" (
    "id"        INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "user"      INTEGER NOT NULL,
    "order"     INTEGER NOT NULL,
    "status"    INTEGER NOT NULL
    FOREIGN KEY (user) REFERENCES user (id),
    FOREIGN KEY (order) REFERENCES order (id)
);

CREATE TABLE "promotion" (
    "id"            INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "product_id"    INTEGER NOT NULL,
    "discount"      INTEGER NOT NULL,
);

# --- !Downs

DROP TABLE "user";
DROP TABLE "categories"
DROP TABLE "subcategories"
DROP TABLE "products"
DROP TABLE "order"
DROP TABLE "shoppingcart"
DROP TABLE "voucher"
DROP TABLE "clientcard"
DROP TABLE "transaction"
DROP TABLE "promotion"