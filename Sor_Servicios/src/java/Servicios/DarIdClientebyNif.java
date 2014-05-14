/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.ClientCEN;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import CEN.ClientCEN;
import CAD.UserCAD;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import security.AES;
import security.KeysManager;

/**
 *
 * @author fran
 */
@WebService(serviceName = "DarIdClientebyNif")
public class DarIdClientebyNif {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "GetIDWeb")
    public int GetIDWeb( @WebParam(name = "nif") String nif) {
        //TODO write your implementation code here:
         ClientCEN cli=ClientCEN.getByNIF(nif);
        if(cli!=null)
            return cli.getId();
        return 0;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "GetID")
    public int GetID(@WebParam(name = "id") int id, @WebParam(name = "nif") String nif) {
        SecretKey key = (SecretKey) KeysManager.GetInstance().getKey(id);
        try {
            String keyString = key.getEncoded().toString();
            System.out.println(keyString);
            nif = AES.decrypt(nif, key);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        
        ClientCEN cli=ClientCEN.getByNIF(nif);
        if(cli!=null)
            return cli.getId();
        return 0;
    }
}
