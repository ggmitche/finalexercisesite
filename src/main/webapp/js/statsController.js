(function() {
	var fitnessApp = angular.module('fitnessApp');
	fitnessApp.controller("statsController", function($scope, $http) {
		
		$scope.labels = ["Abs","Arms","Back","Chest","Legs","Shoulders", "Full Body"];
		$scope.data = [2, 1, 3, 1, 1, 2, 0];
	});
})();