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
    public static void abrirEquipos(char opt){
        vE = new VEquipos(opt);
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
        vPer.dispose();
    }
    
    public static void cerrarJugador(){
        vJ.dispose();
    }
    
    public static void cerrarEquipos(){
        vE.dispose();
    }
    
    public static void cerrarPrincipal(){
        vP.dispose();
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
        Login login = new Login(usuario,contra);
        LoginBD logn= new LoginBD();
        Persona user= logn.logearUsuario(login);
        if(user.getIdPersona()!=null){
             javax.swing.JOptionPane.showMessageDialog(null, "usuario logeado" + user.getNombre()+user.getTipo());
             switch(user.getTipo()){
                 case 1:javax.swing.JOptionPane.showMessageDialog(null,"abrir ventana usuario tipo 1");//abrir la ventana que toque para cada uno
                 break;
                 case 2:javax.swing.JOptionPane.showMessageDialog(null,"abrir ventana usuario tipo 2");//abrir la ventana que toque para cada uno
                 break;
             }
        }else{
             javax.swing.JOptionPane.showMessageDialog(null, "usuario y/o contraseña incorrectos");
        }
       
    }

    public static void cerrarLogin() {
       dL.dispose();
    }
    
    public static void toDLogin(String mensaje) {
        dL.mostrarMensaje(mensaje);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Para administracion">
    public static void insertarPersona(Integer idPersona, String nombre, Integer tipo, String usuario, String contraseña, Equipo e) throws Exception{
        Persona p = new Persona(idPersona, nombre, tipo, e);
        PersonaBD.insertarPersona(p, tipo);
        LoginBD.crearLogin(nombre, tipo, idPersona);
    }
    
    public static void toVPersona(String mensaje) {
        vPer.mostrarMensaje(mensaje);
    }
    // </editor-fold>
    
}
