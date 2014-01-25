/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author esteve
 */
public class UnitsCAD {
    public static ArrayList<String> getAll(){
        ArrayList<String> units = new ArrayList<>();
        try {
            String query = "SELECT * FROM Unidad order by id";
            ResultSet rs = Connector.query(query);
            
            while(rs.next()){
                units.add(rs.getString("unidad"));
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return units;
    }
    
    public static String getByID(int id){
        String unit = "";
        try {
            String query = "SELECT * FROM Unidad WHERE id = " + id;
            ResultSet rs = Connector.query(query);
            
            if(rs.next()){
                unit = rs.getString("unidad");
            }
            Connector.close(rs);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return unit;
    }
}
