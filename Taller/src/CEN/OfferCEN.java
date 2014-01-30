/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CEN;

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
        return ((amount!=null)?amount+" ":"") + request.getType() + ((type!=null)?" | Descripcion: "+type:"") + ((color!=null)?" | "+color:"") + 
                ((size!=null)?" | "+size+darUnidadId(sizeUnit)+" ":"") + ((price!=null)?" | "+price+"€":"");
    }

    private static String darUnidadId(int id) {
        servicios.DarUnidades_Service service = new servicios.DarUnidades_Service();
        servicios.DarUnidades port = service.getDarUnidadesPort();
        return port.darUnidadId(id);
    }
}
