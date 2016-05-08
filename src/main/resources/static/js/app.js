angular.module('app', ['ngRoute', 'ngResource',
'reportController', 'reportService', 'ngCookies',
'ngSanitize'])
  .config(['$routeProvider', '$locationProvider',
    function ($routeProvider, $locationProvider, $cookies) {
      $routeProvider
        .when('/', {
          templateUrl: 'views/home.html',
          controller: 'reportController'
        })
        .when('/home', {
          templateUrl: 'views/home.html',
          controller: 'reportController'
        })
        .when('/upload', {
          templateUrl: 'views/upload.html',
          controller: 'reportController'
        })
        .when('/instantaneous', {
          templateUrl: 'views/reports/instantaneous.html',
          controller: 'reportController',
           resolve:{
             results: function ($route, reportController) {
               res = reportController.instantData({
               }, function (response) {
                 console.log(response);
                 return response;
               });
               return res;
             }
           }
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
        .when('/ime-data', {
          templateUrl: 'views/imedata.html',
          controller: 'reportController'
        })
        .when('/exceedance-report', {
          templateUrl: 'views/reports/exceedancereport.html',
          controller: 'reportController'
        })
        .when('/hotspot-status', {
          templateUrl: 'views/reports/hotspotstatus.html',
          controller: 'reportController'
        })
        .otherwise({
          redirectTo: '/'
        });

      $locationProvider.html5Mode(true);
  }]);
