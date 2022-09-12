/**
1. Create a new XMLHttpRequest object
2. create a variable called request, initialized to the fitness app exercises request

POSSIBLE TODO: LINE 15'S URL MAY CHANGE AS THE PROJECT DEVELOPS

3. use the open method to tie the HTTP method and URL endpoint together
4. setup the callback function (the function to be called as the state of request changes
	(0 - unsent, 1 - opened, 2 - sent, 3 - loading, 4 - done)
5. use onreadystatechange property to sent the callback function
6. invoke the send method to fire the request
*/

const httpRequest = new XMLHttpRequest();
const request = "http://localhost:8080/fitnessapp/webapi/exercises";
httpRequest.open('GET', request);

//Track the state of the changes of the request
httpRequest.onreadystatechange = function() {
	if (httpRequest.readyState == 4 && httpRequest.status == 200) {
		console.log(httpRequest.responseText);
		
	}
}

httpRequest.send();