/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/12 16:07:37
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

import mm.com.aeon.app.dto.SecurityQuestionResDto;

public class RegSecurityQuestionInfoResBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	private String statusMessage;
	private int numOfSecQues;
	private int numOfAnsChar;
	private List<SecurityQuestionResDto> securityQuestionResDtoList;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public int getNumOfSecQues() {
		return numOfSecQues;
	}
	public void setNumOfSecQues(int numOfSecQues) {
		this.numOfSecQues = numOfSecQues;
	}
	public int getNumOfAnsChar() {
		return numOfAnsChar;
	}
	public void setNumOfAnsChar(int numOfAnsChar) {
		this.numOfAnsChar = numOfAnsChar;
	}
	public List<SecurityQuestionResDto> getSecurityQuestionResDtoList() {
		return securityQuestionResDtoList;
	}
	public void setSecurityQuestionResDtoList(List<SecurityQuestionResDto> securityQuestionResDtoList) {
		this.securityQuestionResDtoList = securityQuestionResDtoList;
	}
}
