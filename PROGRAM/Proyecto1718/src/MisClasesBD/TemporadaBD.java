/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.Temporada;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author Aitor Alday
 */
public class TemporadaBD {
    
    private static Connection con;
    
    public static void insertarTemporada(Temporada t) throws Exception{
       GenericoBD gbd = new GenericoBD();
       con = gbd.abrirConexion(con);
       try{
           PreparedStatement sentencia = con.prepareStatement("insert into Temporada(año) values (?)");
           sentencia.setInt(1, t.getAnio());
           
           sentencia.executeUpdate();

           con.close();
       }
       catch(Exception e){

       }
    }
}
