/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClases;
import MisClases.*;
import java.util.ArrayList;
/**
 *
 * @author Aitor Alday
 */
public class Equipo {
    
    private Integer idEquipo;
    private String ref;
    private String nombre;
    private ArrayList<Jugador> jugadores;
    

    public Equipo(Integer idEquipo, String nombre) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
    }
    
     public Equipo(String nombre) {
        this.nombre = nombre;
    }
    
    public Equipo(String ref, String nombre) {
        this.ref=ref;
        this.nombre = nombre;
    }
    
    public Equipo() {
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    
}
