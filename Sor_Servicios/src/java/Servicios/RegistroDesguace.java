/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import CEN.ScrapYardCEN;
import Email.Email;
import Email.EmailFactoria;
import javax.crypto.SecretKey;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.mail.MessagingException;
import logger.ClientLogger;
import logger.SYLogger;
import security.AES;
import security.KeysManager;

/**
 *
 * @author fran
 */
@WebService(serviceName = "RegistroDesguace")
public class RegistroDesguace {
    KeysManager _keysManager = KeysManager.GetInstance();
    
    @WebMethod(operationName = "RegistroWeb")
    public String RegistroWeb(@WebParam(name = "Cif") String Cif, @WebParam(name = "Nombre") String Nombre, @WebParam(name = "Password") String Password, @WebParam(name = "Direccion") String Direccion, @WebParam(name = "Email") String Email) {
        String error = "";
        
        ScrapYardCEN scry = ScrapYardCEN.getByCIF(Cif);
        int type = 3;
        
        if (scry != null) {
            error = "Error: CIF ya registrado";
            type = -2;
        } else {
            scry = new ScrapYardCEN(Nombre, Password, Direccion, Cif, Email);
            scry.insert();

            //Enviamos el email para notificar de su registro
            Email email = EmailFactoria.getEmail(EmailFactoria.tipoEmail.Registro, scry);
            try {
                email.send();
            } catch (MessagingException ex) {
                System.err.println(ex.getMessage());
            }

        }
        
        SYLogger.setLogMessage(type, Cif, error);
        return error;
    }
    
    @WebMethod(operationName = "Registro")
    public String Registro(@WebParam(name = "id") int id, @WebParam(name = "Cif") String Cif, @WebParam(name = "Nombre") String Nombre, @WebParam(name = "Password") String Password, @WebParam(name = "Direccion") String Direccion, @WebParam(name = "Email") String Email) {
        SecretKey key = (SecretKey)_keysManager.getKey(id);
        String error = "";
        
        try {
            Cif = AES.decrypt(Cif, key);
            Nombre = AES.decrypt(Nombre, key);
            Direccion = AES.decrypt(Direccion, key);
            Password = AES.decrypt(Password, key);
            Email = AES.decrypt(Email, key);
        } catch (Exception ex) {
            error = "Error: No se ha podido descifrar el AES";
            ClientLogger.setLogMessage(-2,Cif,error);
            return error;
        }
        
        ScrapYardCEN scry = ScrapYardCEN.getByCIF(Cif);
        int type = 3;
        
        if (scry != null) {
            error = "Error: CIF ya registrado";
            type = -2;
        } else {
            scry = new ScrapYardCEN(Nombre, Password, Direccion, Cif, Email);
            scry.insert();

            //Enviamos el email para notificar de su registro
            Email email = EmailFactoria.getEmail(EmailFactoria.tipoEmail.Registro, scry);
            try {
                email.send();
            } catch (MessagingException ex) {
                System.err.println(ex.getMessage());
            }

        }
        
        SYLogger.setLogMessage(type, Cif, error);
        return error;
    }
}
