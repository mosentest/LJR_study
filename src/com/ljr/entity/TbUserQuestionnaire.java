package com.ljr.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户测试
 * @author 
 *
 */
public class TbUserQuestionnaire implements Serializable{
	private Integer id;
	private TbUser tbUser;
	private TbQuestionnaire tbQuestionnaire;
	private Integer sum;
	private Date createDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TbUser getTbUser() {
		return tbUser;
	}
	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}
	public TbQuestionnaire getTbQuestionnaire() {
		return tbQuestionnaire;
	}
	public void setTbQuestionnaire(TbQuestionnaire tbQuestionnaire) {
		this.tbQuestionnaire = tbQuestionnaire;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
