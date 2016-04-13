angular.module('reportController', ['ngCookies'])
	.controller('reportController',
	['$scope' , '$cookies', function ($scope, $cookies)
	{
		$scope.reportName = "IME";
		
		$scope.imeData = [
	        {date:'1/8/2003', landfill:'GAFFEY', type:'PROBE READING', imeprobe:'8', grid:'B', repairdesc:'', initialppmv:'78000', recheck:''},
	        {date:'9/12/2011', landfill:'GAFFEY', type:'COMPONENT', imeprobe:'GA1109-02', grid:'', repairdesc:'tightened bolts on flange', initialppmv:'700', recheck:'21'},
	        {date:'1/7/2014', landfill:'LOPEZ', type:'INSTANTANEOUS', imerobe:'LC1401-01', grid:'5', repairdesc:'ripped watered compacted', initialppmv:'513', recheck:'5'},
	        {date:'12/17/2015', landfill:'LOPEZ', type:'INTEGRATED', imeprobe:'LC1512-01', grid:'37', repairdesc:'cleared rotting debris', initialppmv:'50', recheck:'12'}
		];
		
		$scope.hotspotstatusData = [
			{imenum:'TC1601-01', grid:'12', foundby:'WA', founddate:'1/1/16', repaired1:'1/3/16', recheckdate1:'1/4/16', passedfail1:'PASSED', repaired2:'', recheckdate2:'', passfail2:'', repaired3:'', recheckdate3:'', passfail3:''},
			{imenum:'TC1601-02', grid:'12', foundby:'WA', founddate:'1/1/16', repaired1:'1/4/16', recheckdate1:'1/4/16', passedfail1:'FAILED', repaired2:'1/5/16', recheckdate2:'1/6/16', passfail2:'PASSED', repaired3:'', recheckdate3:'', passfail3:''},
			{imenum:'TC1601-03', grid:'13', foundby:'JS', founddate:'1/2/16', repaired1:'1/3/16', recheckdate1:'1/5/16', passedfail1:'PASSED', repaired2:'1/7/16', recheckdate2:'1/8/16', passfail2:'FAILED', repaired3:'1/9/16', recheckdate3:'1/9/16', passfail3:'PASSED'}
        ];
		
		$scope.months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
		
		$scope.years = ['2011', '2012', '2013', '2014', '2015', '2016'];
		
		$scope.sites = ['Bishops', 'Gaffey', 'Lopez', 'Sheldon', 'Toyon'];

		$scope.instantData = function(){
			$http.get('http://localhost:9090/instantaneous').
				success(function(data) {
					$scope.results = data;
				});
		}

	}]);