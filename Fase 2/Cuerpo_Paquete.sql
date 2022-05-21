CREATE OR REPLACE PACKAGE BODY CALCULO IS
    FUNCTION PRESUPUESTO_EQUIPO
        (V_EQUIPO IN EQUIPO.NOMBRE%TYPE)
        RETURN NUMBER
    IS
        RESULTADO NUMBER;
    BEGIN

        SELECT SUM(E.SUELDO + J.SUELDO) INTO RESULTADO
        FROM ENTRENADOR E, JUGADOR J
        WHERE ID_E IN(SELECT ID
                    FROM MIEMBRO
                    WHERE ID_EQUIPO=(SELECT ID
                                    FROM EQUIPO
                                    WHERE NOMBRE=V_EQUIPO))
        AND ID_J IN(SELECT ID
                    FROM MIEMBRO
                    WHERE ID_EQUIPO=(SELECT ID
                                    FROM EQUIPO
                                    WHERE NOMBRE=V_EQUIPO));

        RETURN RESULTADO;

    END PRESUPUESTO_EQUIPO;
    
END CALCULO; 