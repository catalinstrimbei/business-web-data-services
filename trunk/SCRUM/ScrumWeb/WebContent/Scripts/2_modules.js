// [] - dependency modules
var demoApp = angular.module('demoApp', ['routeProvider']);


demoApp.controller('SimpleController', 
		function ($scope){
			$scope.customers = [
			     			{name: 'NS1', city: 'c1'}, 
			     			{name: 'NS2', city: 'c2'}, 
			     			{name: 'NS3', city: 'c3'}, 
			     			{name: 'NS4', city: 'c4'}
			     	];
			$scope.addCustomer = function(){
				$scope.customers.push({name: $scope.newCustomer.name, city: $scope.newCustomer.city});
			};
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