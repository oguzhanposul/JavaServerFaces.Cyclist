package server;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Named(value = "user")
@RequestScoped
public class User {

    String name;
    String surname;
    String password;
    String email;
    String nickname;
    String note;
    String address;
    String gsm;
   
    
   
    
    
    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String notlar) {
        this.note = notlar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String Insert() throws SQLException {
        Connection connect = DBConnection.DBAc();
        PreparedStatement addEntry = connect.prepareStatement("INSERT INTO UYELER "
                + "(NAME,SURNAME,PASSWORD,EMAIL,USERNAME,ADDRESS,GSM)"
                + "VALUES ( ?, ?, ?, ?,? ,?,?)");

        // specify the PreparedStatement's arguments
        addEntry.setString(1, getName());
        addEntry.setString(2, getSurname());
        addEntry.setString(3, getPassword());
        addEntry.setString(4, getEmail());
        addEntry.setString(5, getNickname());
        addEntry.setString(6, getAddress());
        addEntry.setString(7, getGsm());
        
        addEntry.executeUpdate(); // insert the entry
        connect.close();
        return "index";
    }
     
}
