/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClases;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Aitor Alday
 */
public class Partido {
    
    private Integer idPartido;
    private Date fecha;
    private Integer puntosLoc;
    private Integer puntosVis;
    
    private ArrayList <Jornada> jornada;

    public Partido(Integer idPartido, Date fecha, Integer puntosLoc, Integer puntosVis, ArrayList<Jornada> jornada) {
        this.idPartido = idPartido;
        this.fecha = fecha;
        this.puntosLoc = puntosLoc;
        this.puntosVis = puntosVis;
        this.jornada = jornada;
    }

    public Partido() {
    }

    public Integer getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getPuntosLoc() {
        return puntosLoc;
    }

    public void setPuntosLoc(Integer puntosLoc) {
        this.puntosLoc = puntosLoc;
    }

    public Integer getPuntosVis() {
        return puntosVis;
    }

    public void setPuntosVis(Integer puntosVis) {
        this.puntosVis = puntosVis;
    }

    public ArrayList<Jornada> getJornada() {
        return jornada;
    }

    public void setJornada(ArrayList<Jornada> jornada) {
        this.jornada = jornada;
    }
    
    
    
}
