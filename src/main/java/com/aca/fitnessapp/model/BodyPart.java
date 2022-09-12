package com.aca.fitnessapp.model;

public enum BodyPart {
	
	Legs, Arms, Chest, Back, Shoulders, Abs, FullBody;
	
	public static BodyPart convertStringToBodyPart(String value) {
		BodyPart myBodyPart = null;
		for (BodyPart bodyPart : BodyPart.values()) { 
			if (bodyPart.toString().equalsIgnoreCase(value)) {
				myBodyPart = bodyPart;
			}
		}
		return myBodyPart;
	}

}
