/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/03 15:20:05
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

public class ResetPasswordConfirmedInfoReqBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String phoneNo;
	private String nrcNo;
	private List<ResetPwdAnsweredSecQuesReqBean> resetPwdAnsweredSecQuesList;
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getNrcNo() {
		return nrcNo;
	}
	public void setNrcNo(String nrcNo) {
		this.nrcNo = nrcNo;
	}
	
	public List<ResetPwdAnsweredSecQuesReqBean> getResetPwdAnsweredSecQuesList() {
		return resetPwdAnsweredSecQuesList;
	}
	public void setResetPwdAnsweredSecQuesList(List<ResetPwdAnsweredSecQuesReqBean> resetPwdAnsweredSecQuesList) {
		this.resetPwdAnsweredSecQuesList = resetPwdAnsweredSecQuesList;
	}
}
