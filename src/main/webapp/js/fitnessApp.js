/*Creates a new module named 'fitnessApp'*/

let myFitnessApp = angular.module("fitnessApp", ["ngRoute", 'chart.js']);

myFitnessApp.config(function($routeProvider) {
	  $routeProvider
	  
	 
	  .when("/resume", {
	    templateUrl : "resume.html"
	  })
	  .when("/main", {
		  templateUrl: "landing.html"
	  })
	  .when("/stats", {
		  templateUrl: "stats.html",
		  controller: "statsController"
	  })
	   .when("/stack", {
	    templateUrl : "stack.html",
	    controller : "stackController"
	  })
	  
	    .when("/update/:exerciseId", {
		  templateUrl: "exerciseUpdate.html",
		  controller: "exerciseUpdateController" 
	  })
	  .when("/exercises", {
		  templateUrl: "exerciseDisplayView.html",
		  controller: "exerciseViewController"	  
	  })
	   .when("/coming-soon", {
		  templateUrl: "comingSoon.html"
		  	  
	  })
	   //individual exercise URL logic
	  
	  .when("/exercise/1", {
		  templateUrl: "1ViewSingleArmPushUp.html",
		  controller: "singleArmPushUpController"	  
	  })
	  .when("/exercise/2", {
		  templateUrl: "2Weighted-Chin-Up.html",
		  controller: "weightedChinUpController"	  
	  })
	   .when("/exercise/3", {
		  templateUrl: "3Bar-Pullover.html",
		  controller: "barPulloverController"	  
	  })
	   .when("/exercise/4", {
		  templateUrl: "4Deadlift.html",
		  controller: "deadliftController"	  
	  })
	   .when("/exercise/5", {
		  templateUrl: "5Front-Lever.html",
		  controller: "frontLeverController"	  
	  })
	   .when("/exercise/6", {
		  templateUrl: "6Wall-Handstand-Push-Up.html",
		  controller: "wallHandstandPushUpController"	  
	  })
	   .when("/exercise/7", {
		  templateUrl: "7Weighted-Upsidedown-Pull-Up.html",
		  controller: "weightedUpsidedownPullUpController"	  
	  })
	   .when("/exercise/8", {
		  templateUrl: "8Skin-The-Cat.html",
		  controller: "skinTheCatController"	  
	  })
	   .when("/exercise/9", {
		  templateUrl: "9Windshield-Wipers.html",
		  controller: "windshieldWipersController"	  
	  })
	   .when("/exercise/10", {
		  templateUrl: "10Reverse-Sled-Drag.html",
		  controller: "reverseSledDragController"	  
	  })
	  //other controller logic
	 
	  .when("/search", {
		  templateUrl: "exerciseSearch.html",
		  controller: "exerciseSearchController"
	  })
	  
	  
	 
	  
	  .otherwise({
	    templateUrl : "landing.html"
	  });
	});