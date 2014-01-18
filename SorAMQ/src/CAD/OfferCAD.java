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
import java.util.Hashtable;

/**
 *
 * @author alberto
 */
public class OfferCAD {
    public static int insert(String type, Double size, int sizeUnit, String color, Integer amount, Double price, int request, int scrapyard, boolean accepted) {
        int code=-1;
        String c = (color == null)? null : "\"" + color + "\"";
        String query = "INSERT INTO Oferta (tipo, tamaño, tamUnidad, color, cantidad, precio, solicitud, desguace, aceptada) "
                + "VALUES ('"+ type +"', " + size + ", " + sizeUnit + ", " + c + ", " + amount + ", " + price + ", " + request + ", " + scrapyard + ", " + ((accepted)?1:0) + ");";
        
        System.out.println(query);
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
            String query = "SELECT * FROM Oferta WHERE codigo = " + code;
            ResultSet rs = Connector.query(query);
            
            if(rs.next()){
                request.put("code", rs.getInt("codigo"));
                request.put("type", rs.getString("tipo"));
                request.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                request.put("sizeUnit", rs.getInt("tamUnidad"));
                request.put("color",(rs.getString("color") == null)? "null" : rs.getString("color"));
                request.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                request.put("price", ((Double) rs.getObject("precio") == null)? -1.0 : (Double) rs.getObject("precio"));
                request.put("request", rs.getInt("solicitud"));
                request.put("scrapyard", rs.getInt("desguace"));
                request.put("accepted", rs.getBoolean("aceptada"));
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
            String query = "SELECT * FROM Oferta";
            ResultSet rs = Connector.query(query);
            
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color",(rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("price", ((Double) rs.getObject("precio") == null)? -1.0 : (Double) rs.getObject("precio"));
                ht.put("request", rs.getInt("solicitud"));
                ht.put("scrapyard", rs.getInt("desguace"));
                ht.put("accepted", rs.getBoolean("aceptada"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<Hashtable> getByRequest(int request){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String query = "SELECT * FROM Oferta WHERE solicitud = " + request + ";";
            ResultSet rs = Connector.query(query);
            
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color",(rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("price", ((Double) rs.getObject("precio") == null)? -1.0 : (Double) rs.getObject("precio"));
                ht.put("request", rs.getInt("solicitud"));
                ht.put("scrapyard", rs.getInt("desguace"));
                ht.put("accepted", rs.getBoolean("aceptada"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<Hashtable> getByNIF(String nif){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String query = "SELECT o.codigo, o.tipo, o.tamaño, o.tamUnidad, o.color, o.cantidad, o.precio, o.solicitud, o.desguace, o.aceptada " +
                            "FROM Oferta o, Solicitud s, Cliente c WHERE o.solicitud = s.codigo and s.usuario = c.id and c.nif = '" + nif + "'" + " and o.aceptada = 0;";
            ResultSet rs = Connector.query(query);
            
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color",(rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("price", ((Double) rs.getObject("precio") == null)? -1.0 : (Double) rs.getObject("precio"));
                ht.put("request", rs.getInt("solicitud"));
                ht.put("scrapyard", rs.getInt("desguace"));
                ht.put("accepted", rs.getBoolean("aceptada"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<Hashtable> getAllAccepted(){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String query = "SELECT * FROM Oferta WHERE aceptada = 1;";
            ResultSet rs = Connector.query(query);
            
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color",(rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("price", ((Double) rs.getObject("precio") == null)? -1.0 : (Double) rs.getObject("precio"));
                ht.put("request", rs.getInt("solicitud"));
                ht.put("scrapyard", rs.getInt("desguace"));
                ht.put("accepted", rs.getBoolean("aceptada"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<Hashtable> getAcceptedByRequest(int request){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String query = "SELECT * FROM Oferta WHERE aceptada = 1 and solicitud = " + request + ";";
            ResultSet rs = Connector.query(query);
            
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color",(rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("price", ((Double) rs.getObject("precio") == null)? -1.0 : (Double) rs.getObject("precio"));
                ht.put("request", rs.getInt("solicitud"));
                ht.put("scrapyard", rs.getInt("desguace"));
                ht.put("accepted", rs.getBoolean("aceptada"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<Hashtable> getAcceptedByUserID(int id ){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String query = "SELECT o.* FROM Oferta o, Solicitud s, Cliente c " +
                            "WHERE o.aceptada = 1 and s.usuario = c.id and o.solicitud = s.codigo and c.id = " + id + ";";
            ResultSet rs = Connector.query(query);
            
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color",(rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("price", ((Double) rs.getObject("precio") == null)? -1.0 : (Double) rs.getObject("precio"));
                ht.put("request", rs.getInt("solicitud"));
                ht.put("scrapyard", rs.getInt("desguace"));
                ht.put("accepted", rs.getBoolean("aceptada"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static ArrayList<Hashtable> getAcceptedByUserNIF(String nif){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String query = "SELECT o.* FROM Oferta o, Solicitud s, Cliente c " +
                            "WHERE o.aceptada = 1 and s.usuario = c.id and o.solicitud = s.codigo and c.nif = " + nif + ";";
            ResultSet rs = Connector.query(query);
            
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color",(rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("price", ((Double) rs.getObject("precio") == null)? -1.0 : (Double) rs.getObject("precio"));
                ht.put("request", rs.getInt("solicitud"));
                ht.put("scrapyard", rs.getInt("desguace"));
                ht.put("accepted", rs.getBoolean("aceptada"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
     public static ArrayList<Hashtable> AutoSelection(String type, Double size, int sizeUnit, String color, Integer amount, Double maxPrice, int request){
        ArrayList<Hashtable> values = new ArrayList();
        try {
            String q_type = "(o.tipo like '%" + type + "%' or o.tipo like '%" + type.toUpperCase() + "%' or o.tipo like '%" + type.toLowerCase() + "%')";
            String q_size = (size == null)? null : "o.tamaño = " + size;
            String q_sizeUnit = (size == null)? null : "o.tamUnidad = " + sizeUnit;
            String q_color = (color == null || color.equals(""))? null : "o.color = '" + color + "'";
            String q_amount = (amount == null)? null: "o.cantidad = " + amount;
            String q_maxPrice = (maxPrice == null)? null : "o.precio <= " + maxPrice;
            
            String query = "SELECT o.codigo, o.tipo, o.tamaño, o.tamUnidad, o.color, o.cantidad, o.precio, o.solicitud, o.desguace, o.aceptada " +
                        "FROM Oferta o, Solicitud s "
                        + "WHERE solicitud = " + request + " and "
                        + q_type + " and "
                        + ((q_size == null)? "" : q_size + " and ")
                        + ((q_sizeUnit == null)? "" : q_sizeUnit + " and ")
                        + ((q_color == null)? "" : q_color + " and ")
                        + ((q_amount == null)? "" : q_amount + " and ")
                        + ((q_maxPrice == null)? "" : q_maxPrice + ";");

            ResultSet rs = Connector.query(query);
            
            while(rs.next()){
                Hashtable ht = new Hashtable();
                ht.put("code", rs.getInt("codigo"));
                ht.put("type", rs.getString("tipo"));
                ht.put("size", ((Double)rs.getObject("tamaño") == null)? -1 : (Double)rs.getObject("tamaño"));
                ht.put("sizeUnit", rs.getInt("tamUnidad"));
                ht.put("color",(rs.getString("color") == null)? "null" : rs.getString("color"));
                ht.put("amount", ((Integer)rs.getObject("cantidad") == null)? -1 : (Integer)rs.getObject("cantidad"));
                ht.put("price", ((Double) rs.getObject("precio") == null)? -1.0 : (Double) rs.getObject("precio"));
                ht.put("request", rs.getInt("solicitud"));
                ht.put("scrapyard", rs.getInt("desguace"));
                ht.put("accepted", rs.getBoolean("aceptada"));
                values.add(ht);
            }
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
    
    public static void update(int code, String type, Double size, int sizeUnit, String color, Integer amount, Double price, int request, int scrapyard, boolean accepted) {
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
