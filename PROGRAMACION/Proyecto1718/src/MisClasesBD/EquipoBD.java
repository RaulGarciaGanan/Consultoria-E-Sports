/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClases.Equipo;
import MisClases.Jugador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

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
            System.out.println(e.getMessage());
        }
    }
    
    public static void actualizarEquipo(Equipo eq) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
         try{
            PreparedStatement sentencia = con.prepareStatement("update Equipo set referencia=?, nombre=? where referencia=?");
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
            System.out.println(e.getMessage());
        }
    }
    
    public static Equipo buscarEquipo (String ref) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("select * from Equipo where referencia=?");
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
    
    public static Equipo buscarEquipoId(String id) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("select * from Equipo where id_equipo=?");
            sentencia.setString(1, id);
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


    public static ArrayList buscarParaCb() throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("select * from Equipo");
            ResultSet resultado = sentencia.executeQuery();
            ArrayList <Equipo> lista = new ArrayList();
            
            while(resultado.next()){
                Equipo e = new Equipo();
                e.setIdEquipo(resultado.getInt(1));
                e.setRef(resultado.getString(2));
                e.setNombre(resultado.getString(3));
                lista.add(e);
            }
            return lista;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static Equipo buscarEquipoPorNombre(String nombre) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("select * from Equipo where nombre=?");
            sentencia.setString(1, nombre);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()){
                Equipo eq = new Equipo(resultado.getString(3)); //Para recoger la informacion de la base y crear un objeto con ella
                eq.setIdEquipo(resultado.getInt(1));
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
    
    public static ArrayList buscarEquipoDueño() throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        
        ArrayList <Jugador> listaJu = new ArrayList();
        try{
            CallableStatement cls = con.prepareCall("{CALL PaqueteBuscarEquipo.buscarJugadoresEquipo(?)}");
            
            cls.registerOutParameter(1, OracleTypes.CURSOR);
            cls.execute();
            
            ResultSet rs = (ResultSet) cls.getObject(1);
            
            if(rs.next()){
                do{
                   Jugador j = new Jugador();
                   j.setNombre(rs.getString("nombre"));
                   
                   listaJu.add(j);
                }
                while(rs.next());  
            }
            else{
                System.out.println("No hay jugadores");
            }
            con.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        con.close();
        return listaJu;
    }
}
