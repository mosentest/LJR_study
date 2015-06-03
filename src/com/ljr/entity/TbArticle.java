package com.ljr.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TbArticle 文章
 */

public class TbArticle implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbSubjectType tbSubjectType;
	private String title;
	private String content;
//	private Set tbUserReadArticles = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbArticle() {
	}

	/** full constructor */
	public TbArticle(TbSubjectType tbSubjectType, String title, String content/*, Set tbUserReadArticles*/) {
		this.tbSubjectType = tbSubjectType;
		this.title = title;
		this.content = content;
//		this.tbUserReadArticles = tbUserReadArticles;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

//	public Set getTbUserReadArticles() {
//		return this.tbUserReadArticles;
//	}
//
//	public void setTbUserReadArticles(Set tbUserReadArticles) {
//		this.tbUserReadArticles = tbUserReadArticles;
//	}

}