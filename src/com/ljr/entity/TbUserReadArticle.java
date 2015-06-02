package com.ljr.entity;

import java.util.Date;

/**
 * TbUserReadArticle entity. @author MyEclipse Persistence Tools
 */

public class TbUserReadArticle implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbUser tbUser;
	private TbArticle tbArticle;
	private Date readDate;
	private Date updateDate;
	private Integer readCount;

	// Constructors

	/** default constructor */
	public TbUserReadArticle() {
	}

	/** full constructor */
	public TbUserReadArticle(TbUser tbUser, TbArticle tbArticle, Date readDate, Date updateDate, Integer readCount) {
		this.tbUser = tbUser;
		this.tbArticle = tbArticle;
		this.readDate = readDate;
		this.updateDate = updateDate;
		this.readCount = readCount;
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

	public TbArticle getTbArticle() {
		return this.tbArticle;
	}

	public void setTbArticle(TbArticle tbArticle) {
		this.tbArticle = tbArticle;
	}

	public Date getReadDate() {
		return this.readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getReadCount() {
		return this.readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

}