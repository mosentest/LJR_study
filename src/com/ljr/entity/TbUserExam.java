package com.ljr.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbUserExam entity. @author MyEclipse Persistence Tools
 */

public class TbUserExam implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbUser tbUser;
	private Integer sum;
	private Date createDate;
//	private Set tbUserExamDisciplines = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbUserExam() {
	}

	/** full constructor */
	public TbUserExam(TbUser tbUser, Integer sum, Date createDate/*, Set tbUserExamDisciplines*/) {
		this.tbUser = tbUser;
		this.sum = sum;
		this.createDate = createDate;
//		this.tbUserExamDisciplines = tbUserExamDisciplines;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbUser getTbUser() {
		return this.tbUser;
	}

	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}

	public Integer getSum() {
		return this.sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

//	public Set getTbUserExamDisciplines() {
//		return this.tbUserExamDisciplines;
//	}
//
//	public void setTbUserExamDisciplines(Set tbUserExamDisciplines) {
//		this.tbUserExamDisciplines = tbUserExamDisciplines;
//	}

}