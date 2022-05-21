insert into calendario values (70, 'Liga E-Sports 2022');

insert into Jornada values (1,  to_date('11-05-2022','dd-mm-yyyy'), 70);
insert into Jornada values (2,  to_date('18-05-2022','dd-mm-yyyy'), 70);
insert into Jornada values (3,  to_date('25-05-2022','dd-mm-yyyy'), 70);
insert into Jornada values (4,  to_date('02-06-2022','dd-mm-yyyy'), 70);
insert into Jornada values (5,  to_date('09-06-2022','dd-mm-yyyy'), 70);
insert into Jornada values (6,  to_date('16-06-2022','dd-mm-yyyy'), 70);
insert into Jornada values (7,  to_date('23-06-2022','dd-mm-yyyy'), 70);
insert into Jornada values (8,  to_date('30-06-2022','dd-mm-yyyy'), 70);

insert into equipo values (1, 'G2', 'G2.com', 'Coca-Cola');
insert into equipo values (2, 'Fnatic', 'fnatic.com', 'Bitcoin');
insert into equipo values (3, 'sentinels', 'sentinels.com', 'Kaiku');
insert into equipo values (4, '100thieves', '100thieves.com', 'Intel');
insert into equipo values (5, 'Version 1', 'version1.com', 'Amazon');
insert into equipo values (6, 'Liquid', 'liquid.com', 'Eroski');
insert into equipo values (7, 'T1', 't1.com', 'Asus');
insert into equipo values (8, 'Heretics', 'heretics.com', 'HP');

insert into partido values (100, 1, 3, null, null, null, 1);
insert into partido values (101, 2, 4, null, null, null, 1);
insert into partido values (102, 5, 6, null, null, null, 1);
insert into partido values (103, 7, 8, null, null, null, 1);
insert into partido values (104, 1, 5, null, null, null, 2);
insert into partido values (105, 2, 3, null, null, null, 2);
insert into partido values (106, 6, 4, null, null, null, 2);
insert into partido values (107, 8, 7, null, null, null, 2);


insert into Miembro values(1, '9999999A', 'Mixwell', 616773300, 1, 1);
insert into Miembro values(2, '8888888A', 'Patitek', 600805812, 9, 1);
insert into Miembro values(3, '3630313N', 'Jacob', 600747160, 9, 1);
insert into Miembro values(4, '2944640V', 'Ardis', 600385228, 7, 1);
insert into Miembro values(5, '4551192V', 'Aleksander', 600863974, 8, 1);
insert into Miembro values(6, '8209229V', 'Domagoj', 600747160, 1, 2);
insert into Miembro values(7, '2944640V', 'Jake', 600747160, 7, 2);
insert into Miembro values(8, '4551192V', 'James', 600805812, 6, 2);
insert into Miembro values(9, '2944640V', 'Nikita', 600863974, 4, 2);
insert into Miembro values(10, '8209229V', 'Martin', 616773300, 9, 2);


insert into Dueno values(1);
insert into Dueno values(6);

insert into Entrenador values(2,2800);
insert into Entrenador values(7,2800);

insert into Jugador values(3, 'Pum', 'Duelista', 3000);
insert into Jugador values(4, 'Hulk', 'sniper', 3000);
insert into Jugador values(5, 'Riky', 'capitan', 3000);
insert into Jugador values(8, 'Toy', 'Duelista', 3000);
insert into Jugador values(9, 'Capi', 'sniper', 3000);
insert into Jugador values(10, 'curry', 'capitan', 3000);


insert into Perfil values(1,'Imanol', '12345', 'Adminstrador');
