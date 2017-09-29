DROP TABLE jCategory IF EXISTS;

CREATE TABLE jCategory (
    id                INTEGER IDENTITY (1, 1) PRIMARY KEY NOT NULL,
    name              VARCHAR(255),
    primaryCategoryId INTEGER,
    CONSTRAINT CatToCat FOREIGN KEY (primaryCategoryId) REFERENCES jCategory (id)
);

DROP TABLE jProducts IF EXISTS;

CREATE TABLE jProducts (
    id         INTEGER IDENTITY (1, 1) PRIMARY KEY NOT NULL,
    name       VARCHAR(255),
    categoryID INTEGER,
    CONSTRAINT ProdToCat FOREIGN KEY (categoryID) REFERENCES jCategory (id)
)
