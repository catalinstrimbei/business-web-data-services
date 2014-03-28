// [] - dependency modules
var demoApp = angular.module('demoApp', ['ngRoute', 'ngResource']);


demoApp.factory('simpleFactory',
		function(){
			var factory = {};
			var customers = [
				     			{name: 'NS31', city: 'c31'}, 
				     			{name: 'NS32', city: 'c32'}, 
				     			{name: 'NS33', city: 'c33'}, 
				     			{name: 'NS34', city: 'c34'}
				     	];
			factory.getCustomers = function(){
				return customers;
			};
			return factory;
		}
);

demoApp.factory('scrumRest', function($resource){
    return $resource('http://localhost:8080/ScrumREST/scrum', {});
  });

demoApp.controller('SimpleController', 
		function ($scope, $http,simpleFactory, scrumRest){
			$scope.customers = simpleFactory.getCustomers();
			$scope.addCustomer = function(){
				$scope.customers.push({name: $scope.newCustomer.name, city: $scope.newCustomer.city});
			};
			
			scrumRest.get(function(response){
				$scope.restData = response;
				console.log('r: ' + response);
			});
			
			$http.get('http://localhost:8080/ScrumREST/scrum')
				.success(function(data){console.log('d: ' + data);})
				.error(function(data){console.log('ERROR');});
	     }
);


demoApp.config(
		function($routeProvider){
			$routeProvider
				.when('/', {controller:'SimpleController', templateUrl: 'parts/View1.html'})
				.when('/view2', {controller:'SimpleController', templateUrl: 'parts/View2.html'})
				.otherwise({redirectTo: '/'});
		}
);