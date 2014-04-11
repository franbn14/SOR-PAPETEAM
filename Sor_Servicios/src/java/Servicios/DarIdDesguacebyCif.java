/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.ScrapYardCEN;
import javax.crypto.SecretKey;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import logger.ClientLogger;
import security.AES;
import security.KeysManager;

/**
 *
 * @author fran
 */
@WebService(serviceName = "DarIdDesguacebyCif")
public class DarIdDesguacebyCif {

    /**
     * This is a sample web service operation

*/
     KeysManager _keysManager = KeysManager.GetInstance();
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getIdDes")
    public int getID(@WebParam(name = "id") int id,@WebParam(name = "nif") String nif) {
        
        SecretKey key = (SecretKey)_keysManager.getKey(id);
        String error="";
        try{
        
            nif = AES.decrypt(nif, key);
            ScrapYardCEN cli=ScrapYardCEN.getByCIF(nif);
            if(cli!=null)
                return cli.getId();
        }
        
         catch (Exception ex) {
            error = "Error: No se ha podido descifrar el AES";
            ClientLogger.setLogMessage(-2,nif,error);
           
        }
        return 0;
    }
}
