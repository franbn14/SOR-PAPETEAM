/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author esteve
 */
public class ClientCAD extends UserCAD{
    
    public static int create(String name, String surname, String password, String dni, String address, Date DOB){
        int id = UserCAD.create(name, password, address);
        String date = DOB.getYear() + "-" + DOB.getMonth() + "-" + DOB.getDate();
        String query = "INSERT INTO Cliente (id, dni, apellidos, fechaNac) VALUES ('"+ id +"', '" + dni + "', '" + surname  + "', '" + date + "')";
        try {
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return id;
    }
    
    public static void updateSurname(int id, String surname){
        try {
            String query = "UPDATE Cliente SET surname = '" + surname + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void updateDNI(int id, String dni){
        try {
            String query = "UPDATE Cliente SET dni = '" + dni + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void updateDOB(int id, Date DOB){
        try {
            String date = DOB.getYear() + "-" + DOB.getMonth() + "-" + DOB.getDate();
            String query = "UPDATE Cliente SET dni = '" + date + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
