package com.ljr.entity;

import java.io.Serializable;
/**
 * 问卷 
 * @author 
 *
 */
public class TbQuestionnaire implements Serializable  {
	private Integer id;
	private String name;
	private TbSubjectType tbSubjectType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TbSubjectType getTbSubjectType() {
		return tbSubjectType;
	}
	public void setTbSubjectType(TbSubjectType tbSubjectType) {
		this.tbSubjectType = tbSubjectType;
	}
	
}
