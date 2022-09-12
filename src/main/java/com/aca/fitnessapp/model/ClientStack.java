package com.aca.fitnessapp.model;

import java.time.LocalDateTime;

public class ClientStack {

	private Integer id;
	private String stack;
	private String description;
	private LocalDateTime updateDate;
	private LocalDateTime createDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public void setUpdateDateTime(LocalDateTime object) {
		// TODO Auto-generated method stub
		
	}
	public void setCreateDateTime(LocalDateTime object) {
		// TODO Auto-generated method stub
		
	}
	
	
}
