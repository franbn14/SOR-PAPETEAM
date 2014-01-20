package CEN;

import java.util.ArrayList;
import java.util.Hashtable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author esteve
 */
public abstract class UserCEN {
    
    protected int id;
    protected String name;
    protected String password;
    protected String address;
    
    /*public UserCEN(){
        this.id = -1;
        this.name = "";
        this.password = "";
        this.address = "";
    }*/

    public UserCEN(String name, String password, String address) {
        this.id = -1;
        this.name = name;
        this.password = password;
        this.address = address;
    }
    
   
    
  

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString(){
        return id + " " + name + " " + password + " " + address;
    }
}
