--TRIGGER JUGADORES MINIMOS
BEGIN
    insert into calendario values (70, 'Liga E-Sports 2022');
END;

--TRIGGER JUGADORES MAXIMOS
BEGIN

    insert into Miembro values(11, '4551192V', 'Aleksander', 600863974, 8, 2);
    insert into Miembro values(12, '8209229V', 'Domagoj', 600747160, 1, 2);
    insert into Miembro values(13, '2944640V', 'Jake', 600747160, 7, 2);
    insert into Miembro values(14, '4551192V', 'James', 600805812, 6, 2);
    insert into Miembro values(15, '2944640V', 'Nikita', 600863974, 4, 2);
    insert into Miembro values(16, '8209229V', 'Martin', 616773300, 9, 2);
    
    insert into Jugador values(11, 'Pum', 'Duelista', 3000);
    insert into Jugador values(12, 'Hulk', 'sniper', 3000);
    insert into Jugador values(13, 'Riky', 'capitan', 3000);
    insert into Jugador values(14, 'Toy', 'Duelista', 3000);
    insert into Jugador values(15, 'Capi', 'sniper', 3000);
    insert into Jugador values(16, 'curry', 'capitan', 3000);
END;

BEGIN
    VICTORIAS_EQUIPO('G2');
end;

BEGIN
    JUGADORES_EQUIPO('G2');
END;