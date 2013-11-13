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
    
    public static int create(String name, String surname, String password, String dni, String address, Date DOB){
        int id = UserCAD.create(name, password, address);
        String date = DOB.getYear() + "-" + DOB.getMonth() + "-" + DOB.getDate();
        String query = "INSERT INTO Cliente (id, dni, apellidos, fechaNac) VALUES ('"+ id +"', '" + dni + "', '" + surname  + "', '" + date + "')";
        try {
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return id;
    }
    
    public static Hashtable getByDNI(String dni){
        Hashtable values = new Hashtable();
        try {
            String query = "SELECT usr.id, dni, nombre, apellidos, usr.password, direccion, fechaNac " +
                    "FROM Usuario usr, Cliente c WHERE c.id = usr.id AND c.dni = \"" + dni + "\"";
            ResultSet rs = Connector.query(query);
            if(rs.next()){
                values.put("id", rs.getInt("id"));
                values.put("dni", rs.getString("dni"));
                values.put("name", rs.getString("nombre"));
                values.put("surname", rs.getString("apellidos"));
                values.put("password", rs.getString("password"));
                values.put("address", rs.getString("direccion"));
                values.put("DOB", rs.getDate("fechaNac"));
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
            String query = "SELECT usr.id, dni, nombre, apellidos, usr.password, direccion, fechaNac " +
                    "FROM Usuario usr, Cliente c WHERE c.id = usr.id";
            ResultSet rs = Connector.query(query);
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("id", rs.getInt("id"));
                ht.put("dni", rs.getString("dni"));
                ht.put("name", rs.getString("nombre"));
                ht.put("surname", rs.getString("apellidos"));
                ht.put("password", rs.getString("password"));
                ht.put("address", rs.getString("direccion"));
                ht.put("DOB", rs.getDate("fechaNac"));
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
            String query = "UPDATE Cliente SET surname = '" + surname + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void updateDNI(int id, String dni){
        try {
            String query = "UPDATE Cliente SET dni = '" + dni + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void updateDOB(int id, Date DOB){
        try {
            String date = DOB.getYear() + "-" + DOB.getMonth() + "-" + DOB.getDate();
            String query = "UPDATE Cliente SET dni = '" + date + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
