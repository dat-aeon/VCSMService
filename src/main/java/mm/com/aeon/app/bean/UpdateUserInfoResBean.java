/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/03 19:23:20
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

public class UpdateUserInfoResBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int customerId;
	private String password;
	private List<SecurityQAUpdateInfo> securityQAUpdateInfoList;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<SecurityQAUpdateInfo> getSecurityQAUpdateInfoList() {
		return securityQAUpdateInfoList;
	}
	public void setSecurityQAUpdateInfoList(List<SecurityQAUpdateInfo> securityQAUpdateInfoList) {
		this.securityQAUpdateInfoList = securityQAUpdateInfoList;
	}
}
