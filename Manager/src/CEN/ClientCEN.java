/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEN;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author esteve
 */
public class ClientCEN extends UserCEN {
    private String surname;
    private String nif;
    private Date DOB;
    
    /*public ClientCEN(){
        super();
        this.surname = "";
        this.nif = "";
        this.DOB = new Date();
    }*/
    
    public ClientCEN(String name, String surname, String password, String nif, String address, Date DOB){
        super(name, password, address);
        this.surname = surname;
        this.nif = nif;
        this.DOB = DOB;
    }
    
    @Override
    public void insert(){
        if(id == -1){
            this.id = CAD.ClientCAD.create(name, surname, password, nif, address, DOB);
        }
    }
    
    public static ClientCEN getById(int id) {
        Hashtable ht = CAD.ClientCAD.getById(id);
        
        ClientCEN client = new ClientCEN((String)ht.get("name"), (String)ht.get("surname"), (String)ht.get("password"), (String)ht.get("nif"), (String)ht.get("address"), (Date)ht.get("DOB"));        
        client.id=id;
        
        return client;
    }
    
    public static ClientCEN getByNIF(String nif){        
        Hashtable ht = CAD.ClientCAD.getByNIF(nif);
        
        ClientCEN client = new ClientCEN((String)ht.get("name"), (String)ht.get("surname"), (String)ht.get("password"), nif, (String)ht.get("address"), (Date)ht.get("DOB"));
        client.id = (int)ht.get("id");
        
        return client;
    }   
    
   public static ArrayList<ClientCEN> getAllClients(){
        ArrayList<Hashtable> values = CAD.ClientCAD.getAll();
        ArrayList<ClientCEN> all =  new ArrayList<>();
        for(Hashtable ht : values){
            
            ClientCEN c = new ClientCEN((String) ht.get("name"), (String) ht.get("surname"), 
                    (String) ht.get("password"), (String) ht.get("nif"), (String) ht.get("address"), (Date)ht.get("DOB"));
            c.id = (int) ht.get("id");
            all.add(c);
        }
        return all;
    }
    
    public void update(String name, String surname, String password, String nif, String address, Date DOB){
        update(name, password, address);
        if(id != -1){
            CAD.ClientCAD.updateNIF(id, nif);
            CAD.ClientCAD.updateSurname(id, surname);
            CAD.ClientCAD.updateDOB(id, DOB);
            
            this.name = name;
            this.surname = surname;
            this.password = password;
            this.nif = nif;
            this.address = address;
            this.DOB = DOB;
        }
    }
    
    public void updateSurname(String surname){
        if(id != -1){
            CAD.ClientCAD.updateSurname(id, surname);
            this.surname = surname;
        }
    }
    
    public void updateNIF(String nif){
        if(id != -1){
            CAD.ClientCAD.updateNIF(id, nif);
            this.nif = nif;
        }
    }
    
    public void updateDOB(Date DOB){       
        if(id != -1){
            CAD.ClientCAD.updateDOB(id, DOB);
            this.DOB = DOB;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNIF() {
        return nif;
    }

    public void setNIF(String nif) {
        this.nif = nif;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
    
    @Override
    public String toString(){
        return id + " " + nif + " " + name + " " + surname + " " + password + " " + address + " " + DOB.toString();
    }
}
