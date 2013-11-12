/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEN;

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
