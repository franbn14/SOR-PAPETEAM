/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import CEN.ScrapYardCEN;
import Email.Email;
import Email.EmailFactoria;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.mail.MessagingException;

/**
 *
 * @author fran
 */
@WebService(serviceName = "RegistroDesguace")
public class RegistroDesguace {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Registro")
    public String Registro(@WebParam(name = "Cif") String Cif, @WebParam(name = "Nombre") String Nombre, @WebParam(name = "Password") String Password, @WebParam(name = "Direccion") String Direccion, @WebParam(name = "Email") String Email) {
        //TODO write your implementation code here:


        String error = "";
        ScrapYardCEN scry = ScrapYardCEN.getByCIF(Cif);

        if (scry != null) {
            error = "Error: CIF ya registrado";
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
        return error;
    }
}
