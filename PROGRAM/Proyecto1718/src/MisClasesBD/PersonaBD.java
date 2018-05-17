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
    
    public static void insertarPersona(Persona p) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("insert into Persona(dni, nombre, tipo) values (?, ?, ?)");
            sentencia.setString(1, p.getDni());
            sentencia.setString(2, p.getNombre());
            sentencia.setInt(3, p.getTipo());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void actualizarPersona(Persona p) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
         try{
            PreparedStatement sentencia = con.prepareStatement("update Persona set dni=?, nombre=?, tipo=?, where dni=?");
            sentencia.setString(1, p.getDni());
            sentencia.setString(2, p.getNombre());
            sentencia.setInt(3, p.getTipo());
            sentencia.setString(4, p.getDni());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void borrarPersona(Persona p) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try {
            PreparedStatement sentencia = con.prepareStatement("delete from Persona where dni=?");
            sentencia.setString(1, p.getDni());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Persona buscarPersona (String dni) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("select * from Persona where dni=?");
            sentencia.setString(1, dni);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()){
                Persona p = new Persona(resultado.getString(2),resultado.getString(3), resultado.getInt(4)); //Para recoger la informacion de la base y crear un objeto con ella
                con.close();
                return p;
            }
            else{
                con.close();
                return null;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
