package CEN;

import java.util.ArrayList;
import java.util.HashMap;

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
    protected String email;
   
    private static UserCEN HashMap2UserCER(HashMap hm){
        UserCEN user = new UserCEN((String) hm.get("name"), (String) hm.get("password"), (String) hm.get("address"), (String) hm.get("email")) {};                 
        user.id = Integer.parseInt(hm.get("id").toString());
        return user;
    }
     
    private static ArrayList<UserCEN> HashMapArray2UserCENArray(ArrayList<HashMap> array){
        ArrayList<UserCEN> users =  new ArrayList<UserCEN>();
        for(HashMap hm : array){
            users.add(HashMap2UserCER(hm));
        }
        return users;
    }

    public UserCEN(String name, String password, String address, String email) {
        this.id = -1;
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString(){
        return id + " " + name + " " + password + " " + address;
    }
}
