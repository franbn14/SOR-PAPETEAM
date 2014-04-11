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
@WebService(serviceName = "DarNombreCliente")
public class DarNombreCliente {

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
    @WebMethod(operationName = "DarNombreDesguace")
    public String DarNombreDesguace(@WebParam(name = "id")int id,@WebParam(name = "cif") String cif) {
        //TODO write your implementation code here:
        SecretKey key = (SecretKey)_keysManager.getKey(id);
        String error="";
        try
        {
                cif = AES.decrypt(cif, key);
              ScrapYardCEN scry=ScrapYardCEN.getByCIF(cif);
              
                return AES.encrypt(scry.getName(),key);
        }
      catch (Exception ex) {
            error = "Error: No se ha podido descifrar el AES";
            ClientLogger.setLogMessage(-2,cif,error);
           
        }
        return error;
       
    }
}
