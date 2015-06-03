package com.ljr.dto;

import java.io.Serializable;

public class UserCollectDisciplineDTO implements Serializable {
	private String userId;
	private String disciplineId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDisciplineId() {
		return disciplineId;
	}
	public void setDisciplineId(String disciplineId) {
		this.disciplineId = disciplineId;
	}

}
