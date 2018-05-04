/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.Partido;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Aitor Alday
 */
public class PartidoBD {
    private static Connection con;
    
    public static void insertarPartido(Partido p)throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("insert into Partido values (?,?,?,?)");
            sentencia.setInt(1, p.getIdPartido());
            sentencia.setDate(2, p.getFecha());
            sentencia.setInt(3, p.getPuntosLoc());
            sentencia.setInt(4, p.getPuntosVis());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            
        }
    }
    
    public static void actualizarPartido(Partido p)throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
         try{
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("update Persona set id_partido=?, fecha=?, puntos_loc=?, puntos_vis=?");
            sentencia.setInt(1, p.getIdPartido());
            sentencia.setDate(2, p.getFecha());
            sentencia.setInt(3, p.getPuntosLoc());
            sentencia.setInt(4, p.getPuntosVis());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            
        }
    }
    
    public static void borrarPartido(Partido p) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try {
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("delete from Partido where id_partido=?");
            sentencia.setInt(1, p.getIdPartido());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch (Exception e) {
            
        }
    }
}
