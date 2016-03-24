angular.module('main', ['ngRoute'])
	.config(['$routeProvider',
		function($routeProvider) {
			$routeProvider
				.when('/', {
					templateUrl: 'upload.html'
				})
				.otherwise({
					redirectTo: '/'
				});
	}])
	.controller('indexController', function($scope) {
		$scope.greeting = {id: 'xxx', content: 'Hello World!'};
	});
