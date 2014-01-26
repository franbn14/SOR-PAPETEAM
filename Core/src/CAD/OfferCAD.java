/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CAD;

import CEN.OfferCEN;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;

/**
 *
 * @author alberto
 */
public class OfferCAD {
    
    private static HashMap toHashMap(ResultSet rs) throws SQLException{
        HashMap offer = new HashMap();
        if(rs.next()){
            offer.put("code", rs.getInt("codigo"));
            offer.put("type", rs.getString("tipo"));
            offer.put("size", (Double)rs.getObject("tamaño"));
            offer.put("sizeUnit", (Integer)rs.getObject("tamUnidad"));
            offer.put("color", rs.getString("color"));
            offer.put("amount", (Integer)rs.getObject("cantidad"));
            offer.put("price", (Double) rs.getObject("precio"));
            offer.put("request", rs.getInt("solicitud"));
            offer.put("scrapyard", rs.getInt("desguace"));
            offer.put("accepted", rs.getBoolean("aceptada"));
        }
        return offer;
    }
    
    private static ArrayList<HashMap> toHashMapArray(ResultSet rs) throws SQLException{
        ArrayList<HashMap> offers =  new ArrayList<HashMap>();
        while(rs.next()){
            HashMap hm = new HashMap();
            hm.put("code", rs.getInt("codigo"));
            hm.put("type", rs.getString("tipo"));
            hm.put("size", (Double)rs.getObject("tamaño"));
            hm.put("sizeUnit", (Integer)rs.getObject("tamUnidad"));
            hm.put("color", rs.getString("color"));
            hm.put("amount", (Integer)rs.getObject("cantidad"));
            hm.put("price", (Double) rs.getObject("precio"));
            hm.put("request", rs.getInt("solicitud"));
            hm.put("scrapyard", rs.getInt("desguace"));
            hm.put("accepted", rs.getBoolean("aceptada"));
            offers.add(hm);
        }
        return offers;
    }
    
    public static int insert(String type, Double size, Integer sizeUnit, String color, Integer amount, Double price, int request, int scrapyard, boolean accepted) {
        int code=-1;
        String c = (color == null)? null : "\"" + color + "\"";
        String query = "INSERT INTO Oferta (tipo, tamaño, tamUnidad, color, cantidad, precio, solicitud, desguace, aceptada) "
                + "VALUES ('"+ type +"', " + size + ", " + sizeUnit + ", " + c + ", " + amount + ", " + price + ", " + request + ", " + scrapyard + ", " + ((accepted)?1:0) + ");";
        
        try {
            code = Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }    
        
        return code;
    }
    
    public static HashMap getByCode(int code){
        HashMap offer = null;
        try {
            String query = "SELECT * FROM Oferta WHERE codigo = " + code;
            ResultSet rs = Connector.query(query);
            offer = toHashMap(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return offer;
    }
    
    public static ArrayList<HashMap> getAll(){
        ArrayList<HashMap> values = new ArrayList();
        try {
            String query = "SELECT * FROM Oferta";
            ResultSet rs = Connector.query(query);
            
            
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<HashMap> getByRequest(int request){
        ArrayList<HashMap> values = null;
        try {
            String query = "SELECT * FROM Oferta WHERE solicitud = " + request + ";";
            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<HashMap> getByNIF(String nif){
        ArrayList<HashMap> values = new ArrayList();
        try {
            String query = "SELECT o.codigo, o.tipo, o.tamaño, o.tamUnidad, o.color, o.cantidad, o.precio, o.solicitud, o.desguace, o.aceptada " +
                            "FROM Oferta o, Solicitud s, Cliente c WHERE o.solicitud = s.codigo and s.usuario = c.id and c.nif = '" + nif + "';";
            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs); 
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
     public static ArrayList<HashMap> getByCIFDesPendientes(String nif){
        ArrayList<HashMap> values = new ArrayList();
        try {
           String query = "SELECT o.codigo, o.tipo, o.tamaño, o.tamUnidad, o.color, o.cantidad, o.precio, o.solicitud, o.desguace, o.aceptada " +
                            "FROM Oferta o, Solicitud s, Desguace c WHERE o.solicitud = s.codigo and o.desguace = c.id and c.cif = '" + nif + "' and o.aceptada= 0;";
            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<HashMap> getAllAccepted(){
        ArrayList<HashMap> values = new ArrayList();
        try {
            String query = "SELECT * FROM Oferta WHERE aceptada = 1;";
            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<HashMap> getAcceptedByRequest(int request){
        ArrayList<HashMap> values = new ArrayList();
        try {
            String query = "SELECT * FROM Oferta WHERE aceptada = 1 and solicitud = " + request + ";";
            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<HashMap> getAcceptedByUserID(int id ){
        ArrayList<HashMap> values = new ArrayList();
        try {
            String query = "SELECT o.* FROM Oferta o, Solicitud s, Cliente c " +
                            "WHERE o.aceptada = 1 and s.usuario = c.id and o.solicitud = s.codigo and c.id = " + id + ";";
            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<HashMap> getAcceptedByUserNIF(String nif){
        ArrayList<HashMap> values = new ArrayList();
        try {
            String query = "SELECT o.* FROM Oferta o, Solicitud s, Cliente c " +
                            "WHERE o.aceptada = 1 and s.usuario = c.id and o.solicitud = s.codigo and c.nif = " + nif + ";";
            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
     public static ArrayList<HashMap> getAcceptedByDesNIF(String nif){
        ArrayList<HashMap> values = new ArrayList();
        try {
            
            String query = "SELECT o.* FROM Oferta o, Solicitud s, Desguace c " +
                            "WHERE o.aceptada = 1 and o.desguace = c.id and o.solicitud = s.codigo and c.cif = '" + nif + "';";
            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
     public static ArrayList<HashMap> AutoSelection(String type, Double size, Integer sizeUnit, String color, Integer amount, Double maxPrice, int request){
        ArrayList<HashMap> values = new ArrayList();
        try {
            String q_type = "(o.tipo like '%" + type + "%' or o.tipo like '%" + type.toUpperCase() + "%' or o.tipo like '%" + type.toLowerCase() + "%')";
            String q_size = (size == null)? null : "o.tamaño = " + size;
            String q_sizeUnit = (size == null)? null : "o.tamUnidad = " + sizeUnit;
            String q_color = (color == null || color.equals(""))? null : "o.color = '" + color + "'";
            String q_amount = (amount == null)? null: "o.cantidad = " + amount;
            String q_maxPrice = (maxPrice == null)? null : "o.precio <= " + maxPrice;
            
            String query = "SELECT DISTINCT o.codigo, o.tipo, o.tamaño, o.tamUnidad, o.color, o.cantidad, o.precio, o.solicitud, o.desguace, o.aceptada " +
                        "FROM Oferta o, Solicitud s "
                        + "WHERE solicitud = " + request + " and "
                        + q_type
                        + ((q_size == null)? "" : " and " + q_size )
                        + ((q_sizeUnit == null)? "" : " and " + q_sizeUnit)
                        + ((q_color == null)? "" : " and " + q_color)
                        + ((q_amount == null)? "" : " and " + q_amount)
                        + ((q_maxPrice == null)? "" : " and " + q_maxPrice);

            ResultSet rs = Connector.query(query);
            values = toHashMapArray(rs);
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
     
    /*public static String getSYEmail(int offer){
        String email = "";
        try {
            String query = "SELECT d.email FROM Oferta o, Desguace d WHERE o.desguace = d.id and o.codigo = " + offer + ";";
            ResultSet rs = Connector.query(query);
            email = rs.getString("email");
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return email;
    }*/
    
    public static void update(int code, String type, Double size, Integer sizeUnit, String color, Integer amount, Double price, int request, int scrapyard, boolean accepted) {
        try {
            String c = (color == null)? null : "\"" + color + "\"";
            String query = "UPDATE Oferta SET tipo = \"" + type + "\", tamaño = " + size + ", tamUnidad = " + sizeUnit + ", color = " + c + ", "
                    + "cantidad = " + amount + ", precio = " + price + ", solicitud = " + request + ", desguace = " + scrapyard + ", aceptada = " + ((accepted)?1:0)
                    + " WHERE codigo = " + code + ";"; 
            
            System.out.println(query);
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
   
    public static void delete(int code){
        try {
            String query = "DELETE FROM Oferta WHERE codigo = " + code;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void deleteByRequest(int request){
        try {
            String query = "DELETE FROM Oferta WHERE solicitud = " + request;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
