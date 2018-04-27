SET SERVEROUTPUT ON
--TRIGGER NO MÁS DE 6 JUGADORES
CREATE TRIGGER DisparadorJugadores
  BEFORE INSERT ON Equipo
  FOR EACH ROW
DECLARE
  cont NUMBER;
BEGIN 
  SELECT COUNT(id_equipo) INTO cont FROM Jugador;
  IF cont>5 THEN
    RAISE_APPLICATION_ERROR(-20001, 'Hay demasiados jugadores, el máximo es 5');
  END IF;
END DisparadorJugadores;
--TRIGGER NO MÁS DE 200.000€
CREATE TRIGGER DisparadorSueldo
  BEFORE INSERT ON Equipo
  FOR EACH ROW
DECLARE
  cont NUMBER;
BEGIN 
  SELECT COUNT(sueldo) INTO cont FROM Jugador;
  sueldo := cont + sueldo;
  IF cont>200000 THEN
    RAISE_APPLICATION_ERROR(-20001, 'El sueldo entre todos los jugadores no puede pasar de los 200.000€');
  END IF;
END DisparadorJugadores;
