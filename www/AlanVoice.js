/*global cordova, module*/

module.exports = {
    greet: function (name, successCallback, errorCallback) {
        console.log('alanVoice method')
        cordova.exec(successCallback, errorCallback, "AlanVoice", "greet", [name]);
    }
};
