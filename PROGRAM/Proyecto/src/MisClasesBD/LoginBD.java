/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

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
    
    public static String logearUsuario(String nombre, String contrasenya) throws Exception {
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        //Utilizamos unuario y nombre para logear a un usuario.
        try {
            gbd = new GenericoBD();
            nombre = nombre.toLowerCase();
            contrasenya = contrasenya.toLowerCase();
            String a = "No se ha encontrado el usuario";
            PreparedStatement sentencia = con.prepareStatement("select p.tipo from Login l, Persona p where p.id_persona=l.id_persona and lower(l.usuario)=? and lower(contraseña)=?");
            sentencia.setString(1, nombre);
            sentencia.setString(2, contrasenya);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                a = resultado.getString("tipo");   
            }
            con.close();
            return a;
        } 
        catch (Exception e) {
            Proyecto.toDLogin("Problemas en logearUsuario, en LoginBD: " + e.getMessage());
            return "Error";
        }
    }
    
    public static String obtenerNombre(String nombre, String contrasenya) throws Exception {
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        //Para saludar debidamente el trabajador, utilizamos una select con una join para saber como se llama el Trabajador. Utilizamos su usuario y contraseña
        try {
            gbd = new GenericoBD();
            nombre = nombre.toLowerCase();
            contrasenya = contrasenya.toLowerCase();
            String a = "No se ha encontrado el usuario";
            PreparedStatement sentencia = con.prepareStatement("select t.nombre from Login l, Trabajador t where t.dni=l.dni and lower(l.usuario)=? and lower(contraseña)=?");
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
    
    public static void crearLogin (String nombre, Integer tipo, Integer id) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        //Cada vez que creamos a un trabajador, creamos un login para este. Se compone de su primera letra del nombre y su tipo.
        //En caso de que el usuario exista tambien usaremos su primer caracter del id.
        try {
            String cadena = nombre.charAt(0) + tipo.toString();
            gbd = new GenericoBD();
            Statement sentencia = con.createStatement();
            ResultSet resultado = sentencia.executeQuery("select * from Login");
            while (resultado.next()) {
                if (cadena.equalsIgnoreCase(resultado.getString("usuario")))
                    cadena = cadena+ id.toString().charAt(0);
            }
            PreparedStatement ps = con.prepareStatement("insert into login values (?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, cadena);
            ps.setString(3, cadena);
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
