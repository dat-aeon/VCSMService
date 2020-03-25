/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/12 16:13:31
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="APP_CONFIG")
public class AppConfigResDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="APP_CONFIG_ID")
	private int appConfigId;
	@Column(name="NUM_OF_SECURITY_QUESTION")
	private int numOfSecQuestion;
	@Column(name="NUM_OF_ANSWER_CHARACTER")
	private int numOfAnsChar;
	
	public int getAppConfigId() {
		return appConfigId;
	}
	public void setAppConfigId(int appConfigId) {
		this.appConfigId = appConfigId;
	}
	public int getNumOfSecQuestion() {
		return numOfSecQuestion;
	}
	public void setNumOfSecQuestion(int numOfSecQuestion) {
		this.numOfSecQuestion = numOfSecQuestion;
	}
	public int getNumOfAnsChar() {
		return numOfAnsChar;
	}
	public void setNumOfAnsChar(int numOfAnsChar) {
		this.numOfAnsChar = numOfAnsChar;
	}
}
