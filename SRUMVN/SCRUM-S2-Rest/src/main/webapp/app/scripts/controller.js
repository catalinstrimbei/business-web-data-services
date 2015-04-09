console.log("INIT ... >>> controller.js: featuresCtrl");
var myApp = angular.module('myApp', []);
var projectsRestURL = 'http://localhost:8080/SCRUM-S2/data/features';
//main controller
myApp.controller('mainController', 
  ['$scope', '$http',       // dependencies
  function($scope, $http) {	// implementation
	$scope.features = [];	
	$http.get(projectsRestURL)
	.success(function(data){
		console.log("mainController:GET URL : " + projectsRestURL);
		$scope.features = data;
	});
}]);
// http://localhost:8080/SCRUM-S2/app/ShowFeatures.html