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
    public static int insert(OfferCEN offer) {
        int code=-1;
                        
        String query = "INSERT INTO Oferta (descripcion, solicitud, desguace) VALUES ('"+ offer.getDescription()+"', '" + offer.getRequest().getCode() + "', '" + offer.getScrapyard().getId() + "')";
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
                request.put("description", rs.getString("descripcion"));
                request.put("request", rs.getString("solicitud"));
                request.put("scrapyard", rs.getInt("scrapyard"));
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
                ht.put("description", rs.getString("descripcion"));
                ht.put("request", rs.getString("solicitud"));
                ht.put("scrapyard", rs.getInt("scrapyard"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }
    
    public static void update(OfferCEN offer) {
        try {
            String query = "UPDATE Oferta SET descripcion = '" + offer.getDescription()+ "', solicitud = '" + 
                            + offer.getRequest().getCode() + "', desguace = '" + offer.getScrapyard().getId() + "' WHERE codigo = " + offer.getCode();
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
