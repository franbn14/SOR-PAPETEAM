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
public class Package {
    public String msg;
    public String sign;

    public Package(String msg, String sign) {
        this.msg = msg;
        this.sign = sign;
    }

    public Package(String json) {
        Gson gson = new Gson();
        JsonElement jelement = new JsonParser().parse(json);
        Package p = (Package) gson.fromJson(jelement, Package.class);
        msg = p.msg;
        sign = p.sign;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
        String json = gson.toJson(this);
        return json;
    }
}
