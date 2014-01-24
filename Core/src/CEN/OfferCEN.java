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
    private Integer sizeUnit;
    private String color;
    private Integer amount;
    private Double price;
    private RequestCEN request;
    private ScrapYardCEN scrapyard;
    private boolean accepted;

    /*public OfferCEN() {
    }*/

    private void setAttributes(String type, Double size, Integer sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard, boolean accepted) {
        this.type = type;
        this.size = size;
        this.sizeUnit = sizeUnit;
        this.color = color;
        this.amount = amount;
        this.price = price;
        this.request = request;
        this.scrapyard = scrapyard;          
        this.accepted = accepted;
    }
    
    public OfferCEN(String type, Double size, Integer sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard, boolean accepted) {        
        setAttributes(type, size, sizeUnit, color, amount, price, request, scrapyard, accepted);
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

    public Integer getSizeUnit() {
        return sizeUnit;
    }

    public String getSizeUnitString(){
        return UnitsCEN.getByID(sizeUnit);
    }
    
    public void setSizeUnit(Integer sizeUnit) {
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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
       
    @Override
    public String toString() {
        return code + " " + type + " " + size + " " + sizeUnit + " " + color + " " + amount + " " + price + " " + request + " " + scrapyard;
    }
    
    public void insert() {
        this.code = OfferCAD.insert(type, size, sizeUnit, color, amount, price, request.getCode(), scrapyard.getId(), accepted);
    }
    
    /*String type, Double size, Integer sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard*/
    
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
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")),
                                 (boolean)ht.get("accepted"));
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
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")),
                                 (boolean)ht.get("accepted"));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        }        
        return all;
    }
   
   public static ArrayList<OfferCEN> getByRequest(int request) {
        ArrayList<Hashtable> values = OfferCAD.getByRequest(request);
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
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")),
                                 (boolean)ht.get("accepted"));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        }        
        return all;
    }
   
   public static ArrayList<OfferCEN> getByNIF(String nif) {
        ArrayList<Hashtable> values = OfferCAD.getByNIF(nif);
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
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")),
                                 (boolean)ht.get("accepted"));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        }        
        return all;
    }
   
   public static ArrayList<OfferCEN> getAllAccepted() {
        ArrayList<Hashtable> values = OfferCAD.getAllAccepted();
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
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")),
                                 (boolean)ht.get("accepted"));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        }        
        return all;
    }
   public static ArrayList<OfferCEN> getByCIFDesPendientes(String nif) {
        ArrayList<Hashtable> values = OfferCAD.getByCIFDesPendientes(nif);
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
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")),
                                 (boolean)ht.get("accepted"));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        }        
        return all;
    }
    public static ArrayList<OfferCEN> getAcceptedByDesNIF(String nif) {
        ArrayList<Hashtable> values = OfferCAD.getAcceptedByDesNIF(nif);
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
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")),
                                 (boolean)ht.get("accepted"));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        }        
        return all;
    }
   public static ArrayList<OfferCEN> getAcceptedByRequest(int request){
        ArrayList<Hashtable> values = OfferCAD.getAcceptedByRequest(request);
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
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")),
                                 (boolean)ht.get("accepted"));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        }        
        return all;
    }

   public static ArrayList<OfferCEN>  getAcceptedByUserID(int id ){
        ArrayList<Hashtable> values = OfferCAD.getAcceptedByUserID(id);
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
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")),
                                 (boolean)ht.get("accepted"));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        }        
        return all;
    }
   
     public static ArrayList<OfferCEN>  getAcceptedByUserNIF(String nif ){
        ArrayList<Hashtable> values = OfferCAD.getAcceptedByUserNIF(nif);
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
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")),
                                 (boolean)ht.get("accepted"));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        }        
        return all;
    }
   
   public static  ArrayList<OfferCEN> AutoSelection(RequestCEN r){
        ArrayList<Hashtable> values = OfferCAD.AutoSelection(r.getType(), r.getSize(), r.getSizeUnit(), r.getColor(), r.getAmount(), r.getMaxPrice(), r.getCode());
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
                                 ScrapYardCEN.getByID((int)ht.get("scrapyard")),
                                 (boolean)ht.get("accepted"));
                offer.code=(int) ht.get("code");
                all.add(offer);
            }           
        } 
        if(all == null){
            return null;
        }
        else if(all.isEmpty()){
            return null;
        }
        
        else {
          
            return all;
        }
   }
   
   /*public String getSYEMail(){
       return OfferCAD.getSYEmail(code);
   }*/
    
    public void update(String type, Double size, Integer sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard, boolean accepted) {        
        setAttributes(type, size, sizeUnit, color, amount, price, request, scrapyard, accepted);
        OfferCAD.update(this.code, type, size, sizeUnit, color, amount, price, request.getCode(), scrapyard.getId(), accepted);
    }
    
    public void delete() {
        if(code != -1){
            OfferCAD.delete(code);
        }
    }   
    
    public static void deleteByRequest(int request){
        OfferCAD.deleteByRequest(request);
    }
}
