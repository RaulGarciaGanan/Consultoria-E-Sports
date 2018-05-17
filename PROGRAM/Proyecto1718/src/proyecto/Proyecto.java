/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import javax.swing.JOptionPane;
import MisVentanas.*;
import MisClases.*;
import MisClasesBD.*;
import static MisVentanas.VJugador.e;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
/**
 *
 * @author Aitor Alday
 */
public class Proyecto {

    public static DLogin dL;
    public static VPrincipal vP;
    public static VPrincipal vP1;
    public static VEquipos vE;
    public static VEquiposDu vE2;
    public static VJugador vJ;
    public static VPersonas vPer;
    
    public static Persona per;
    
    public static ArrayList <Equipo> listaEq;
    public static ArrayList <Jugador> listaJu;
    
    public static void main(String[] args) {
        iniciarProyecto();
    }
    
    public static void cerrarProyecto() {
        System.exit(0);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Abrir ventanas">
    public static void iniciarProyecto(){
        vP = new VPrincipal();
        vP.setVisible(true);
        vP.setLocationRelativeTo(null);
        
        dL = new DLogin(vP, true);
        dL.setVisible(true);
    }
    
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
    
    public static void abrirEquipoDueño(){
        vE2 = new VEquiposDu();
        vE2.setVisible(true);
        vE2.setLocationRelativeTo(null);
    }
    
    public static void abrirPrincipal(){
        vP = new VPrincipal();
        vP.setVisible(true);
        vP.setLocationRelativeTo(vP);
    }
    
    public static void abrirPrincipal(int tipo){
        vP1 = new VPrincipal(tipo);
        vP1.setVisible(true);
        vP1.setLocationRelativeTo(null);
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
        Login l = new Login(usuario, contra);
        LoginBD lo = new LoginBD();
        
        Persona user = lo.logearUsuario(l);
        
        if(user.getIdPersona() != null){
           JOptionPane.showMessageDialog(null, "Usuario logeado: " + user.getNombre() + user.getTipo()); 
           
            abrirPrincipal(user.getTipo());
            dL.dispose();
            vP.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Usuario incorrecto");
            dL.contaErrores();
        }
          
    }
    
    public static void cerrarLogin() {
       System.exit(0);
    }
    /*ESTE ES EL INICIO DE LOGIN DEL ADMINISTRADOR, QUE LO TENDRÁ TODO ACTIVADO Y HAY QUE INICIAR EL PROGRAMA POR
    PRIMERA VEZ PARA CREAR UN ADMINISTRADOR; DUEÑO Y USUARIO*/
    public static void inicioRoot() {
        vP = new VPrincipal();
        vP.setVisible(true);
        vP.setLocationRelativeTo(null);
        
        dL.dispose();
        vP.dispose();
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
            Jugador j = new Jugador( dni, nick, nombre, sueldo);
            JugadorBD.borrarJugador(j);
        }
        
        public static void modificarJugador(String dni, String nick, String nombre, Double sueldo) throws Exception{
            Jugador j = new Jugador(dni, nick, nombre, sueldo);
            JugadorBD.actualizarJugador(j);
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
        
        public static Equipo buscarEquipoId(String id)throws Exception{
            return EquipoBD.buscarEquipoId(id);
        }
        
        public static void toVEquipo(String mensaje){
            vE.mostrarMensaje(mensaje);
        }
        
        public static Integer buscarEquipoEditarJugador(Jugador j) throws Exception{
            for(int k =0;k<listaEq.size();k++){
                if(listaEq.get(k).getNombre().equalsIgnoreCase(j.getEquipo().getNombre())){
                    return k;
                }
            }
            return null;
        }
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="LIGA">
        
        /*public static void iniciarParse() {
            try {
            DOMParserInforme.runExample(generarCalendario());
            } catch (Exception e) {
                toVPersona("Error mostrando partes: "+e.getMessage());
            }
        }*/
        
        public static void generarCalendario(ArrayList <Equipo> e){
            try{
            /*Funcion basada en el algoritmo Round Robin para la conmutacion a pares*/
            JornadaBD jb =new JornadaBD();
            PartidoBD pb =new PartidoBD();
            Equipo eq = new Equipo();
            Date fecha=new Date(1,1,1);
            
            ArrayList <Equipo> locales = new ArrayList <Equipo> ();
            ArrayList <Equipo> visitantes = new ArrayList <Equipo> ();
            
            
            
            if(locales.size() % 2 != 0){
                locales.add(eq);
            }
            
            int jornadas = locales.size() - 1;
            int mitad = (locales.size() / 2) - 1;
            int contador = 0;
            
            for(int x =locales.size() - 1; x>mitad; x--){
                visitantes.add(locales.get(x));
                locales.remove(x);
            }
            
            Equipo fijo = locales.get(0);
            locales.remove(0);
            
            for(int y=0;y<jornadas*2;y++){
                System.out.println("----Jornada " + (y + 1) + "----");
                Jornada jor=new Jornada();
                jor.setFecIni(fecha);
                jor.setFecFin(fecha);
                int idjornada=jb.insertarJornada(jor);
                jor.setIdJornada(idjornada);
                Partido p =new Partido();
                p.setFecha(fecha);
                p.setLocal(fijo);
                p.setVisitante(visitantes.get(0));
                p.setJornada(jor);
                pb.insertarPartido(p);                
                contador++;
                
                for(int i=0; i<locales.size(); i++){
                    Partido par =new Partido();
                    par.setFecha(fecha);
                    par.setLocal(locales.get(i));
                    par.setVisitante(visitantes.get(i+1));
                    par.setJornada(jor);
                    pb.insertarPartido(par);  
                    
                    contador ++;
                }
                
                locales.add(0, visitantes.get(0));
                visitantes.remove(0);
                visitantes.add(locales.get(locales.size() - 1));
                locales.remove(locales.size() - 1);
            }
            
            System.out.println(contador);
            
            }
            catch(Exception ex){
            
            }
            
        }
        // </editor-fold>
        
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc="Para dueño">  
        
        // <editor-fold defaultstate="collapsed" desc="Gestion de equipos">   
            public static void dueñoCreaEquipo(String eq, String nombre) throws Exception{
                Equipo eqq = EquipoBD.buscarEquipoPorNombre(eq);
                Jugador j = JugadorBD.buscarJugadorPorNombre(nombre);
                j.setEquipo(eqq);
                JugadorBD.dueñoCreaEquipo(j);
            }
            
            public static void buscarParaRellenar(javax.swing.JComboBox cbEquipo) throws Exception{
                listaEq = EquipoBD.buscarParaCb();
            
                for(int x = 0; x<listaEq.size(); x++){
                    cbEquipo.addItem(listaEq.get(x).getNombre());            
                }
            }
            
            public static void buscarParaRellenarJu(javax.swing.JList jlJugadores) throws Exception{
                listaJu = JugadorBD.buscarParaLista();
                DefaultListModel dlm = new DefaultListModel();
                
                for(int x = 0; x<listaJu.size(); x++){
                    dlm.add(x, listaJu.get(x).getNombre());
                }
                
                jlJugadores.setModel(dlm);
            }
        // </editor-fold>
        
    // </editor-fold>
}

