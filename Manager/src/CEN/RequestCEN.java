/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CEN;

import CAD.RequestCAD;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author alberto
 */
public class RequestCEN {
    private int code;
    private String criteria;
    private int amount;
    private UserCEN user;

    public RequestCEN() {
    }
        
    public RequestCEN(String criteria, int amount, ClientCEN client) {        
        setAttributes(criteria, amount, client);                
    }
    
    public void setAttributes(String criteria, int amount, ClientCEN client) {
        this.criteria = criteria;
        this.amount = amount;
        this.user= client;
        
        if(user==null)
            userError();
    }
    
    public void userError() {
        System.err.println("Error: se ha realizado una solicitud sin ser usuario del sistema");
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UserCEN getUser() {
        return user;
    }

    public void setUser(ClientCEN user) {
        this.user = user;
    }
            
    public void insert() {
        this.code=RequestCAD.insert(this);
    }
    
    public static RequestCEN getByCode(int code) {
        Hashtable ht = RequestCAD.getByCode(code); 
        RequestCEN req = new RequestCEN();
        
        if(!ht.isEmpty()) {
            req.setAttributes((String)ht.get("criteria"), Integer.parseInt(ht.get("amount").toString()), ClientCEN.getById(Integer.parseInt(ht.get("user").toString())));                  
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
                RequestCEN req=new RequestCEN();                
                
                req.setAttributes((String)ht.get("criteria"), Integer.parseInt(ht.get("amount").toString()),ClientCEN.getById((int)ht.get("user")));                                  
                req.code=Integer.parseInt(ht.get("code").toString());
                all.add(req);
            }           
        }        
        return all;
    }
    
    public void update(String criteria, int amount, ClientCEN client) {        
        setAttributes(criteria, amount, client);
        RequestCAD.update(this);
    }
                    
    public void delete() {
        if(code != -1){
            RequestCAD.delete(code);
        }
    }            
    
    @Override
    public String toString(){
        return code + " " + criteria + " " + amount + " " + user.getId();
    }
}
