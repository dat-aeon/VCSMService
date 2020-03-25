/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:42:14
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
@Table(name="CUST_SEC_QUESTION")
public class CustSecQuestionResDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CUST_SEC_QUES_ID")
	private int custSecQuesId;
	@Column(name="SEC_QUES_ID")
	private int secQuesId;
	@Column(name="CUSTOMER_ID")
	private int customerId;
	@Column(name="ANSWER")
	private String answer;
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
	
	public int getCustSecQuesId() {
		return custSecQuesId;
	}
	public void setCustSecQuesId(int custSecQuesId) {
		this.custSecQuesId = custSecQuesId;
	}
	public int getSecQuesId() {
		return secQuesId;
	}
	public void setSecQuesId(int secQuesId) {
		this.secQuesId = secQuesId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
