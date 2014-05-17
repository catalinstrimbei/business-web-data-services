var app = angular.module('formSelectionApp', ['ngGrid', 'ngRoute', 'ngResource']);

var projectRestURL = 'http://localhost:8080/ScrumREST/projects/1001';

app.controller('formMasterDetailCtrlRest', function($scope, $http) {
	$scope.project = {};
	$scope.releasesList = [];
	$scope.releaseSelected = [];	
	
    $scope.detailGrid = { 
      data: 'releasesList',
      selectedItems: $scope.releaseSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      enableCellSelection: true,
      enableRowSelection: false,
      enableCellEdit: true,
      width: 600,
      columnDefs: [{ field: "releaseId", width: 120, displayName: 'ID', enableCellEdit: true},
                   { field: "indicative", width: 180 , displayName: 'Indicative', enableCellEdit: true },
                   { field: "publishDate", width: 300 , displayName: 'PublishDate', enableCellEdit: true }
                   ]
    };		
	
	// projects data model
	$http.get(projectRestURL)
	.success(function(data){
		console.log(data);
		$scope.project = data;
		$scope.releasesList = $scope.project.releases;
	})
	.error(function(data){console.log('ERROR');});
});
/*
Costom editors example
http://plnkr.co/edit/VABAEu?p=preview

*/