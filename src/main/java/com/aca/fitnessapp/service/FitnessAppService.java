package com.aca.fitnessapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.aca.fitnessapp.dao.FitnessAppDaoMock;  Use when you want to switch back to FitnessAppDaoMock data

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aca.fitnessapp.dao.FitnessAppDao;
import com.aca.fitnessapp.dao.FitnessAppDaoImpl;
import com.aca.fitnessapp.model.BodyPart;
import com.aca.fitnessapp.model.Exercise;
import com.aca.fitnessapp.model.Type;
import com.aca.fitnessapp.model.RequestError;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class FitnessAppService {

	private FitnessAppDao fitnessAppDao = new FitnessAppDaoImpl();

	public List<Exercise> getExercises() {
		return fitnessAppDao.getExercises();
	}

	public List<Exercise> getExercisesByBodyPart(BodyPart bodyPart) {  //no need to make a validation method for nulls because the controller handles the enum
		return fitnessAppDao.getExercisesByBodyPart(bodyPart);
	}

	public List<Exercise> getExercisesById(Integer exerciseId) {
		validateExerciseId(exerciseId);
		return fitnessAppDao.getExercisesById(exerciseId);
		
	}

	
	
	
	public List<Exercise> getExercisesByType(Type exerciseType) {
		
		return fitnessAppDao.getExercisesByType(exerciseType);
	}

	public Exercise createExercise(Exercise exercise) { //only creating one exercise at a time.  A public list is not needed
		
		return fitnessAppDao.createExercise(exercise);
	}

	

	public Exercise updateExercise(Exercise updateExercise) {
		validateTitle(updateExercise.getTitle());
		validateExerciseId(updateExercise.getExerciseId());
		
		List<Exercise> exercises = fitnessAppDao.getExercisesById(updateExercise.getExerciseId());
		if (exercises.size() == 1) {
			return fitnessAppDao.updateExercise(updateExercise);
		} else {
			RequestError error = new RequestError(2, "Exercise id does not exist: '" + updateExercise.getExerciseId() + "'");
			Response response = Response.status(400) // this comes from the Jersey Framework import.
					.entity(error).build();
			throw new WebApplicationException(response);
		}
	}

	public Exercise deleteExercise(Integer exerciseId) { //deletes exercise if the id exists
		List<Exercise> exercises = fitnessAppDao.getExercisesById(exerciseId);
		if (exercises.size() == 1) {
			return fitnessAppDao.deleteExercise(exerciseId);
		} else {
			RequestError error = new RequestError(1, "Exercise id does not exist: '" + "'");
			Response response = Response.status(400) // this comes from the Jersey Framework import.
					.entity(error).build();
			throw new WebApplicationException(response);
		}
	}
	
	
	private void validateTitle(String title) {
		if (null == title || title.length() < 1 || title.length() > 30) {
			RequestError error = new RequestError(1,
					"Invalid length for title: '" + title + "' - Must have length > 1 and <=30 characters");
			Response response = Response.status(400) // this comes from the Jersey Framework import.
					.entity(error).build();
			throw new WebApplicationException(response);
		}
	}
		
	private void validateExerciseId(Integer exerciseId) {
		if (null == exerciseId || exerciseId < 0) {
			RequestError error = new RequestError(2, "Invalid exercise id: '" + exerciseId + "' - must be greater than 0");
			Response response = Response.status(400) // this comes from the Jersey Framework import.
					.entity(error).build();
			throw new WebApplicationException(response);
		}
		
	}

	public List<Exercise> getExercisesByTitle(String title) {
		validateTitle(title);
		return fitnessAppDao.getExercisesByTitle(title);
	}
	
	//getReport not needed at this time.  Possibly implement later after a range of exercises have been made across a range of different dates
//	public List<Exercise> getReport(LocalDateTime addedDate, LocalDateTime updateDate) {
//		
//		return fitnessAppDao.getReport(addedDate, updateDate);
//	}
	
	
}
