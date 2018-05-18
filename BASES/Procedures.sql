SET serveroutput ON;
--PAQUETE DE BUSCAR EL EQUIPO

--CABECERA DEL PAQUETE
CREATE OR REPLACE PACKAGE Proyecto1718 IS
  PROCEDURE buscarEquipoEntero (v_id equipo.id_equipo%type, v_equipo OUT equipo.nombre%type , v_jugador OUT jugador.nombre%type);
END Proyecto1718;  

--CUERPO DEL PAQUETE
--Esta procedure busca el equipo que crea el dueño
CREATE OR REPLACE PACKAGE BODY Proyecto1718 IS
  PROCEDURE buscarEquipoEntero (v_id equipo.id_equipo%type, v_equipo OUT equipo.nombre%type , v_jugador OUT jugador.nombre%type) AS
BEGIN 
    SELECT e.nombre, j.nombre INTO v_equipo, v_jugador
    FROM equipo e, jugador j
    WHERE e.id_equipo = j.id_equipo;
    end;
END Proyecto1718;
 

