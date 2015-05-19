myApp.service("dataService", function(){
	var names = [
        {name:'Jan',country:'Norway'},
        {name:'Hege',country:'Sweden'},
        {name:'Kai',country:'Denmark'}
    ]; 

    var _getNames = function(){
    	return names;
    };

    return {getNames : _getNames};
});

console.log("INIT ... angular-sample-app-services.js");