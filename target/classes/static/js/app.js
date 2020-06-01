var app = angular.module('app', ['ui.bootstrap']);
 
var url = window.location.origin;

app.controller('resultadoController', function ($scope, $http) {
	
	$scope.scotches = []; 
	$http.get(url + '/consumoInfoCompleta')
	.success(function (data) {
		console.log(data);
	  $scope.scotches=data;
	}).error(function (data, status, headers, config) {
 		console.log(data);
        $scope.loading = false;
	});
});