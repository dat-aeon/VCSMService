/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/04 14:30:52
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

public class UpdateUserQAInfoReqBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerId;
	private String password;
	//private String securityQAUpdateInfo;
	private List<SecurityQAUpdateInfo> securityQAUpdateInfo;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<SecurityQAUpdateInfo> getSecurityQAUpdateInfo() {
		return securityQAUpdateInfo;
	}
	public void setSecurityQAUpdateInfo(List<SecurityQAUpdateInfo> securityQAUpdateInfo) {
		this.securityQAUpdateInfo = securityQAUpdateInfo;
	}
	
	
}
