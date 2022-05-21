CREATE OR REPLACE PROCEDURE VICTORIAS_EQUIPO(NOM_E IN VARCHAR2)
AS
TOTAL_VICTORIAS NUMBER;
BEGIN

SELECT SUM(RESULTADO_VISITANTE+RESULTADO_LOCAL) INTO TOTAL_VICTORIAS
FROM PARTIDO
WHERE ID_VISITANTE=(SELECT ID
				FROM EQUIPO 
				WHERE NOMBRE=NOM_E)
AND ID_LOCAL=(SELECT ID
			FROM EQUIPO 
			WHERE NOMBRE=NOM_E);

DBMS_OUTPUT.put_line('El numero total de victorias del equipo ' || NOM_E || 'es ' || TOTAL_VICTORIAS);


END;
