-- noinspection SqlNoDataSourceInspectionForFile

-- Insert Chefs
INSERT INTO chef VALUES (default, 'Nicolas Crausaz');
INSERT INTO chef VALUES (default, 'Maxime Scharwath');
INSERT INTO chef VALUES (default, 'Gordon Ramsey');

-- Insert Dishes
INSERT INTO dish VALUES (default, 'Falafels, sauce yoghourt');
INSERT INTO dish VALUES (default, 'Steak haché de bœuf (CH)');
INSERT INTO dish VALUES (default, 'Risotto au parmesan');
INSERT INTO dish VALUES (default, 'Poulet grillé (CH)');
INSERT INTO dish VALUES (default, 'Spaghetti sauce tomates et légumes');
INSERT INTO dish VALUES (default, 'Ragout de bœuf stroganoff (CH)');
INSERT INTO dish VALUES (default, 'Steak haché végétal maison');
INSERT INTO dish VALUES (default, 'Filet de cabillaud aux herbes');
INSERT INTO dish VALUES (default, 'Epaule de porc (CH) mijotée');

-- Insert Menus
INSERT INTO menu VALUES (default, 'Menu du jour', 1, 2, 3);
INSERT INTO menu VALUES (default, 'Menu de la semaine', 3, 4, 5);
INSERT INTO menu VALUES (default, 'Menu de du mois', 6, 7, 8);

-- Insert MenuDishes
INSERT INTO chef_dishes VALUES (1, 1);
INSERT INTO chef_dishes VALUES (1, 2);
INSERT INTO chef_dishes VALUES (1, 3);

INSERT INTO chef_dishes VALUES (2, 3);
INSERT INTO chef_dishes VALUES (2, 4);
INSERT INTO chef_dishes VALUES (2, 5);
INSERT INTO chef_dishes VALUES (2, 6);

INSERT INTO chef_dishes VALUES (3, 4);
INSERT INTO chef_dishes VALUES (3, 5);
INSERT INTO chef_dishes VALUES (3, 6);
INSERT INTO chef_dishes VALUES (3, 7);
INSERT INTO chef_dishes VALUES (3, 8);
INSERT INTO chef_dishes VALUES (3, 9);
