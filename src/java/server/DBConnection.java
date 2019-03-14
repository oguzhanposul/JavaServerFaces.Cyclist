
package server;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
private static Connection connection;
    public static Connection DBAc() throws SQLException {
        /*DataSource dataSource = null;
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc:derby://localhost:1527/MyDB");
        } catch (NamingException e) {
            e.printStackTrace();
        } */

        /*if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        } */
        //connection = dataSource.getConnection();
        
        //jdbc:derby://localhost:1527/TeamRaptor
        
        Properties info = new Properties();
        info.put("user", "teamraptor");
        info.put("password", "teamraptor");
        info.put("charSet", "utf-8");
        connection =DriverManager.getConnection("jdbc:derby://localhost:1527/TeamRaptor",info);
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        return connection;
    }
    public static void DBKapa() throws SQLException {
        connection.close();
    }

}
