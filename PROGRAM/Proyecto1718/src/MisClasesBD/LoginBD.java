/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.Login;
import MisClases.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import proyecto.Proyecto;

/**
 *
 * @author Aitor Alday
 */
public class LoginBD {
    
    private static Connection con;
    
    
    public static Persona logearUsuario(Login login) {
        //Utilizamos unuario y nombre para logear a un usuario.
         Persona user =new Persona();
        try {
            GenericoBD gbd = new GenericoBD();
            con=gbd.abrirConexion(con);
            String nombre = login.getUsuario();
            String contrasenya = login.getContrasena();
            PreparedStatement sentencia = gbd.abrirConexion(con).prepareStatement("SELECT id_persona, nombre, usuario, contrasena, tipo, id_equipo FROM persona WHERE usuario=? AND contrasena=?");
            sentencia.setString(1, nombre);
            sentencia.setString(2, contrasenya);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {                
                 user.setIdPersona(resultado.getInt("id_persona"));
                 user.setNombre(resultado.getString("nombre"));
                 user.setTipo( resultado.getInt("tipo"));
            }
            gbd.cerrarConexion(con);
            return user;
        } 
        catch (Exception e) {
            Proyecto.toDLogin("Problemas en logearUsuario , en LoginBD: " + e.getMessage());
            return user;
        }
    }
        
    public static String obtenerNombre(String nombre, String contrasenya) throws Exception {
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        //Para saludar debidamente la persona, utilizamos una select con una join para saber como se llama esa persona. Utilizamos su usuario y contraseña
        try {
            gbd = new GenericoBD();
            nombre = nombre.toLowerCase();
            contrasenya = contrasenya.toLowerCase();
            String a = "No se ha encontrado el usuario";
            PreparedStatement sentencia = con.prepareStatement("select p.nombre from Login l, Persona p where p.id_persona=l.id_persona and lower(l.usuario)=? and lower(contraseña)=?");
            sentencia.setString(1, nombre);
            sentencia.setString(2, contrasenya);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                a = resultado.getString("nombre");
            }
            
            con.close();
            return a;
        } 
        catch (Exception e) {
            Proyecto.toDLogin("Problemas en obtenerNombre, en LoginBD: " + e.getMessage());
            return "Error";
        }
    }
    
    public static void crearLogin (String nombre, Integer tipo) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        //Cada vez que creamos a una persona, creamos un login para este. Se compone de su primera letra del nombre y su tipo.
        try {
            String cadena = nombre.charAt(0) + tipo.toString();
            gbd = new GenericoBD();
            Statement sentencia = con.createStatement();
            ResultSet resultado = sentencia.executeQuery("select * from Login");
            PreparedStatement ps = con.prepareStatement("insert into login values (?,?,?)");
            ps.setString(1, cadena);
            ps.setString(2, cadena);
            ps.executeUpdate();
            
            Proyecto.toVPersona("Login generado.\n Usuario :"+cadena+"\nContraseña: "+cadena);
            
            con.close();
        } 
        catch (Exception e) {
            Proyecto.toVPersona("Problemas en crearLogin, en LoginBD: " + e.getMessage());
        }
    }
    
    public static void borrarLogin(String dni) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try {
            gbd = new GenericoBD();
            PreparedStatement ps = con.prepareStatement("delete from login where dni=?");
            ps.setString(1, dni);
            ps.executeUpdate();
            
            con.close();
        } 
        catch (Exception e) {
            Proyecto.toVPersona("Problemas en borrarLogin, en LoginBD: " + e.getMessage());
        }
    }
    
    public static void modificarLogin(String usu, String pas, String dni) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try {
            gbd = new GenericoBD();
            PreparedStatement ps = con.prepareStatement("update login set usuario=?, contraseña=? where dni=?");
            ps.setString(1, usu);
            ps.setString(2, pas);
            ps.setString(3, dni);
            ps.executeUpdate();
            
            con.close();
            Proyecto.toVPersona("Login actualizado");
        } 
        catch (Exception e) {
            Proyecto.toVPersona("Problemas en modificarLogin, en LoginBD: " + e.getMessage());
        }
    }
}
