/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import MisClases.*;
import MisClasesBD.*;
import MisVentanas.*;
import java.util.ArrayList;

/**
 *
 * @author Aitor Alday
 */
public class Proyecto {

    public static DLogin dL;
    public static NewJFrame fondo;
    public static VPrincipal vP;
    public static VEquipos vE;
    public static VJugador vJ;
    public static VPersonas vPer;
    
    
    
    public static void main(String[] args) {
        dL = new DLogin(fondo, true);
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
        if(user.getIdPersona() != null){
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
    
        // <editor-fold defaultstate="collapsed" desc="PERSONA">
        public static void insertarPersona(String dni, String nombre, Integer tipo) throws Exception{
            Persona p = new Persona(dni, nombre, tipo);
            PersonaBD.insertarPersona(p);
        }
        public static void borrarPersona(String dni,String nombre, Integer tipo) throws Exception{
            Persona p = new Persona(dni, nombre, tipo);
            PersonaBD.borrarPersona(p);
        }

        public static void modificarPersona(String dni, String nombre, Integer tipo) throws Exception{
            Persona p = new Persona(dni, nombre, tipo);
            PersonaBD.actualizarPersona(p);
        }
        public static Persona buscarPersonaDNI(String dni) throws Exception{
            return PersonaBD.buscarPersona(dni);
        }

        public static void toVPersona(String mensaje) {
            vPer.mostrarMensaje(mensaje);
        }
    
        // </editor-fold>
    
        // <editor-fold defaultstate="collapsed" desc="JUGADOR">
        public static void insertarJugador(String dni, String nick, String nombre, Double sueldo) throws Exception{
            Jugador j = new Jugador(dni, nick, nombre, sueldo);
            JugadorBD.insertarJugador(j);
        }
        
        public static void borrarJugador(String dni, String nick, String nombre, Double sueldo) throws Exception{
            Jugador j = new Jugador(dni, nick, nombre, sueldo);
            JugadorBD.borrarJugador(j);
        }
        
        public static void modificarJugador(String dni, String nick, String nombre, Double sueldo) throws Exception{
            Jugador j = new Jugador(dni, nick, nombre, sueldo);
            JugadorBD.borrarJugador(j);
        }
        
        public static Jugador buscarJugadorDNI(String dni) throws Exception{
            return JugadorBD.buscarJugador(dni);
        }
        
        public static void toVJugador(String mensaje){
            vJ.mostrarMensaje(mensaje);
        }
        // </editor-fold>
    
        // <editor-fold defaultstate="collapsed" desc="EQUIPO">
        public static void insertarEquipo(String ref, String nombre) throws Exception{
            Equipo eq = new Equipo(ref, nombre);
            EquipoBD.insertarEquipo(eq);
        }
        
        public static void borrarEquipo(String ref, String nombre)throws Exception{
            Equipo eq = new Equipo(ref, nombre);
            EquipoBD.borrarEquipo(eq);
        }
        
        public static void modificarEquipo(String ref, String nombre) throws Exception{
            Equipo eq = new Equipo(ref, nombre);
            EquipoBD.actualizarEquipo(eq);
        }
        
        public static Equipo buscarEquipoRef(String ref)throws Exception{
            return EquipoBD.buscarEquipo(ref);
        }
        
        public static void toVEquipo(String mensaje){
            vE.mostrarMensaje(mensaje);
        }
        // </editor-fold>
    public static void generarCalendario(ArrayList <Equipo> e){//añadir arraylist de equipos en parametros
        /*
        Funcion basada en el algoritmo 
        round robin para la conmutacion a pares
        */
        Equipo eq = new Equipo();
        
        ArrayList <Equipo> locales = new ArrayList <Equipo>();//CAMBIAR POR VALORES QUE VENGAN DE BBDD Y PASARLOS COMO PARAMETRO EN ARRAYLIST
        //CAMBIAR VALORES DE STRING POR EL OBJETO CORRESPONDIENTE
        ArrayList<Equipo> visitantes = new ArrayList <Equipo>();
        locales.add("A");
        locales.add("B");
        locales.add("C");
        locales.add("D");
        locales.add("E");
        locales.add("F");
        locales.add("G");
        locales.add("H");

       
       
        if(locales.size()%2!=0){
            locales.add(eq);
        }
        int jornadas=locales.size()-1;
        int mitad=(locales.size()/2)-1;
        int contador=0;
        for(int i=locales.size()-1;i>mitad;i--){
            visitantes.add(locales.get(i));
            locales.remove(i);
        }
        
            String fijo=locales.get(0);
            locales.remove(0);
        for(int y=0;y<jornadas*2;y++){
            System.out.println("----JORNADA "+(y +1) +"---------- ");
            System.out.println(fijo+"-"+visitantes.get(0));
            contador++;
            for(int x=0;x<locales.size();x++){
                System.out.println(locales.get(x)+"-"+visitantes.get(x+1));
                contador++;
            }
            locales.add(0,visitantes.get(0));
            visitantes.remove(0);
            visitantes.add(locales.get(locales.size()-1));
            locales.remove(locales.size()-1);
        }        
        System.out.println(contador);//si contador es igua a n!/(n-2)!*2 es que todo va bien
    }
    
    
    // </editor-fold>
    
}
