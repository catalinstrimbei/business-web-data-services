//http://localhost:8080/ScrumWeb/Start.html
var app = angular.module('App', ['ngGrid', 'ngRoute', 'ngResource']);
var projectsRestURL = 'http://localhost:8080/ScrumREST/projects';
var x2js = new X2JS();  	