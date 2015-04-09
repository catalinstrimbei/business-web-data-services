var myApp = angular.module('myApp', []);
var projectsRestURL = 'http://localhost:8080/SCRUM-S2/data/features';

myApp.controller('namesCtrl', function($scope, dataService) {
    $scope.names = dataService.getNames();
});

console.log("INIT ... >>> angular-sample-app-ctrls.js: namesCtrl");

//main controller
myApp.controller('mainController', 
  ['$scope', '$http', '$timeout', // dependencies
  function($scope, $http, $timeout) {	// implementation
	console.log("mainController");
	
	$scope.features = [];
	
	$http.get(projectsRestURL)
	.success(function(data){
		console.log("mainController:GET URL : " + projectsRestURL);
		$scope.features = data;
	});
}]);

console.log("INIT ... >>> angular-sample-app-ctrls.js: mainController");

// http://localhost:8080/SCRUM-S2/app/Start.html
// http://localhost:8080/SCRUM-S2/app/ShowFeatures.html