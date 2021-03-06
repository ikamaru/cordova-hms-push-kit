var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'HMSPushKit', 'coolMethod', [arg0]);
};
exports.isHMSAvailable = function (success,error) {
    exec(success, error,'HMSPushKit', 'isHMSAvailable', []);
};
exports.isGMSAvailable = function (success,error) {
    exec(success, error,'HMSPushKit', 'isGMSAvailable', []);
};
exports.getToken = function (success, error) {
    exec(success, error,'HMSPushKit', 'getToken', []);
};
exports.subscribeToTopic = function (topic,success, error) {
    exec(success, error,'HMSPushKit', 'subscribeToTopic', [topic]);
};
exports.unsubscribeFromTopic = function (topic,success, error) {
    exec(success, error,'HMSPushKit', 'unsubscribeFromTopic', [topic]);
};