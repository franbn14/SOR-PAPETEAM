package CAD;

import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author esteve
 */

public class UserCAD{    
    protected static int create (String name, String pass, String address){
        int id = -1;
        String query = "INSERT INTO Usuario (nombre, password, direccion) VALUES ('"+ name +"', '" + pass + "', '" + address + "')";
        try {
            id = Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return id;
    }
    
    public static void delete(int id){
        try {
            String query = "DELETE FROM Usuario WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public static void updateName(int id, String name) {
        try {
            String query = "UPDATE Usuario SET nombre = '" + name + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public static void updatePassword(int id, String password) {
        try {
            String query = "UPDATE Usuario SET password = '" + password + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public static void updateAddress(int id, String address) {
        try {
            String query = "UPDATE Usuario SET direccion = '" + address + "' WHERE id = " + id;
            Connector.updates(query);
        }
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
