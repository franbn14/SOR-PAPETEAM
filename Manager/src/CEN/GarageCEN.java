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
public class GarageCEN extends UserCEN{
    private String cif;
    
    public GarageCEN(String name, String password, String address, String cif){
        super(name, password, address);
        this.cif = cif;
    }
    
    @Override
    public void insert(){
        CAD.GarageCAD.create(name, password, address, cif);
    }
    
    public void getByCIF(String cif){
        Hashtable ht = CAD.GarageCAD.getByCIF(cif);
        this.id = (int) ht.get("id");
        this.cif = cif;
        this.name = (String) ht.get("name");
        this.address = (String) ht.get("address");
        this.password = (String) ht.get("password");
    }
    
    public static ArrayList<GarageCEN> getAllGarages(){
        ArrayList<Hashtable> values = CAD.GarageCAD.getAll();
        ArrayList<GarageCEN> all =  new ArrayList<GarageCEN>();
        for(Hashtable ht : values){
            GarageCEN g = new GarageCEN((String) ht.get("name"), (String) ht.get("password"), (String) ht.get("address"), (String) ht.get("cif"));
            g.id = (int) ht.get("id");
            all.add(g);
        }
        return all;
    }
    
    public void update(String name, String password, String address, String cif){
        update(name, password, address);
        if(id != -1){
            CAD.GarageCAD.updateCIF(id, cif);
        }
    }
    
    public void updateCIF(String cif){
        if(id != -1){
            CAD.GarageCAD.updateCIF(id, cif);
        }
    }
}
