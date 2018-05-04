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
public class Persona {
    
    private Integer idPersona;
    private String nombre;
    private Integer tipo;
    private Equipo equipo;

    public Persona(Integer idPersona, String nombre, Integer tipo, Equipo equipo) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.equipo = equipo;
        this.tipo = tipo;
    }

    public Persona() {
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
    
    
}
