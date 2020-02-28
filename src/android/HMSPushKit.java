package cordova.hms.push.kit;

import android.text.TextUtils;
import android.util.Log;

import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class CordovaHMSPushKit extends CordovaPlugin {
    private static final String TAG = "CordovaHMSPushKit";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getToken")) {
            this.getToken(callbackContext);
            return true;
        }
        return false;
    }

    private void getToken(CallbackContext callbackContext) {
        Log.i(TAG, "get token: begin");
        try {
            String appId = AGConnectServicesConfig.fromContext(cordova.getContext()).getString("client/app_id");
            String pushToken = HmsInstanceId.getInstance(cordova.getContext()).getToken(appId, "HCM");
            if (!TextUtils.isEmpty(pushToken)) {
                Log.i(TAG, "get token:" + pushToken);
                callbackContext.success(pushToken);
            }
        } catch (Exception e) {
            Log.e(TAG, "getToken Failed, " + e);
            callbackContext.error("getToken Failed, error : " + e.getMessage());
        }
    }
}
