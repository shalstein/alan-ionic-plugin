package com.alan.alanvoice;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;

import com.alan.alansdk.Alan;
import com.alan.alansdk.alanbase.DialogState;
import com.alan.alansdk.button.AlanButton;
import com.alan.alansdk.BasicSdkListener;
import android.support.annotation.NonNull;



public class AlanVoice extends CordovaPlugin {

    private static final String TAG = "plugins.AlanVoice";
    private DialogState alanState;
    private Alan sdk;
    private AlanStateListener stateListener = new AlanStateListener();


    public void initialize(CordovaInterface cordova, CordovaWebView webView){
        super.initialize(cordova, webView);

        Log.d(TAG, "intitalizing AlanVoice plugin");
        this.sdk = Alan.getInstance();
        this.sdk.init("f18a4135b0857d6ee7fe2f0078af3aeb2e956eca572e1d8b807a3e2338fdd0dc/stage");
        this.alanState = DialogState.IDLE;
        this.sdk.registerCallback(this.stateListener);



        // if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
        //     ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), PERMISSION_REQUEST_CODE);
        // }

    }

    private void start()
    {
        if (this.sdk == null) {
            return;
        }
        if (this.alanState != DialogState.IDLE)
        {
            this.sdk.turnOff();
        }
        else
        {
            this.sdk.turnOn();
            this.sdk.record();
            this.sdk.speak();
        }
    }

    private DialogState getState()
    {
      return this.alanState;
    }



    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if(action.equals("coolMethod")){
            Log.d(TAG, "this is awesome");
        }
        else if(action.equals("start")) {
            this.start();
        }
        else if(action.equals("getState")) {
            PluginResult stateResult = new PluginResult(PluginResult.Status.OK, (this.getState()).toString());
            callbackContext.sendPluginResult(stateResult);
        }
        else if(action.equals("greet")) {

            String name = data.getString(0);
            String message = "Hello, " + name;
            callbackContext.success(message);

        }
        return true;
    }

    class AlanStateListener extends BasicSdkListener
    {
        AlanStateListener() {}

        public void onDialogStateChanged(@NonNull DialogState dialogState)
        {
            AlanVoice.this.alanState = dialogState;
        }
    }
}




