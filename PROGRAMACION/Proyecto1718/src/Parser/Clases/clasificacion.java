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
public class clasificacion {
    private String nombre;
    private String referencia;
    private String resultado;

    public clasificacion() {
    }

    public clasificacion(String nombre, String referencia, String resultado) {
        this.nombre = nombre;
        this.referencia = referencia;
        this.resultado = resultado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    
   
}
