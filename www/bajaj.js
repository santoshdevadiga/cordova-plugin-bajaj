var exec = require('cordova/exec');

function Bajaj () 
{
   
}

Bajaj.prototype.encryptdata = function (arg0,successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'Bajaj', 'getEncryptedData', arg0);
};

module.exports = new Bajaj();
