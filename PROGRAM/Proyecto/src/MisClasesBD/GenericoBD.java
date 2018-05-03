/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Aitor Alday
 */
public class GenericoBD {
    
    private Connection con;
    
    public Connection abrirConexion()throws Exception{       
            Class.forName("oracle.jdbc.OracleDriver");

            String login="system";
            String password= "oracle";
            String url = "jdbc:oracle:thin:@SrvOracle:1521:orcl";
            con = DriverManager.getConnection(url,login ,password);
            
            return con;
    }
    
    public void cerrarConexion() throws Exception{
            con.close();
    }
}
