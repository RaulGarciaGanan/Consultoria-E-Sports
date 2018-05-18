--LAS TABLAS DEL PROYECTO

 /*Necesitamos un id autoincremental para poder identificar la temporada en la que estamos, y tendrá un  atributo año para saber el año
 en el que se celebró y son ambos atributos obligatorios. La clase se usa para poder agrupar los partidos.*/
DROP TABLE Temporada CASCADE CONSTRAINTS;
CREATE TABLE Temporada (
  id_temporada INTEGER 
    GENERATED ALWAYS AS IDENTITY
                      MINVALUE 1
                      MAXVALUE 999999
                      INCREMENT BY 1
                      START WITH 1
                      NOORDER NOCYCLE NOT NULL
                      ENABLE
            CONSTRAINT id_te_pk PRIMARY KEY,
  año INTEGER NOT NULL        
);

 /*Necesitamos otro id autoincremental para identificar el equipo, y aparte tendrá un atributo referencia para poder buscarlo más fácilmente
  en la BD con progrmación y también un nombre cualquiera y son todos obligatorios. La clase se usará para que el dueño pueda meter jugadores a algún lado*/
DROP TABLE Equipo CASCADE CONSTRAINTS;
CREATE TABLE Equipo (
  id_equipo INTEGER 
    GENERATED ALWAYS AS IDENTITY
                      MINVALUE 1
                      MAXVALUE 999999
                      INCREMENT BY 1
                      START WITH 1
                      NOORDER NOCYCLE NOT NULL
                      ENABLE
            CONSTRAINT id_eq_pk PRIMARY KEY,
  referencia VARCHAR(4) NOT NULL,          
  nombre VARCHAR(15) NOT NULL 
  );


 /*Tendremos un id autoincremental también para jugador para identificarlo en la BD, un dni para poder buscarlo fácilmente con NetBeans, un nickname
  con el que el jugador se identifique en el juego, su nombre y su sueldo y son todos obligatorios. La tabla se usará para almcenar jugadores de los E-sports.*/
DROP TABLE Jugador CASCADE CONSTRAINTS;
CREATE TABLE Jugador (
  id_jugador INTEGER 
    GENERATED ALWAYS AS IDENTITY
                      MINVALUE 1
                      MAXVALUE 999999
                      INCREMENT BY 1
                      START WITH 1
                      NOORDER NOCYCLE NOT NULL
                      ENABLE
             CONSTRAINT id_ju_pk PRIMARY KEY,
  dni VARCHAR(9) UNIQUE NOT NULL,           
  nickname VARCHAR(15) UNIQUE NOT NULL,
  nombre VARCHAR(15) NOT NULL,
  sueldo NUMBER(6,2) NOT NULL,
  id_equipo INTEGER NOT NULL,
  
  CONSTRAINT id_eqj_fk FOREIGN KEY(id_equipo) REFERENCES Equipo (id_equipo));
  
  /*Tendremos un id autoincremental también para persona para identificarlo en la BD, un dni para poder buscarlo fácilmente con NetBeans,  su nombre 
  y su tipo, que será integer para en progrmacion elegir 1, 2 o 3 (admin, dueño o usuario) y son todos obligatorios. La tabla se usará para almcenar los tipos
  de personas que toman parte en el evento.*/
DROP TABLE Persona CASCADE CONSTRAINTS;
CREATE TABLE Persona (
  id_persona INTEGER 
    GENERATED ALWAYS AS IDENTITY
                      MINVALUE 1
                      MAXVALUE 999999
                      INCREMENT BY 1
                      START WITH 1
                      NOORDER NOCYCLE NOT NULL
                      ENABLE
             CONSTRAINT id_pe_pk PRIMARY KEY,
  dni VARCHAR(9) UNIQUE NOT NULL,             
  nombre VARCHAR(15) NOT NULL,
  tipo INTEGER NOT NULL,
  id_equipo INTEGER NULL,
  
  CONSTRAINT id_eqp_fk FOREIGN KEY (id_equipo) REFERENCES Equipo (id_equipo));
  /*Tiene otro id autoincremental, un usuario y una contraseña para acceder al programa. Sirve para guardar a los usuarios.*/
DROP TABLE Login CASCADE CONSTRAINTS;
CREATE TABLE Login (
  id_login INTEGER 
    GENERATED ALWAYS AS IDENTITY
                      MINVALUE 1
                      MAXVALUE 999999
                      INCREMENT BY 1
                      START WITH 1
                      NOORDER NOCYCLE NOT NULL
                      ENABLE
           CONSTRAINT id_lo_pk PRIMARY KEY,
  usuario VARCHAR(15) NOT NULL,
  contraseña VARCHAR(15) NOT NULL,
  id_persona INTEGER NOT NULL,
  
  CONSTRAINT id_per_fk FOREIGN KEY (id_persona) REFERENCES Persona (id_persona));
  
  /*También tiene un id autoincremental, una fecha de inicio y una fecha final, todos obligaorios. Sirve para almacenar los partidos y dividirlosd 
   de algun modo.*/
DROP TABLE Jornada CASCADE CONSTRAINTS;
CREATE TABLE Jornada (
  id_jornada INTEGER 
    GENERATED ALWAYS AS IDENTITY
                      MINVALUE 1
                      MAXVALUE 999999
                      INCREMENT BY 1
                      START WITH 1
                      NOORDER NOCYCLE NOT NULL
                      ENABLE
             CONSTRAINT id_jo_pk PRIMARY KEY,
  fec_ini DATE NOT NULL,
  fec_fin DATE NOT NULL,
  id_temporada INTEGER NOT NULL,

  CONSTRAINT id_tem_fk FOREIGN KEY (id_temporada) REFERENCES Temporada (id_temporada)
);
  /*tenemos un id autoincremental, como en todas, para identificar cada partido jugado. la fk a local y a visitante pueden ser nulos porque 
  en el caso de que sea un número impar de equipos un equipo descansara dicha jornada y en la tabla partidos será un nulo. El id ganador puede 
  ser nulo porque al generar el calendario el partido no se habrá jugado aún y no habra ganador aun, es una fk para facilitar la generación de 
  la clasificación(la select consistiría en contar el número de partidos de la temporada en el que el ganador sea idEquipo y multiplicarlo por 
  el número de puntos que se obtienen por victoria y ordenarlos posteriormente)*/
DROP TABLE Partido CASCADE CONSTRAINTS;
CREATE TABLE Partido (
  id_partido INTEGER 
    GENERATED ALWAYS AS IDENTITY
                      MINVALUE 1
                      MAXVALUE 999999
                      INCREMENT BY 1
                      START WITH 1
                      NOORDER NOCYCLE NOT NULL
                      ENABLE
             CONSTRAINT id_pa_pk PRIMARY KEY,
  fecha DATE NOT NULL,
  id_local INTEGER,
  id_visitante INTEGER,
  id_ganador INTEGER,
  resultado VARCHAR(10),
  id_jornada INTEGER NOT NULL,
  
  CONSTRAINT id_jo_fk FOREIGN KEY (id_jornada) REFERENCES Jornada (id_jornada),
  CONSTRAINT id_local_fk FOREIGN KEY (id_local) REFERENCES Equipo (id_equipo),
  CONSTRAINT id_visit_fk FOREIGN KEY (id_visitante) REFERENCES Equipo (id_equipo),
  CONSTRAINT id_gana_fk FOREIGN KEY (id_ganador) REFERENCES Equipo (id_equipo)
  );
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  