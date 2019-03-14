/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author mrcoa
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;


@Named(value = "otherUsers")
@SessionScoped
public class OtherUsers implements Serializable {

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
    String username;
    String date;
    String time;
    String address;
    String exp;
   
  
    
   
    
    public ResultSet getUsers() throws SQLException {
        Connection connect = DBConnection.DBAc();
        PreparedStatement showEntry = connect.prepareStatement("SELECT * FROM PERF");
        CachedRowSet rs= new com.sun.rowset.CachedRowSetImpl();
        rs.populate(showEntry.executeQuery());
        return rs;
   
    }
    
  
    
    
    
}
