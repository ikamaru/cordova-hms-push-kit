package cordova.hms.push.kit;

import org.apache.cordova.CallbackContext;

public class CallbackContextSingleton {
  private static CallbackContextSingleton instance=null;
  private CallbackContext callbackContext=null;
  private CallbackContextSingleton(){}
  public static CallbackContextSingleton getInstance(){
    if(instance==null)
      instance=new CallbackContextSingleton();
    return instance;
  }
  public void setCallbackContext(CallbackContext callbackContext){
    this.callbackContext=callbackContext;
  }
  public CallbackContext getCallbackContext(){
    return this.callbackContext=callbackContext;
  }


}
