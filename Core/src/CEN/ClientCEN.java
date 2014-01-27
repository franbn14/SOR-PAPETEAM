/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEN;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author esteve
 */
public class ClientCEN extends UserCEN {
    private String surname;
    private String nif;
    private Date DOB;
    
    private static ClientCEN HashMap2ClientCEN(HashMap hm){
        ClientCEN client = null;
        if(hm != null && !hm.isEmpty()){
            client = new ClientCEN((String)hm.get("name"), (String)hm.get("surname"), (String)hm.get("password"), (String)hm.get("nif"), (String)hm.get("address"), (Date)hm.get("DOB"), (String)hm.get("email"));        
            client.id = (int)hm.get("id");
        }
        return client;
    }
    
    private static ArrayList<ClientCEN> HashMapArray2ClientCENArray(ArrayList<HashMap> array){
        ArrayList<ClientCEN> clients = new ArrayList<>();
        for(HashMap hm : array){
            clients.add(HashMap2ClientCEN(hm));
        }
        return clients;
    }
    
    public ClientCEN(String name, String surname, String password, String nif, String address, Date DOB, String email){
        super(name, password, address, email);
        this.surname = surname;
        this.nif = nif;
        this.DOB = DOB;
    }
    
    @Override
    public void insert(){
        if(id == -1){
            this.id = CAD.ClientCAD.create(name, surname, password, nif, address, DOB, email);
        }
    }
    
    public static ClientCEN getByID(int id) {
        HashMap hm = CAD.ClientCAD.getById(id);
        return HashMap2ClientCEN(hm);
    }
    
    public static ClientCEN getByNIF(String nif){        
        HashMap hm = CAD.ClientCAD.getByNIF(nif);
        return HashMap2ClientCEN(hm);
    }   
    
   public static ArrayList<ClientCEN> getAllClients(){
        ArrayList<HashMap> values = CAD.ClientCAD.getAll();
        return HashMapArray2ClientCENArray(values);
    }
    
    public void update(String name, String surname, String password, String nif, String address, Date DOB, String email){
        update(name, password, address, email);
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
            this.email = email;
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
