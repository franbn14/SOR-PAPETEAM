/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author esteve
 */
public class ClientCAD extends UserCAD{
    
    public static int create(String name, String surname, String password, String nif, String address, Date DOB){
        int id = UserCAD.create(name, password, address);
        String date = DOB.getYear()+1900 + "-" + (DOB.getMonth()+1) + "-" + DOB.getDate();
        String query = "INSERT INTO Cliente (id, nif, apellidos, fechaNac) VALUES ('"+ id +"', '" + nif + "', '" + surname  + "', '" + date + "')";
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
            String query = "SELECT usr.id, nif, nombre, apellidos, usr.password, direccion, fechaNac " +
                    "FROM Usuario usr, Cliente c WHERE c.id = usr.id AND c.id = "+id;
            ResultSet rs = Connector.query(query);
            if(rs.next()){
                values.put("id", rs.getInt("id"));
                values.put("nif", rs.getString("nif"));
                values.put("name", rs.getString("nombre"));
                values.put("surname", rs.getString("apellidos"));
                values.put("password", rs.getString("password"));
                values.put("address", rs.getString("direccion"));
                Date date = new Date(rs.getDate("fechaNac").getYear(), rs.getDate("fechaNac").getMonth(), rs.getDate("fechaNac").getDate());
                values.put("DOB", date);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static Hashtable getByNIF(String nif){
        Hashtable values = new Hashtable();
        try {
            String query = "SELECT usr.id, nif, nombre, apellidos, usr.password, direccion, fechaNac " +
                    "FROM Usuario usr, Cliente c WHERE c.id = usr.id AND c.nif = \"" + nif + "\"";
            ResultSet rs = Connector.query(query);
            if(rs.next()){
                values.put("id", rs.getInt("id"));
                values.put("nif", rs.getString("nif"));
                values.put("name", rs.getString("nombre"));
                values.put("surname", rs.getString("apellidos"));
                values.put("password", rs.getString("password"));
                values.put("address", rs.getString("direccion"));
                Date date = new Date(rs.getDate("fechaNac").getYear(), rs.getDate("fechaNac").getMonth(), rs.getDate("fechaNac").getDate());
                values.put("DOB", date);
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
            String query = "SELECT usr.id, nif, nombre, apellidos, usr.password, direccion, fechaNac " +
                    "FROM Usuario usr, Cliente c WHERE c.id = usr.id";
            ResultSet rs = Connector.query(query);
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("id", rs.getInt("id"));
                ht.put("nif", rs.getString("nif"));
                ht.put("name", rs.getString("nombre"));
                ht.put("surname", rs.getString("apellidos"));
                ht.put("password", rs.getString("password"));
                ht.put("address", rs.getString("direccion"));
                Date date = new Date(rs.getDate("fechaNac").getYear(), rs.getDate("fechaNac").getMonth(), rs.getDate("fechaNac").getDate()); 
                ht.put("DOB", date);
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static void updateSurname(int id, String surname){
        try {
            String query = "UPDATE Cliente SET apellidos = '" + surname + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void updateNIF(int id, String nif){
        try {
            String query = "UPDATE Cliente SET nif = '" + nif + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void updateDOB(int id, Date DOB){
        try {
            String date = DOB.getYear()+1900 + "-" + (DOB.getMonth()+1) + "-" + DOB.getDate();
            String query = "UPDATE Cliente SET fechaNac = '" + date + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
