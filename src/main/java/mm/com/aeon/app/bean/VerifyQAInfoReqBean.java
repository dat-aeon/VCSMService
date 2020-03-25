/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/03 15:20:05
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

public class VerifyQAInfoReqBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerId;
	private List<ResetPwdAnsweredSecQuesReqBean> secQuesList;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<ResetPwdAnsweredSecQuesReqBean> getSecQuesList() {
		return secQuesList;
	}

	public void setSecQuesList(List<ResetPwdAnsweredSecQuesReqBean> secQuesList) {
		this.secQuesList = secQuesList;
	}

}
