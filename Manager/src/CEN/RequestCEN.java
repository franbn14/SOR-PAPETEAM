/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CEN;

import CAD.RequestCAD;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author alberto
 */
public class RequestCEN {
    private int code;
    private Date dateLine;
    private String type;
    private Double size;
    private int sizeUnit;
    private String color;
    private Integer amount;
    private Double maxPrice;
    private ClientCEN client;
    private boolean autoElect;
    private boolean finished;

    private void setAttributes(Date dateLine, String type, Double size, int sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoElect, boolean finished) {
        this.dateLine = dateLine;
        this.type = type;
        this.size = size;
        this.sizeUnit =  sizeUnit;
        this.color = color;
        this.amount = amount;
        this.maxPrice = maxPrice;
        this.amount = amount;
        this.client= client;
        this.autoElect = autoElect;
        this.finished = finished;
        
        if(client == null)
            userError();
    }
    
    public RequestCEN() {
    }
        
    public RequestCEN(Date dateLine, String type, Double size, int sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoSelec, boolean finished) {        
        setAttributes(dateLine, type, size, sizeUnit, color, amount, maxPrice, client, autoSelec, finished);      
        this.code = -1;
    }
    
    public void userError() {
        System.err.println("Error: se ha realizado una solicitud sin ser usuario del sistema");
    }
    
    public int getCode() {
        return code;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UserCEN getClient() {
        return client;
    }

    public void setClient(ClientCEN client) {
        this.client = client;
    }

    public Date getDateLine() {
        return dateLine;
    }

    public void setDateLine(Date dateLine) {
        this.dateLine = dateLine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
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

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public boolean isAutoElect() {
        return autoElect;
    }

    public void setAutoElect(boolean autoSelec) {
        this.autoElect = autoSelec;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
            
    public void insert() {
        if(this.code == -1)
            this.code = RequestCAD.insert(dateLine, type, size, sizeUnit, color, amount, maxPrice,  client.getId(), autoElect, finished);
    }
    

    public static RequestCEN getByCode(int code) {
        Hashtable ht = RequestCAD.getByCode(code); 
        RequestCEN req = null;
        
        if(!ht.isEmpty()) {
            req = new RequestCEN((Date)ht.get("dateLine"), 
                                 (String)ht.get("type"), 
                                 ((Double)ht.get("size") == -1.0)? null : (Double)ht.get("size"), 
                                 (int)ht.get("sizeUnit"), 
                                 (((String)ht.get("color")).equals("null"))? null : (String)ht.get("color"), 
                                 ((Integer)ht.get("amount") == -1)? null : (Integer)ht.get("amount"), 
                                 ((Double)ht.get("maxPrice") == -1.0)? null : (Double)ht.get("maxPrice"), 
                                 ClientCEN.getByID((int)ht.get("client")), 
                                 (boolean)ht.get("autoElect"), 
                                 (boolean)ht.get("finished"));
            req.code=(int)ht.get("code");
        }            
        
        return req;
    }
    
    public static ArrayList<RequestCEN> getAllRequests(){
        ArrayList<Hashtable> values = RequestCAD.getAll();
        ArrayList<RequestCEN> all = null;
        
        if(!values.isEmpty()) {
            all = new ArrayList<RequestCEN>();            

            for(Hashtable ht : values){                            
                RequestCEN req = new RequestCEN((Date)ht.get("dateLine"), 
                                                (String)ht.get("type"), 
                                                ((Double)ht.get("size") == -1.0)? null : (Double)ht.get("size"), 
                                                (int)ht.get("sizeUnit"), 
                                                (((String)ht.get("color")).equals("null"))? null : (String)ht.get("color"), 
                                                ((Integer)ht.get("amount") == -1)? null : (Integer)ht.get("amount"), 
                                                ((Double)ht.get("maxPrice") == -1.0)? null : (Double)ht.get("maxPrice"), 
                                                ClientCEN.getByID((int)ht.get("client")), 
                                                (boolean)ht.get("autoElect"), 
                                                (boolean)ht.get("finished"));
                req.code=(int)ht.get("code");
                all.add(req);
            }           
        }        
        return all;
    }
    
    public void update(Date dateLine, String type, Double size, int sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoElect, boolean finished) {        
        setAttributes(dateLine, type, size, sizeUnit, color, amount, maxPrice, client, autoElect, finished);
        RequestCAD.update(this.code, dateLine, type, size, sizeUnit, color, amount, maxPrice, client.getId(), autoElect, finished);
    }
                    
    public void delete() {
        if(code != -1){
            RequestCAD.delete(code);
        }
    }           
    
    
    @Override
    public String toString(){
        return code + " " + dateLine + " " + type + " " + size + " " + sizeUnit + " " + color  + 
                " " + amount + " " + maxPrice + " " + client.getId() + " " + autoElect + " " + finished;
    }
}
