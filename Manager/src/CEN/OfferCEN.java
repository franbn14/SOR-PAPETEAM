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
    private String type;
    private Double size;
    private int sizeUnit;
    private String color;
    private Integer amount;
    private Double price;
    private RequestCEN request;
    private ScrapYardCEN scrapyard;

    /*public OfferCEN() {
    }*/

    private void setAttributes(String type, Double size, int sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard) {
        this.type = type;
        this.size = size;
        this.sizeUnit = sizeUnit;
        this.color = color;
        this.amount = amount;
        this.price = price;
        this.request = request;
        this.scrapyard = scrapyard;               
    }
    
    public OfferCEN(String type, Double size, int sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard) {        
        setAttributes(type, size, sizeUnit, color, amount, price, request, scrapyard);
        this.code = -1;
    }     
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public int getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(int sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        return code + " " + type + " " + size + " " + sizeUnit + " " + color + " " + amount + " " + price + " " + request + " " + scrapyard;
    }
    
    public void insert() {
        this.code = OfferCAD.insert(type, size, sizeUnit, color, amount, price, request.getCode(), scrapyard.getId());
    }
    
    /*String type, Double size, int sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard*/
    
    public static OfferCEN getByCode(int code) {
        Hashtable ht = OfferCAD.getByCode(code);         
        OfferCEN offer=null;
        
        if(!ht.isEmpty()) {
            offer = new OfferCEN((String)ht.get("type"), 
                                 ((Double)ht.get("size") == -1.0)? null : (Double)ht.get("size"), 
                                 (int)ht.get("sizeUnit"), 
                                 (((String)ht.get("color")).equals("null"))? null : (String)ht.get("color"), 
                                 ((Integer)ht.get("amount") == -1)? null : (Integer)ht.get("amount"), 
                                 ((Double)ht.get("price") == -1.0)? null : (Double)ht.get("price"), 
                                 RequestCEN.getByCode((int)ht.get("request")),
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")));
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
                OfferCEN offer= new OfferCEN((String)ht.get("type"), 
                                 ((Double)ht.get("size") == -1.0)? null : (Double)ht.get("size"), 
                                 (int)ht.get("sizeUnit"), 
                                 (((String)ht.get("color")).equals("null"))? null : (String)ht.get("color"), 
                                 ((Integer)ht.get("amount") == -1)? null : (Integer)ht.get("amount"), 
                                 ((Double)ht.get("price") == -1.0)? null : (Double)ht.get("price"), 
                                 RequestCEN.getByCode((int)ht.get("request")),
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        }        
        return all;
    }
    
    public void update(String type, Double size, int sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard) {        
        setAttributes(type, size, sizeUnit, color, amount, price, request, scrapyard);
        OfferCAD.update(this.code, type, size, sizeUnit, color, amount, price, request.getCode(), scrapyard.getId());
    }
    
    public void delete() {
        if(code != -1){
            OfferCAD.delete(code);
        }
    }            
}
