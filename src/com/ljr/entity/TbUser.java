package com.ljr.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbUser entity. @author MyEclipse Persistence Tools
 */

public class TbUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String name;
	private String password;
	private Date loginTime;
//	private Set tbUserWrongDisciplines = new HashSet(0);
//	private Set tbUserCollectDisciplines = new HashSet(0);
//	private Set tbUserReadArticles = new HashSet(0);
//	private Set tbUserExams = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbUser() {
	}

	/** full constructor */
	public TbUser(String username, String name, String password, Date loginTime/*, Set tbUserWrongDisciplines, Set tbUserCollectDisciplines,
			Set tbUserReadArticles, Set tbUserExams*/) {
		this.username = username;
		this.name = name;
		this.password = password;
		this.loginTime = loginTime;
//		this.tbUserWrongDisciplines = tbUserWrongDisciplines;
//		this.tbUserCollectDisciplines = tbUserCollectDisciplines;
//		this.tbUserReadArticles = tbUserReadArticles;
//		this.tbUserExams = tbUserExams;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

//	public Set getTbUserWrongDisciplines() {
//		return this.tbUserWrongDisciplines;
//	}
//
//	public void setTbUserWrongDisciplines(Set tbUserWrongDisciplines) {
//		this.tbUserWrongDisciplines = tbUserWrongDisciplines;
//	}
//
//	public Set getTbUserCollectDisciplines() {
//		return this.tbUserCollectDisciplines;
//	}
//
//	public void setTbUserCollectDisciplines(Set tbUserCollectDisciplines) {
//		this.tbUserCollectDisciplines = tbUserCollectDisciplines;
//	}
//
//	public Set getTbUserReadArticles() {
//		return this.tbUserReadArticles;
//	}
//
//	public void setTbUserReadArticles(Set tbUserReadArticles) {
//		this.tbUserReadArticles = tbUserReadArticles;
//	}
//
//	public Set getTbUserExams() {
//		return this.tbUserExams;
//	}
//
//	public void setTbUserExams(Set tbUserExams) {
//		this.tbUserExams = tbUserExams;
//	}

}