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

    public RequestCEN(int code, String criteria, int amount, ClientCEN client) {
        this.code = code;
        this.criteria = criteria;
        this.amount = amount;
        this.user=client;
    }
    
    public RequestCEN(int code, String criteria, int amount, GarageCEN garage) {
        this.code = code;
        this.criteria = criteria;
        this.amount = amount;
        this.user=garage;
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

    public void setUser(UserCEN user) {
        this.user = user;
    }
    
    public void insert() {
        
    }
    
     public static ArrayList<RequestCEN> getAllUsers(){
        ArrayList<Hashtable> values = RequestCAD.getAll();
        ArrayList<RequestCEN> all = new ArrayList<RequestCEN>();
        for(Hashtable ht : values){
            /*RequestCEN req = new RequestCEN((String)ht.get("criteria"), (int)ht.get("amount"));
            req.code = (int) ht.get("code");
            all.add(req);*/
        }        
        return all;
    }
}
