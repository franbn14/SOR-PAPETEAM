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
public class ScrapYardCEN extends UserCEN {
    
    private String cif;
    
    public ScrapYardCEN(String name, String password, String address, String cif){
        super(name, password, address);
        this.cif = cif;
    }
    
    @Override
    public void insert(){
        this.id=CAD.ScrapYardCAD.create(name, password, address, cif);        
    }
    
   
    
    public static ScrapYardCEN getById(int id) {
        Hashtable ht = CAD.ScrapYardCAD.getById(id);
        
        ScrapYardCEN scrapyard = new ScrapYardCEN((String)ht.get("name"), (String)ht.get("password"), (String)ht.get("address"), (String)ht.get("cif"));        
        scrapyard.id=id;
       
        return scrapyard;
    }
     public static ScrapYardCEN getByCIF(String cif){
        Hashtable ht = CAD.ScrapYardCAD.getByCIF(cif);
        ScrapYardCEN scy=null;
        if(!ht.isEmpty())
        {
           scy=new ScrapYardCEN((String) ht.get("name"),(String) ht.get("password"),(String) ht.get("address"), (String) ht.get("cif"));
           scy.id=(int) ht.get("id");
        
        }
    
      return scy;
    }
    public static ArrayList<ScrapYardCEN> getAllScrapYards(){
        ArrayList<Hashtable> values = CAD.ScrapYardCAD.getAll();
        ArrayList<ScrapYardCEN> all =  new ArrayList<ScrapYardCEN>();
        for(Hashtable ht : values){
            ScrapYardCEN sy = new ScrapYardCEN((String) ht.get("name"), (String) ht.get("password"), (String) ht.get("address"), (String) ht.get("cif"));
            sy.id = (int) ht.get("id");
            all.add(sy);
        }
        return all;
    }
    
    public void update(String name, String password, String address, String cif){
        update(name, password, address);
        if(id != -1){
            CAD.ScrapYardCAD.updateCIF(id, cif);
        }
    }
    
    public void updateCIF(String cif){
        if(id != -1){
            CAD.ScrapYardCAD.updateCIF(id, cif);
        }
    }

    @Override
    public String toString() {
        return id + " " + cif + " " +  name + " " + password + " " + address;
    }
    
    
}
