/**
 * 
 */

(function() {
	let fitnessApp = angular.module("fitnessApp");

	myFitnessApp.controller("exerciseSearchController", function($scope, $http, $location, $routeParams) {  
	
	
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
	/*note $location on lines 8 and 26 lets you route clickable objects to another location*/
	/*check the function on line 22 via browser.  click on exercises and see if the id shows in the source tab*/
	
	/* $scope.getAllExercises(); */



/*shows the spinner while the exercises are being pulled.  
You have to slow down the thread that calls the exercise service layer through the FitnessAppController.java to see the spinner actually work*/

