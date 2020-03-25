/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/03 19:26:45
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;

public class SecurityQAUpdateInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String custSecQuesId;
	private String answer;
	private String secQuesId;
	
	public String getCustSecQuesId() {
		return custSecQuesId;
	}
	public void setCustSecQuesId(String custSecQuesId) {
		this.custSecQuesId = custSecQuesId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSecQuesId() {
		return secQuesId;
	}
	public void setSecQuesId(String secQuesId) {
		this.secQuesId = secQuesId;
	}
}
