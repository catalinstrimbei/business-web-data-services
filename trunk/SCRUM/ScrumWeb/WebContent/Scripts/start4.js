// [] - dependency modules
var demoAppRest = angular.module('demoAppRest', ['ngRoute', 'ngResource']);
restServiceURL = 'http://localhost:8080/ScrumREST/scrum';
projectServiceURL = "http://localhost:8080/ScrumREST/scrum/project";

demoAppRest.factory('simpleTextFactory',
		function($http){
			var factory = {};
			
			factory.getRestText = function(restText){
				$http.get(restServiceURL)
				.success(function(data){
					console.log('restText received: ' + data);
					restText = data;
				})
				.error(function(data){console.log('ERROR');});	
			};
			return factory;
		}
);


demoAppRest.factory('simpleResourceFactory',
		function($resource){
    		return $resource(restServiceURL, {});
  });

demoAppRest.controller('SimpleController', 
		function ($scope, $http, simpleTextFactory, simpleResourceFactory){
			console.log('INIT SimpleController ... ');
	
			simpleTextFactory.getRestText($scope.restText);
			
			$http.get(restServiceURL)
			.success(function(data){
				console.log('restText received: ' + data);
				$scope.restText = data;
			})
			.error(function(data){console.log('ERROR');});				
			
			simpleResourceFactory.get(function(response){
				 $scope.restSrc = response;
				 console.log('restSrc received: ' + $scope.restSrc);
			});
			
			$http.get(projectServiceURL)
			.success(function(data){
				console.log('project received: ' + data);
				console.log(data);
				$scope.project = data;
			})
			.error(function(data){console.log('ERROR');});
});