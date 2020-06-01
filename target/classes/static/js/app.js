var app = angular.module('app', []);
 
var url = window.location.origin;

app.controller('TNTIndexController', ['$scope', function ($scope, $http) {

	
	$http.get(url+"/consumoInfoCompleta").success(function(data)
		$scope.tntPortfolio = data;
		$scope.gridOptions = {};
  
		$scope.gridOptions = {
		enableSorting: true,
		enableRowSelection: true,
		enableFullRowSelection: true,
		multiSelect: true,
		enableRowHeaderSelection: false,
		enableColumnMenus: false,
		enableFiltering: true,
		minRowsToShow: $scope.tntPortfolio.length+1
	};
  
	$scope.gridOptions.data =  $scope.tntPortfolio;
    
}]);