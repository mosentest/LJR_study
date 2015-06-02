package com.ljr.entity;

import java.io.Serializable;

public class TbQuestionnaire implements Serializable  {
	private Integer id;
	private TbSubjectType tbSubjectType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TbSubjectType getTbSubjectType() {
		return tbSubjectType;
	}
	public void setTbSubjectType(TbSubjectType tbSubjectType) {
		this.tbSubjectType = tbSubjectType;
	}
	
}
