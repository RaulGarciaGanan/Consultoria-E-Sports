/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            PreparedStatement sentencia = con.prepareStatement("insert into Jugador(dni, nickname, nombre, sueldo, id_equipo) values (?,?,?,?,?)");
            sentencia.setString(1, j.getDni());
            sentencia.setString(2, j.getNick());
            sentencia.setString(3, j.getNombre());
            sentencia.setDouble(4, j.getSueldo());
            sentencia.setInt(5, j.getEquipo().getIdEquipo());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            System.out.println("ERRor");
        }
    }
    
    public static void actualizarJugador(Jugador j)throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
         try{
            PreparedStatement sentencia = con.prepareStatement("update Jugador set id_jugador=?, dni=? nickname=? nombre=?, sueldo=? where id_jugador=?");
            sentencia.setInt(1, j.getIdJugador());
            sentencia.setString(2, j.getDni());
            sentencia.setString(3, j.getNick());
            sentencia.setString(4, j.getNombre());
            sentencia.setDouble(5, j.getSueldo());
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
            PreparedStatement sentencia = con.prepareStatement("delete from Jugador where id_jugador=?");
            sentencia.setInt(1, j.getIdJugador());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch (Exception e) {
            
        }
    }

    public static Jugador buscarJugador(String dni) throws Exception{
       GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("select * from Jugador where dni=?");
            sentencia.setString(1, dni);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()){
                Equipo e= proyecto.Proyecto.buscarEquipoId(Integer.toString(resultado.getInt("ID_EQUIPO")));
                Jugador j = new Jugador(resultado.getInt(1),resultado.getString(2),resultado.getString(3), resultado.getString(4), resultado.getDouble(5), e); 
                //Para recoger la informacion de la base y crear un objeto con ella
                con.close();
                return j;
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
