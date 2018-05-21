/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.Temporada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Aitor Alday
 */
public class TemporadaBD {
    
    private static Connection con;
    
    public static int insertarTemporada(Temporada t) throws Exception{
       GenericoBD gbd = new GenericoBD();
       con = gbd.abrirConexion(con);
       try{
           PreparedStatement sentencia = con.prepareStatement("insert into Temporada(año) values (?)",Statement.RETURN_GENERATED_KEYS);
           sentencia.setInt(1, t.getAnio());
           
           int id=sentencia.executeUpdate();

           con.close();
           return id;
       }
       catch(Exception e){
           return 0;

       }
    }
    
    public static Temporada selectTemp(Integer anio) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement ps = con.prepareStatement("select * from temporada where año=?");
            ps.setInt(1, anio);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Temporada t = new Temporada();
                t.setIdTemporada(rs.getInt(1));
                con.close();
                return t;
            }
            else{
                con.close();
                return null;
            }
        }
        catch(Exception e)   {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
