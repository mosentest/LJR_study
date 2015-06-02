package com.ljr.entity;

/**
 * TbUserExamDiscipline entity. @author MyEclipse Persistence Tools
 */

public class TbUserExamDiscipline implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbUserExam tbUserExam;
	private TbDiscipline tbDiscipline;
	private String answers;

	// Constructors

	/** default constructor */
	public TbUserExamDiscipline() {
	}

	/** full constructor */
	public TbUserExamDiscipline(TbUserExam tbUserExam, TbDiscipline tbDiscipline, String answers) {
		this.tbUserExam = tbUserExam;
		this.tbDiscipline = tbDiscipline;
		this.answers = answers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbUserExam getTbUserExam() {
		return this.tbUserExam;
	}

	public void setTbUserExam(TbUserExam tbUserExam) {
		this.tbUserExam = tbUserExam;
	}

	public TbDiscipline getTbDiscipline() {
		return this.tbDiscipline;
	}

	public void setTbDiscipline(TbDiscipline tbDiscipline) {
		this.tbDiscipline = tbDiscipline;
	}

	public String getAnswers() {
		return this.answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

}