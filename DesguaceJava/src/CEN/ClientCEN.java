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
        ClientCEN client = new ClientCEN((String)hm.get("name"), (String)hm.get("surname"), (String)hm.get("password"), (String)hm.get("nif"), (String)hm.get("address"), (Date)hm.get("DOB"), (String)hm.get("email"));        
        client.id = (Integer)hm.get("id");
        return client;
    }
    
    private static ArrayList<ClientCEN> HashMapArray2ClientCENArray(ArrayList<HashMap> array){
        ArrayList<ClientCEN> clients = new ArrayList<ClientCEN>();
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
