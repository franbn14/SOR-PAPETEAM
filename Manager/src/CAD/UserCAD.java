package CAD;

import CEN.UserCEN;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author esteve
 */

public class UserCAD{    
    protected static int create (String name, String pass, String address){
        int id = -1;
        String query = "INSERT INTO Usuario (nombre, password, direccion) VALUES ('"+ name +"', '" + pass + "', '" + address + "')";
        try {
            id = Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return id;
    }
    
    public static void delete(int id){
        try {
            String query = "DELETE FROM Usuario WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static Hashtable getByID(int id){
        Hashtable values = new Hashtable();
        try {
            String query = "SELECT * FROM Usuario WHERE id = " + id;
            ResultSet rs = Connector.query(query);
            
            if(rs.next()){
                values.put("name", rs.getString("nombre"));
                values.put("password", rs.getString("password"));
                values.put("address", rs.getString("direccion"));
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<Hashtable> getAll(){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String query = "SELECT * FROM Usuario";
            ResultSet rs = Connector.query(query);
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("id", rs.getInt("id"));
                ht.put("name", rs.getString("nombre"));
                ht.put("password", rs.getString("password"));
                ht.put("address", rs.getString("direccion"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }

    public static void updateName(int id, String name) {
        try {
            String query = "UPDATE Usuario SET nombre = '" + name + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public static void updatePassword(int id, String password) {
        try {
            String query = "UPDATE Usuario SET password = '" + password + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public static void updateAddress(int id, String address) {
        try {
            String query = "UPDATE Usuario SET direccion = '" + address + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
