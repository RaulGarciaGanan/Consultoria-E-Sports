--PRUEBAS
DESC jugador
desc equipo
--INSERTS
insert into equipo (referencia,nombre) values ('2634','test');
commit;

--SELECTS+
SELECT * FROM Login;
SELECT * FROM Persona;
SELECT * FROM Jugador;
SELECT * FROM Jornada;
SELECT * FROM Equipo;
SELECT * FROM Partido;
desc equipo;

--DELETE
DELETE FROM Jugador;
DELETE FROM Equipo;
DELETE FROM Partido;
DELETE FROM Jornada;
DELETE FROM Temporada;
--UODATES
UPDATE Jugador SET ID_EQUIPO=null;
update Jugador set id_equipo=81 where nombre='Jugador 2';
COMMIT;



