/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 *
 * @author esteve
 */
public class PubKey {
        public int id;
        public String modulus;
        public String exponent;

        public PubKey(int id, String modulus, String exponent){
            this.id = id;
            this.modulus = modulus;
            this.exponent = exponent;
        }
        
        public PubKey(String json){
            Gson gson = new Gson();
            JsonElement jelement = new JsonParser().parse(json);
            PubKey pk = (PubKey) gson.fromJson(jelement, PubKey.class);
            id = pk.id;
            modulus = pk.modulus;
            exponent = pk.exponent;
        }
        
        public String toJSON() {
            Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
            String json = gson.toJson(this);
            return json;
        }
    }
