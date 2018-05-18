/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.Jornada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author Aitor Alday
 */
public class JornadaBD {
    
    private static Connection con;
    
    public static int insertarJornada(Jornada j) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        
        try{
            PreparedStatement sentencia = con.prepareStatement("insert into Jornada(id_temporada,fec_ini,fec_fin) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            sentencia.setInt(1, j.getTemporada().getIdTemporada());
            sentencia.setDate(2, j.getFecIni());
            sentencia.setDate(3, j.getFecFin());
           int id= sentencia.executeUpdate();
            
            con.close();
            return id;
        }
        catch(Exception e){
            return 0;
        }
    }
    
    public void actualizarJornada(Jornada j) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
         try{
            PreparedStatement sentencia = con.prepareStatement("update Jornada set id_jornada=?, fec_ini=?, fec_fin=?");
            sentencia.setInt(1, j.getIdJornada());
            sentencia.setDate(2, j.getFecIni());
            sentencia.setDate(3, j.getFecFin());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            
        }
    }
    
    public static void borrarJornada(Jornada j) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try {
            PreparedStatement sentencia = con.prepareStatement("delete from Jornada where id_jornada=?");
            sentencia.setInt(1, j.getIdJornada());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch (Exception e) {
            
        }
    }
}
