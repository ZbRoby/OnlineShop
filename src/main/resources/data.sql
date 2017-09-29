INSERT INTO JCategory (id,name,primaryCategoryId) VALUES
    (1,'Laptops, Tablets & phones',null),
    (2,'Laptops and accessorises',1),
    (3,'Laptops',2),
    (4,'Phone and accessorises',1),
    (5,'Phones',4);

INSERT INTO JProducts (id,name,categoryId) VALUES
    (1,'laptop1',3),
    (2,'laptop2',3),
    (3,'laptop3',3),
    (4,'accessory2',2),
    (5,'accessory4',2),
    (6,'accessory6',2),
    (7,'phone1',5),
    (8,'phone2',5),
    (9,'phone3',5),
    (10,'accessory1',4),
    (11,'accessory3',4),
    (12,'accessory5',4);
