package com.ljr.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TbDiscipline entity. @author MyEclipse Persistence Tools
 */

public class TbDiscipline implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbSubjectType tbSubjectType;
	private String question;
	private String answers;
	private Integer score;
//	private Set tbUserWrongDisciplines = new HashSet(0);
//	private Set tbUserExamDisciplines = new HashSet(0);
//	private Set tbDisciplineOptions = new HashSet(0);
//	private Set tbUserCollectDisciplines = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbDiscipline() {
	}

	/** full constructor */
	public TbDiscipline(TbSubjectType tbSubjectType, String question, String answers, Integer score/*, Set tbUserWrongDisciplines,
			Set tbUserExamDisciplines, Set tbDisciplineOptions, Set tbUserCollectDisciplines*/) {
		this.tbSubjectType = tbSubjectType;
		this.question = question;
		this.answers = answers;
		this.score = score;
//		this.tbUserWrongDisciplines = tbUserWrongDisciplines;
//		this.tbUserExamDisciplines = tbUserExamDisciplines;
//		this.tbDisciplineOptions = tbDisciplineOptions;
//		this.tbUserCollectDisciplines = tbUserCollectDisciplines;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbSubjectType getTbSubjectType() {
		return this.tbSubjectType;
	}

	public void setTbSubjectType(TbSubjectType tbSubjectType) {
		this.tbSubjectType = tbSubjectType;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswers() {
		return this.answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

//	public Set getTbUserWrongDisciplines() {
//		return this.tbUserWrongDisciplines;
//	}
//
//	public void setTbUserWrongDisciplines(Set tbUserWrongDisciplines) {
//		this.tbUserWrongDisciplines = tbUserWrongDisciplines;
//	}
//
//	public Set getTbUserExamDisciplines() {
//		return this.tbUserExamDisciplines;
//	}
//
//	public void setTbUserExamDisciplines(Set tbUserExamDisciplines) {
//		this.tbUserExamDisciplines = tbUserExamDisciplines;
//	}
//
//	public Set getTbDisciplineOptions() {
//		return this.tbDisciplineOptions;
//	}
//
//	public void setTbDisciplineOptions(Set tbDisciplineOptions) {
//		this.tbDisciplineOptions = tbDisciplineOptions;
//	}
//
//	public Set getTbUserCollectDisciplines() {
//		return this.tbUserCollectDisciplines;
//	}
//
//	public void setTbUserCollectDisciplines(Set tbUserCollectDisciplines) {
//		this.tbUserCollectDisciplines = tbUserCollectDisciplines;
//	}

}