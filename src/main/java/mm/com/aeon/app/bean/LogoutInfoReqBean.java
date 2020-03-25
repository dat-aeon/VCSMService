/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/05 18:18:37
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;

public class LogoutInfoReqBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String appUsageDetailId;
	private String logoutTime;
	
	public String getAppUsageDetailId() {
		return appUsageDetailId;
	}
	public void setAppUsageDetailId(String appUsageDetailId) {
		this.appUsageDetailId = appUsageDetailId;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	
	
}
