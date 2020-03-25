/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/04 11:42:22
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class SecQAUpdateInfoResDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//cust_sec_question
	@Id
	@Column(name="CUST_SEC_QUES_ID")
	private int custSecQuesId;
	@Column(name="ANSWER")
	private String answer;
	//security_question
	@Column(name="SEC_QUES_ID")
	private int secQuestionId;
	@Column(name="QUESTION_MYAN")
	private String questionMM;
	@Column(name="QUESTION_ENG")
	private String questionEN;
	
	public int getCustSecQuesId() {
		return custSecQuesId;
	}
	public void setCustSecQuesId(int custSecQuesId) {
		this.custSecQuesId = custSecQuesId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
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
}

