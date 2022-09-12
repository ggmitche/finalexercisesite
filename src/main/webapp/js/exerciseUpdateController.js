
(function() { // makes this an IIFE
	var fitnessApp = angular.module("fitnessApp");

	fitnessApp.controller("exerciseUpdateController", 
			function($scope, $http, $routeParams, $location) {
		
		var exerciseId = $routeParams.exerciseId;
		
		$scope.getExercisesById = function() {
			$http.get("/fitnessapp/webapi/exercises/" + $routeParams.exerciseId)
					.then(
							function(response) {
								var exercises = response.data;
								if (exercises.length == 1) {
									$scope.exercise = exercises[0];
								} else {
									// TODO error message
								}
							},
							function(response) {
								console.log('error http GET exercises by id: '
										+ response.status);
							});
		}

		$scope.getExercisesById();
		$scope.bodyParts = [ 'Abs', 'Arms', 'Back', 'Chest', 'Legs', 'Shoulders' ];
		$scope.exerciseTypes = [ 'Calisthenics', 'Warmup', 'Weighted', 'Recovery' ];
		
		
		$scope.goToSearchView = function() {
			console.log('go to search view');
			$location.path('/search');
		}
		
		$scope.updateExercise = function() {
			$http.put("/fitnessapp/webapi/exercises", $scope.exercise)
			.then(function(response) {				
				$scope.updateStatus = 'update successful';			
			}, function(response) {
				$scope.updateStatus = 'error trying to update exercise';	
				console.log('error http PUT exercise: ' + response.status);
			});
			
			$scope.deleteExercise = function() {
				$http.delete("/fitnessapp/webapi/exercises/" + $scope.exercise.exerciseId)
				.then(function(response) {				
					$scope.updateStatus = 'delete successful';	
					$scope.disableUpdate = true;
				}, function(response) {
					$scope.updateStatus = 'error trying to delete exercise';	
					console.log('error http DELETE exercises: ' + response.status);
				});
			} //exercises must be updated prior to deletion.
			
		
		}
		
		
	})
	

	

	
	
})(); //IIFE's have to have the extra () or they will not be executed, causing a $controller:ctrlreg error


//Note how every $scope method is inside the controller here.  If it's defined in the controller's functions, it needs to be executed there.

/*
 * $routeParams - lets you get access to the last params that were part of routing
 */


/*
 * Initial testing of this will result in a $controller:ctrlreg error. It's
 * saying it can't find update controller. Remember that because this is a
 * single page app, views are injected into the index. So you need to update the
 * index to bring in the exerciseUpdateController
 */