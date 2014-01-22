/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CEN;


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
<<<<<<< HEAD
    private int sizeUnit;
=======
    private Integer sizeUnit;
>>>>>>> 77745aba8873573b304986371074292ad3268110
    private String color;
    private Integer amount;
    private Double price;
    private RequestCEN request;
    private ScrapYardCEN scrapyard;
    private boolean accepted;

    /*public OfferCEN() {
    }*/

<<<<<<< HEAD
    private void setAttributes(String type, Double size, int sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard, boolean accepted) {
=======
    private void setAttributes(String type, Double size, Integer sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard, boolean accepted) {
>>>>>>> 77745aba8873573b304986371074292ad3268110
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
    
<<<<<<< HEAD
    public OfferCEN(String type, Double size, int sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard, boolean accepted) {        
=======
    public OfferCEN(String type, Double size, Integer sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard, boolean accepted) {        
>>>>>>> 77745aba8873573b304986371074292ad3268110
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

<<<<<<< HEAD
    public int getSizeUnit() {
=======
    public Integer getSizeUnit() {
>>>>>>> 77745aba8873573b304986371074292ad3268110
        return sizeUnit;
    }

    public String getSizeUnitString(){
        return UnitsCEN.getByID(sizeUnit);
    }
    
<<<<<<< HEAD
    public void setSizeUnit(int sizeUnit) {
=======
    public void setSizeUnit(Integer sizeUnit) {
>>>>>>> 77745aba8873573b304986371074292ad3268110
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
<<<<<<< HEAD
        return amount+" "+type;
    }
    
   
=======
        return ((amount!=null)?amount+" ":"") + type + " " + ((color!=null)?color+" ":"") + 
                ((size!=null)?size+" "+darUnidadId(sizeUnit)+" ":"");
    }

    private static String darUnidadId(int id) {
        servicios.DarUnidades_Service service = new servicios.DarUnidades_Service();
        servicios.DarUnidades port = service.getDarUnidadesPort();
        return port.darUnidadId(id);
    }
    
    
>>>>>>> 77745aba8873573b304986371074292ad3268110
}
