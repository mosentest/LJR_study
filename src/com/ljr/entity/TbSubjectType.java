package com.ljr.entity;


/**
 * TbSubjectType 科目
 */

public class TbSubjectType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
//	private Set tbArticles = new HashSet(0);
//	private Set tbDisciplines = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbSubjectType() {
	}

	/** full constructor */
	public TbSubjectType(String name/*, Set tbArticles, Set tbDisciplines*/) {
		this.name = name;
//		this.tbArticles = tbArticles;
//		this.tbDisciplines = tbDisciplines;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Set getTbArticles() {
//		return this.tbArticles;
//	}
//
//	public void setTbArticles(Set tbArticles) {
//		this.tbArticles = tbArticles;
//	}
//
//	public Set getTbDisciplines() {
//		return this.tbDisciplines;
//	}
//
//	public void setTbDisciplines(Set tbDisciplines) {
//		this.tbDisciplines = tbDisciplines;
//	}

}