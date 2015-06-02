package com.ljr.entity;

import java.util.Date;

/**
 * TbUserWrongDiscipline entity. @author MyEclipse Persistence Tools
 */

public class TbUserWrongDiscipline implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbUser tbUser;
	private TbDiscipline tbDiscipline;
	private Date createDate;
	private Date updateDate;
	private Integer count;

	// Constructors

	/** default constructor */
	public TbUserWrongDiscipline() {
	}

	/** full constructor */
	public TbUserWrongDiscipline(TbUser tbUser, TbDiscipline tbDiscipline, Date createDate, Date updateDate, Integer count) {
		this.tbUser = tbUser;
		this.tbDiscipline = tbDiscipline;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.count = count;
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

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}