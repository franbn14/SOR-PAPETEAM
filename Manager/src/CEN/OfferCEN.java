/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CEN;

import CAD.OfferCAD;
import CAD.RequestCAD;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author alberto
 */
public class OfferCEN {
    private int code;
    private String description;
    private RequestCEN request;
    private ScrapYardCEN scrapyard;

    public OfferCEN() {
    }

    public OfferCEN(String description, RequestCEN request, ScrapYardCEN scrapyard) {        
        setAttributes(description, request, scrapyard);
    }    
       
    public void setAttributes(String description, RequestCEN request, ScrapYardCEN scrapyard) {
        this.description = description;
        this.request = request;
        this.scrapyard = scrapyard;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public RequestCEN getRequest() {
        return request;
    }

    public void setRequest(RequestCEN request) {
        this.request = request;
    }

    public ScrapYardCEN getScrapyard() {
        return scrapyard;
    }

    public void setScrapyard(ScrapYardCEN scrapyard) {
        this.scrapyard = scrapyard;
    }
       
    @Override
    public String toString() {
        return code + " " + description;
    }
    
    public void insert() {
        this.code=OfferCAD.insert(this);
    }
    
    public static OfferCEN getByCode(int code) {
        Hashtable ht = OfferCAD.getByCode(code);         
        OfferCEN offer=null;
        
        if(!ht.isEmpty()) {
            offer = new OfferCEN((String)ht.get("description"), RequestCEN.getByCode((int)ht.get("request")), (ScrapYardCEN)ScrapYardCEN.getByID((int)ht.get("scrapyard")));
            offer.code=(int) ht.get("code");
        }
        
        return offer;
    }
    
    public static ArrayList<OfferCEN> getAllOffers() {
        ArrayList<Hashtable> values = OfferCAD.getAll();
        ArrayList<OfferCEN> all = null;
        
        if(!values.isEmpty()) {
            all = new ArrayList<OfferCEN>();        

            for(Hashtable ht : values){                            
                OfferCEN offer=new OfferCEN();                
                offer = new OfferCEN((String)ht.get("description"), RequestCEN.getByCode((int)ht.get("request")), (ScrapYardCEN)ScrapYardCEN.getByID((int)ht.get("scrapyard")));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        }        
        return all;
    }
    
    public void update(String description, RequestCEN request , ScrapYardCEN scrapyard) {        
        setAttributes(description, request, scrapyard);
        OfferCAD.update(this);
    }
    
    public void delete() {
        if(code != -1){
            OfferCAD.delete(code);
        }
    }            
}
