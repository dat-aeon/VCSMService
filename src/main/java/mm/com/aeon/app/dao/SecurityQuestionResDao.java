/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:43:01
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="SECURITY_QUESTION")
public class SecurityQuestionResDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SEC_QUES_ID")
	private int secQuestionId;
	@Column(name="QUESTION_MYAN")
	private String questionMM;
	@Column(name="QUESTION_ENG")
	private String questionEN;
	@Column(name="DEL_FLAG")
	private int delFlag;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="CREATED_TIME")
	private Timestamp createdTime;
	@Column(name="UPDATED_TIME")
	private Timestamp updatedTime;
	
	public int getSecQuestionId() {
		return secQuestionId;
	}
	public void setSecQuestionId(int secQuestionId) {
		this.secQuestionId = secQuestionId;
	}
	public String getQuestionMM() {
		return questionMM;
	}
	public void setQuestionMM(String questionMM) {
		this.questionMM = questionMM;
	}
	public String getQuestionEN() {
		return questionEN;
	}
	public void setQuestionEN(String questionEN) {
		this.questionEN = questionEN;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
