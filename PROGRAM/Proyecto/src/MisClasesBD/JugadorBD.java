/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Aitor Alday
 */
public class JugadorBD {
    
    private static Connection con;
    
    public static void insertarJugador(Jugador j) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("insert into Jugador values (?,?,?,?)");
            sentencia.setInt(1, j.getIdJugador());
            sentencia.setString(2, j.getNick());
            sentencia.setString(3, j.getNombre());
            sentencia.setDouble(4, j.getSueldo());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            
        }
    }
    
    public static void actualizarJugador(Jugador j)throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
         try{
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("update Jugador set id_jugador=?, nickname=? nombre=?, sueldo=?");
            sentencia.setInt(1, j.getIdJugador());
            sentencia.setString(2, j.getNick());
            sentencia.setString(3, j.getNombre());
            sentencia.setDouble(4, j.getSueldo());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            
        }
    }
    
    public static void borrarJugador(Jugador j) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try {
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("delete from Jugador where id_jugador=?");
            sentencia.setInt(1, j.getIdJugador());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch (Exception e) {
            
        }
    }
}
