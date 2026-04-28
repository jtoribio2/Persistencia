/* TIPUS_VIA */
INSERT INTO tipus_via (tipus) VALUES
('esportiva'),
('classica'),
('gel');

/* ESCOLES */
INSERT INTO escoles (nom, lloc, aproximacio, popularitat) VALUES
('Siurana', 'Tarragona', '20 min caminant', 3),
('Margalef', 'Tarragona', '15 min caminant', 3),
('Montserrat', 'Barcelona', '30 min caminant', 3),
('Rodellar', 'Osca', '25 min caminant', 2),
('Chulilla', 'Valencia', '20 min caminant', 2),
('Cogne', 'Italia', '10 min caminant', 3),
('Rjukan', 'Noruega', '15 min caminant', 3),
('Kandersteg', 'Suiza', '20 min caminant', 3),
('Ouray', 'USA', '5 min caminant', 2),
('La Grave', 'França', '25 min caminant', 2);

/* SECTORS */
INSERT INTO sectors (id_escola, nom, latitut, longitut, aproximacio, popularitat) VALUES
(1, 'Sector A', 41.250000, 1.15, '10 min', 3),
(2, 'Sector B', 41.300000, 1.10, '15 min', 3),
(3, 'Sector C', 41.600000, 1.80, '20 min', 3),
(4, 'Sector D', 42.300000, -0.10, '25 min', 2),
(5, 'Sector E', 39.700000, -0.80, '15 min', 2),
(6, 'Sector F', 45.600000, 7.30, '10 min', 3),
(7, 'Sector G', 59.800000, 8.50, '20 min', 3),
(8, 'Sector H', 46.500000, 7.60, '15 min', 3),
(9, 'Sector I', 38.000000, -107.70, '5 min', 2),
(10, 'Sector J', 45.000000, 6.40, '25 min', 2);

/* VIES */
INSERT INTO vies (id_sector, id_tipus_via, nom, llargada, dificultat, orientacio, ancoratge, troca) VALUES
-- escoles 1–5 (tipus 1 i 2)
(1, 1, 'Via1', 25, '6a', 'N', 'parablt', 'si'),
(2, 2, 'Via2', 30, '6b', 'S', 'friend', 'no'),
(3, 1, 'Via3', 20, '5c', 'E', 'parablt', 'si'),
(4, 2, 'Via4', 35, '6c', 'W', 'friend', 'no'),
(5, 1, 'Via5', 28, '6a', 'N', 'parablt', 'si'),

-- escoles 6–10 (només gel)
(6, 3, 'Via6', 40, 'WI4', 'N', 'gel', 'no'),
(7, 3, 'Via7', 50, 'WI5', 'N', 'gel', 'no'),
(8, 3, 'Via8', 45, 'WI3', 'N', 'gel', 'no'),
(9, 3, 'Via9', 35, 'WI4', 'N', 'gel', 'no'),
(10, 3, 'Via10', 60, 'WI6', 'N', 'gel', 'no');

/* DISPONIBILITATS */
INSERT INTO disponibilitats (id_via, inici, final, rao) VALUES
(1, '2026-01-01', '2026-01-10', 'manteniment'),
(2, '2026-02-01', '2026-02-05', 'pluja'),
(3, '2026-03-01', '2026-03-03', 'seguretat'),
(4, '2026-04-01', '2026-04-10', 'neteja'),
(5, '2026-05-01', '2026-05-07', 'event'),
(6, '2026-01-15', '2026-02-01', 'gel inestable'),
(7, '2026-02-10', '2026-02-20', 'allaus'),
(8, '2026-03-10', '2026-03-15', 'clima'),
(9, '2026-01-20', '2026-01-25', 'gel'),
(10, '2026-02-25', '2026-03-05', 'seguretat');

/* LLARS */
INSERT INTO llars (id_via, metres) VALUES
(1, 10),
(2, 15),
(3, 12),
(4, 18),
(5, 14),
(6, 20),
(7, 25),
(8, 22),
(9, 19),
(10, 30);

/* ESCALADORS */
INSERT INTO escaladors (dni, nom, edat, estil) VALUES
('11111111A', 'Joan', 25, 1),
('22222222B', 'Marc', 30, 2),
('33333333C', 'Anna', 22, 1),
('44444444D', 'Laura', 28, 2),
('55555555E', 'Pau', 35, 1),
('66666666F', 'Eric', 40, 3),
('77777777G', 'Nora', 27, 3),
('88888888H', 'Ivan', 33, 3),
('99999999I', 'Sara', 29, 3),
('00000000J', 'Pol', 31, 3);

/* ESCALADORS_VIES */
INSERT INTO escaladors_vies (id_escalador, id_via) VALUES
(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9),
(10,10);