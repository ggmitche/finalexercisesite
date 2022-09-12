package com.aca.fitnessapp.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.aca.fitnessapp.model.BodyPart;
import com.aca.fitnessapp.model.BodyPartTotal;
import com.aca.fitnessapp.model.Exercise;
import com.aca.fitnessapp.model.Type;

public interface FitnessAppDao {
	
	public List<Exercise> getExercises();
	
	public List<Exercise> getExercisesByBodyPart(BodyPart bodyPart);
	
	public List<Exercise> getExercisesById(Integer exerciseId);
	
	public List<Exercise> getExercisesByType(Type exerciseType);
	
	public List<BodyPartTotal> getBodyPartTotals();
	List<Exercise> getExercisesByTitle(String title);
	
	
	public Exercise createExercise(Exercise exercise); //user is only creating one exercise at a time here.  List is not needed
	public Exercise updateExercise(Exercise exercise);
	public Exercise deleteExercise(Integer exerciseId);
	
	
//	public List<Exercise> getReport(LocalDateTime addedDate, LocalDateTime updateDate);
	// getReport not necessary at this time.  Implement once many exercises have been created across multiple months.
	

	


}
