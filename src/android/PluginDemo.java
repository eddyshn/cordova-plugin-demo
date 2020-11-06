package cordova.plugin.plugindemo;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.widget.Toast;


/**
 * This class echoes a string called from JavaScript.
 */
public class PluginDemo extends CordovaPlugin {

    CallbackContext mCallbackContext;

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        PluginResult result = new PluginResult(PluginResult.Status.OK, "onResume");
        result.setKeepCallback(true);
        this.mCallbackContext.sendPluginResult(result);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        PluginResult result = new PluginResult(PluginResult.Status.OK, "onActivityResult");
        result.setKeepCallback(true);
        this.mCallbackContext.sendPluginResult(result);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    private void test(){
        PluginResult result = new PluginResult(PluginResult.Status.OK, "YOUR_MESSAGE");
        result.setKeepCallback(true);
        this.mCallbackContext.sendPluginResult(result);


        PluginResult result2 = new PluginResult(PluginResult.Status.OK, "wwwYOUR_MESSAGE");
        result2.setKeepCallback(true);
        this.mCallbackContext.sendPluginResult(result2);
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        mCallbackContext = callbackContext;
        if (message != null && message.length() > 0) {
        Toast.makeText(cordova.getContext(),message,Toast.LENGTH_SHORT).show();

            PluginResult result = new PluginResult(PluginResult.Status.OK, "aaaaa");
            result.setKeepCallback(true);
            this.mCallbackContext.sendPluginResult(result);

            PluginResult result2 = new PluginResult(PluginResult.Status.OK, "bbbb");
            result2.setKeepCallback(true);
            this.mCallbackContext.sendPluginResult(result2);

            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    // Then you're allowed to execute more than twice a callback.
                    PluginResult resultA = new PluginResult(PluginResult.Status.OK, "myfirstJSONResponse");
                    resultA.setKeepCallback(true);
                    PluginDemo.this.mCallbackContext.sendPluginResult(resultA);

                    // Some more code

                    Boolean something = true;

                    // bla bla bla code


                    PluginResult resultB = new PluginResult(PluginResult.Status.OK, "secondJSONResponse");
                    resultB.setKeepCallback(true);
                    PluginDemo.this.mCallbackContext.sendPluginResult(resultB);
                }
            });

        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
