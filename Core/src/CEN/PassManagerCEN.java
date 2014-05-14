/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEN;

import CAD.PassManagerCAD;
import CAD.UserCAD;
import Email.Email;
import Email.EmailFactoria;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author esteve
 */
public class PassManagerCEN {
    public static void forgetPass(String doc) throws Exception{
        UserCEN usr = ClientCEN.getByNIF(doc);
        if(usr == null)
            usr = ScrapYardCEN.getByCIF(doc);
        if(usr == null)
            throw new Exception("El usuario " + doc + " no existe");
        
        try {
            HashMap dbUsr = PassManagerCAD.getByUser(usr.getId());
            if(! dbUsr.isEmpty()){
                PassManagerCAD.delete((String)dbUsr.get("user"));
            }
            String recordID = PassManagerCAD.insert(usr.getId());

            ArrayList args = new ArrayList();
            args.add(usr);
            args.add(recordID);
            Email email = EmailFactoria.getEmail(EmailFactoria.tipoEmail.EmailRecPass, args);
            email.send();
        }
        catch(Exception ex){
            throw new Exception("Error interno");
        }
    }
    
    public static void changePass(String token, String doc, String pass) throws Exception{
        
        HashMap info = PassManagerCAD.getByID(token);
        if(info.isEmpty())
            throw new Exception("Token incorrecto");
        
        UserCEN usr = ClientCEN.getByNIF(doc);
        if(usr == null)
            usr = ScrapYardCEN.getByCIF(doc);
        if(usr == null)
            throw new Exception("El usuario " + doc + " no existe");
        
        if((int) info.get("user") != usr.getId())
            throw new Exception("Usuario incorrecto");
        
        UserCAD.updatePassword(usr.getId(), pass);
        PassManagerCAD.delete(token);
    }
}
