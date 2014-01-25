package CAD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author esteve
 */

public class UserCAD{    
    
    private static HashMap toHashMap(ResultSet rs) throws SQLException{
        HashMap hm = new HashMap();
        if(rs.next()){
            hm.put("id", rs.getString("id"));
            hm.put("name", rs.getString("nombre"));
            hm.put("password", rs.getString("password"));
            hm.put("address", rs.getString("direccion"));
            hm.put("email", rs.getString("email"));
        }
        return hm;
    }
    
    private static ArrayList<HashMap> toHashMapArray (ResultSet rs) throws SQLException{
        ArrayList<HashMap> values = new ArrayList<>();
        while(rs.next()){
            HashMap hm = new HashMap();
            hm.put("id", rs.getInt("id"));
            hm.put("name", rs.getString("nombre"));
            hm.put("password", rs.getString("password"));
            hm.put("address", rs.getString("direccion"));
            hm.put("email", rs.getString("email"));
            values.add(hm);
        }
        return values;
    }
    
    protected static int create (String name, String pass, String address, String email){
        int id = -1;
        String query = "INSERT INTO Usuario (nombre, password, direccion, email) VALUES ('"+ name +"', '" + pass + "', '" + address + "', '" + email + "')";
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
    
    public static HashMap getByID(int id){
        HashMap values = null;
        try {
            String query = "SELECT * FROM Usuario WHERE id = " + id;
            ResultSet rs = Connector.query(query);
            values = toHashMap(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<HashMap> getAll(){
        ArrayList<HashMap> values = null;
        try {
            String query = "SELECT * FROM Usuario";
            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs);
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
    
    public static void updateEmail(int id, String email){
        try {
            String query = "UPDATE Usuario SET email = '" + email + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
