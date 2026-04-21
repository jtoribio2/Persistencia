-- ESCOLAS
INSERT INTO escoles VALUES 
(1, 'Siurana', 'Tarragona', '20 min', 3),
(2, 'Montserr', 'Barcelona', '15 min', 3),
(3, 'Rodellar', 'Huesca', '10 min', 3),
(4, 'Margalef', 'Tarragona', '5 min', 2),
(5, 'Chulilla', 'Valencia', '25 min', 3),
(6, 'Albarracin', 'Teruel', '10 min', 2),
(7, 'Patones', 'Madrid', '15 min', 2),
(8, 'Cuenca', 'Cuenca', '20 min', 3),
(9, 'Vadiello', 'Huesca', '30 min', 1),
(10, 'Riglos', 'Huesca', '5 min', 3);

-- SECTORS
INSERT INTO sectors VALUES
(1, 1, 'Pati', 41.260000, 0.95, '10 min', 3),
(2, 1, 'Melafots', 41.261000, 0.95, '15 min', 2),
(3, 2, 'Benet', 41.590000, 1.83, '20 min', 3),
(4, 2, 'Gorros', 41.591000, 1.83, '25 min', 2),
(5, 3, 'Ventanas', 42.290000, -0.06, '5 min', 3),
(6, 4, 'Torxa', 41.270000, 0.90, '10 min', 2),
(7, 5, 'Blanca', 39.650000, -0.89, '20 min', 3),
(8, 6, 'Techos', 40.400000, -1.44, '15 min', 2),
(9, 7, 'Oliva', 40.860000, -3.45, '10 min', 2),
(10, 8, 'Encantada', 40.080000, -2.14, '20 min', 3);

-- TIPUS_VIA
INSERT INTO tipus_via VALUES
(1, 'Depor'),
(2, 'Clas'),
(3, 'Bould'),
(4, 'Mixta'),
(5, 'Artif'),
(6, 'Esc'),
(7, 'Placa'),
(8, 'Despl'),
(9, 'Fisur'),
(10, 'Techo');

-- VIES
INSERT INTO vies VALUES
(1, 1, 1, 'Reina', 30, '6a', 'S', 'Parabol', 'Si'),
(2, 1, 1, 'Intento', 25, '6b', 'SE', 'Parabol', 'Si'),
(3, 2, 1, 'Camino', 20, '5c', 'E', 'Parabol', 'No'),
(4, 3, 2, 'Normal', 100, 'V+', 'N', 'Clavos ', 'No'),
(5, 4, 2, 'Frigi', 120, '6a', 'N', 'Mixto  ', 'No'),
(6, 5, 1, 'Power', 35, '7a', 'S', 'Parabol', 'Si'),
(7, 6, 1, 'Style', 28, '6c', 'SW', 'Parabol', 'Si'),
(8, 7, 1, 'King', 40, '7b', 'S', 'Parabol', 'Si'),
(9, 8, 3, 'Bloque', 5, '7a', 'N', 'Crashp ', 'No'),
(10, 9, 1, 'Flow', 18, '6a', 'E', 'Parabol', 'Si');

-- DISPONIBILITATS
INSERT INTO disponibilitats VALUES
(1, 1, '2026-01-01', '2026-02-01', 'Mant'),
(2, 2, '2026-03-01', '2026-03-10', 'Fauna'),
(3, 3, '2026-04-01', '2026-04-15', 'Reeq'),
(4, 4, '2026-05-01', '2026-05-10', 'Seg'),
(5, 5, '2026-06-01', '2026-06-20', 'Obra'),
(6, 6, '2026-07-01', '2026-07-15', 'Fauna'),
(7, 7, '2026-08-01', '2026-08-10', 'Mant'),
(8, 8, '2026-09-01', '2026-09-10', 'Obra'),
(9, 9, '2026-10-01', '2026-10-10', 'Seg'),
(10, 10, '2026-11-01', '2026-11-15', 'Rev');

-- LLARS
INSERT INTO llars VALUES
(1, 1, 15),
(2, 2, 12),
(3, 3, 10),
(4, 4, 30),
(5, 5, 35),
(6, 6, 18),
(7, 7, 20),
(8, 8, 25),
(9, 9, 5), 
(10, 10, 12);

-- ESCALADORS
INSERT INTO escaladors VALUES
(1, '12345678A', 'Carlos', 28, 1),
(2, '12345678B','Marta', 25, 1),
(3,'12345678C', 'Luis', 32, 2),
(4, '12345678D','Ana', 27, 1),
(5, '12345678E','Javi', 35, 2),
(6,'12345678F', 'Lucia', 24, 1),
(7, '12345678G','Pedro', 30, 3),
(8, '12345678I','Elena', 29, 1),
(9, '12345678J','Raul', 33, 2),
(10, '12345678K','Sara', 26, 1);

-- ESCALADORS_VIES
INSERT INTO escaladors_vies VALUES
(1,1),(2,2),(3,3),(4,4),(5,5),
(6,6),(7,7),(8,8),(9,9),(10,10);