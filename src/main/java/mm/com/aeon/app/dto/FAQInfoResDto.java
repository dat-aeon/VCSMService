/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:49:05
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dto;

import java.io.Serializable;

public class FAQInfoResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int faqId;
	private String questionMM;
	private String questionEN;
	private String answerMM;
	private String answerEN;
	private int categoryId;
	
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
}
