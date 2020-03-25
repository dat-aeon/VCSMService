/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:49:05
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
@Table(name="FAQ_INFO")
public class FAQInfoResDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="FAQ_ID")
	private int faqId;
	@Column(name="QUESTION_MYAN")
	private String questionMM;
	@Column(name="QUESTION_ENG")
	private String questionEN;
	@Column(name="ANSWER_MYAN")
	private String answerMM;
	@Column(name="ANSWER_ENG")
	private String answerEN;
	@Column(name="CATEGORY_ID")
	private int categoryId;
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
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getFaqId() {
		return faqId;
	}
	public void setFaqId(int faqId) {
		this.faqId = faqId;
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
	public String getAnswerMM() {
		return answerMM;
	}
	public void setAnswerMM(String answerMM) {
		this.answerMM = answerMM;
	}
	public String getAnswerEN() {
		return answerEN;
	}
	public void setAnswerEN(String answerEN) {
		this.answerEN = answerEN;
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






























