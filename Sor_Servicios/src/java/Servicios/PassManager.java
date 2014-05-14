/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import CEN.PassManagerCEN;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author esteve
 */
@WebService(serviceName = "PassManager")
public class PassManager {

    @WebMethod(operationName = "forgetPass")
    public String forgetPass(@WebParam(name = "doc") String doc) {
        try{
            PassManagerCEN.forgetPass(doc);
            return "";
        }
        catch (Exception ex){
            return ex.getMessage();
        }
    }
    
    @WebMethod(operationName = "changePass")
    public String changePass(@WebParam(name = "token") String token, @WebParam(name = "doc") String doc, @WebParam(name = "pass") String pass) {
        try{
            PassManagerCEN.changePass(token, doc, pass);
            return "";
        }
        catch (Exception ex){
            return ex.getMessage();
        }
    }
    
}
