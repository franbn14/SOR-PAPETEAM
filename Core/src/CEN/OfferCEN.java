/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CEN;

import CAD.OfferCAD;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alberto
 */
public class OfferCEN implements Comparable<OfferCEN>{
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
    
    private static OfferCEN HashMap2OfferCEN(HashMap hm){
        OfferCEN offer=null;
        if(!hm.isEmpty()) {
            offer = new OfferCEN((String)hm.get("type"), 
                                 (Double)hm.get("size"), 
                                 (Integer)hm.get("sizeUnit"), 
                                 (String)hm.get("color"), 
                                 (Integer)hm.get("amount"), 
                                 (Double)hm.get("price"), 
                                 RequestCEN.getByCode((int)hm.get("request")),
                                 ScrapYardCEN.getByID((int)hm.get("scrapyard")),
                                 (boolean)hm.get("accepted"));
            offer.code=(int) hm.get("code");
        }
        return offer;
    }
    
    private static ArrayList<OfferCEN> HashMapArra2OfferCENArray(ArrayList<HashMap> array){
        ArrayList<OfferCEN> offers = null;
        if(!array.isEmpty()){
            offers = new ArrayList<OfferCEN>();
            for(HashMap hm : array){
                offers.add(HashMap2OfferCEN(hm));
            }
        }
        return offers;
    }
    
    @Override
    public int compareTo(OfferCEN offer) {
       if(price<offer.price) 
           return -1;
       else if(price==offer.price)
           return 0;
       else
           return 1;
    }
        
    public boolean equals(OfferCEN offer) {
        if (offer == null) {
            return false;
        }
        if (getClass() != offer.getClass()) {
            return false;
        }
        final OfferCEN other = (OfferCEN) offer;
        if (this.price != other.price) {
            return false;
        }
        if (this.amount != other.amount) {                
            return false;
        }
        if(this.color != other.color)
            return false;
        return true;
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
        HashMap hm = OfferCAD.getByCode(code);         
        return HashMap2OfferCEN(hm);
    }
    
   public static ArrayList<OfferCEN> getAllOffers() {
        ArrayList<HashMap> values = OfferCAD.getAll();
        return HashMapArra2OfferCENArray(values);
    }
   
   public static ArrayList<OfferCEN> getByRequest(int request) {
        ArrayList<HashMap> values = OfferCAD.getByRequest(request);
        return HashMapArra2OfferCENArray(values);
    }
   
   public static ArrayList<OfferCEN> getByNIF(String nif) {
        ArrayList<HashMap> values = OfferCAD.getByNIF(nif);
        return HashMapArra2OfferCENArray(values);
    }
   
   public static ArrayList<OfferCEN> getAllAccepted() {
        ArrayList<HashMap> values = OfferCAD.getAllAccepted();
        return HashMapArra2OfferCENArray(values);
    }
   public static ArrayList<OfferCEN> getByCIFDesPendientes(String nif) {
        ArrayList<HashMap> values = OfferCAD.getByCIFDesPendientes(nif);
        return HashMapArra2OfferCENArray(values);
    }
    public static ArrayList<OfferCEN> getAcceptedByDesNIF(String nif) {
        ArrayList<HashMap> values = OfferCAD.getAcceptedByDesNIF(nif);
        return HashMapArra2OfferCENArray(values);
    }
   public static ArrayList<OfferCEN> getAcceptedByRequest(int request){
        ArrayList<HashMap> values = OfferCAD.getAcceptedByRequest(request);
        return HashMapArra2OfferCENArray(values);
    }

   public static ArrayList<OfferCEN>  getAcceptedByUserID(int id ){
        ArrayList<HashMap> values = OfferCAD.getAcceptedByUserID(id);
        return HashMapArra2OfferCENArray(values);
    }
   
     public static ArrayList<OfferCEN>  getAcceptedByUserNIF(String nif ){
        ArrayList<HashMap> values = OfferCAD.getAcceptedByUserNIF(nif);
        return HashMapArra2OfferCENArray(values);
    }
   
   public static  ArrayList<OfferCEN> AutoSelection(RequestCEN r){
        ArrayList<HashMap> values = OfferCAD.AutoSelection(r.getType(), r.getSize(), r.getSizeUnit(), r.getColor(), r.getAmount(), r.getMaxPrice(), r.getCode());
        ArrayList<OfferCEN> all = HashMapArra2OfferCENArray(values);
        if(all == null)
            return null;
        else if(all.isEmpty())
            return null;
        else 
            return all;
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
