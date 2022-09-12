


(function() {
	let fitnessApp = angular.module('fitnessApp');
	
	fitnessApp.controller('stackController', function($scope, $http) {
		
		
	$scope.getClientStacks = function() {
		$http.get("/fitnessapp/webapi/stacks/client")
		.then(function(response) {
			$scope.client_stacks = response.data;
		}, function(response) {
			console.log('error http GET stacks/client: ' + response.status);
		});
	}
	$scope.getClientStacks();
	
	});
})();