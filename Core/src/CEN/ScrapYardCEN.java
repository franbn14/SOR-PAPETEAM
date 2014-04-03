/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEN;

import CAD.ScrapYardCAD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;

/**
 *
 * @author esteve
 */
public class ScrapYardCEN extends UserCEN {
    
    private String cif;
    
    private static ScrapYardCEN HashMap2ScrapYardCEN(HashMap hm){
        ScrapYardCEN scy = null;
        if(!hm.isEmpty()) {
           scy=new ScrapYardCEN((String) hm.get("name"),(String) hm.get("password"),(String) hm.get("address"), (String) hm.get("cif"), (String)hm.get("email"));
           scy.id=(int) hm.get("id");
        }
        return scy;
    }
    
    private static ArrayList<ScrapYardCEN> HashMapArray2ScrapYardCENArray(ArrayList<HashMap> array){
        ArrayList<ScrapYardCEN> sys = new ArrayList<>();
        for(HashMap hm : array){
            sys.add(HashMap2ScrapYardCEN(hm));
        }
        return sys;
    }
    
    public ScrapYardCEN(String name, String password, String address, String cif, String email){
        super(name, password, address, email);
        this.cif = cif;
    }
    
    @Override
    public void insert(){
        if(id == -1){
            this.id = CAD.ScrapYardCAD.create(name, password, address, cif, email);  
        }
    }
    

    public static ScrapYardCEN getByCIF(String cif){
        HashMap hm = CAD.ScrapYardCAD.getByCIF(cif);
        return HashMap2ScrapYardCEN(hm);
    }
    public static ScrapYardCEN getByID(int id) {
        HashMap hm = CAD.ScrapYardCAD.getById(id);
        return HashMap2ScrapYardCEN(hm);
    }
    
    public static ArrayList<ScrapYardCEN> getAllScrapYards(){
        ArrayList<HashMap> values = CAD.ScrapYardCAD.getAll();
        return HashMapArray2ScrapYardCENArray(values);
    }
    
    public void update(String name, String password, String address, String cif, String email){
        update(name, password, address, email);
        if(id != -1){
            CAD.ScrapYardCAD.updateCIF(id, cif);
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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @Override
    public String toString() {
        return id + " " + cif + " " +  name + " " + password + " " + address + " " + email;
    }
    
    
}
