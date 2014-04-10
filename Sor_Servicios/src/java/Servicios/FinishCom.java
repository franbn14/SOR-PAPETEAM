/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import security.KeysManager;

/**
 *
 * @author esteve
 */
@WebService(serviceName = "FinishCom")
public class FinishCom {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "Finish")
    public void Finish(@WebParam(name = "id") int id) {
        KeysManager km = KeysManager.GetInstance();
        km.CloseSession(id);
    }
}
