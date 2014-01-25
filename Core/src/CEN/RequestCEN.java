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
    private Date deadline;
    private String type;
    private Double size;
    private Integer sizeUnit;
    private String color;
    private Integer amount;
    private Double maxPrice;
    private ClientCEN client;
    private boolean autoElect;
    private boolean finished;
    private boolean expired;
    
    private static RequestCEN Hashtable2RequesCEN (Hashtable ht){
        RequestCEN request = null;
        if(!ht.isEmpty()) {
            request = new RequestCEN((Date)ht.get("deadline"), 
                                 (String)ht.get("type"), 
                                 ((Double)ht.get("size") == -1.0)? null : (Double)ht.get("size"), 
                                 (int)ht.get("sizeUnit"), 
                                 (((String)ht.get("color")).equals("null"))? null : (String)ht.get("color"), 
                                 ((Integer)ht.get("amount") == -1)? null : (Integer)ht.get("amount"), 
                                 ((Double)ht.get("maxPrice") == -1.0)? null : (Double)ht.get("maxPrice"), 
                                 ClientCEN.getByID((int)ht.get("client")), 
                                 (boolean)ht.get("autoElect"), 
                                 (boolean)ht.get("finished"),
                                 (boolean)ht.get("expired"));
            request.code=(int)ht.get("code");
        }
        
        return request;
    }
    
    private static ArrayList<RequestCEN> HashtableArray2RequesCENArray (ArrayList<Hashtable> values){
        ArrayList<RequestCEN> all = null;
        if(!values.isEmpty()) {
            all = new ArrayList<RequestCEN>();            
            for(Hashtable ht : values){                            
                all.add(Hashtable2RequesCEN(ht));
            }           
        }        
        return all;
    }

    private void setAttributes(Date deadline, String type, Double size, Integer sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoElect, boolean finished, boolean expired) {
        this.deadline = deadline;
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
        this.expired = expired;
        
        if(client == null)
            userError();
    }
    
    public RequestCEN() {
    }
        
    public RequestCEN(Date deadline, String type, Double size, Integer sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoSelec, boolean finished, boolean expired) {        
        setAttributes(deadline, type, size, sizeUnit, color, amount, maxPrice, client, autoSelec, finished, expired);      
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

    public Date getdeadline() {
        return deadline;
    }

    public void setdeadline(Date deadline) {
        this.deadline = deadline;
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

    public void setSize(double size) {
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

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
    
            
    public int insert() {
        if(this.code == -1)
            this.code = RequestCAD.insert(deadline, type, size, sizeUnit, color, amount, maxPrice,  client.getId(), autoElect, finished, expired);
        return this.code;
    
    }
    

    public static RequestCEN getByCode(int code) {
        Hashtable ht = RequestCAD.getByCode(code); 
        return Hashtable2RequesCEN(ht);
    }
    
    public static ArrayList<RequestCEN> getAllRequests(){
        ArrayList<Hashtable> values = RequestCAD.getAll();
        return HashtableArray2RequesCENArray(values);
    }
    
    public static ArrayList<RequestCEN> getAllByNIF(String nif){
        ArrayList<Hashtable> values = RequestCAD.getAllByNIF(nif);
        return HashtableArray2RequesCENArray(values);
    }
    
    public static ArrayList<RequestCEN> getAllFinishedByNIF(String nif){
        ArrayList<Hashtable> values = RequestCAD.getAllFinishedByNIF(nif);
        return HashtableArray2RequesCENArray(values);
    }
    
    public static ArrayList<RequestCEN> getAllOpenedByNIF(String nif){
        ArrayList<Hashtable> values = RequestCAD.getAllOpenedByNIF(nif);
        return HashtableArray2RequesCENArray(values);
    }
    
    public static ArrayList<RequestCEN> getAllOpened(){
        ArrayList<Hashtable> values = RequestCAD.getAllOpened();
        return HashtableArray2RequesCENArray(values);
    }
    
    public void update(Date deadline, String type, Double size, Integer sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoElect, boolean finished, boolean expired) {        
        setAttributes(deadline, type, size, sizeUnit, color, amount, maxPrice, client, autoElect, finished, expired);
        RequestCAD.update(this.code, deadline, type, size, sizeUnit, color, amount, maxPrice, client.getId(), autoElect, finished, expired);
    }
                    
    public void delete() {
        if(code != -1){
            RequestCAD.delete(code);
        }
    }           
    
    
    @Override
    public String toString(){
        return code + " " + deadline + " " + type + " " + size + " " + sizeUnit + " " + color  + 
                " " + amount + " " + maxPrice + " " + client.getId() + " " + autoElect + " " + finished;
    }
}
