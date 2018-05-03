/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Aitor Alday
 */
public class PersonaBD {
    
    private static GenericoBD gbd;
    
    public static void insertarPersona(Persona p, Integer tipo){
        try{
            gbd = new GenericoBD();
            PreparedStatement sentencia = gbd.abrirConexion().prepareStatement("insert into Persona values (?,?,?,?,?)");
            sentencia.setInt(1, p.getIdPersona());
            sentencia.setString(2, p.getNombre());
            sentencia.setInt(3, p.getTipo());
            sentencia.setString(4, p.getUser());
            sentencia.setString(5, p.getPass());
            
            gbd.cerrarConexion();
        }
        catch(Exception e){
            
        }
    }
    
    public static String obtenerTipo(Integer idPersona) {
        try {
            gbd = new GenericoBD();
            String a = "No se ha encontrado la persona";
            PreparedStatement sentencia = gbd.abrirConexion().prepareStatement("select * from persona where dni=?");
            sentencia.setInt(1, idPersona);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                a = resultado.getString("tipo");
            }
            
            gbd.cerrarConexion();
            return a;
        }
        catch (Exception e) {
            //ProyectoDAW.toDAdminTrabajador("Problemas en obtenerTipo, en TrabajadorBD: " + e.getMessage());
            return null;
        }
    }
    
    public static void actualizarPersona(Persona p){
         try{
            gbd = new GenericoBD();
            PreparedStatement sentencia = gbd.abrirConexion().prepareStatement("update persona set id_persona=?, nombre=?, tipo=?, usuario=?, contraseña=?");
            sentencia.setInt(1, p.getIdPersona());
            sentencia.setString(2, p.getNombre());
            sentencia.setInt(3, p.getTipo());
            sentencia.setString(4, p.getUser());
            sentencia.setString(5, p.getPass());
            
            gbd.cerrarConexion();
        }
        catch(Exception e){
            
        }
    }
    
    public static void borrarPersona(Persona p) {
        try {
            gbd = new GenericoBD();
            PreparedStatement sentencia = gbd.abrirConexion().prepareStatement("delete from persona where id_persona=?");
            sentencia.setInt(1, p.getIdPersona());
            sentencia.executeUpdate();
            
            gbd.cerrarConexion();
        }
        catch (Exception e) {
            
        }
    }
    
    public static void crearLogin (String nombre, Integer tipo, Integer idPersona) {
        //Cada vez que creamos a una parsona, creamos un login para este. Se compone de su primera letra del nombre y su tipo.
        //En caso de que el usuario exista tambien usaremos su primer numero del id
        try {
            String cadena = nombre.charAt(0)+ tipo;
            gbd = new GenericoBD();
            Statement sentencia = gbd.abrirConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("select * from Personas");
            while (resultado.next()) {
                if (cadena.equalsIgnoreCase(resultado.getString("usuario")))
                    cadena = cadena;//+ idPersona.charAt(0);
            }
            PreparedStatement ps = gbd.abrirConexion().prepareStatement("insert into login values (?,?,?)");
            ps.setInt(1, idPersona);
            ps.setString(2, cadena);
            ps.setString(3, cadena);
            ps.executeUpdate();
            Proyecto.toVAdministracion("Login generado.\n Usuario :"+cadena+"\nContraseña: "+cadena);
            gbd.cerrarConexion();
        } 
        catch (Exception e) {
            Proyecto.toVAdministracion("Problemas en crearLogin, en LoginBD: " + e.getMessage());
        }
    }
    
    public static String logearUsuario(String user, String contrasenya) {
        //Utilizamos usuario y contraseña para logear a un usuario.
        try {
            gbd = new GenericoBD();
            user = user.toLowerCase();
            contrasenya = contrasenya.toLowerCase();
            String a = "No se ha encontrado el usuario";
            PreparedStatement sentencia = gbd.abrirConexion().prepareStatement("select tipo from persona where lower(usuario)=? and lower(contraseña)=?");
            sentencia.setString(1, user);
            sentencia.setString(2, contrasenya);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                a = resultado.getString("tipo");   
            }
            gbd.cerrarConexion();
            return a;
        } 
        catch (Exception e) {
            return "Error";
        }
    }
    
}
