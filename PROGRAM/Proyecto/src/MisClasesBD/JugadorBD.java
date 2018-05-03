/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.*;
import java.sql.PreparedStatement;

/**
 *
 * @author Aitor Alday
 */
public class JugadorBD {
    
    private static GenericoBD gbd;
    
    public static void insertarPersona(Jugador j, String tipo){
        try{
            gbd = new GenericoBD();
            PreparedStatement sentencia = gbd.abrirConexion().prepareStatement("insert into Jugador values (?,?,?,?,?)");
            sentencia.setInt(1, j.getIdJugador());
            sentencia.setString(2, j.getNombre());
            sentencia.setString(3, j.getNick());
            sentencia.setDouble(4, j.getSueldo());
            
            gbd.cerrarConexion();
        }
        catch(Exception e){
            
        }
    }
}
