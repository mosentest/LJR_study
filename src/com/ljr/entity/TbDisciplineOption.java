package com.ljr.entity;

/**
 * TbDisciplineOption 题目选项
 */

public class TbDisciplineOption implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbDiscipline tbDiscipline;
	private String content;

	// Constructors

	/** default constructor */
	public TbDisciplineOption() {
	}

	/** full constructor */
	public TbDisciplineOption(TbDiscipline tbDiscipline, String content) {
		this.tbDiscipline = tbDiscipline;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbDiscipline getTbDiscipline() {
		return this.tbDiscipline;
	}

	public void setTbDiscipline(TbDiscipline tbDiscipline) {
		this.tbDiscipline = tbDiscipline;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}