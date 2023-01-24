-- noinspection SqlNoDataSourceInspectionForFile

-- Insert Chefs
INSERT INTO chef VALUES (1, 'Nicolas Crausaz');
INSERT INTO chef VALUES (2, 'Maxime Scharwath');
INSERT INTO chef VALUES (3, 'Gordon Ramsey');

-- Insert Dishes
INSERT INTO dish VALUES (1, 'Falafels, sauce yoghourt');
INSERT INTO dish VALUES (2, 'Steak haché de bœuf (CH)');
INSERT INTO dish VALUES (3, 'Risotto au parmesan');
INSERT INTO dish VALUES (4, 'Poulet grillé (CH)');
INSERT INTO dish VALUES (5, 'Spaghetti sauce tomates et légumes');
INSERT INTO dish VALUES (6, 'Ragout de bœuf stroganoff (CH)');
INSERT INTO dish VALUES (7, 'Steak haché végétal maison');
INSERT INTO dish VALUES (8, 'Filet de cabillaud aux herbes');
INSERT INTO dish VALUES (9, 'Epaule de porc (CH) mijotée');

-- Insert Menus
INSERT INTO menu VALUES (1, 'Menu du jour', 1, 2, 3);
INSERT INTO menu VALUES (2, 'Menu de la semaine', 3, 4, 5);
INSERT INTO menu VALUES (3, 'Menu de du mois', 6, 7, 8);
