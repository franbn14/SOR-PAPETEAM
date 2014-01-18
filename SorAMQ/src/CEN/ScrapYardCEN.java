/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEN;

import CAD.ScrapYardCAD;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author esteve
 */
public class ScrapYardCEN extends UserCEN {
    
    private String cif;
    private String email;
    
    public ScrapYardCEN(String name, String password, String address, String cif, String email){
        super(name, password, address);
        this.cif = cif;
        this.email = email;
    }
    
    @Override
    public void insert(){
        if(id == -1){
            this.id = CAD.ScrapYardCAD.create(name, password, address, cif, email);  
        }
    }
    

    public static ScrapYardCEN getByCIF(String cif){
        Hashtable ht = CAD.ScrapYardCAD.getByCIF(cif);
        ScrapYardCEN scy=null;
        if(!ht.isEmpty()) {
           scy=new ScrapYardCEN((String) ht.get("name"),(String) ht.get("password"),(String) ht.get("address"), (String) ht.get("cif"), (String)ht.get("email"));
           scy.id=(int) ht.get("id");
        
        }
    
      return scy;
    }
    public static ScrapYardCEN getByID(int id) {
        Hashtable ht = CAD.ScrapYardCAD.getById(id);
        
        ScrapYardCEN scrapyard = new ScrapYardCEN((String)ht.get("name"), (String)ht.get("password"), (String)ht.get("address"), (String)ht.get("cif"), (String)ht.get("email"));        
        scrapyard.id=id;
       
        return scrapyard;
    }
    
    public static ArrayList<ScrapYardCEN> getAllScrapYards(){
        ArrayList<Hashtable> values = CAD.ScrapYardCAD.getAll();
        ArrayList<ScrapYardCEN> all =  new ArrayList<ScrapYardCEN>();
        for(Hashtable ht : values){
            ScrapYardCEN sy = new ScrapYardCEN((String) ht.get("name"), (String) ht.get("password"), (String) ht.get("address"), (String) ht.get("cif"), (String)ht.get("email"));
            sy.id = (int) ht.get("id");
            all.add(sy);
        }
        return all;
    }
    
    public void update(String name, String password, String address, String cif, String email){
        update(name, password, address);
        if(id != -1){
            CAD.ScrapYardCAD.updateCIF(id, cif);
            CAD.ScrapYardCAD.updateEmail(id, email);
            this.name =  name;
            this.password = password;
            this.address = address;
            this.cif = cif;
            this.email = email;
        }
    }
    
    public void updateCIF(String cif){
        if(id != -1){
            CAD.ScrapYardCAD.updateCIF(id, cif);
            this.cif = cif;
        }
    }
    
    public void updateEmail(String email){
        if(id != -1){
            CAD.ScrapYardCAD.updateEmail(id, email);
            this.email = email;
        }
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + " " + cif + " " +  name + " " + password + " " + address;
    }
    
    
}