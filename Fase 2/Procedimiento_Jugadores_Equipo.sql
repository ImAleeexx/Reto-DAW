create or replace procedure Jugadores_Equipo(Equipo in VARCHAR2)
as
resultado number;
begin
select count(id_j) into resultado
from Jugador
where id_j=(select id
            from Miembro
            where ID_Equipo =(select id
                                from equipo
                                where lower(nombre)=Equipo));
DBMS_OUTPUT.put_line('El numero de miembros del equipo' || Equipo || 'es de:  ' || RESULTADO);
end;
