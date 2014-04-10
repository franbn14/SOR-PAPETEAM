package security;


import com.sun.crypto.provider.SunJCE;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author esteve
 */
public class AES {
    
    public static SecretKey GenKey() throws Exception{
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        return kgen.generateKey();
    }
    
    private static Cipher getCipher(int mode, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", new SunJCE());
        byte[] iv = "e675f725e675f725".getBytes("UTF-8");
        cipher.init(mode, key, new IvParameterSpec(iv));
        return cipher;
    }
    
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, secretKey);
        BASE64Encoder b64e = new BASE64Encoder();
        return b64e.encode(cipher.doFinal(plainText.getBytes("UTF-8")));
    }

    public static String decrypt(String cipherText, SecretKey secretKey) throws Exception {
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE, secretKey);
        BASE64Decoder b64d = new BASE64Decoder();
        return new String(cipher.doFinal(b64d.decodeBuffer(cipherText)), "UTF-8");
    }
}
