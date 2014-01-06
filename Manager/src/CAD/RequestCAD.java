/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CAD;

import CEN.ClientCEN;
import CEN.RequestCEN;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author alberto
 */
public class RequestCAD {
    public static int insert(Date deadline, String type, Double size, int sizeUnit, String color, Integer amount, Double maxPrice,  int clientID, boolean autoElec, boolean finished) {
        int code = -1;
        String date = deadline.getYear() + "-" + (deadline.getMonth()+1) + "-" + deadline.getDate();
        String c = (color == null)? null : "\"" + color + "\"";
        String query = "INSERT INTO Solicitud (fechaTope, tipo, tamaño, tamUnidad, color, cantidad, precioMax, usuario, autoElect, finalizado) VALUES "+
                    "('" + date + "', \"" + type + "\", " + size + ", " + sizeUnit + ", " + "" + c + ", " + amount + ", " + maxPrice + ", " +
                    clientID + ", " + ((autoElec)?1:0) + ", " + ((finished)?1:0) + ");";
        
        try {
            code = Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }                
        
        return code;
    }
    
    public static Hashtable getByCode(int code){
        Hashtable request = new Hashtable();
        try {
            String query = "SELECT * FROM Solicitud WHERE codigo = " + code;
            ResultSet rs = Connector.query(query);
            
            if(rs.next()){
                request.put("code", rs.getInt("codigo"));
                Date date = new Date(rs.getDate("fechaTope").getYear()+1900, rs.getDate("fechaTope").getMonth(), rs.getDate("fechaTope").getDate());
                request.put("deadline", date);
                request.put("type", rs.getString("tipo"));
                request.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                request.put("sizeUnit", rs.getInt("tamUnidad"));
                request.put("color", (rs.getString("color") == null)? "null" : rs.getString("color"));
                request.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                request.put("maxPrice", ((Double) rs.getObject("precioMax") == null)? -1.0 : (Double) rs.getObject("precioMax"));
                request.put("autoElect", rs.getBoolean("autoElect"));
                request.put("finished", rs.getBoolean("finalizado"));
                request.put("client", rs.getInt("usuario"));
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return request;
    }
    
    public static ArrayList<Hashtable> getAll(){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String query = "SELECT * FROM Solicitud";
            ResultSet rs = Connector.query(query);
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                Date date = new Date(rs.getDate("fechaTope").getYear()+1900, rs.getDate("fechaTope").getMonth(), rs.getDate("fechaTope").getDate());
                ht.put("deadline", date);
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color", (rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("maxPrice", ((Double) rs.getObject("precioMax") == null)? -1.0 : (Double) rs.getObject("precioMax"));
                ht.put("autoElect", rs.getBoolean("autoElect"));
                ht.put("finished", rs.getBoolean("finalizado"));
                ht.put("client", rs.getInt("usuario"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }            
    
    public static void update(int code, Date deadline, String type, Double size, int sizeUnit, String color, Integer amount, Double maxPrice,  int client, boolean autoElect, boolean finished) {
        try {
            String date = deadline.getYear() + "-" + (deadline.getMonth()+1) + "-" + deadline.getDate();
            String c = (color == null)? null : "\"" + color + "\"";
            String query = "UPDATE Solicitud SET fechaTope = '"+ date +"', tipo = \"" + type + "\", tamaño = "+ size +", "+
                           "tamUnidad = "+ sizeUnit +", color = "+ color +", cantidad = " + amount + ",precioMax = " + maxPrice + ", " +
                           "usuario = " + client + ", autoElect = " + ((autoElect)?1:0) + ", finalizado = " + ((finished)?1:0) + " WHERE codigo = " + code +";";
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
   
    public static void delete(int code){
        try {
            String query = "DELETE FROM Solicitud WHERE codigo = " + code;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static ArrayList<Hashtable> getAllByNIF(String nif){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String query = "SELECT s.codigo, s.fechaTope, s.tipo, s.tamaño, s.tamUnidad, s.color, s.cantidad, s.precioMax, s.usuario, s.autoElect, s.finalizado " +
                           "FROM Solicitud s, Cliente c WHERE s.usuario = c.id and c.nif = '" + nif + "';";
            ResultSet rs = Connector.query(query);
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                Date date = new Date(rs.getDate("fechaTope").getYear()+1900, rs.getDate("fechaTope").getMonth(), rs.getDate("fechaTope").getDate());
                ht.put("deadline", date);
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color", (rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("maxPrice", ((Double) rs.getObject("precioMax") == null)? -1.0 : (Double) rs.getObject("precioMax"));
                ht.put("autoElect", rs.getBoolean("autoElect"));
                ht.put("finished", rs.getBoolean("finalizado"));
                ht.put("client", rs.getInt("usuario"));
                values.add(ht);
            }
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<Hashtable> getAllFinishedByNIF(String nif){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String query = "SELECT s.codigo, s.fechaTope, s.tipo, s.tamaño, s.tamUnidad, s.color, s.cantidad, s.precioMax, s.usuario, s.autoElect, s.finalizado " +
                           "FROM Solicitud s, Cliente c WHERE s.usuario = c.id and s.finalizado = 1 and c.nif = '" + nif + "';";
            ResultSet rs = Connector.query(query);
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                Date date = new Date(rs.getDate("fechaTope").getYear()+1900, rs.getDate("fechaTope").getMonth(), rs.getDate("fechaTope").getDate());
                ht.put("deadline", date);
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color", (rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("maxPrice", ((Double) rs.getObject("precioMax") == null)? -1.0 : (Double) rs.getObject("precioMax"));
                ht.put("autoElect", rs.getBoolean("autoElect"));
                ht.put("finished", rs.getBoolean("finalizado"));
                ht.put("client", rs.getInt("usuario"));
                values.add(ht);
            }
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    } 
    
    public static ArrayList<Hashtable> getAllOpenedByNIF(String nif){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String query = "SELECT s.codigo, s.fechaTope, s.tipo, s.tamaño, s.tamUnidad, s.color, s.cantidad, s.precioMax, s.usuario, s.autoElect, s.finalizado " +
                           "FROM Solicitud s, Cliente c WHERE s.usuario = c.id and s.finalizado = 0 and c.nif = '" + nif + "';";
            ResultSet rs = Connector.query(query);
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                Date date = new Date(rs.getDate("fechaTope").getYear()+1900, rs.getDate("fechaTope").getMonth(), rs.getDate("fechaTope").getDate());
                ht.put("deadline", date);
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color", (rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("maxPrice", ((Double) rs.getObject("precioMax") == null)? -1.0 : (Double) rs.getObject("precioMax"));
                ht.put("autoElect", rs.getBoolean("autoElect"));
                ht.put("finished", rs.getBoolean("finalizado"));
                ht.put("client", rs.getInt("usuario"));
                values.add(ht);
            }
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    } 
    
     public static ArrayList<Hashtable> getAllOpened(){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            Date now = new Date();
            String s_now = (now.getYear()+1900) + "-" + (now.getMonth()+1) + "-" + now.getDate();
            String query = "SELECT s.codigo, s.fechaTope, s.tipo, s.tamaño, s.tamUnidad, s.color, s.cantidad, s.precioMax, s.usuario, s.autoElect, s.finalizado " +
                           "FROM Solicitud s WHERE s.finalizado = 0 and s.fechaTope >= '" + s_now + "';";
            
            ResultSet rs = Connector.query(query);
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                Date date = new Date(rs.getDate("fechaTope").getYear()+1900, rs.getDate("fechaTope").getMonth(), rs.getDate("fechaTope").getDate());
                ht.put("deadline", date);
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color", (rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("maxPrice", ((Double) rs.getObject("precioMax") == null)? -1.0 : (Double) rs.getObject("precioMax"));
                ht.put("autoElect", rs.getBoolean("autoElect"));
                ht.put("finished", rs.getBoolean("finalizado"));
                ht.put("client", rs.getInt("usuario"));
                values.add(ht);
            }
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    } 
}
