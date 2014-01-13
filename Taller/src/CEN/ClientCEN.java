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
