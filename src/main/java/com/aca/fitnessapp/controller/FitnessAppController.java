package com.aca.fitnessapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.aca.fitnessapp.model.BodyPart;
import com.aca.fitnessapp.model.Exercise;
import com.aca.fitnessapp.model.Type;
import com.aca.fitnessapp.service.FitnessAppService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/exercises")
@Produces(MediaType.APPLICATION_JSON) // all I want for the initial app is JSON. Moved to the top level to avoid
										// repetition

public class FitnessAppController {

	private FitnessAppService service = new FitnessAppService();

	@DELETE
	@Path("/{exerciseIdValue}")
	public Exercise deleteExercise(@PathParam("exerciseIdValue") Integer exerciseId) {
		// testing what was passed in without even checking postman. Look at the console
		// log
		System.out.println("exerciseId: " + exerciseId);

		return service.deleteExercise(exerciseId);

	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Exercise updateExercise(Exercise exercise) {
		System.out.println(exercise);
		return service.updateExercise(exercise);
	}

	@GET
	public List<Exercise> getExercises() {
		return service.getExercises();

	}

	@GET
	@Path("/title/{titleValue}") // search in Postman by using title/*whatever you want* in the URL bar
	public List<Exercise> getExercisesByTitle(@PathParam("titleValue") String title) {
		System.out.println("exercise title: " + title);
		return service.getExercisesByTitle(title);
	}

	@GET
	@Path("/bodypart/{bodyPartValue}")
	public List<Exercise> getExercisesByBodyPart(@PathParam("bodyPartValue") BodyPart bodyPart) {
		// testing what was passed in without needing to check Postman. Use console log
		System.out.println("bodyPart: " + bodyPart);

		return service.getExercisesByBodyPart(bodyPart);

	}

	@GET
	@Path("/{exerciseIdValue}")
	public List<Exercise> getExercisesById(@PathParam("exerciseIdValue") Integer exerciseId) {
		System.out.println("exerciseId: " + exerciseId);
		return service.getExercisesById(exerciseId);
	}

	@GET
	@Path("/type/{exerciseTypeValue}")
	public List<Exercise> getExercisesByType(@PathParam("exerciseTypeValue") Type exerciseType) {
		System.out.println("exerciseType: " + exerciseType);

		return service.getExercisesByType(exerciseType);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON) // for creating and updating new exercises.
	public Exercise createExercise(Exercise exercise) { // instantiating a new object of Exercise in exercise

		// test via Postman POST requests. Make sure the header keys match the body
		// format

		System.out.println(exercise); // For displaying in Eclipse console after creation
		return service.createExercise(exercise);

	}

	

//	@GET
//	@Path("/report")
//	public List<Exercise> getReport(
//			@QueryParam("addedDate")LocalDateTime addedDate,
//			@QueryParam("updateDate")LocalDateTime updateDate){
//				return service.getReport(addedDate, updateDate);
//			}
//			
//			

}
