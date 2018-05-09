/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.Equipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Aitor Alday
 */
public class EquipoBD {

    private static Connection con;
    
    public static void insertarEquipo(Equipo eq) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("insert into Equipo(referencia, nombre) values (?, ?)");
            sentencia.setString(1, eq.getRef());
            sentencia.setString(2, eq.getNombre());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            
        }
    }
    
    public static void actualizarEquipo(Equipo eq) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
         try{
            PreparedStatement sentencia = con.prepareStatement("update Equipo set referencia=?, nombre=?");
            sentencia.setString(1, eq.getRef());
            sentencia.setString(2, eq.getNombre());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            
        }
    }
    
    public static void borrarEquipo(Equipo eq) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try {
            PreparedStatement sentencia = con.prepareStatement("delete from Equipo where referencia=?");
            sentencia.setString(1, eq.getRef());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch (Exception e) {
            
        }
    }
    
    public static Equipo buscarEquipo (String ref) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("select * from Persona where referencia=?");
            sentencia.setString(1, ref);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()){
                Equipo eq = new Equipo(resultado.getString(2), resultado.getString(3)); //Para recoger la informacion de la base y crear un objeto con ella
                con.close();
                return eq;
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
