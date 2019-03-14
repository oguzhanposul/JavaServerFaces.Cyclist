/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


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


@Named(value = "adver")
@SessionScoped

/**
 *
 * @author Ali Oğuzhan ÇİÇEK
 */

public class Advertisement implements Serializable{
    
    String nickname;
    String whatIsit;
    String date;
    String explanation;
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWhatIsit() {
        return whatIsit;
    }

    public void setWhatIsit(String whatIsit) {
        this.whatIsit = whatIsit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    
     public ResultSet getADV() throws SQLException {
        Connection connect = DBConnection.DBAc();
        PreparedStatement showEntry = connect.prepareStatement("SELECT * FROM ADV");
        CachedRowSet rs= new com.sun.rowset.CachedRowSetImpl();
        rs.populate(showEntry.executeQuery());
        return rs;
   
    }
     public void Insert() throws SQLException {
        
        Connection connect = DBConnection.DBAc();
        PreparedStatement addEntry = connect.prepareStatement("INSERT INTO ADV (USERNAME, DATE, WHATISIT, EXPLANATION)"
                + "VALUES (?, ?, ?, ?)");        
        
        addEntry.setString(1, getNickname());
        addEntry.setString(2, getDate());
        addEntry.setString(3, getWhatIsit());
        addEntry.setString(4, getExplanation());
        
        
        addEntry.executeUpdate();
        
        
        
        
        
        
        connect.close();
        
    }
}
