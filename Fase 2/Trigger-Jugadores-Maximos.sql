
CREATE OR REPLACE TRIGGER Tamaño_equipo
BEFORE INSERT OR UPDATE ON MIEMBRO
FOR EACH ROW
DECLARE
    JUG_MAX NUMBER(10);
    ID_MAX NUMBER(10);
BEGIN
                       
    SELECT MAX(COUNT(*))
    INTO JUG_MAX
    FROM MIEMBRO
    WHERE :NEW.ID_EQUIPO=ID_EQUIPO
    GROUP BY ID_EQUIPO;
                        
    IF JUG_MAX=6 THEN 
        raise_application_error(-20500,'No puede insertar mas de 6 jugadores en el equipo');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
      raise_application_error(-20500,'No existen jugadores');
    WHEN too_many_rows then
       raise_application_error (-20500,'Error');
END Tamaño_equipo;