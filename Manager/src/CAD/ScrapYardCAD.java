/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author esteve
 */
public class ScrapYardCAD {
    public static int create(String name, String password, String address, String cif){
        int id = UserCAD.create(name, password, address);
        
        String query = "INSERT INTO Desguace (id, cif) VALUES ('"+ id +"', '" + cif + "')";
        try {
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            UserCAD.delete(id);
            System.err.println(e.getMessage());
        }
        return id;
    }
    
    public static Hashtable getById(int id){
        Hashtable values = new Hashtable();
        try {
            String query = "SELECT usr.id, cif, nombre, direccion, usr.password " +
                    "FROM Usuario usr, Desguace d WHERE d.id = usr.id AND d.id = "+id;
            ResultSet rs = Connector.query(query);
            if(rs.next()){
                values.put("id", rs.getInt("id"));
                values.put("cif", rs.getString("cif"));
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
    
    public static Hashtable getByCIF(String cif){
        Hashtable values = new Hashtable();
        try {
            String query = "SELECT usr.id, cif, nombre, usr.password, direccion " +
                    "FROM Usuario usr, Desguace d WHERE d.id = usr.id AND d.cif = '" + cif + "'";
            ResultSet rs = Connector.query(query);
            if(rs.next()){
                values.put("id", rs.getInt("id"));
                values.put("cif", rs.getString("cif"));
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
        ArrayList<Hashtable> values = new ArrayList<Hashtable>();
        try {
            String query = "SELECT usr.id, cif, nombre, usr.password, direccion " +
                    "FROM Usuario usr, Desguace d WHERE d.id = usr.id";
            ResultSet rs = Connector.query(query);
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("id", rs.getInt("id"));
                ht.put("cif", rs.getString("cif"));
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
    
    public static void updateCIF(int id, String cif){
        try {
            String query = "UPDATE Desguace SET cif = '" + cif + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
