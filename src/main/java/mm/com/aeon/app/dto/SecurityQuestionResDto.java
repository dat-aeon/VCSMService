/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:43:01
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dto;

import java.io.Serializable;

public class SecurityQuestionResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int secQuestionId;
	private String questionMM;
	private String questionEN;
	
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
