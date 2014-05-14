/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author esteve
 */
public class PassManagerCAD {
    
    private static HashMap toHashMap(ResultSet rs) throws SQLException {
        HashMap info = new HashMap();
        if(rs.next()){
            info.put("id", rs.getString("id"));
            info.put("user", rs.getInt("usuario"));
            info.put("date", new Date(rs.getTimestamp("fecha").getTime()));
        }
        return info;
    }
    
    public static String genRandString(int length){
        Random rand = new Random(System.currentTimeMillis());
        String randString = "";
        for(int i = 0; i < length; i++){
            switch(rand.nextInt(3)){
                case 0: randString += (char)(rand.nextInt('z' - 'a') + 'a'); break;
                case 1: randString += (char)(rand.nextInt('Z' - 'A') + 'A'); break;
                case 2: randString += (char)(rand.nextInt('9' - '0') + '0'); break;
            }
        }
        return randString;
    }
    
    public static HashMap getByID(String id) throws Exception{
        HashMap info = null;
        String query = "SELECT * FROM RecPass WHERE id = '" + id + "'";
        ResultSet rs = Connector.query(query);
        info = toHashMap(rs);
        Connector.close(rs);
        return info;
    }
    
    public static  HashMap getByUser(int user) throws Exception {
        HashMap info = null;
        String query = "SELECT * FROM RecPass WHERE usuario = " + user;
        ResultSet rs = Connector.query(query);
        info = toHashMap(rs);
        Connector.close(rs);
        return info;
    }
    
    public static void deleteExpired() throws Exception {
        String query = "DELETE FROM RecPass WHERE date_add(fecha, interval 1 day) < NOW(); ";
        Connector.updates(query);
    }
    
    public static String insert(int user) throws Exception {
        String id;
        do{
            id = genRandString(16);
        }while(!(getByID(id).isEmpty()));
        String query = "INSERT INTO RecPass (id, usuario) VALUES ('"+ id +"', " + user + ");";
        Connector.updates(query);
        return id;
    }
    
    public static void delete(String id) throws Exception {
        String query = "DELETE FROM RecPass WHERE id = '" + id + "'";
        Connector.updates(query);
    }
}
