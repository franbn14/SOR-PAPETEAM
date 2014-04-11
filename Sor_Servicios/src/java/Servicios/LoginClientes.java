/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import CEN.ClientCEN;
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
@WebService(serviceName = "LoginClientes")
public class LoginClientes {

    @WebMethod(operationName = "LoginWeb")
    public String LoginWeb(@WebParam(name = "Password") String Password, @WebParam(name = "nif_dni") String nif_dni) {
        //TODO write your implementation code here:
        String error = "";
        ClientCEN cli = ClientCEN.getByNIF(nif_dni);
        int type = 1;

        if (cli == null) {
            error = "Login incorrecto";
            type = -1;
        } else if (cli != null && !cli.getPassword().equals(Password)) {
            error = "Password Incorrecto";
            type = -1;
        }
        ClientLogger.setLogMessage(type, nif_dni, error);

        return error;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Login")
    public String Login(@WebParam(name = "id") int id, @WebParam(name = "Password") String Password, @WebParam(name = "nif_dni") String nif_dni) {

        SecretKey key = (SecretKey) KeysManager.GetInstance().getKey(id);
        try {
            Password = AES.decrypt(Password, key);
            nif_dni = AES.decrypt(nif_dni, key);
        } catch (Exception ex) {
            System.err.println(ex);
        }


        String error = "";
        ClientCEN cli = ClientCEN.getByNIF(nif_dni);
        int type = 1;

        if (cli == null) {
            error = "Login incorrecto";
            type = -1;
        } else if (cli != null && !cli.getPassword().equals(Password)) {
            error = "Password Incorrecto";
            type = -1;
        }
        ClientLogger.setLogMessage(type, nif_dni, error);
        try {
            error = AES.encrypt(error, key);
        } catch (Exception ex) {
            System.err.println(ex);
        }

        return error;
    }
}
