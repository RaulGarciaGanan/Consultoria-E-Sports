SET SERVEROUTPUT ON

CREATE OR REPLACE PACKAGE triggersEquipo AS
  v_idEquipo equipo.id_equipo%type := null;
  v_sueldo jugador.sueldo%type := null;
END;

--TRIGGERS PARA PAQUETE
CREATE OR REPLACE TRIGGER noMutaTablaJu
  AFTER INSERT OR UPDATE ON Jugador
  FOR EACH ROW
BEGIN
  triggersEquipo.v_idEquipo := :new.id_equipo;
END noMutaTablaJu;  

CREATE OR REPLACE TRIGGER noMutaTablaSu
  AFTER INSERT OR UPDATE ON Jugador
  FOR EACH ROW
BEGIN
  triggersEquipo.v_sueldo := :new.sueldo;
END noMutaTablaSu;

--TRIGGER NO MÁS DE 6 JUGADORES
CREATE OR REPLACE TRIGGER DisparadorJugadores
  BEFORE INSERT ON Equipo
  FOR EACH ROW
DECLARE
  cont NUMBER;
BEGIN 
  SELECT COUNT(id_equipo) INTO cont FROM Jugador WHERE id_equipo = triggersEquipo.v_idEquipo;
  IF cont>5 THEN
    RAISE_APPLICATION_ERROR(-20001, 'Hay demasiados jugadores, el máximo es 6');
  END IF;
END DisparadorJugadores;

--TRIGGER NO MÁS DE 200.000€
DROP TRIGGER DisparadorSueldo;
CREATE OR REPLACE TRIGGER DisparadorSueldo
  BEFORE INSERT ON Jugador
  FOR EACH ROW
DECLARE
  cont NUMBER;
BEGIN 
  SELECT SUM(sueldo) INTO cont FROM Jugador WHERE id_equipo = triggersEquipo.v_idEquipo;
  cont := cont + triggersEquipo.v_sueldo;
  IF cont>200000 THEN
    RAISE_APPLICATION_ERROR(-20001, 'El sueldo entre todos los jugadores no puede pasar de los 200.000€');
  END IF;
END DisparadorJugadores;

--TRIGGER CREAR UN LOGIN
CREATE OR REPLACE TRIGGER GenerarLogin
  AFTER INSERT ON Persona
  FOR EACH ROW 
BEGIN  
  INSERT INTO Login (usuario, contraseña, id_persona) VALUES (:new.dni, SUBSTR(:new.nombre, 1, 3), :new.id_persona);
END GenerarLogin;
  
--TRIGGER BORRAR PERSONA DEL LOGIN
CREATE OR REPLACE TRIGGER BorrarUser
  AFTER DELETE ON Persona 
  FOR EACH ROW
BEGIN
  DELETE FROM Login WHERE login.id_persona=:old.id_persona; 
END BorrarUser;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  