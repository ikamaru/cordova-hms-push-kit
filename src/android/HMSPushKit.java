package cordova.hms.push.kit;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

import android.text.TextUtils;
import android.util.Log;

import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.push.HmsMessageService;

/**
 * This class echoes a string called from JavaScript.
 */
public class HMSPushKit extends CordovaPlugin {
    private static final String TAG = "PushDemoLog";
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      CallbackContextSingleton.getInstance().setCallbackContext(callbackContext);
      if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }else if(action.equals("isHMSAvailable")) {
            this.isHMSAvailable(callbackContext);
            return true;
        }
        else if(action.equals("isGMSAvailable")) {
          this.isGMSAvailable(callbackContext);
          return true;
        }
        else if(action.equals("getToken")) {
          this.getToken();
          return true;
        }
        return false;
    }


    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void isHMSAvailable(CallbackContext callbackContext) {
      int available = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(cordova.getActivity());
      if(available == com.huawei.hms.api.ConnectionResult.SUCCESS) {
        //everything is fine and the user can make map requests
        Log.d(TAG, "isServicesOK: HMS is working");
        //callbackContext.success("HMS exists");
        CallbackContextSingleton.getInstance().getCallbackContext().success("HMS exists...");
      } else {
        CallbackContextSingleton.getInstance().getCallbackContext().error("HMS doesn't exist");
        //callbackContext.error("no GMS");
      }
    }
    private void isGMSAvailable(CallbackContext callbackContext) {
      int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(cordova.getActivity());
      if(available == ConnectionResult.SUCCESS) {
        //everything is fine and the user can make map requests
        Log.d(TAG, "isServicesOK: GMS is working");
        callbackContext.success("GMS exists...");
      } else {
        callbackContext.error("GMS doesn't exist");
      }
    }
    private void getToken() {
        Log.i(TAG, "get token: begin");

        // get token
        new Thread() {
            @Override
            public void run() {
                try {
                    // read from agconnect-services.json
                    String appId = AGConnectServicesConfig.fromContext(cordova.getActivity()).getString("client/app_id");
                    String pushtoken = HmsInstanceId.getInstance(cordova.getActivity()).getToken(appId, "HCM");
                    if(!TextUtils.isEmpty(pushtoken)) {
                        Log.i(TAG, "get token:" + pushtoken);
                        CallbackContextSingleton.getInstance().getCallbackContext().success("gettoken : receive token:" + pushtoken);
                        //showLog(pushtoken);
                    }else{
                        Log.i(TAG, "get token: empty"); 
                    }
                } catch (Exception e) {
                    Log.i(TAG,"getToken failed, " + e);
                    CallbackContextSingleton.getInstance().getCallbackContext().error("getToken failed, " + e);
                }
            }
        }.start();
    }
}
