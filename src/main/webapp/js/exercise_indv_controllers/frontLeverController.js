
(function() {
	let fitnessApp = angular.module("fitnessApp");

	myFitnessApp.controller("frontLeverController", function($scope, $http, $location, $routeParams) {  
	
	
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

	$scope.goToUpdateView = function(exerciseId) {
	console.log("go to update view: " + exerciseId);
	$location.path("/update/" + exerciseId)
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