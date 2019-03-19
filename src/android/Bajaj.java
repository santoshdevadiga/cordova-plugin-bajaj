package org.apache.cordova.bajaj;

import java.util.TimeZone;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.provider.Settings;
import org.apache.cordova.bajaj.CryptoLib;

public class Bajaj extends CordovaPlugin {
    public static final String TAG = "Bajaj";
    public Bajaj() {
    }

    
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("getEncryptedData".equals(action)) 
		{
			
			String plaintext = args.getString(0); //plain text
			String saltkey = args.getString(2);  //salt key
            String supplierid = args.getString(1); //supplier id
			
			
			encrypt(plaintext,supplierid,saltkey,callbackContext);
	
        }
        else {
            return false;
        }
        return true;
    }
	
	
	private void encrypt(String plaintext,String supplierid,String saltkey, CallbackContext callbackContext) 
	{

        try 
        {

            CryptoLib _crypt = new CryptoLib();
            String key = CryptoLib.SHA256(supplierid, 32);
            String iv = saltkey.substring(0, 16); //16 bytes = 128 bit
            String output = _crypt.encrypt(plaintext, key, iv); //encrypt
			
			JSONObject r = new JSONObject();
            r.put("data", output);
            callbackContext.success(r);
			
        }
		catch (Exception ex)
		{
			//JSONObject err = new JSONObject();
            //err.put("error", ex.getMessage());
            callbackContext.error("error"+ex.getMessage());
        }
			
       
    }

}
