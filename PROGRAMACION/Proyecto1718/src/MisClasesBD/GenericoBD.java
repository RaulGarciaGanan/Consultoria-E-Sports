/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aitor Alday
 */
public class GenericoBD {
    
    public Connection abrirConexion(Connection con)throws SQLException{  
        try
        {
             Class.forName("oracle.jdbc.OracleDriver");
             con = DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.9:1521:db12102", "system", "oracle");
             if(con != null)
             {
                 System.out.print("Conexion establecida");
                 return con;
             }
             else
             {
                 System.out.print("No se ha podido establecer la conexion");
                 return null;
             }
        }
        catch(ClassNotFoundException | SQLException e){
            return null;
        }
    }
    
    public void cerrarConexion(Connection con) throws SQLException{
        try
        {
            System.out.print("Conexion cerrada");
            con.close();
        }
        catch(SQLException e){
            
        }
    }
}
