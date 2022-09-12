(function() {
	let fitnessApp = angular.module("fitnessApp");

	myFitnessApp.controller("exerciseViewController", function($scope, $http, $location, $routeParams) {  
	
	
		$scope.getAllExercises = function() {
			//$scope.showSpinner = true;  				
			$http.get("/fitnessapp/webapi/exercises").then(function(response) {
				$scope.exercises = response.data;
				console.log('number of exercises: ' + $scope.exercises.length);
				// $scope.showSpinner = false;
			}, function(response) {
				console.log('error http GET exercises: ' + response.status);
				
			});
		}

	$scope.goToExercisePage = function(exerciseId) {
	console.log("go to exercise page: " + exerciseId);
	$location.path("/exercise/" + exerciseId)
	
	
	}
	
	$scope.orderByColumn = function(column) {
		$scope.orderByValue = column;
		if ($scope.reverse == true) {
		$scope.reverse = false;
		
		} else {
			$scope.reverse = true;
			}
	}
	
		})
})()