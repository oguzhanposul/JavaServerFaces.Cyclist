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
import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import server.Login;


@Named(value = "perf")
@SessionScoped

public class Performance implements Serializable{

   
    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    
    String username;
    float distance;
    float weight;
    float time;
    float calories;
    
       
   
    
    
   
    
    
    
   
     public void Insert() throws SQLException {
         
          Connection connect = DBConnection.DBAc();
       PreparedStatement ps = connect.prepareStatement("SELECT USERNAME, DISTANCE, TIME, WEIGHT, CALORIES FROM PERF");
       CachedRowSet rs= new com.sun.rowset.CachedRowSetImpl();
       String dbUsername;
       float dbCalori;
       float dbDistance;
       float dbTime;
       float dbWeight;
       float time1 = getTime();
         float weight1 = getWeight();
         float distance1 = getDistance();
         float hiz = distance1 / (time1/60);
         float calories1 =  hiz * weight1;
         setCalories(calories1);
         rs.populate(ps.executeQuery());
         
       while(rs.next()){
           dbUsername = rs.getString("USERNAME").toString();
           if (getUsername().equals(dbUsername)){
               dbCalori = rs.getFloat("CALORIES");
               setCalories(getCalories() + dbCalori);
               dbDistance = rs.getFloat("DISTANCE");
               dbTime = rs.getFloat("TIME");
               dbWeight = rs.getFloat("WEIGHT");
               setDistance(getDistance() + dbDistance);
               setTime(getTime() + dbTime);
               //setWeight(dbWeight);
               
                PreparedStatement upEntry = connect.prepareStatement("UPDATE PERF SET DISTANCE = ? , TIME = ?, CALORIES = ?, WEIGHT = ? WHERE CAST(USERNAME AS VARCHAR(128)) = ?");
                
                upEntry.setFloat(1, getDistance());
                upEntry.setFloat(2, getTime());
                upEntry.setFloat(3, getCalories());
                upEntry.setFloat(4, getWeight());
                upEntry.setString(5, getUsername());
                
                upEntry.executeUpdate();
                connect.close();
                return;
           }
       }  

       
        
        PreparedStatement addEntry = connect.prepareStatement("INSERT INTO PERF "
                + "(USERNAME,DISTANCE,TIME,WEIGHT,CALORIES)"
                + "VALUES ( ?, ?, ?, ?,?)");

        // specify the PreparedStatement's arguments
        addEntry.setString(1, getUsername());
        addEntry.setFloat(2, getDistance());
        addEntry.setFloat(3, getTime());
        addEntry.setFloat(4, getWeight());
        addEntry.setFloat(5, getCalories());
       
        
        addEntry.executeUpdate(); // insert the entry
        connect.close();
       
    }
  }
    
    
    

