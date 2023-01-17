-- noinspection SqlNoDataSourceInspectionForFile

-- Insert Chefs
INSERT INTO CHEF VALUES (1, "Nicolas Crausaz");
INSERT INTO CHEF VALUES (2, "Maxime Scharwath");
INSERT INTO CHEF VALUES (3, "Gordon Ramsey");

-- Insert Dishes
INSERT INTO DISH VALUES (1, "Falafels, sauce yoghourt");
INSERT INTO DISH VALUES (2, "Steak haché de bœuf (CH)");
INSERT INTO DISH VALUES (3, "Risotto au parmesan");
INSERT INTO DISH VALUES (4, "Poulet grillé (CH)");
INSERT INTO DISH VALUES (5, "Spaghetti sauce tomates et légumes");
INSERT INTO DISH VALUES (6, "Ragout de bœuf stroganoff (CH)");
INSERT INTO DISH VALUES (7, "Steak haché végétal maison");
INSERT INTO DISH VALUES (8, "Filet de cabillaud aux herbes");
INSERT INTO DISH VALUES (9, "Epaule de porc (CH) mijotée");

-- Insert Menus
INSERT INTO MENU (NAME, STARTER_ID, MAIN_ID, DESSERT_ID) VALUES ("Menu du jour", 1, 2, 3);
INSERT INTO MENU (NAME, STARTER_ID, MAIN_ID, DESSERT_ID) VALUES ("Menu de la semaine", 3, 4, 5);
INSERT INTO MENU (NAME, STARTER_ID, MAIN_ID, DESSERT_ID) VALUES ("Menu de l'année", 6, 7, 8);
