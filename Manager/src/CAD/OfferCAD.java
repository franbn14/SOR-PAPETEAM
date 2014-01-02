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
    public static int insert(String type, Double size, int sizeUnit, String color, Integer amount, Double price, int request, int scrapyard) {
        int code=-1;
        String c = (color == null)? null : "\"" + color + "\"";
        String query = "INSERT INTO Oferta (tipo, tamaño, tamUnidad, color, cantidad, precio, solicitud, desguace) "
                + "VALUES ('"+ type +"', " + size + ", " + sizeUnit + ", " + c + ", " + amount + ", " + price + ", " + request + ", " + scrapyard + ");";
        
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
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static void update(int code, String type, Double size, int sizeUnit, String color, Integer amount, Double price, int request, int scrapyard) {
        try {
            String c = (color == null)? null : "\"" + color + "\"";
            String query = "UPDATE Oferta SET tipo = \"" + type + "\", tamaño = " + size + ", tamUnidad = " + sizeUnit + ", color = " + c + ", "
                    + "cantidad = " + amount + ", precio = " + price + ", solicitud = " + request + ", desguace = " + scrapyard + " WHERE codigo = " + code + ";"; 
            
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
}
