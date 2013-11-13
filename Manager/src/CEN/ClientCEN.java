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
    private String dni;
    private Date DOB;
    
    public ClientCEN(){
        super();
        this.surname = "";
        this.dni = "";
        this.DOB = new Date();
    }
    
    public ClientCEN(String name, String surname, String password, String dni, String address, Date DOB){
        super(name, password, address);
        this.surname = surname;
        this.dni = dni;
        this.DOB = DOB;
    }
    
    @Override
    public void insert(){
        this.id = CAD.ClientCAD.create(name, surname, password, dni, address, DOB);
    }
    
    public void getByDNI(String dni){
        Hashtable ht = CAD.ClientCAD.getByDNI(dni);
        this.id = (int) ht.get("id");
        this.dni = dni;
        this.name = (String) ht.get("name");
        this.surname = (String) ht.get("surname");
        this.address = (String) ht.get("address");
        this.password = (String) ht.get("password");
        this.DOB = (Date) ht.get("DOB");
    }
    
   public static ArrayList<ClientCEN> getAllClients(){
        ArrayList<Hashtable> values = CAD.ClientCAD.getAll();
        ArrayList<ClientCEN> all =  new ArrayList<>();
        for(Hashtable ht : values){
            ClientCEN c = new ClientCEN((String) ht.get("name"), (String) ht.get("surname"), 
                    (String) ht.get("password"), (String) ht.get("dni"), (String) ht.get("address"), (Date) ht.get("DOB"));
            c.id = (int) ht.get("id");
            all.add(c);
        }
        return all;
    }
    
    public void update(String name, String surname, String password, String dni, String address, Date DOB){
        update(name, password, address);
        if(id != -1){
            CAD.ClientCAD.updateDNI(id, dni);
            CAD.ClientCAD.updateSurname(id, surname);
            CAD.ClientCAD.updateDOB(id, DOB);
        }
    }
    
    public void updateSurname(String surname){
        if(id != -1){
            CAD.ClientCAD.updateSurname(id, surname);
        }
    }
    
    public void updateDNI(String dni){
        if(id != -1){
            CAD.ClientCAD.updateDNI(id, dni);
        }
    }
    
    public void updateDOB(Date DOB){
        if(id != -1){
            CAD.ClientCAD.updateDOB(id, DOB);
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
    
    @Override
    public String toString(){
        return id + " " + dni + " " + name + " " + surname + " " + password + " " + address + " " + DOB.toString();
    }
}
