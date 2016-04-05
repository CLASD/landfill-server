angular.module('reportController', ['ngCookies'])
	.controller('reportController',
	['$scope' , '$cookies', function ($scope, $cookies)
	{
		$scope.reportName = "IME";

	}]);
