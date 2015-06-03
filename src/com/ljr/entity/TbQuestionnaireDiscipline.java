package com.ljr.entity;

import java.io.Serializable;
/**
 * 问卷中的题目
 * @author 
 *
 */
public class TbQuestionnaireDiscipline implements Serializable {
	private Integer id;
	private TbQuestionnaire tbQuestionnaire;
	private TbDiscipline tbDiscipline;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TbQuestionnaire getTbQuestionnaire() {
		return tbQuestionnaire;
	}
	public void setTbQuestionnaire(TbQuestionnaire tbQuestionnaire) {
		this.tbQuestionnaire = tbQuestionnaire;
	}
	public TbDiscipline getTbDiscipline() {
		return tbDiscipline;
	}
	public void setTbDiscipline(TbDiscipline tbDiscipline) {
		this.tbDiscipline = tbDiscipline;
	}
	
}
