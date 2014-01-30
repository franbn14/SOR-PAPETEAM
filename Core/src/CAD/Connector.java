/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

/**
 *
 * @author esteve
 */
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
    static final String driver = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost/mydb";
    static final String usr = "root";
    //static final String pass = "NF8VGUD5";
    //static final String pass = "";
    //static final String pass = "root";
    static  String pass = "";
    
    private static Connection getConnection() throws ClassNotFoundException, SQLException, UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        pass = (hostname.equals("stv") && hostname.equals("alberto-PC"))? "root" : (hostname.equals("fran-PC"))? "NF8VGUD5" : "";
        Class.forName(driver);
        return DriverManager.getConnection(url, usr, pass);
    }
    
    
    public static ResultSet query(String q) throws ClassNotFoundException, SQLException, UnknownHostException {
        Connection cnt = getConnection();
        Statement st = cnt.createStatement();
        ResultSet rs = st.executeQuery(q);
        /*st.close();
        cnt.close();*/
        return rs;
    }
    
    public static int updates(String up) throws ClassNotFoundException, SQLException, UnknownHostException {
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
    
    public static void close(ResultSet rs) throws ClassNotFoundException, SQLException{
        Connection cnt = rs.getStatement().getConnection();
        rs.getStatement().close();
        cnt.close();
    }
}
