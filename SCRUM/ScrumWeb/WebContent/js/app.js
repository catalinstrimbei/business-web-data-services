//http://localhost:8080/ScrumWeb/Start.html
var app = angular.module('App', ['ngGrid', 'ngRoute', 'ngResource']);
var projectsRestURL = 'http://localhost:8080/ScrumREST/projects';
var x2js = new X2JS();

//App navigation control
app.config(
		function($routeProvider){
			$routeProvider
				.when('/', 		{controller:'view1Controller', templateUrl: 'partials/view1.html'})
				.when('/view1', {controller:'view1Controller', templateUrl: 'partials/view1.html'})
				.when('/view2', {controller:'view2Controller', templateUrl: 'partials/view2.html'})
				.when('/view3', {controller:'view3Controller', templateUrl: 'partials/view3.html'})
				.otherwise({redirectTo: '/'});
		}
);