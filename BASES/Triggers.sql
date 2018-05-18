SET SERVEROUTPUT ON

--TRIGGER NO M�S DE 6 JUGADORES
CREATE TRIGGER DisparadorJugadores
  BEFORE INSERT ON Equipo
  FOR EACH ROW
DECLARE
  cont NUMBER;
BEGIN 
  SELECT COUNT(id_equipo) INTO cont FROM Jugador;
  IF cont>5 THEN
    RAISE_APPLICATION_ERROR(-20001, 'Hay demasiados jugadores, el m�ximo es 6');
  END IF;
END DisparadorJugadores;

--TRIGGER NO M�S DE 200.000�
CREATE TRIGGER DisparadorSueldo
  BEFORE INSERT ON Equipo
  FOR EACH ROW
DECLARE
  cont NUMBER;
BEGIN 
  SELECT COUNT (sueldo) INTO cont FROM Jugador;
  sueldo := cont + sueldo;
  IF cont>200000 THEN
    RAISE_APPLICATION_ERROR(-20001, 'El sueldo entre todos los jugadores no puede pasar de los 200.000�');
  END IF;
END DisparadorJugadores;

--TRIGGER CREAR UN LOGIN
CREATE OR REPLACE TRIGGER GenerarLogin
  AFTER INSERT ON Persona
  FOR EACH ROW 
BEGIN  
  INSERT INTO Login (usuario, contrase�a, id_persona) VALUES (:new.dni, SUBSTR(:new.nombre, 1, 3), :new.id_persona);
END GenerarLogin;
  
--TRIGGER BORRAR PERSONA DEL LOGIN
CREATE OR REPLACE TRIGGER BorrarUser
  AFTER DELETE ON Persona 
  FOR EACH ROW
BEGIN
  DELETE FROM Login WHERE login.id_persona=:old.id_persona; 
END BorrarUser;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  