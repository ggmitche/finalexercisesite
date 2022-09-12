//package com.aca.fitnessapp.dao;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.aca.fitnessapp.model.BodyPart;
//import com.aca.fitnessapp.model.Exercise;
//import com.aca.fitnessapp.model.Type;
//
//public class FitnessAppDaoMock implements FitnessAppDao {
//
//	private static List<Exercise> exercises = new ArrayList<Exercise>();
//
//	private static Integer lastExerciseId = 12; // testing for future function
//
//	private static Integer getNextExerciseId() {
//		return ++lastExerciseId;
//	}
//
//	// static blocks for Postman testing:
//
////	static {
////		Exercise pushup = new Exercise();
////		pushup.setTitle("Pushup");
////		pushup.setBodyPart(BodyPart.Chest);
////		pushup.setExerciseType(Type.Calisthenics);
////		pushup.setExerciseId(1);
////
////		Exercise pullup = new Exercise();
////		pullup.setTitle("Pullup");
////		pullup.setBodyPart(BodyPart.Back);
////		pullup.setExerciseType(Type.Calisthenics);
////		pullup.setExerciseId(2);
////
////		Exercise barPullover = new Exercise();
////		barPullover.setTitle("Bar Pullover");
////		barPullover.setBodyPart(BodyPart.Abs);
////		barPullover.setExerciseType(Type.Calisthenics);
////		barPullover.setExerciseId(3);
////
////		Exercise deadlift = new Exercise();
////		deadlift.setTitle("Deadlift");
////		deadlift.setBodyPart(BodyPart.Abs);
////		deadlift.setExerciseType(Type.Weighted);
////		deadlift.setExerciseId(4);
////
////		Exercise hamstringStretch = new Exercise();
////		hamstringStretch.setTitle("Hamstring Stretch");
////		hamstringStretch.setBodyPart(BodyPart.Legs);
////		hamstringStretch.setExerciseType(Type.Recovery);
////		hamstringStretch.setExerciseId(5);
////
////		Exercise dowellTwist = new Exercise();
////		dowellTwist.setTitle("Dowell Twists");
////		dowellTwist.setBodyPart(BodyPart.Shoulders);
////		dowellTwist.setExerciseType(Type.Warmup);
////		dowellTwist.setExerciseId(6);
////
////		exercises.add(pushup);
////		exercises.add(pullup);
////		exercises.add(barPullover);
////		exercises.add(deadlift);
////		exercises.add(hamstringStretch);
////		exercises.add(dowellTwist);
////
////	}
//
//	@Override
//	public List<Exercise> getExercises() {
//		List<Exercise> myExercises = new ArrayList<Exercise>();
//		myExercises.addAll(exercises);
//		return myExercises;
//	}
//
//	@Override
//	public List<Exercise> getExercisesByBodyPart(BodyPart bodyPart) {
//		List<Exercise> myExercises = new ArrayList<Exercise>();
//		for (Exercise exercise : exercises) {
//			if (exercise.getBodyPart().equals(bodyPart)) { // checks body part entries to make sure they're the same as
//															// the ones designated by the Enum
//				myExercises.add(exercise);
//			}
//		}
//		return myExercises;
//	}
//
//	@Override
//	public List<Exercise> getExercisesById(Integer exerciseId) {
//		List<Exercise> myExercises = new ArrayList<Exercise>();
//		for (Exercise exercise : exercises) {
//			if (exercise.getExerciseId().intValue() == exerciseId.intValue()) {
//
//				myExercises.add(exercise);
//			}
//		}
//		return myExercises;
//	}
//
//	@Override
//	public List<Exercise> getExercisesByType(Type exerciseType) {
//		List<Exercise> myExercises = new ArrayList<Exercise>();
//		for (Exercise exercise : exercises) {
//			if (exercise.getExerciseType().equals(exerciseType)) {
//
//				myExercises.add(exercise);
//			}
//		}
//		return myExercises;
//
//	}
//
//	@Override
//	public Exercise createExercise(Exercise newExercise) { // new exercises created outside of the Java methods. Will be
//															// erased when Postman closes
//		newExercise.setExerciseId(getNextExerciseId());// creates new exerciseId.
//		exercises.add(newExercise);
//		return newExercise;
//
//		// Postman testing: use POST request, put name-value pairs in the raw body and
//		// change headers to Accept application/json and Content-Type application/json
//		// remember it must be in the form of an array
//	}
//
//	@Override
//	public Exercise updateExercise(Exercise exercise) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Exercise deleteExercise(Integer exerciseId) { // deletes Movie by Id if the id exists
//		int index = 0;
//		for (int i = 0; i < exercises.size(); i++) {
//			if (exercises.get(i).getExerciseId().intValue() == exerciseId.intValue()) {
//				index = i;
//				break;
//			}
//		}
//		Exercise exercise = exercises.get(index); // shows the exercise in the index before it's deleted
//		exercises.remove(index);
//		return exercise;
//	}
//
////	@Override
////	public List<Exercise> getReport(LocalDateTime addedDate, LocalDateTime updateDate) {
////		// TODO Auto-generated method stub
////		return null;
////	}
//
//	@Override
//	public List<Exercise> getExercisesByTitle(String title) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
