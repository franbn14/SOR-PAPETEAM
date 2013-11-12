/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import java.sql.SQLException;

/**
 *
 * @author esteve
 */
public class GarageCAD extends UserCAD{
   
    public static int create(String name, String password, String address, String cif){
        int id = UserCAD.create(name, password, address);
        String query = "INSERT INTO Taller (id, cif) VALUES ('"+ id +"', '" + cif + "')";
        try {
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return id;
    }
    
    public static void updateCIF(int id, String cif){
        try {
            String query = "UPDATE Taller SET cif = '" + cif + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
