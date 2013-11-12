/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

/**
 *
 * @author esteve
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
    static final String driver = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost/mydb";
    static final String usr = "root";
    static final String pass = "root";
    
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, usr, pass);
    }
    
    
    public static ResultSet query(String q) throws ClassNotFoundException, SQLException {
        Connection cnt = getConnection();
        Statement st = cnt.createStatement();
        ResultSet rs = st.executeQuery(q);
        st.close();
        cnt.close();
        return rs;
    }
    
    public static int updates(String up) throws ClassNotFoundException, SQLException {
        Connection cnt = getConnection();
        Statement st = cnt.createStatement();
        st.executeUpdate(up, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.executeQuery("select LAST_INSERT_ID()");
        int lastID = 1;
        if(rs.next())
            lastID = rs.getInt("LAST_INSERT_ID()");
        st.close();
        cnt.close();
        return lastID;
    }
}
