/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEN;

import java.util.ArrayList;

/**
 *
 * @author esteve
 */
public class UnitsCEN {
    public static ArrayList<String> getAll(){
        return CAD.UnitsCAD.getAll();
    }
    
    public static String getByID(int id){
        return CAD.UnitsCAD.getByID(id);
    }
}
