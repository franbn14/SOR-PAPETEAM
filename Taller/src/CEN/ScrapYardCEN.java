/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;

/**
 *
 * @author esteve
 */
public class ScrapYardCEN extends UserCEN {
    
    private String cif;
    
    public ScrapYardCEN(String name, String password, String address, String cif, String email){
        super(name, password, address, email);
        this.cif = cif;
    }
    
    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @Override
    public String toString() {
        return id + " " + cif + " " +  name + " " + password + " " + address + " " + email;
    }
    
    
}
