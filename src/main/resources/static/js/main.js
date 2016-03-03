angular.module('landfill', ['ngRoute'])
	.config(['$routeProvider',
		function($routeProvider) { 
			$routeProvider
				.when('/', {
					templateUrl: 'index.html'
				})
				.otherwise({
					redirectTo: '/'
				});   	 
	}])
	.controller('indexController', function($scope) {
		$scope.greeting = {id: 'xxx', content: 'Hello World!'};
	});