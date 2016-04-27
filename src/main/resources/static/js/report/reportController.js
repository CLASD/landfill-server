angular.module('reportController', ['ngCookies', 'ngMaterial', 'ngMessages'])
	.controller('reportController',
	['$scope' , '$cookies', '$http', function ($scope, $cookies, $http, $element)
	{
		
		$scope.hotspotstatusData = [
			{imenum:'TC1601-01', grid:'12', foundby:'WA', founddate:'1/1/16', repaired1:'1/3/16', recheckdate1:'1/4/16', passedfail1:'PASSED', repaired2:'', recheckdate2:'', passfail2:'', repaired3:'', recheckdate3:'', passfail3:''},
			{imenum:'TC1601-02', grid:'12', foundby:'WA', founddate:'1/1/16', repaired1:'1/4/16', recheckdate1:'1/4/16', passedfail1:'FAILED', repaired2:'1/5/16', recheckdate2:'1/6/16', passfail2:'PASSED', repaired3:'', recheckdate3:'', passfail3:''},
			{imenum:'TC1601-03', grid:'13', foundby:'JS', founddate:'1/2/16', repaired1:'1/3/16', recheckdate1:'1/5/16', passedfail1:'PASSED', repaired2:'1/7/16', recheckdate2:'1/8/16', passfail2:'FAILED', repaired3:'1/9/16', recheckdate3:'1/9/16', passfail3:'PASSED'}
        ];
		
		/* Start of Dummy Data */
		
		// Hotspot Status
		$scope.months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
		
		$scope.years = ['2011', '2012', '2013', '2014', '2015', '2016'];
		
		$scope.sites = ['Bishops', 'Gaffey', 'Lopez', 'Sheldon', 'Toyon'];
		
		$scope.types = ['Probes', 'Instantaneous', 'Integrated', 'Leak Check', 'All'];
		
		// IME Data
		$scope.inspections = [
		    {date:'1/2/2012', time:'0715', description:'Crack across bench and down slope.', type:'Line', inspector:'William Andrews', value:'50,000', 
		    	repairs: [{soil:'X', water:'X', other:'Ripped, watered, walked with D8 dozer.', crew:'Gas Crew', date:'1/8/2012', time:'1100'},
		    	          {soil:'X', water:'X', other:'Extended repaired area.', crew:'Ed & Doug', date:'1/9/2012', time:'1500'}]},
		    {date:'1/10/2012', time:'0730', description:'Now only around base of well 6ABHW02.', type:'Spot', inspector:'Brijesh Misra', value:'750', 
		    	repairs: [{soil:'', water:'X', other:'Hand tamped around well.', crew:'John & Bob', date:'1/11/2012', time:'1400'}]},
		    {date:'1/13/2012', time:'0900', description:'PASSED', type:'', inspector:'John Salas', value:'12.5',
		    	repairs: [{}]},
        ];
		
		$scope.hoverDisplay = true;
		$scope.hoverEdit = false;
		
		$scope.hoverIn = function() {
			this.hoverDisplay = false;
			this.hoverEdit = true;
		}
		
		$scope.hoverOut = function() {
			this.hoverDisplay = true;
			this.hoverEdit = false;
		}
		
//		$scope.repairs = [
//  		    {soil:'X', water:'X', other:'Ripped, watered, walked with D8 dozer.', crew:'Gas Crew', date:'1/8/2012', time:'1100'},
//	  		{soil:'X', water:'X', other:'Extended repaired area.', crew:'Ed & Doug', date:'1/9/2012', time:'1500'},
//	  		{soil:'', water:'X', other:'Hand thamped around well.', crew:'John & Bob', date:'1/11/2012', time:'1400'},
//        ];
		
		/* End of Dummy Data */
		
		$scope.instantData = function(){
			$http.get('http://localhost:9091/instantaneous').
				success(function(data) {
					$scope.results = data;
				});
		};
		
		$scope.generateReport = function(){
			
			$scope.imeData = [];
			$scope.iseData = [];
			
			if($scope.selectedType == 'Instantaneous')
			{
				$scope.getImeData();
			}else if($scope.selectedType == 'Integrated'){
				$scope.getIseData();
			}else{
				$scope.getImeData();
				$scope.getIseData();
			}
			
			$scope.currentTime = new Date();
			
		};
		
		
		$scope.getImeData = function(){
			
			console.log($scope.startDate)
			console.log($scope.endDate)
			
			var url = 'http://localhost:9091/data/exceedance/ime';
			console.log($scope.selectedSite)
			if($scope.selectedSite != "")
				url += "?site=" + $scope.selectedSite;
			
			if($scope.startDate != undefined){
				
				var str = $scope.startDate.toISOString().slice(0, 10);
				console.log(str);
				url += "&fromDate=" + str;
				
			}
			
			if($scope.endDate != undefined){
				var str = $scope.endDate.toISOString().slice(0, 10);
				console.log(str);
				url += "&toDate=" + str;
			}
			
			$http.get(url).
				success(function(data) {
					$scope.imeData = data;
				});
		};
		
		$scope.getIseData = function(site){
			
			var url = 'http://localhost:9091/data/exceedance/ise';
			console.log($scope.selectedSite)
			if($scope.selectedSite != "")
				url += "?site=" + $scope.selectedSite;
			
			if($scope.startDate != undefined && $scope.startDate != ""){
				var str = $scope.startDate.toISOString().slice(0, 10);
				console.log(str);
				url += "&fromDate=" + str;
			}
				
			
			if($scope.endDate != undefined && $scope.endDate != ""){
				var str = $scope.endDate.toISOString().slice(0, 10);
				console.log(str);
				url += "&toDate=" + str;
			}
				
			
			$http.get(url).
				success(function(data) {
					$scope.iseData = data;
				});
		};
		
		$scope.sendEmail = function(messageType) {

//			searchService.query({
////				searchString: str,
////				sessionId: $cookies.get('tv_sessionId')
//			}, function (response) {
//				$scope.result = response;
//				console.log($scope.result);
//			});
			
			$http.get('http://localhost:9091/data/email?messageType=' + messageType).
			success(function(data) {
				$scope.results = data;
			});

		};
		
	  $scope.myDate = new Date();
	  $scope.minDate = new Date(
	      $scope.myDate.getFullYear(),
	      $scope.myDate.getMonth() - 2,
	      $scope.myDate.getDate());
	  $scope.maxDate = new Date(
	      $scope.myDate.getFullYear(),
	      $scope.myDate.getMonth() + 2,
	      $scope.myDate.getDate());
	  $scope.onlyWeekendsPredicate = function(date) {
	    var day = date.getDay();
	    return day === 0 || day === 6;
	  }
		  
	}]);
