/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author esteve
 */
public class ScrapYardCAD {
    private static HashMap toHashMap(ResultSet rs) throws SQLException{
        HashMap hm = new HashMap();
        if(rs.next()){
            hm.put("id", rs.getInt("id"));
            hm.put("cif", rs.getString("cif"));
            hm.put("name", rs.getString("nombre"));                
            hm.put("password", rs.getString("password"));
            hm.put("address", rs.getString("direccion"));                
            hm.put("email", rs.getString("email"));
        }
        return hm;
    }
    
    private static ArrayList<HashMap> toHashMapArray(ResultSet rs) throws SQLException {
        ArrayList<HashMap> values =  new ArrayList<>();
        while(rs.next()){
            HashMap hm = new HashMap();
            hm.put("id", rs.getInt("id"));
            hm.put("cif", rs.getString("cif"));
            hm.put("name", rs.getString("nombre"));
            hm.put("password", rs.getString("password"));
            hm.put("address", rs.getString("direccion"));
            hm.put("email", rs.getString("email"));
            values.add(hm);
        }
        return values;
    }
    
    public static int create(String name, String password, String address, String cif, String email){
        int id = UserCAD.create(name, password, address, email);
        
        String query = "INSERT INTO Desguace (id, cif) VALUES ('"+ id +"', '" + cif + "')";
        try {
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException | UnknownHostException e){
            UserCAD.delete(id);
            System.err.println(e.getMessage());
        }
        return id;
    }
    
    public static HashMap getById(int id){
        HashMap values = null;
        try {
            String query = "SELECT usr.id, cif, nombre, direccion, usr.password, email " +
                    "FROM Usuario usr, Desguace d WHERE d.id = usr.id AND d.id = "+id;
            ResultSet rs = Connector.query(query);
            values = toHashMap(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static HashMap getByCIF(String cif){
        HashMap values = null;
        try {
            String query = "SELECT usr.id, cif, nombre, usr.password, direccion, email " +
                    "FROM Usuario usr, Desguace d WHERE d.id = usr.id AND d.cif = \"" + cif + "\"";
            ResultSet rs = Connector.query(query);
            values = toHashMap(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<HashMap> getAll(){
        ArrayList<HashMap> values = new ArrayList<HashMap>();
        try {
            String query = "SELECT usr.id, cif, nombre, usr.password, direccion, email " +
                    "FROM Usuario usr, Desguace d WHERE d.id = usr.id";
            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static void updateCIF(int id, String cif){
        try {
            String query = "UPDATE Desguace SET cif = '" + cif + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
    }
}
