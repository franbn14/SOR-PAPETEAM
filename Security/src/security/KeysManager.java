/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;


import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import javax.crypto.SecretKey;

/**
 *
 * @author esteve
 */
public class KeysManager {
    private int _cont;
    private HashMap<Integer, Key> _keys;
    private GenKeys _serverKeys;
    private static KeysManager _instance = new KeysManager();
    
    private KeysManager(){
        _cont = 0;
        _keys = new HashMap<Integer, Key>();
        try {
            _serverKeys = new GenKeys();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    public static KeysManager GetInstance(){
        return _instance;
    }
    
    public int addKey(Key key){
        int pos = _cont;
        _keys.put(pos, key);
        _cont++;
        return pos;
    }
    
    public Key getKey(int pos){
        return _keys.get(pos);
    }
    
    public PublicKey getPubKey(){
        return _serverKeys.getPublic();
    }
    
    public PrivateKey getPriKey(){
        return _serverKeys.getPrivate();
    }
    
    public SecretKey GenAESKey(int id){
        SecretKey secretKey = null;
        try {
             secretKey = AES.GenKey();
            _keys.put(id, secretKey);
        }
        catch(Exception ex){
            System.err.println(ex);
        }
        return secretKey;
    }
    
    public void CloseSession(int id){
        _keys.remove(id);
    }
}

