/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/09 15:35:52
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;

public class CustomerInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private String statusCode;
	private String statusMessage;
	private String hotlinePhone;
	private CheckMemberInformationResBean checkMemInfoResBean;
	
	public String getHotlinePhone() {
		return hotlinePhone;
	}
	public void setHotlinePhone(String hotlinePhone) {
		this.hotlinePhone = hotlinePhone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CheckMemberInformationResBean getCheckMemInfoResBean() {
		return checkMemInfoResBean;
	}
	public void setCheckMemInfoResBean(CheckMemberInformationResBean checkMemInfoResBean) {
		this.checkMemInfoResBean = checkMemInfoResBean;
	}
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
}
