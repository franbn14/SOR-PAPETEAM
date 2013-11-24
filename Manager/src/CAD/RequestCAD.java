/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CAD;

import CEN.RequestCEN;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author alberto
 */
public class RequestCAD {
    public static int insert(RequestCEN request) {
        int code=-1;
        int userId=request.getUser().getId();
        
        String query = "INSERT INTO Solicitud (criterios, cantidad, usuario) VALUES ('"+ request.getCriteria() +"', '" + request.getAmount() + "', '" + userId + "')";
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
                request.put("criteria", rs.getString("criterios"));
                request.put("amount", rs.getString("cantidad"));
                request.put("user", rs.getInt("usuario"));
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
                ht.put("criteria", rs.getString("criterios"));
                ht.put("amount", rs.getString("cantidad"));
                ht.put("user", rs.getInt("usuario"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
    }            
    
    public static void update(RequestCEN request) {
        try {
            String query = "UPDATE Solicitud SET criterios = '" + request.getCriteria() + "', cantidad = '"+ 
                            + request.getAmount() + "', usuario = '"+ request.getUser().getId() + "' WHERE codigo = " + request.getCode();
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
   
    public static void delete(int code){
        try {
            String query = "DELETE FROM Solicitud WHERE codigo = " + code;
            System.out.println(query);
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
