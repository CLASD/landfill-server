angular.module('app', ['ngRoute', 'ngResource',
'reportController', 'ngCookies',
'ngSanitize'])
  .config(['$routeProvider', '$locationProvider',
    function ($routeProvider, $locationProvider, $cookies) {
      $routeProvider
        .when('/', {
          templateUrl: 'views/upload.html',
          controller: 'reportController'
          // resolve:{
          //   history: function ($route, sessionService) {
          //     res = sessionService.query({
          //     }, function (response) {
          //       console.log(response);
          //       return response;
          //     });
          //     return res;
          //   }
          // }
        })
        .when('/upload', {
          templateUrl: 'views/upload.html',
          controller: 'reportController'
        })
        .when('/report', {
          templateUrl: 'views/report.html',
          controller: 'reportController'
//          resolve:{
//            searchHistory: function ($route, historyService) {
//                res = historyService.query({
//          		}, function (response) {
//          			console.log(response);
//          			return response;
//          		});
//              return res;
//            }
//          }

        })
        .otherwise({
          redirectTo: '/'
        });

      $locationProvider.html5Mode(true);
  }]);
