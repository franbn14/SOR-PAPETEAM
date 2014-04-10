/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author esteve
 */
public class RSA {

    public static String toHexadecimal(byte[] digest){
        String hash = "";
        
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }
    
    /*public static String encrypt(GenKeys keys, String msg) throws Exception {
        Signature sig = Signature.getInstance(keys.getPrivate().getAlgorithm());
        SignedObject so = new SignedObject(msg, keys.getPrivate(), sig);
        return so.toString();
    }*/
    
    /*public static String encrypt(PublicKey key, String msg) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipheredText = cipher.doFinal(msg.getBytes());
        BASE64Encoder b64e = new BASE64Encoder();
        return b64e.encode(cipheredText);
    }*/
    
    public static String encrypt(PublicKey publicKey, PrivateKey privateKey, String msg) throws Exception {
        /*************** CIFRADO ****************/
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipheredText = cipher.doFinal(msg.getBytes());
        BASE64Encoder b64e = new BASE64Encoder();
        String cryptedText = b64e.encode(cipheredText);
        
        /**************** FIRMA ******************/
        Signature sig = Signature.getInstance("MD5WithRSA");
        sig.initSign(privateKey);
        sig.update(msg.getBytes());
        String sign = b64e.encode(sig.sign());
        
        /************ GENERACION JSON *************/
        Package pack = new Package(cryptedText, sign);
        return pack.toJSON();
        
    }
    
    /*public static String decrypt(PrivateKey key, String msg) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        BASE64Decoder b64d = new BASE64Decoder();
        byte[] decipheredText = cipher.doFinal(b64d.decodeBuffer(msg));
        return new String(decipheredText);
    }*/
    
    public static String decrypt(PublicKey publicKey, PrivateKey privateKey, String msg, String sign) throws Exception {
        /************** DESCIFRADO ****************/
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder b64d = new BASE64Decoder();
        byte[] decipheredText = cipher.doFinal(b64d.decodeBuffer(msg));
        String text = new String(decipheredText);
        
        /************* VERIFICACIÓN ***************/
        byte[] signature = b64d.decodeBuffer(sign);
        Signature sig = Signature.getInstance("MD5WithRSA");
        sig.initVerify(publicKey);
        sig.update(text.getBytes());
        
        if (sig.verify(signature)) { 
            return text;
        }
         else 
            return "Error de verificación";
        
        
        
    }
}