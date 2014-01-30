/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CAD;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author alberto
 */
public class RequestCAD {
    
    private static HashMap toHashMap(ResultSet rs) throws SQLException, ParseException{
        HashMap request = new HashMap();
        if(rs.next()){
            request.put("code", rs.getInt("codigo"));
            Date date = null;
            if(rs.getDate("fechaTope") != null)
                date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getDate("fechaTope").toString());
            request.put("deadline", date);
            request.put("type", rs.getString("tipo"));
            request.put("size", (Double)rs.getObject("tamaño"));
            request.put("sizeUnit", (Integer)rs.getObject("tamUnidad"));
            request.put("color", rs.getString("color"));
            request.put("amount", (Integer)rs.getObject("cantidad"));
            request.put("maxPrice", (Double) rs.getObject("precioMax"));
            request.put("autoElect", rs.getBoolean("autoElect"));
            request.put("finished", rs.getBoolean("finalizado"));
            request.put("client", rs.getInt("usuario"));
            request.put("expired", rs.getBoolean("caducado"));
        }
        return request;
    }
    
    private static ArrayList<HashMap> toHashMapArray(ResultSet rs) throws SQLException, ParseException {
        ArrayList<HashMap> values = new ArrayList();
        while(rs.next()){
            HashMap hm = new HashMap();
            hm.put("code", rs.getInt("codigo"));
            //Date date = new Date(rs.getDate("fechaTope").getYear(), rs.getDate("fechaTope").getMonth(), rs.getDate("fechaTope").getDate());
            Date date = null;
            if(rs.getDate("fechaTope") != null)
                date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getDate("fechaTope").toString());
            hm.put("deadline", date);
            hm.put("type", rs.getString("tipo"));
            hm.put("size", (Double)rs.getObject("tamaño"));
            hm.put("sizeUnit",(Integer)rs.getObject("tamUnidad"));
            hm.put("color", rs.getString("color"));
            hm.put("amount", (Integer)rs.getObject("cantidad"));
            hm.put("maxPrice", (Double) rs.getObject("precioMax"));
            hm.put("autoElect", rs.getBoolean("autoElect"));
            hm.put("finished", rs.getBoolean("finalizado"));
            hm.put("client", rs.getInt("usuario"));
            hm.put("expired", rs.getBoolean("caducado"));
            values.add(hm);
        }
        return values;
    }
    
    public static int insert(Date deadline, String type, Double size, Integer sizeUnit, String color, Integer amount, Double maxPrice,  int clientID, boolean autoElec, boolean finished, boolean expired) {
        int code = -1;
         
        String date = deadline.getYear()+1900 + "-" + (deadline.getMonth()+1) + "-" + deadline.getDate();
        
        String c = (color == null)? null : "\"" + color + "\"";
        String query = "INSERT INTO Solicitud (fechaTope, tipo, tamaño, tamUnidad, color, cantidad, precioMax, usuario, autoElect, finalizado, caducado) VALUES "+
                    "('" + date + "', '" + type + "', " + size + ", " + sizeUnit + ", " + c + ", " + amount + ", " + maxPrice + ", " +
                    clientID + ", " + ((autoElec)?1:0) + ", " + ((finished)?1:0) + ", " + ((expired)?1:0) +");";
        
        try {
            code = Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException | UnknownHostException e){
            System.err.println(e.getMessage());
        }                
        
        return code;
    }
    
    public static HashMap getByCode(int code){
        HashMap request = new HashMap();
        try {
            String query = "SELECT * FROM Solicitud WHERE codigo = " + code;
            ResultSet rs = Connector.query(query);
            request = toHashMap(rs);
        }
        catch (ClassNotFoundException | SQLException | ParseException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
        return request;
    }
    
    public static ArrayList<HashMap> getAll(){
        ArrayList<HashMap> requests = new ArrayList();
        try {
            String query = "SELECT * FROM Solicitud";
            ResultSet rs = Connector.query(query);
            requests = toHashMapArray(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException | ParseException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
        return requests;
    }            
    
    public static void update(int code, Date deadline, String type, Double size, Integer sizeUnit, String color, Integer amount, Double maxPrice,  int client, boolean autoElect, boolean finished, boolean expired) {
        try {
            String date = deadline.getYear()+1900 + "-" + (deadline.getMonth()+1) + "-" + deadline.getDate();
            String c = (color == null)? null : "\"" + color + "\"";
            String query = "UPDATE Solicitud SET fechaTope = '"+ date +"', tipo = \"" + type + "\", tamaño = "+ size +", "+
                           "tamUnidad = "+ sizeUnit +", color = "+ c +", cantidad = " + amount + ",precioMax = " + maxPrice + ", " +
                           "usuario = " + client + ", autoElect = " + ((autoElect)?1:0) + ", finalizado = " + ((finished)?1:0) + 
                           ", caducado = " + ((expired)?1:0) + " WHERE codigo = " + code +";";
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
    }
   
    public static void delete(int code){
        try {
            String query = "DELETE FROM Solicitud WHERE codigo = " + code;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static ArrayList<HashMap> getAllByNIF(String nif){
        ArrayList<HashMap> requests = new ArrayList();
        try {
            String query = "SELECT s.* FROM Solicitud s, Cliente c WHERE s.usuario = c.id and c.nif = '" + nif + "';";
            ResultSet rs = Connector.query(query);
            requests = toHashMapArray(rs);
        }
        catch (ClassNotFoundException | SQLException | ParseException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
        return requests;
    }
    
    public static ArrayList<HashMap> getAllFinishedByNIF(String nif){
        ArrayList<HashMap> requests = new ArrayList();
        try {
            String query = "SELECT s.* FROM Solicitud s, Cliente c WHERE s.usuario = c.id and s.finalizado = 1 and c.nif = '" + nif + "';";
            ResultSet rs = Connector.query(query);
            requests = toHashMapArray(rs);
        }
        catch (ClassNotFoundException | SQLException | ParseException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
        return requests;
    } 
    
    public static ArrayList<HashMap> getAllOpenedByNIF(String nif){
        ArrayList<HashMap> requests = new ArrayList();
        try {
            String query = "SELECT s.* FROM Solicitud s, Cliente c WHERE s.usuario = c.id and s.finalizado = 0 and c.nif = '" + nif + "';";
            ResultSet rs = Connector.query(query);
            requests = toHashMapArray(rs);
        }
        catch (ClassNotFoundException | SQLException | ParseException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
        return requests;
    } 
    
     public static ArrayList<HashMap> getAllOpened(){
        ArrayList<HashMap> requests = new ArrayList();
        try {
            /*String query = "SELECT s.codigo, s.fechaTope, s.tipo, s.tamaño, s.tamUnidad, s.color, s.cantidad, s.precioMax, s.usuario, s.autoElect, s.finalizado " +
                           "FROM Solicitud s WHERE s.finalizado = 0 AND DATE(s.fechaTope) > DATE(NOW());";*/
            
            String query = "SELECT * FROM Solicitud WHERE finalizado = 0 AND caducado = 0;";
            
            ResultSet rs = Connector.query(query);
            requests = toHashMapArray(rs);
        }
        catch (ClassNotFoundException | SQLException | ParseException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
        return requests;
    } 
     
     public static ArrayList<HashMap> getAllExpired(){
        ArrayList<HashMap> requests = new ArrayList();
        try {
            String query = "SELECT * FROM Solicitud WHERE caducado = 1;";
            
            ResultSet rs = Connector.query(query);
            requests = toHashMapArray(rs);
        }
        catch (ClassNotFoundException | SQLException | ParseException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
        return requests;
    } 
     
    public static ArrayList<HashMap> getExpired(){
        ArrayList<HashMap> expired = null;
        try {
            String query = "SELECT * FROM Solicitud WHERE finalizado = 0 AND DATE(fechaTope) < DATE(NOW());";
            ResultSet rs = Connector.query(query);
            expired = toHashMapArray(rs);
            
            query = "UPDATE Solicitud SET caducado = 1 WHERE DATE(fechaTope) < DATE(NOW());";
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException | ParseException | UnknownHostException e){
            System.err.println(e.getMessage());
        }
        return expired;
    }
}
