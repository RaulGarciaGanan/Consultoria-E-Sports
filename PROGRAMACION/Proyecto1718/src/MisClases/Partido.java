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
    private Equipo local;
    private Equipo visitante;
    private Equipo ganador;
    private Jornada jornada;
    private String resultado;

    public Partido(Integer idPartido, Date fecha, Equipo local, Equipo visitante, Equipo ganador, Jornada jornada, String resultado) {
        this.idPartido = idPartido;
        this.fecha = fecha;
        this.local = local;
        this.visitante = visitante;
        this.ganador = ganador;
        this.jornada = jornada;
        this.resultado = resultado;
    }

    public Partido(Date fecha, Equipo local, Equipo visitante, Jornada jornada) {
        this.fecha = fecha;
        this.local = local;
        this.visitante = visitante;
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

    public Equipo getLocal() {
        return local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisitante() {
        return visitante;
    }

    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    public Equipo getGanador() {
        return ganador;
    }

    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
   
    
    
    
}
