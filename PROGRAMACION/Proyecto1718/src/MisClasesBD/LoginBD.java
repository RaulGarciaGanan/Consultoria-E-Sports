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
    
    
    public static Persona logearUsuario(Login login) throws Exception{
        //Utilizamos usuario y nombre para logear a un usuario.
         Persona user =new Persona();
         GenericoBD gbd = new GenericoBD();
         con=gbd.abrirConexion(con);
        try {
            String nombre = login.getUsuario();
            String contrasenya = login.getContrasena();
            PreparedStatement sentencia = gbd.abrirConexion(con).prepareStatement("SELECT p.id_persona, p.nombre, p.tipo, p.id_equipo, l.usuario, l.contraseña FROM persona p, login l WHERE l.id_persona=p.id_persona AND usuario=? AND contraseña=?");
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
    
    public static void borrarLogin(String usu) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try {
            gbd = new GenericoBD();
            PreparedStatement ps = con.prepareStatement("delete from login where usuario=?");
            ps.setString(1, usu);
            ps.executeUpdate();
            
            con.close();
        } 
        catch (Exception e) {
            Proyecto.toVPersona("Problemas en borrarLogin, en LoginBD: " + e.getMessage());
        }
    }
}
