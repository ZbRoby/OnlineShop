DELETE FROM PRODUCTS_LOCATIONS;
DELETE FROM ORDER_DETAILS;
DELETE FROM PRODUCTS;
DELETE FROM PRODUCT_CATEGORIES;
DELETE FROM PRODUCT_DETAILS;
DELETE FROM ORDERS;
DELETE FROM USERS_JOIN_ROLES;
DELETE FROM USER_ROLES;
DELETE FROM USERS;
DELETE FROM LOCATIONS;
DELETE FROM EMPLOYEES;
DELETE FROM CUSTOMERS;
DELETE FROM ADDRESSES;

-- ADDRESSES

insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (1, 'Vietnam', 'Bến Cầu', '85938 Independence Park', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (2, 'Russia', 'Chernigovka', '42 Bartelt Pass', '692385', null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (3, 'China', 'Jiantou', '0 Arrowood Hill', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (4, 'Indonesia', 'Cibebek', '0443 Vahlen Parkway', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (5, 'Indonesia', 'Rappang', '9671 Onsgard Lane', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (6, 'China', 'Chengzi', '20571 Maple Trail', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (7, 'China', 'Yuanmou', '64 Village Green Lane', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (8, 'Vietnam', 'Ngô Đồng', '8813 Crownhardt Lane', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (9, 'Argentina', 'Chorotis', '56256 Oak Center', '3733', null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (10, 'Indonesia', 'Sungairaya', '70 Fordem Crossing', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (11, 'Indonesia', 'Sukaraja', '529 Karstens Avenue', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (12, 'Philippines', 'Banag', '03759 Tennyson Crossing', '4234', null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (13, 'Macedonia', 'Pirava', '51819 Toban Center', '7316', null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (14, 'Sweden', 'Nacka', '54150 Karstens Street', '131 22', null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (15, 'Colombia', 'San Gil', '84140 Carioca Street', '684039', null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (16, 'Nigeria', 'Langtang', '4181 Hoard Pass', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (17, 'Ecuador', 'Machala', '6937 Center Circle', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (18, 'Indonesia', 'Rancabuaya', '3777 Hintze Plaza', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (19, 'China', 'Xintai', '010 Redwing Parkway', null, null);
insert into ADDRESSES (ID, COUNTRY, CITY, STREET, ZIP_CODE, OTHER) values (20, 'Ethiopia', 'Goba', '89 Cherokee Way', null, null);

--USER_ROLES

insert into USER_ROLES (ID, NAME) values (1,'ADMIN');
insert into USER_ROLES (ID, NAME) values (2,'CUSTOMER');

--USERS

insert into USERS (ID,PASSWORD, USERNAME) values ( 1, 'test', 'customer'             );
insert into USERS (ID,PASSWORD, USERNAME) values ( 2, 'soJN0FBw', 'lchieco1'     );
insert into USERS (ID,PASSWORD, USERNAME) values ( 3, 'bUFzeF', 'mbacken2'       );
insert into USERS (ID,PASSWORD, USERNAME) values ( 4, 'joYvIP', 'mrowter3'       );
insert into USERS (ID,PASSWORD, USERNAME) values ( 5, 'SxvyMThvX', 'vkeegan4'    );
insert into USERS (ID,PASSWORD, USERNAME) values ( 6, '8PJscrLQNc1', 'pholby5'   );
insert into USERS (ID,PASSWORD, USERNAME) values ( 7, 'Sr2OK7Q', 'cclendening6'  );
insert into USERS (ID,PASSWORD, USERNAME) values ( 8, 'keB7ce', 'ublayd7'        );
insert into USERS (ID,PASSWORD, USERNAME) values ( 9, 'pqs9KYgsJXV', 'ryansons8' );
insert into USERS (ID,PASSWORD, USERNAME) values (10, 'Vr0Cbe4ZJ5Pg', 'bfarrent9');
insert into USERS (ID,PASSWORD, USERNAME) values (11, 'upJYZg', 'etarberta'      );
insert into USERS (ID,PASSWORD, USERNAME) values (12, 'GyBeOH3g', 'shyderb'      );
insert into USERS (ID,PASSWORD, USERNAME) values (13, 'lxEfu8wDou', 'aannellc'   );
insert into USERS (ID,PASSWORD, USERNAME) values (14, 'jWMFrNz', 'ctuckerd'      );
insert into USERS (ID,PASSWORD, USERNAME) values (15, 'Fj4ucluf9Mb7', 'mmerwee'  );
insert into USERS (ID,PASSWORD, USERNAME) values (16, 'RcQx6fG', 'lparnabyf'     );
insert into USERS (ID,PASSWORD, USERNAME) values (17, 'ewi4BJ', 'wdibleyg'       );
insert into USERS (ID,PASSWORD, USERNAME) values (18, 'sO5e5ZP', 'ttetth'        );
insert into USERS (ID,PASSWORD, USERNAME) values (19, 'f2Z4hVEVogU', 'cglossupi' );
insert into USERS (ID,PASSWORD, USERNAME) values (20, 'Nwo1IrM', 'lbelinj'       );
insert into USERS (ID,PASSWORD, USERNAME) values (21, 'test', 'admin'            );
insert into USERS (ID,PASSWORD, USERNAME) values (22, 'Wa6R0W4vzbp', 'amucillo1' );
insert into USERS (ID,PASSWORD, USERNAME) values (23, '0hCuLd2', 'ckryszka2'     );
insert into USERS (ID,PASSWORD, USERNAME) values (24, 'MYlUHcTND', 'tdybald3'    );
insert into USERS (ID,PASSWORD, USERNAME) values (25, 'cpmjAZVAj', 'btrembey4'   );
insert into USERS (ID,PASSWORD, USERNAME) values (26, 'eXHdEzchf', 'kkoles5'     );
insert into USERS (ID,PASSWORD, USERNAME) values (27, 'J0R1ztjSJ', 'lgirtin6'    );
insert into USERS (ID,PASSWORD, USERNAME) values (28, 'QwssCXZyd', 'vblundan7'   );
insert into USERS (ID,PASSWORD, USERNAME) values (29, 'D9EHEqg', 'tmatushevitz8' );
insert into USERS (ID,PASSWORD, USERNAME) values (30, 'LzUhBb', 'blorimer9'      );
insert into USERS (ID,PASSWORD, USERNAME) values (31, 'B9pOIF9', 'hlowersa'      );
insert into USERS (ID,PASSWORD, USERNAME) values (32, 'WufVFtvgkBe', 'gbostickb' );
insert into USERS (ID,PASSWORD, USERNAME) values (33, '7GSjfdBLkS', 'driddingc'  );
insert into USERS (ID,PASSWORD, USERNAME) values (34, 'd52IHm2H', 'dwhitterd'    );
insert into USERS (ID,PASSWORD, USERNAME) values (35, 'Jy3XyY', 'kwelle'         );
insert into USERS (ID,PASSWORD, USERNAME) values (36, 'jDJqyxYDc', 'vpatronef'   );
insert into USERS (ID,PASSWORD, USERNAME) values (37, '6lxPyxKeP', 'cwadlowg'    );
insert into USERS (ID,PASSWORD, USERNAME) values (38, 'TCDJewB', 'dvallensh'     );
insert into USERS (ID,PASSWORD, USERNAME) values (39, 'xfAG4TyiJ', 'gfalconeri'  );
insert into USERS (ID,PASSWORD, USERNAME) values (40, 'Ze1iQB', 'spirreyj'       );

--USERS_JOIN_ROLES

insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values ( 1, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values ( 2, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values ( 3, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values ( 4, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values ( 5, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values ( 6, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values ( 7, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values ( 8, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values ( 9, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (10, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (11, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (12, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (13, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (14, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (15, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (16, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (17, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (18, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (19, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (20, 2);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (21, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (22, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (23, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (24, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (25, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (26, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (27, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (28, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (29, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (30, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (31, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (32, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (33, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (34, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (35, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (36, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (37, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (38, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (39, 1);
insert into USERS_JOIN_ROLES (USER_ID, ROLE_ID) values (40, 1);

-- CUSTOMERS

insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (1, 'Schuyler', 'Meaders'  , 1,1);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (2, 'Liuka', 'Chieco'      , 2,2);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (3, 'Massimo', 'Backen'    , 3,5);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (4, 'Mair', 'Rowter'       , 4,10);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (5, 'Vance', 'Keegan'      , 5,11);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (6, 'Petrina', 'Holby'     , 6,5);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (7, 'Cherin', 'Clendening' , 7,7);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (8, 'Ursula', 'Blayd'      , 8,13);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (9, 'Reynold', 'Yansons'   , 9,9);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (10, 'Benedicta', 'Farrent',10,10);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (11, 'Earle', 'Tarbert'    ,11,11);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (12, 'Stefan', 'Hyder'     ,12,10);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (13, 'Anatola', 'Annell'   ,13,1);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (14, 'Cathy', 'Tucker'     ,14,13);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (15, 'Mayne', 'Merwe'      ,15,12);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (16, 'Lonnie', 'Parnaby'   ,16,17);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (17, 'Wallache', 'Dibley'  ,17,9);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (18, 'Teodorico', 'Tett'   ,18,1);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (19, 'Cristal', 'Glossup'  ,19,2);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID) values (20, 'Lexine', 'Belin'     ,20,8);

-- EMPLOYEES

insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (1, 'Beverlie', 'Wallett' , 21, 10, '308-327-6450', 'SALESMAN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (2, 'Agace', 'Mucillo'    , 22, 17, '946-834-2845', 'SALESMAN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (3, 'Callida', 'Kryszka'  , 23, 16, '799-964-5613', 'INTERN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (4, 'Town', 'Dybald'      , 24, 12, '786-249-8810', 'MANAGER');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (5, 'Beilul', 'Trembey'   , 25, 10, '482-519-0617', 'SALESMAN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (6, 'Kayle', 'Koles'      , 26, 12, '500-962-8730', 'MANAGER');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (7, 'Lynde', 'Girtin'     , 27, 20, '632-527-0367', 'SALESMAN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (8, 'Veradis', 'Blundan'  , 28,  3, '492-732-8174', 'SALESMAN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (9, 'Tybi', 'Matushevitz' , 29,  7, '136-557-0556', 'INTERN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (10, 'Bess', 'Lorimer'    , 30,  8, '973-869-6721', 'MANAGER');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (11, 'Hyacintha', 'Lowers', 31, 10, '497-360-5771', 'SALESMAN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (12, 'Gran', 'Bostick'    , 32, 18, '168-761-1873', 'INTERN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (13, 'Dominga', 'Ridding' , 33, 11, '423-197-4594', 'MANAGER');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (14, 'Darn', 'Whitter'    , 34, 12, '966-951-8491', 'INTERN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (15, 'Karyl', 'Well'      , 35, 19, '504-875-4446', 'INTERN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (16, 'Viviana', 'Patrone' , 36,  8, '303-712-9818', 'INTERN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (17, 'Christos', 'Wadlow' , 37, 12, '430-122-1466', 'SALESMAN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (18, 'Dewitt', 'Vallens'  , 38,  2, '862-716-6066', 'SALESMAN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (19, 'Gerald', 'Falconer' , 39, 11, '183-886-6691', 'SALESMAN');
insert into EMPLOYEES (ID, FIRST_NAME, LAST_NAME,USER_ID,ADDRESS_ID, HOME_PHONE, Title) values (20, 'Scarlet', 'Pirrey'  , 40,  9, '625-133-9709', 'SALESMAN');

-- LOCATIONS

insert into LOCATIONS (ID, ADDRESS_ID) values (1, 16);
insert into LOCATIONS (ID, ADDRESS_ID) values (2, 3);
insert into LOCATIONS (ID, ADDRESS_ID) values (3, 12);
insert into LOCATIONS (ID, ADDRESS_ID) values (4, 17);
insert into LOCATIONS (ID, ADDRESS_ID) values (5, 6);
insert into LOCATIONS (ID, ADDRESS_ID) values (6, 8);
insert into LOCATIONS (ID, ADDRESS_ID) values (7, 13);
insert into LOCATIONS (ID, ADDRESS_ID) values (8, 2);
insert into LOCATIONS (ID, ADDRESS_ID) values (9, 11);
insert into LOCATIONS (ID, ADDRESS_ID) values (10,19);
insert into LOCATIONS (ID, ADDRESS_ID) values (11,18);
insert into LOCATIONS (ID, ADDRESS_ID) values (12,9);
insert into LOCATIONS (ID, ADDRESS_ID) values (13,5);
insert into LOCATIONS (ID, ADDRESS_ID) values (14,20);
insert into LOCATIONS (ID, ADDRESS_ID) values (15,1);
insert into LOCATIONS (ID, ADDRESS_ID) values (16,7);
insert into LOCATIONS (ID, ADDRESS_ID) values (17,15);
insert into LOCATIONS (ID, ADDRESS_ID) values (18,14);
insert into LOCATIONS (ID, ADDRESS_ID) values (19,10);
insert into LOCATIONS (ID, ADDRESS_ID) values (20,4);

-- ORDERS

insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (1, '2016-12-24', '2017-09-08', 20, 20, 16);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (2, '2017-05-25', '2016-10-23', 2, 6, 15);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (3, '2016-10-29', '2017-07-25', 16, 11, 7);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (4, '2017-08-20', '2017-02-14', 15, 6, 2);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (5, '2017-08-20', '2017-01-14', 4, 6, 7);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (6, '2017-08-03', '2017-09-10', 14, 19, 5);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (7, '2017-07-22', '2017-05-10', 14, 20, 10);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (8, '2016-12-03', '2017-06-15', 2, 7, 16);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (9, '2016-12-04', '2017-09-18', 18, 2, 19);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (10, '2017-02-22', '2017-04-26', 20, 5, 2);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (11, '2017-05-01', '2016-11-22', 15, 20, 13);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (12, '2016-12-06', '2016-10-22', 4, 13, 8);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (13, '2016-12-13', '2016-10-15', 6, 1, 2);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (14, '2017-01-26', '2017-08-12', 5, 9, 17);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (15, '2016-11-12', '2016-11-29', 5, 3, 15);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (16, '2016-10-09', '2016-11-11', 18, 2, 2);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (17, '2017-04-04', '2017-08-06', 17, 15, 6);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (18, '2017-08-07', '2017-07-25', 8, 13, 7);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (19, '2016-12-27', '2016-11-17', 2, 17, 9);
insert into ORDERS (ID, ORDER_DATE, SHIPPED_DATE, ADDRESS_ID, CUSTOMER_ID, EMPLOYEE_ID) values (20, '2017-08-19', '2017-01-19', 14, 20, 5);

-- PRODUCT_DETAILS

insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (1, 283.09, 'CM', 'LBS', 635.73, 229.77, 478.85);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (2, 49.3, 'INCH', 'G', 53.73, 329.63, 567.29);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (3, 157.42, 'CM', 'LBS', 570.19, 564.15, 602.25);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (4, 847.76, 'KM', 'G', 125.34, 990.14, 439.02);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (5, 182.91, 'KM', 'KG', 499.96, 233.97, 190.8);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (6, 390.36, 'M', 'G', 91.69, 51.51, 173.89);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (7, 162.52, 'KM', 'LBS', 976.96, 966.86, 337.55);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (8, 240.67, 'KM', 'KG', 105.33, 655.09, 635.64);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (9, 889.87, 'CM', 'LBS', 111.82, 377.84, 313.86);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (10, 253.99, 'INCH', 'KG', 948.72, 13.94, 290.62);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (11, 700.32, 'M', 'G', 830.99, 617.95, 323.46);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (12, 382.29, 'M', 'KG', 145.83, 575.55, 90.14);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (13, 527.14, 'KM', 'G', 414.0, 893.98, 242.64);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (14, 261.28, 'KM', 'G', 955.46, 314.62, 856.89);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (15, 744.33, 'CM', 'KG', 369.61, 294.54, 296.34);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (16, 543.49, 'CM', 'KG', 662.71, 973.27, 849.67);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (17, 838.94, 'CM', 'LBS', 523.64, 848.28, 213.71);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (18, 615.75, 'CM', 'LBS', 27.25, 886.06, 90.62);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (19, 139.24, 'INCH', 'LBS', 487.76, 682.77, 944.74);
insert into PRODUCT_DETAILS (ID, DEPTH, DISTANCE_UNIT, MASS_UNIT, HEIGHT, MASS, WIDTH) values (20, 751.51, 'INCH', 'KG', 812.82, 533.62, 551.59);

-- PRODUCT_CATEGORIES

insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (1, 'Rooxo', null);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (2, 'Browsebug', null);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (3, 'Flipopia', 1);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (4, 'Divavu', 2);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (5, 'Rhycero', 1);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (6, 'Aibox', 2);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (7, 'Buzzbean', 3);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (8, 'Edgeclub', 4);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (9, 'Eamia', 3);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (10, 'Skibox', 4);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (11, 'Meemm', null);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (12, 'Blogtags', null);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (13, 'Edgeclub99', 11);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (14, 'Katz', 12);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (15, 'Reallinks', 11);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (16, 'Mybuzz', 12);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (17, 'RooxooR', 13);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (18, 'Tanoodle', 14);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (19, 'Flip', 13);
insert into PRODUCT_CATEGORIES (ID, NAME, MAIN_CATEGORY_ID) values (20, 'Zooxo', 14);

-- PRODUCTS

insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (1, 'CNY', 408.88, 'sit amet cursus id turpis integer aliquet massa id lobortis convallis tortor risus dapibus augue vel', 'Span', 'Mayert Inc', 10, 16);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (2, 'CUP', 408.88, 'proin eu mi nulla ac enim in tempor turpis nec euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh', 'Bigtax', 'Monahan-Strosin', 20, 17);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (3, 'ARS', 283.24, 'sed interdum venenatis turpis enim blandit mi in porttitor pede justo', 'Trippledex', 'Auer-Lebsack', 9, 8);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (4, 'PHP', 777.74, 'ut erat curabitur gravida nisi at nibh in hac habitasse platea dictumst', 'Sonair', 'Quigley LLC', 7, 17);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (5, 'EUR', 389.24, 'duis at velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis diam', 'Asoka', 'Hickle Group', 13, 14);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (6, 'IDR', 241.89, 'nonummy maecenas tincidunt lacus at velit vivamus vel nulla eget eros elementum pellentesque quisque', 'Alphazap', 'Goodwin-Beahan', 19, 10);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (7, 'EUR', 616.51, 'sed ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris eget massa', 'Fintone', 'Glover, Bosco and Dare', 6, 17);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (8, 'BOB', 994.01, 'tellus semper interdum mauris ullamcorper purus sit amet nulla quisque arcu libero rutrum ac lobortis', 'Greenlam', 'Eichmann, Watsica and Wilkinson', 8, 17);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (9, 'THB', 97.25, 'volutpat quam pede lobortis ligula sit amet eleifend pede libero quis', 'Namfix', 'Stoltenberg-Blanda', 8, 1);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (10, 'PLN', 9.91  , 'aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt ante', 'Viva', 'MacGyver, Waters and Moen', 3, 18);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (11, 'RUB', 788.58, 'in quam fringilla rhoncus mauris enim leo rhoncus sed vestibulum sit amet cursus id turpis integer aliquet massa', 'Tempsoft', 'Lubowitz-Waelchi', 10, 13);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (12, 'PLN', 370.15, 'donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi venenatis tristique fusce congue diam', 'Tin', 'Barton and Sons', 13, 3);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (13, 'CNY', 522.42, 'cras pellentesque volutpat dui maecenas tristique est et tempus semper est quam', 'Lotlux', 'Parker Inc', 5, 18);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (14, 'TZS', 959.09, 'iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis', 'Alphazap2', 'Kozey, Romaguera and Batz', 9, 14);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (15, 'GTQ', 347.03, 'porta volutpat erat quisque erat eros viverra eget congue eget semper rutrum nulla nunc purus phasellus', 'Alpha', 'Purdy-Kuvalis', 13, 6);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (16, 'IDR', 870.84, 'sed tristique in tempus sit amet sem fusce consequat nulla nisl nunc nisl duis bibendum felis', 'Matsoft', 'Hudson Inc', 18, 6);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (17, 'CNY', 967.51, 'et commodo vulputate justo in blandit ultrices enim lorem ipsum', 'Stim', 'Greenholt LLC', 4, 3);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (18, 'RUB', 892.3, 'nisl duis bibendum felis sed interdum venenatis turpis enim blandit mi in porttitor pede justo eu massa donec', 'Transcof', 'Baumbach, Jaskolski and McLaughlin', 3, 11);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (19, 'ETB', 517.26, 'augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at', 'Domainer', 'Miller, Herzog and Collins', 11, 2);
insert into PRODUCTS (ID, CURRENCY_CODE, PRICE,DESCRIPTION, NAME, SUPPLIER_NAME, CATEGORY_ID, PRODUCT_DETAILS_ID) values (20, 'CAD', 566.36, 'in porttitor pede justo eu massa donec dapibus duis at velit eu est congue elementum', 'Namfix2', 'Orn-Treutel', 13, 7);

-- ORDER_DETAILS

insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (1, 13.72, 18, 404.38, 9, 11);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (2, 17.18, 7, 764.89, 1, 9);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (3, 9.8, 9, 195.36, 19, 15);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (4, 20.27, 20, 192.74, 7, 15);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (5, 74.91, 9, 530.1, 16, 16);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (6, 42.0, 5, 786.18, 17, 1);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (7, 21.65, 20, 370.62, 12, 20);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (8, 32.04, 12, 130.19, 19, 4);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (9, 2.77, 15, 142.51, 20, 2);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (10, 51.09, 20, 235.91, 8, 6);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (11, 31.33, 9, 726.25, 5, 14);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (12, 22.1, 7, 603.05, 15, 9);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (13, 55.0, 18, 842.24, 2, 8);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (14, 29.75, 2, 694.59, 16, 1);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (15, 1.48, 15, 471.39, 20, 4);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (16, 21.62, 9, 959.25, 20, 19);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (17, 1.11, 13, 208.84, 8, 1);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (18, 39.59, 8, 46.95, 9, 2);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (19, 5.69, 6, 636.2, 19, 7);
insert into ORDER_DETAILS (ID, DISCOUNT, QUANTITY, UNIT_PRICE, ORDER_ID, PRODUCT_ID) values (20, 64.77, 13, 176.86, 12, 3);

-- PRODUCTS_LOCATIONS

insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (19, 10, 13);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (1, 6, 16);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (6, 4, 6);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (15, 3, 9);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (1, 4, 13);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (9, 7, 10);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (6, 1, 11);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (2, 8, 1);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (7, 3, 14);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (17, 12, 16);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (10, 15, 1);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (18, 11, 8);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (2, 18, 20);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (13, 5, 20);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (4, 11, 16);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (2, 13, 1);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (5, 9, 1);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (17, 9, 8);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (2, 20, 3);
insert into PRODUCTS_LOCATIONS (PRODUCT_ID, LOCATION_ID, QUANTITY) values (8, 19, 18);
