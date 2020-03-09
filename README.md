# cordova-hms-push-kit
A cordova plugin to use the following [Huawei Push Kit](https://developer.huawei.com/consumer/en/hms/huawei-pushkit)

[demo ionic-hmsPushKitDemo](https://github.com/ikamaru/ionic-hmsPushKitDemo)

### Getting Started
Create a new ionic project
```
ionic start ionic-hmsPushKitDemo blank --type=angular && cd ionic-hmsPushKitDemo
```
Add the android platform to your project
```
ionic cordova platform add android
```

### Preparation
* Generate a keystore file 
```
keytool -genkey -v -keystore my-release-key.keystore -alias alias_name -keyalg RSA -keysize 2048 -validity 10000
```
* Change the keystore info here **build.json**
* Sign in to [HUAWEI Developer](https://developer.huawei.com/consumer/en/console) then create a new app in HUAWEI AppGallery > My Apps.
* Run the following keytool command and obtain the SHA-256 fingerprint from the result
```
keytool -list -v -keystore my-release-key.keystore
```
* In AppGallery Connect, click the app that you have created and go to Develop -> Overview -> App information , then Insert the SHA-256 
* Enable the services
  * Develop > Overview, click the settings icon, and choose Manage APIs. Then Enable HUAWEI Push Kit.
  * Click the Develop tab and go to Growing > Push from the navigation bar on the left. Then Click Enable to change the status of HUAWEI Push Kit to Enabled.
* Download the **agconnect-services.json** file and put it in the root directory of your ionic project

### Add the plugin
* Add the plugin to your project
```
ionic cordova plugin add https://github.com/ikamaru/cordova-hms-push-kit
```
* Check [the plugin's doc](https://github.com/ikamaru/cordova-hms-push-kit#readme) or this [demo](https://github.com/ikamaru/ionic-hmsPushKitDemo) and enjoy your coding

These preparation steps are base on these [Preparations for Integrating HUAWEI HMS Core](https://developer.huawei.com/consumer/en/codelab/HMSPreparation/index.html#0) (I tried to choose what we will need in our ionic project and automate the other steps inside the plugin)

### Build the APK
* Don't forget to build the ionic project using the same keystore file you generated for the AppGallery connect

Build the APK release according to the build configuration file **build.json**
```
ionic cordova build android --buildConfig=build.json --release
```
Plug your android phone into the computer's USB port and install the apk
```
adb install "platforms\android\app\build\outputs\apk\release\app-release.apk"
```
### Documentation
* cordova.plugins.HMSPushKit.getToken(successCallback,failCallback)
```
cordova.plugins.HMSPushKit.getToken(
			(token) => {
			    //token
			},(err) => {
			    //Error while trying to get the token
			});
```
* cordova.plugins.HMSPushKit.subscribeToTopic(topic,successCallback,failCallback)
```
cordova.plugins.HMSPushKit.subscribeToTopic(
			"aTopicX",
			(msg) => {
			    //subscribe to the topic 「aTopicX」 Completed
			},(err) => {
			    //subscribe to the topic 「aTopicX」 Failed
			});
```
* cordova.plugins.HMSPushKit.unsubscribeFromTopic(topic,successCallback,failCallback)
```
cordova.plugins.HMSPushKit.unsubscribeFromTopic(
			"aTopicX",
			(msg) => {
			    //unsubscribe from the topic 「aTopicX」 Completed
			},(err) => {
			    //unsubscribe from the topic 「aTopicX」 Failed
			});
```
* cordova.plugins.HMSPushKit.isHMSAvailable(successCallback,failCallback)
```
cordova.plugins.HMSPushKit.isHMSAvailable(
			(msg) => {
			    //HMS exists
			},(err) => {
			    //HMS doesn't exists
			});
```
* cordova.plugins.HMSPushKit.isGMSAvailable(successCallback,failCallback)
```
cordova.plugins.HMSPushKit.isGMSAvailable(
			(msg) => {
			    //GMS exists
			},(err) => {
			    //GMS doesn't exists
			});
```
