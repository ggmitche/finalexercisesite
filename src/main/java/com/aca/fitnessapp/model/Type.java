package com.aca.fitnessapp.model;

public enum Type {
	Weighted, Calisthenics, Recovery, Corrective, Warmup;
	
	public static Type convertStringToType(String value) {
		Type myExerciseType = null;
		for (Type exerciseType : Type.values()) { 
			if (exerciseType.toString().equalsIgnoreCase(value)) {
				myExerciseType = exerciseType;
			}
		}
		return myExerciseType;
	}

}


