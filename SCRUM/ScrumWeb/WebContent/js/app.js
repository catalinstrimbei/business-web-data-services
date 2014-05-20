//http://localhost:8080/ScrumWeb/Start.html

var app = angular.module('App', ['ngGrid', 'ngRoute', 'ngResource']);
var projectsRestURL = 'http://localhost:8080/ScrumREST/projects';
var x2js = new X2JS();  

// main controller
app.controller('mainController', function($scope, $http) {
	console.log("mainController");
	
	$scope.projectsSelected = [];
	$scope.releasesList = [];
	$scope.releaseSelected = [];
	
	// projects data model
	$http.get(projectsRestURL)
	.success(function(data){
		console.log(data);
		$scope.projectsList = data;
		
		console.log("mainController-get-data");
		console.log(data);
	})
	.error(function(data){console.log('ERROR');});		
});

// view1 controller
app.controller('view1Controller', function($scope, $http) {
	console.log("view1Controller");
	
	$scope.view1_name = "Project list";
    
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
//      ,afterSelectionChange: function (rowItem) {
//    	    if (rowItem.selected)  {  // I don't know if this is true or just truey
//    	         //write code to execute only when selected.
//    	         //rowItem.entity is the "data" here
//    	    	projectsRestURL = rowItem.entity.link.href;
//    	    	$http.get(projectsRestURL)
//    	    	.success(function(data){
//    	    		console.log("view1Controller-grid-afterSelectionChange-getdata");
//    	    		$scope.project = data;
//    	    		console.log($scope.project);
//    	    	})
//    	    	.error(function(data){console.log('ERROR');});	    	    	
//    	    } else {
//    	        //write code on deselection.
//    	    }
//    	}      
    };		
	    
});


//view2 controller
app.controller('view2Controller', function($scope, $http, $location) {
	console.log("view2Controller");
	
	$scope.view2_name = "Project edit form";
	
	
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
    
    $scope.save = function(){
    	console.log("view2Controller: save action");
    	
    	if($scope.projectsSelected[0] == null)
    		return;
    	
    	        
        // JSON to DOM: Content-Type:application/xml
//    	console.log($scope.projectsSelected[0]);
//        xmlDoc = x2js.json2xml_str($scope.projectsSelected[0]);
//    	console.log(xmlDoc);
    	
    	//$http.put($scope.projectsSelected[0].link.href, xmlDoc, {headers: {'Content-Type':'application/xml'}})
    	project = $scope.projectsSelected[0];
    	link = project.link.href;
    	delete project.link;
    	
    	console.log("view2Controller link: " + link);
    	console.log(project);
    	
//    	$http.put($scope.projectsSelected[0].link.href,$scope.projectsSelected[0])
    	$http.put(link,project)
    	.success(function(data){
    		console.log("view2Controller: PUT action");
    		console.log(data);

        	// restore link (workaround for json deserialization problem)
        	$scope.projectsSelected[0].link = link;
        	console.log("view2Controller: relink: " + $scope.projectsSelected[0].link);
    	})
    	.error(function(data){console.log('ERROR');});    	
    	
    };
    
    $scope.cancel = function(){
    	console.log("view2Controller: cancel action");
    };    
    
    
    $scope.go = function ( path ) {
    	  $location.path( path );
    };
});

//view3 controller
app.controller('view3Controller', function($scope, $http, $location) {
	console.log("view3Controller");
	
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
    projectRestURL = $scope.projectsSelected[0].link.href;
	console.log("projectsRestURL: " + projectRestURL);
	
	$http.get(projectRestURL)
	.success(function(data){
		console.log(data);
		$scope.project = data;
		$scope.releasesList = $scope.project.releases;
	})
	.error(function(data){console.log('ERROR');});
	
	$scope.go = function ( path ) {
  	  $location.path( path );
  };	
	
});

// App navigation control
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