DROP TABLE jCategory IF EXISTS;

CREATE TABLE jCategory (
    id                BIGINT PRIMARY KEY NOT NULL,
    name              VARCHAR(255),
    primaryCategoryId BIGINT,
    CONSTRAINT CatToCat FOREIGN KEY (primaryCategoryId) REFERENCES jCategory (id)
);

DROP TABLE jProducts IF EXISTS;

CREATE TABLE jProducts (
    id         BIGINT PRIMARY KEY NOT NULL,
    name       VARCHAR(255),
    categoryID BIGINT,
    CONSTRAINT ProdToCat FOREIGN KEY (categoryID) REFERENCES jCategory (id)
)
