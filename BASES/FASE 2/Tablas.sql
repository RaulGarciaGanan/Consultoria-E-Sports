--LAS TABLAS DEL PROYECTO

DROP TABLE Equipo CASCADE CONSTRAINTS;
CREATE TABLE Equipo (
  id_equipo INTEGER NOT NULL,
  nombre VARCHAR(15) NOT NULL,
  
  CONSTRAINT id_eq_pk PRIMARY KEY(id_equipo));

DROP TABLE Jugador CASCADE CONSTRAINTS;
CREATE TABLE Jugador (
  id_jugador INTEGER NOT NULL,
  nickname VARCHAR(15) UNIQUE NOT NULL,
  nombre VARCHAR(15) NOT NULL,
  sueldo NUMBER(6,2) NOT NULL,
  id_equipo INTEGER NOT NULL,
  
  CONSTRAINT id_ju_pk PRIMARY KEY(id_jugador),
  CONSTRAINT id_eqj_fk FOREIGN KEY(id_equipo) REFERENCES Equipo (id_equipo));
  
DROP TABLE Persona CASCADE CONSTRAINTS;
CREATE TABLE Persona (
  id_persona INTEGER NOT NULL,
  nombre VARCHAR(15) NOT NULL,
  usuario VARCHAR(15) NOT NULL,
  contraseña VARCHAR(15) NOT NULL,
  tipo INTEGER NOT NULL,
  id_equipo INTEGER NOT NULL,
  
  CONSTRAINT id_pe_pk PRIMARY KEY (id_persona),
  CONSTRAINT id_eqp_fk FOREIGN KEY (id_equipo) REFERENCES Equipo (id_equipo));
  
DROP TABLE Jornada CASCADE CONSTRAINTS;  
CREATE TABLE Jornada (
  id_jornada INTEGER NOT NULL,
  fec_ini DATE NOT NULL,
  fec_fin DATE NOT NULL,
  
  CONSTRAINT id_jo_pk PRIMARY KEY (id_jornada));
  
DROP TABLE Partido CASCADE CONSTRAINTS;
CREATE TABLE Partido (
  id_partido INTEGER NOT NULL,
  fecha DATE NOT NULL,
  puntos_loc INTEGER NOT NULL,
  puntos_vis INTEGER NOT NULL,
  id_jornada INTEGER NOT NULL,
  
  CONSTRAINT id_pa_pk PRIMARY KEY (id_partido),
  CONSTRAINT id_jo_fk FOREIGN KEY (id_jornada) REFERENCES Jornada (id_jornada));
 
DROP TABLE Juegan CASCADE CONSTRAINTS; 
CREATE TABLE Juegan (
  id_partido INTEGER NOT NULL,
  id_equipo INTEGER NOT NULL,
  
  CONSTRAINT id_pa_fk FOREIGN KEY (id_partido) REFERENCES Partido (id_partido),
  CONSTRAINT id_eqju_fk FOREIGN KEY(id_equipo) REFERENCES Equipo (id_equipo)); 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  