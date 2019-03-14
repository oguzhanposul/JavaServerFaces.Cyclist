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
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author oguz-
 */
@Named(value = "news")
@SessionScoped
public class News implements Serializable {

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
        PreparedStatement showEntry = connect.prepareStatement("SELECT * FROM NEWS");
        CachedRowSet rs= new com.sun.rowset.CachedRowSetImpl();
        rs.populate(showEntry.executeQuery());
        return rs;
   
    }
    
   
    public void Insert() throws SQLException {
        
        Connection connect = DBConnection.DBAc();
        PreparedStatement addEntry = connect.prepareStatement("INSERT INTO NEWS(BASLIK,BILGI)"
                + "VALUES (?, ?)");        
        
        addEntry.setString(1, getNickname());
        addEntry.setString(2, getOpinion());
        
        addEntry.executeUpdate();
        
        
        
        
        
        
        connect.close();
        
    }

}
