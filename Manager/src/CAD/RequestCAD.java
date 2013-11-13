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
        int id=-1;
        int userId=request.getUser().getId();
        
        String query = "INSERT INTO Solicitud (criterios, cantidad, usuario) VALUES ('"+ request.getCriteria() +"', '" + request.getAmount() + "', '" + userId + "')";
        try {
            id = Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }                
        return id;
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
                ht.put("user", rs.getString("usuario"));
                values.add(ht);
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return values;
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
}
