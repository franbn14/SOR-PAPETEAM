/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEN;

import java.util.Date;

/**
 *
 * @author esteve
 */
public class ClientCEN extends UserCEN {
    private String surname;
    private String dni;
    private Date DOB;
    
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
}
