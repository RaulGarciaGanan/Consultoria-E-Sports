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
import java.util.ArrayList;

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
            PreparedStatement sentencia = con.prepareStatement("insert into Jugador(dni, nickname, nombre, sueldo) values (?,?,?,?)");
            sentencia.setString(1, j.getDni());
            sentencia.setString(2, j.getNick());
            sentencia.setString(3, j.getNombre());
            sentencia.setDouble(4, j.getSueldo());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void actualizarJugador(Jugador j)throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
         try{
            PreparedStatement sentencia = con.prepareStatement("update Jugador set nickname=?, nombre=?, sueldo=? where dni=?");
            sentencia.setString(1, j.getNick());
            sentencia.setString(2, j.getNombre());
            sentencia.setDouble(3, j.getSueldo());
            sentencia.setString(4, j.getDni());
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
            PreparedStatement sentencia = con.prepareStatement("delete from Jugador where dni=?");
            sentencia.setString(1, j.getDni());
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
                Equipo e= proyecto.Proyecto.buscarEquipoId(Integer.toString(resultado.getInt(1)));
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
    
    public static void dueñoMeteJugador(Jugador j) throws Exception {
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("update Jugador set id_equipo=? where nombre=?");
            sentencia.setInt(1, j.getEquipo().getIdEquipo());//Equipo
            sentencia.setString(2, j.getNombre());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void dueñoSacaJugador(Jugador j) throws Exception {
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("update Jugador set id_equipo=null where nombre=?");           
            sentencia.setString(1, j.getNombre());
            sentencia.executeUpdate();
            
            con.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /*public static ArrayList buscarParaListaSacar() throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("SELECT j.* FROM Jugador j, Equipo e WHERE j.id_equipo=e.id_equipo AND e.nombre=?");
            ResultSet resultado = sentencia.executeQuery();
            ArrayList <Jugador> lista = new ArrayList();
            
            while(resultado.next()){
                Jugador j = new Jugador();
                j.setIdJugador(resultado.getInt(1));
                j.setDni(resultado.getString(2));
                j.setNick(resultado.getString(3));
                j.setNombre(resultado.getString(4));
                j.setSueldo(resultado.getDouble(5));
                lista.add(j);
            }
            return lista;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }*/

    public static ArrayList buscarParaLista() throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("select * from Jugador where id_equipo is not null");
            ResultSet resultado = sentencia.executeQuery();
            ArrayList <Jugador> lista = new ArrayList();
            
            while(resultado.next()){
                Jugador j = new Jugador();
                j.setIdJugador(resultado.getInt(1));
                j.setDni(resultado.getString(2));
                j.setNick(resultado.getString(3));
                j.setNombre(resultado.getString(4));
                j.setSueldo(resultado.getDouble(5));
                lista.add(j);
            }
            return lista;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static ArrayList buscarParaListaMeter() throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("select * from Jugador where id_equipo is null");
            ResultSet resultado = sentencia.executeQuery();
            ArrayList <Jugador> lista = new ArrayList();
            
            while(resultado.next()){
                Jugador j = new Jugador();
                j.setIdJugador(resultado.getInt(1));
                j.setDni(resultado.getString(2));
                j.setNick(resultado.getString(3));
                j.setNombre(resultado.getString(4));
                j.setSueldo(resultado.getDouble(5));
                lista.add(j);
            }
            return lista;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static Jugador buscarJugadorPorNombre(String nombre) throws Exception{
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("select * from Jugador where nombre=?");
            sentencia.setString(1, nombre);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()){
                Jugador j = new Jugador(); //Para recoger la informacion de la base y crear un objeto con ella
                j.setDni(resultado.getString(2));
                j.setNick(resultado.getString(3));
                j.setNombre(resultado.getString(4));
                j.setSueldo(resultado.getDouble(5));
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
