/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/04 12:03:55
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dto;

import java.io.Serializable;

public class SecQAUpdateInfoResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int custSecQuesId;
	private String answer;
	private int secQuestionId;
	private String questionMM;
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
