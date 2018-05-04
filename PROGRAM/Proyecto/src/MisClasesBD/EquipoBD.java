/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.Equipo;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Aitor Alday
 */
public class EquipoBD {

    private Connection con;
    
    
    public void insertarEquipo(Equipo eq) throws Exception{
        
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("insert into Equipo() values (?,?)");
            sentencia.setInt(1, eq.getIdEquipo());
            sentencia.setString(2, eq.getNombre());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            
        }
    }
    
    public void actualizarEquipo(Equipo eq) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
         try{
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("update Equipo set id_equipo=?, nombre=?");
            sentencia.setInt(1, eq.getIdEquipo());
            sentencia.setString(2, eq.getNombre());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            
        }
    }
    
    public void borrarEquipo(Equipo eq) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try {
            gbd = new GenericoBD();
            PreparedStatement sentencia = con.prepareStatement("delete from Equipo where id_equipo=?");
            sentencia.setInt(1, eq.getIdEquipo());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch (Exception e) {
            
        }
    }
}
