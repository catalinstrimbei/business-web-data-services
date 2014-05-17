var app = angular.module('formSelectionApp', ['ngGrid', 'ngRoute', 'ngResource']);

app.controller('formSelectionCtrl', function($scope) {
	// projects data model
	$scope.projectsList = [
		{
		    "projectNo": 1,
		    "name": "Project 1",
		    "startDate": "2014-04-11"
		},
		{
		    "projectNo": 2,
		    "name": "Project 2",
		    "startDate": "2014-04-12"
		},
		{
		    "projectNo": 3,
		    "name": "Project 3",
		    "startDate": "2014-04-13"
		}		
    ];	
	
    $scope.projectsSelected = [];
    
    $scope.gridOptions = { 
      data: 'projectsList',
      selectedItems: $scope.projectsSelected,
      multiSelect: false
    };
});

var projectsRestURL = 'http://localhost:8080/ScrumREST/projects';
app.controller('formSelectionCtrlRest', function($scope, $http) {
	//$scope.projectsList = [];
	$scope.projectsSelected = [];
    
    $scope.gridOptions = { 
      data: 'projectsList',
      selectedItems: $scope.projectsSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      width: 420,
      heigh: 100,
      columnDefs: [{ field: "projectNo", width: 120, displayName: 'ID'},
                   { field: "name", width: 300 , displayName: 'Name' }]
    };		
	
	// projects data model
	$http.get(projectsRestURL)
	.success(function(data){
		console.log(data);
		$scope.projectsList = data;
	})
	.error(function(data){console.log('ERROR');});
});
