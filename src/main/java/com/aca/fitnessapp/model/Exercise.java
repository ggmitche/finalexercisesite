package com.aca.fitnessapp.model;

import java.time.LocalDateTime;

public class Exercise {
	
private String title;
private BodyPart bodyPart;
private Integer exerciseId;
private Type exerciseType;
private LocalDateTime addedDateTime;
private LocalDateTime updateDateTime;




public Type getExerciseType() {
	return exerciseType;
}
public void setExerciseType(Type exerciseType) {
	this.exerciseType = exerciseType;
}
public LocalDateTime getAddedDateTime() {
	return addedDateTime;
}
public void setAddedDateTime(LocalDateTime addedDateTime) {
	this.addedDateTime = addedDateTime;
}
public LocalDateTime getUpdateDateTime() {
	return updateDateTime;
}
public void setUpdateDate(LocalDateTime updateDateTime) {
	this.updateDateTime = updateDateTime;
}
public Integer getExerciseId() {
	return exerciseId;
}
public void setExerciseId(Integer exerciseId) {
	this.exerciseId = exerciseId;
}
public String getTitle() {
	return title;
	
}
public void setTitle(String title) {
	this.title = title;
}
public BodyPart getBodyPart() {
	return bodyPart;
}
public void setBodyPart(BodyPart bodyPart) {
	this.bodyPart = bodyPart;
}


}
