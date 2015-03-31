var myApp = angular.module('myApp', []);

myApp.controller('namesCtrl', function($scope, dataService) {
    // $scope.names = [
    //     {name:'Jani',country:'Norway'},
    //     {name:'Hege',country:'Sweden'},
    //     {name:'Kai',country:'Denmark'}
    // ];
    $scope.names = dataService.getNames();
});

console.log("INIT ... >> angular-sample-app-ctrls.js");