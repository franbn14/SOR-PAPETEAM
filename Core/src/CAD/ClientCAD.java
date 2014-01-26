/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author esteve
 */
public class ClientCAD extends UserCAD{
    
    private static HashMap toHashMap(ResultSet rs) throws SQLException, ParseException{
        HashMap hm = new HashMap();
         if(rs.next()){
            hm.put("id", rs.getInt("id"));
            hm.put("nif", rs.getString("nif"));
            hm.put("name", rs.getString("nombre"));
            hm.put("surname", rs.getString("apellidos"));
            hm.put("password", rs.getString("password"));
            hm.put("address", rs.getString("direccion"));
            //Date date = new Date(rs.getDate("fechaNac").getYear(), rs.getDate("fechaNac").getMonth(), rs.getDate("fechaNac").getDate());
            Date date = null;
            if(rs.getDate("fechaNac") != null)
                date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getDate("fechaNac").toString());
            hm.put("DOB", date);
            hm.put("email", rs.getString("email"));
        }
        return hm;
    }
    
    private static ArrayList<HashMap> toHashMapArray(ResultSet rs) throws SQLException, ParseException{
        ArrayList<HashMap> values = new ArrayList<>();
        while(rs.next()){
            HashMap hm = new HashMap();
            hm.put("id", rs.getInt("id"));
            hm.put("nif", rs.getString("nif"));
            hm.put("name", rs.getString("nombre"));
            hm.put("surname", rs.getString("apellidos"));
            hm.put("password", rs.getString("password"));
            hm.put("address", rs.getString("direccion"));
            //Date date = new Date(rs.getDate("fechaNac").getYear(), rs.getDate("fechaNac").getMonth(), rs.getDate("fechaNac").getDate()); 
            Date date = null;
            if(rs.getDate("fechaNac") != null)
                date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getDate("fechaNac").toString());
            hm.put("DOB", date);
            hm.put("email", rs.getString("email"));
            values.add(hm);
        }
        return values;
    }
    
    public static int create(String name, String surname, String password, String nif, String address, Date DOB, String email){
        int id = UserCAD.create(name, password, address, email);
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
    
    public static HashMap getById(int id){
        HashMap values = null;
        try {
            String query = "SELECT usr.id, nif, nombre, apellidos, usr.password, direccion, fechaNac, email " +
                    "FROM Usuario usr, Cliente c WHERE c.id = usr.id AND c.id = "+id;
            ResultSet rs = Connector.query(query);
            values = toHashMap(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException | ParseException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static HashMap getByNIF(String nif){
        HashMap values = new HashMap();
        try {
            String query = "SELECT usr.id, nif, nombre, apellidos, usr.password, direccion, fechaNac, email " +
                    "FROM Usuario usr, Cliente c WHERE c.id = usr.id AND c.nif = \"" + nif + "\"";
            ResultSet rs = Connector.query(query);
            values = toHashMap(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException | ParseException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<HashMap> getAll(){
        ArrayList<HashMap> values = new ArrayList<HashMap>();
        try {
            String query = "SELECT usr.id, nif, nombre, apellidos, usr.password, direccion, fechaNac, email " +
                    "FROM Usuario usr, Cliente c WHERE c.id = usr.id";
            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException | ParseException e){
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
