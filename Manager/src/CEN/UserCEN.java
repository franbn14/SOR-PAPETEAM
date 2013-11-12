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
    
    public UserCEN(){
        this.id = -1;
        this.name = "";
        this.password = "";
        this.address = "";
    }

    public UserCEN(String name, String password, String address) {
        this.id = -1;
        this.name = name;
        this.password = password;
        this.address = address;
    }
    
    public void insert(){}
    
    public static ArrayList<UserCEN> getAllUsers(){
        ArrayList<Hashtable> values = CAD.UserCAD.getAll();
        ArrayList<UserCEN> all = new ArrayList<UserCEN>();
        for(Hashtable ht : values){
            UserCEN usr = new UserCEN((String) ht.get("name"), (String) ht.get("password"), (String) ht.get("address")) {};
            usr.id = (int) ht.get("id");
            all.add(usr);
        }
        return all;
    }
    
    public void getByID(int id){
        Hashtable ht = CAD.UserCAD.getByID(id);
        this.id = id;
        this.name = (String) ht.get("name");
        this.address = (String) ht.get("address");
        this.password = (String) ht.get("password");
    }
    
    public void delete(){
        if(id != -1){
            CAD.UserCAD.delete(id);
        }
    }
    
    public void update(String name, String password, String address){
        if(id != -1){
            CAD.UserCAD.updateName(id, name);
            CAD.UserCAD.updatePassword(id, password);
            CAD.UserCAD.updateAddress(id, address);
        }
    }
    
    public void updateName(String name){
        if(id != -1){
            CAD.UserCAD.updateName(id, name);
        }
    }
    
    public void updatePassword(String password){
        if(id != -1){
            CAD.UserCAD.updatePassword(id, password);
        }
    }
    
    public void updateAddress(String address){
        if(id != -1){
            CAD.UserCAD.updateAddress(id, address);
        }
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
