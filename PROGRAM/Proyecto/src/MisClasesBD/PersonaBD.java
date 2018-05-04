/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Aitor Alday
 */
public class PersonaBD {
    
    private static Connection con;
    
    public static void insertarPersona(Persona p, Integer tipo) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("insert into Persona values (?,?,?,?)");
            sentencia.setInt(1, p.getIdPersona());
            sentencia.setString(2, p.getNombre());
            sentencia.setInt(3, p.getTipo());
            sentencia.setInt(4, p.getEquipo().getIdEquipo());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            
        }
    }
    
    public static void actualizarPersona(Persona p) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
         try{
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("update Persona set id_persona=?, nombre=?, tipo=?, id_equipo=?");
            sentencia.setInt(1, p.getIdPersona());
            sentencia.setString(2, p.getNombre());
            sentencia.setInt(3, p.getTipo());
            sentencia.setInt(4, p.getEquipo().getIdEquipo());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            
        }
    }
    
    public static void borrarPersona(Persona p) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try {
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("delete from Persona where id_persona=?");
            sentencia.setInt(1, p.getIdPersona());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch (Exception e) {
            
        }
    }
}
