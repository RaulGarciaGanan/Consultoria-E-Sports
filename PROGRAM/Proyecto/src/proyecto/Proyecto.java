/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import MisClases.*;
import MisClasesBD.*;
import MisVentanas.*;

/**
 *
 * @author Aitor Alday
 */
public class Proyecto {

    public static DLogin dL;
    public static VPrincipal vP;
    public static VEquipos vE;
    public static VJugador vJ;
    public static VPersonas vPer;
    
    public static void main(String[] args) {
        dL = new DLogin(vP, true);
        dL.setVisible(true);
        
    }
    
    public static void cerrarProyecto() {
        System.exit(0);
    }
    
    /*ESTE ES EL INICIO DE LOGIN DEL ADMINISTRADOR, QUE LO TENDRÁ TODO ACTIVADO Y HAY QUE INICIAR EL PROGRAMA POR
    PRIMERA VEZ PARA CREAR UN ADMINISTRADOR; DUEÑO Y USUARIO*/
    public static void inicioRoot() {
        vP = new VPrincipal();
        vP.setVisible(true);
        vP.setLocationRelativeTo(vP);
        
        dL.dispose();
    }
    // <editor-fold defaultstate="collapsed" desc="Abrir ventanas">
    public static void abrirPersona(){
        vPer = new VPersonas();
        vPer.setVisible(true);
        vPer.setLocationRelativeTo(null);
    }
    
    public static void abrirJugador(){
        vJ = new VJugador();
        vJ.setVisible(true);
        vJ.setLocationRelativeTo(null);
    }
    
    public static void abrirEquipos(){
        vE = new VEquipos();
        vE.setVisible(true);
        vE.setLocationRelativeTo(null);
    }
    
    public static void abrirPrincipal(){
        vP = new VPrincipal();
        vP.setVisible(true);
        vP.setLocationRelativeTo(vP);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Cerrar ventanas">
    public static void cerrarPersona(){
        
    }
    
    public static void cerrarJugador(){
        
    }
    
    public static void cerrarEquipos(){
        
    }
    
    public static void cerrarPrincipal(){
        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Para DLogin">
    
    /** 
     * Las clases que no son de vistas pueden generar dialogos.
     * Con este metodo sacan el dialogo por la vista DLogin
     * @param mensaje El mensaje que se desea sacar por pantalla
     * @param usuario El usuario con el que te logeas
     * @param contraseña La contraseña con la que te logeas
     */
    
    public static void login(String usuario, String contra) throws Exception{
        
    }

    public static void cerrarLogin() {
       dL.dispose();
    }
    
    public static void toDLogin(String mensaje) {
        dL.mostrarMensaje(mensaje);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Para administracion">
    public static void insertarPersona(Integer idPersona, String nombre, Integer tipo, String usuario, String contraseña, Equipo e) {
        Persona p = new Persona(idPersona, nombre, tipo, usuario, contraseña, e);
        PersonaBD.insertarPersona(p, tipo);
        PersonaBD.crearLogin(nombre, tipo, idPersona);
    }
    // </editor-fold>
    
}
