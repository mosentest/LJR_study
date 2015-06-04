package com.ljr.dto;

import java.io.Serializable;


public class DisciplineDTO implements Serializable {
	
	private String id;//修改的时候使用
	
	
	private String question;
	private String answers;
	
	private String optionId1;
	private String optionId2;
	private String optionId3;
	private String optionId4;
	//对应的4个答案
	private String name1;
	private String name2;
	private String name3;
	private String name4;
	
	public String getOptionId1() {
		return optionId1;
	}
	public void setOptionId1(String optionId1) {
		this.optionId1 = optionId1;
	}
	public String getOptionId2() {
		return optionId2;
	}
	public void setOptionId2(String optionId2) {
		this.optionId2 = optionId2;
	}
	public String getOptionId3() {
		return optionId3;
	}
	public void setOptionId3(String optionId3) {
		this.optionId3 = optionId3;
	}
	public String getOptionId4() {
		return optionId4;
	}
	public void setOptionId4(String optionId4) {
		this.optionId4 = optionId4;
	}
	private String tpyeId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTpyeId() {
		return tpyeId;
	}
	public void setTpyeId(String tpyeId) {
		this.tpyeId = tpyeId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	public String getName4() {
		return name4;
	}
	public void setName4(String name4) {
		this.name4 = name4;
	}
	
	
}
