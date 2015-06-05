package com.ljr.dto;

import java.io.Serializable;

public class UserQuestionnaireDTO implements Serializable {
	
	private String userId;
	private String questionnarieId;
	private String sum;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQuestionnarieId() {
		return questionnarieId;
	}

	public void setQuestionnarieId(String questionnarieId) {
		this.questionnarieId = questionnarieId;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

}
