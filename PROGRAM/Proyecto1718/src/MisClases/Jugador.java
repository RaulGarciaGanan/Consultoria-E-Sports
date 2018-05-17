/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClases;



/**
 *
 * @author Aitor Alday
 */
public class Jugador {
    
    private Integer idJugador;
    private String dni;
    private String nombre;
    private String nick;
    private Double sueldo;
    
    private Equipo equipo;

    public Jugador(String dni, String nombre, String nick, Double sueldo, Equipo equipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.nick = nick;
        this.sueldo = sueldo;
        this.equipo = equipo;
    }
    
    public Jugador(Integer idJugador, String dni, String nombre, String nick, Double sueldo) {
        this.idJugador = idJugador;
        this.dni = dni;
        this.nombre = nombre;
        this.nick = nick;
        this.sueldo = sueldo;
    }
    
    public Jugador(String dni, String nombre, String nick, Double sueldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.nick = nick;
        this.sueldo = sueldo;
    }

    public Jugador(Integer idJugador, String dni, String nombre, String nick, Double sueldo, Equipo equipo) {
        this.idJugador = idJugador;
        this.dni = dni;
        this.nombre = nombre;
        this.nick = nick;
        this.sueldo = sueldo;
        this.equipo = equipo;
    }
    
    

    public Jugador() {
    }

    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
        this.idJugador = idJugador;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

  
    
}
