/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClases;
import java.util.ArrayList;
/**
 *
 * @author Estíbaliz
 */
public class Temporada {
    private Integer idTemporada;
    private Integer anio;
    private ArrayList<Partido> partidos;

    public Temporada(Integer idTemporada, Integer anio) {
        this.idTemporada = idTemporada;
        this.anio = anio;
    }

    public Temporada(Integer idTemporada, Integer anio, ArrayList<Partido> partidos) {
        this.idTemporada = idTemporada;
        this.anio = anio;
        this.partidos = partidos;
    }
    

    public Temporada() {
    }

    public Integer getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(Integer idTemporada) {
        this.idTemporada = idTemporada;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }
    
    
}
