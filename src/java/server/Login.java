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
import server.Activities;

/**
 *
 * @author oguz-
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    /**
     * Creates a new instance of Login
     */
    String nickname;
    String password;
    String name;
    String surname;
    String email;
    String note;
    String address;
    String gsm;
    int cont = 0;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    String alert = "Giriş başarısız.";

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logInCheck() throws SQLException {
        Connection connect = DBConnection.DBAc();

        PreparedStatement ps = connect.prepareStatement("SELECT * FROM UYELER ");
        CachedRowSet rs = new com.sun.rowset.CachedRowSetImpl();
        rs.populate(ps.executeQuery());
        String dbUsername = "a";
        String dbPassword = "a";
        String dbName;
        String dbSurname;
        String dbEmail;
        String dbAddress;
        String dbGsm;
        while (rs.next()) {
            dbUsername = rs.getString("USERNAME").toString();
            dbPassword = rs.getString("PASSWORD").toString();
            dbName = rs.getString("NAME").toString();
            dbSurname = rs.getString("SURNAME").toString();
            dbEmail = rs.getString("EMAIL").toString();
            dbAddress = rs.getString("ADDRESS").toString();
            dbGsm = rs.getString("GSM").toString();

            if (dbUsername.equals(getNickname()) && dbPassword.equals(getPassword())) {
                setNickname(dbUsername);
                setName(dbName);
                setSurname(dbSurname);
                setEmail(dbEmail);
                setAddress(dbAddress);
                setGsm(dbGsm);
                return "mainPage";
            }

        }
        return "index";
    }

    public String EditProfile() throws SQLException {
        Connection connect = DBConnection.DBAc();
        PreparedStatement ps = connect.prepareStatement("UPDATE UYELER SET PASSWORD=?, NAME=?, SURNAME=?, EMAIL=?,"
                + " ADDRESS=?, GSM=? WHERE CAST(USERNAME AS VARCHAR(128)) = ? ");
        ps.setString(1, getPassword());
        ps.setString(2, getName());
        ps.setString(3, getSurname());
        ps.setString(4, getEmail());
        ps.setString(5, getAddress());
        ps.setString(6, getGsm());
        ps.setString(7, getNickname()); 
        

        ps.executeUpdate();

        DBConnection.DBKapa();
        return "mainPage";
    }

}
