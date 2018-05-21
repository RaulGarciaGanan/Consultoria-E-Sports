/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser.Clases;

/**
 *
 * @author 1gdaw10
 */
public class liga {
    private String equipo_local;
    private String equipo_visitante;
    private String resultado;
    private String fecha_ini;

    public liga() {
    }

    public liga(String equipo_local, String equipo_visitante, String resultado, String fecha_ini) {
        this.equipo_local = equipo_local;
        this.equipo_visitante = equipo_visitante;
        this.resultado = resultado;
        this.fecha_ini = fecha_ini;
    }

    public String getEquipo_local() {
        return equipo_local;
    }

    public void setEquipo_local(String equipo_local) {
        this.equipo_local = equipo_local;
    }

    public String getEquipo_visitante() {
        return equipo_visitante;
    }

    public void setEquipo_visitante(String equipo_visitante) {
        this.equipo_visitante = equipo_visitante;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(String fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    @Override
    public String toString() {
        return "liga{" + "equipo_local=" + equipo_local + ", equipo_visitante=" + equipo_visitante + ", resultado=" + resultado + ", fecha_ini=" + fecha_ini + '}';
    }

    
}
