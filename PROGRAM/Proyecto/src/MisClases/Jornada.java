/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClases;

import java.sql.Date;

/**
 *
 * @author Aitor Alday
 */
public class Jornada {
    
    private Integer idJornada;
    private Date fecIni;
    private Date fecFin;

    public Jornada(Integer idJornada, Date fecIni, Date fecFin) {
        this.idJornada = idJornada;
        this.fecIni = fecIni;
        this.fecFin = fecFin;
    }

    public Jornada() {
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Date getFecIni() {
        return fecIni;
    }

    public void setFecIni(Date fecIni) {
        this.fecIni = fecIni;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }
    
    
}
