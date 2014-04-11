/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.ClientCEN;
import CEN.ScrapYardCEN;
import javax.crypto.SecretKey;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import logger.ClientLogger;
import logger.SYLogger;
import security.AES;
import security.KeysManager;

/**
 *
 * @author fran
 */
@WebService(serviceName = "LoginDesguace")
public class LoginDesguace {

    KeysManager _keysManager = KeysManager.GetInstance();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Login_Des")
    public String Login_Des(@WebParam(name = "id") int id,@WebParam(name = "Password") String Password, @WebParam(name = "cif") String cif) {
        
        SecretKey key = (SecretKey)_keysManager.getKey(id);
        
        
        String error="";
        try{
                Password=AES.decrypt(Password, key);
                cif=AES.decrypt(cif, key);
                ScrapYardCEN scry=ScrapYardCEN.getByCIF(cif);
                int type=1;

                if(scry==null)
                {
                    error="CIF incorrecto";
                    error=AES.encrypt(error, key);
                    type = -1;
                }
                else if(scry!=null && !scry.getPassword().equals(Password))
                {
                    error="Password Incorrecto";
                    error=AES.encrypt(error, key);
                    
                    type = -1;
                }

                SYLogger.setLogMessage(type, cif, error);
         } catch (Exception ex) {
            error = "Error: No se ha podido descifrar el AES";
            ClientLogger.setLogMessage(-2,cif,error);
            
        }
         return error;
        
    }
}
