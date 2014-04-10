/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

/**
 *
 * @author esteve
 */

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.SecretKey;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import security.KeysManager;
import security.PubKey;
import security.RSA;
import sun.misc.BASE64Encoder;

/**
 *
 * @author esteve
 */
@WebService(serviceName = "InitCom")
public class InitCom {
    private KeysManager keysManager = KeysManager.GetInstance();
    
    
    @WebMethod(operationName = "exchangeKeys")
    public String exchangeKeys(@WebParam(name = "modulus") String modulus, @WebParam(name = "exponent") String exponent) {
        //Asignamos la clave publica del cliente
        int id;
        try{
            BigInteger mod = new BigInteger(modulus);
            BigInteger exp = new BigInteger(exponent);
            RSAPublicKeySpec spec = new RSAPublicKeySpec(mod, exp);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey clientPubKey = factory.generatePublic(spec);
            id = keysManager.addKey(clientPubKey);
            
        }
        catch(Exception ex){
            return ex.toString();
        }
        
        //Devolvemos la clave publica en JSON
        RSAPublicKey RSAPubKey = (RSAPublicKey)keysManager.getPubKey();
        PubKey pubKey =  new PubKey(id, RSAPubKey.getModulus().toString(), RSAPubKey.getPublicExponent().toString());
        return pubKey.toJSON();
    }
    
    @WebMethod(operationName = "getAESKey")
    public String getAESKey(@WebParam(name = "id") int id){
        try {
            PublicKey pubKey = (PublicKey)keysManager.getKey(id);
            SecretKey key = keysManager.GenAESKey(id);
            BASE64Encoder b64 = new BASE64Encoder();
            String keyString = b64.encodeBuffer(key.getEncoded());
            return RSA.encrypt(pubKey, keysManager.getPriKey(), keyString);
        } catch (Exception ex) {
            return ex.toString();
        }
    }
}

