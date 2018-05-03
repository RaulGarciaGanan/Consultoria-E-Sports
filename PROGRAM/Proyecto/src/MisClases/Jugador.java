/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClases;

import java.util.ArrayList;

/**
 *
 * @author Aitor Alday
 */
public class Jugador {
    
    private Integer idJugador;
    private String nombre;
    private String nick;
    private Double sueldo;
    
    private ArrayList <Equipo> equipo;

    public Jugador(Integer idJugador, String nombre, String nick, Double sueldo, ArrayList<Equipo> equipo) {
        this.idJugador = idJugador;
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

    public ArrayList<Equipo> getEquipo() {
        return equipo;
    }

    public void setEquipo(ArrayList<Equipo> equipo) {
        this.equipo = equipo;
    }
    
    
}
