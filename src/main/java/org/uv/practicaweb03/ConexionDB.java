
package org.uv.practicaweb03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionDB {
    
    private static  ConexionDB cx =null;
    
    public static ConexionDB getInstance(){
        if (cx==null){
            cx = new ConexionDB();
        }
        return cx;
    }
    
    private String uri = "jdbc:postgresql://localhost:5432/ejemplo";
    private Connection con = null;
    
    private ConexionDB(){
        try{
            Class.forName("org.postgresql.Driver"); 
            con = DriverManager.getConnection(uri, "postgres", "1234");
            Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO, "SE CONECTO");
        }catch (ClassNotFoundException | SQLException ex){
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean execute(String sql){
        Statement st = null;
        try{
            st = con.createStatement();
            st.execute(sql);
            return true;
        }catch(SQLException ex){
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean execute(TransactionDB t){
        return t.execute(con);
    }
    
    public ResultSet query(String sql){
        Statement st = null;
        try{
            st = con.createStatement();
            return st.executeQuery(sql);
        } catch(SQLException ex){
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}