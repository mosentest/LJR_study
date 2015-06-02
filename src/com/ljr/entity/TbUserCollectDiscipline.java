package com.ljr.entity;

import java.util.Date;

/**
 * TbUserCollectDiscipline entity. @author MyEclipse Persistence Tools
 */

public class TbUserCollectDiscipline implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbUser tbUser;
	private TbDiscipline tbDiscipline;
	private Date collectDate;

	// Constructors

	/** default constructor */
	public TbUserCollectDiscipline() {
	}

	/** full constructor */
	public TbUserCollectDiscipline(TbUser tbUser, TbDiscipline tbDiscipline, Date collectDate) {
		this.tbUser = tbUser;
		this.tbDiscipline = tbDiscipline;
		this.collectDate = collectDate;
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

	public TbDiscipline getTbDiscipline() {
		return this.tbDiscipline;
	}

	public void setTbDiscipline(TbDiscipline tbDiscipline) {
		this.tbDiscipline = tbDiscipline;
	}

	public Date getCollectDate() {
		return this.collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

}