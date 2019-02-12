/*global cordova, module*/
var exec = require('cordova/exec');

module.exports = {
    greet: function (name, successCallback, errorCallback) {
        console.log('alanVoice method')
        exec(successCallback, errorCallback, "AlanVoice", "greet", [name]);
    },
    start: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, "AlanVoice", "start", "");
    }
};

