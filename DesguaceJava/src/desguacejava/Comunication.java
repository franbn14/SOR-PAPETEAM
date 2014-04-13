package desguacejava;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import security.GenKeys;
import security.PubKey;
import security.RSA;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
*
* @author esteve
*/
public class Comunication {
    private static Comunication _instance = new Comunication();
    
    private int ID;
    private PublicKey serverKey;
    private SecretKey aesKey;
    
    private Comunication(){
        try{
            //Generamos la pareja clave publica / clave privada
            GenKeys keys = new GenKeys();
            RSAPublicKey pubKey = (RSAPublicKey)keys.getPublic();
            PrivateKey priKey = keys.getPrivate();
            
            //intercambiamos la clave publica con el servidor
            BASE64Encoder b64e = new BASE64Encoder();
            String json = exchangeKeys(b64e.encode(pubKey.getModulus().toByteArray()), b64e.encode(pubKey.getPublicExponent().toByteArray()));
            
            //Parseamos el json y obtenemos el id, modulo y exponente
            PubKey pk = new PubKey(json);
            ID = pk.id;
            BigInteger mod = new BigInteger(pk.modulus);
            BigInteger exp = new BigInteger(pk.exponent);
            
            //Obtenemos la clave Publica del servidor;
            RSAPublicKeySpec spec = new RSAPublicKeySpec(mod, exp);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            serverKey = factory.generatePublic(spec);
            
            //Solicitamos clave AES
            json = getAESKey(ID);
            
            //Parseamos json y obetnemos mensaje y firma
            security.Package p = new security.Package(json);
            
            //Desciframos el mensaje
            String aesKeyString = RSA.decrypt(serverKey, priKey, p.msg, p.sign);
            BASE64Decoder b64d = new BASE64Decoder();
            byte[] aesKeyBytes = b64d.decodeBuffer(aesKeyString);
            aesKey = new SecretKeySpec(aesKeyBytes, 0, aesKeyBytes.length, "AES");
            
        }
        catch(Exception ex){
            System.err.println(ex);
        }
    }
    
    private static String exchangeKeys(java.lang.String modulus, java.lang.String exponent) {
        servicios.InitCom_Service service = new servicios.InitCom_Service();
        servicios.InitCom port = service.getInitComPort();
        return port.exchangeKeys(modulus, exponent);
    }

    private static String getAESKey(int id) {
        servicios.InitCom_Service service = new servicios.InitCom_Service();
        servicios.InitCom port = service.getInitComPort();
        return port.getAESKey(id);
    }
    
    private static void finish(int id) {
        servicios.FinishCom_Service service = new servicios.FinishCom_Service();
        servicios.FinishCom port = service.getFinishComPort();
        port.finish(id);
    }
    
    public static Comunication getInstance(){
        if(_instance == null){
            _instance = new Comunication();
        }
        return _instance;
    }
    
    public void Finish(){
        finish(ID);
        _instance = null;
    }

    public int getID() {
        return ID;
    }

    public PublicKey getServerKey() {
        return serverKey;
    }

    public SecretKey getAesKey() {
        return aesKey;
    }

}