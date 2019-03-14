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


@Named(value = "Op")
@SessionScoped

public class Opinions implements Serializable{
    
    private String nickname;
    private String opinion;
    
public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the opinion
     */
    public String getOpinion() {
        return opinion;
    }

    /**
     * @param opinion the opinion to set
     */
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

   
    public ResultSet getOP() throws SQLException {
        Connection connect = DBConnection.DBAc();
        PreparedStatement showEntry = connect.prepareStatement("SELECT * FROM OP");
        CachedRowSet rs= new com.sun.rowset.CachedRowSetImpl();
        rs.populate(showEntry.executeQuery());
        return rs;
   
    }
    
   
    public void Insert() throws SQLException {
        
        Connection connect = DBConnection.DBAc();
        PreparedStatement addEntry = connect.prepareStatement("INSERT INTO OP (USERNAME,OPINION)"
                + "VALUES (?, ?)");        
        
        addEntry.setString(1, getNickname());
        addEntry.setString(2, getOpinion());
        
        addEntry.executeUpdate();
        
        
        
        
        
        
        connect.close();
        
    }

}
