/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEN;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author esteve
 */
public class ScrapYardCEN extends UserCEN {
    
    private String cif;
    private String email;
    
    public ScrapYardCEN(String name, String password, String address, String cif, String email){
        super(name, password, address);
        this.cif = cif;
        this.email = email;
    }
    
   

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + " " + cif + " " +  name + " " + password + " " + address;
    }
    
    
}
