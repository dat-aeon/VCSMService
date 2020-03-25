/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/12 12:50:19
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;

public class CouponInfoReqBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String customerId;
	private String siteActivationKey;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getSiteActivationKey() {
		return siteActivationKey;
	}
	public void setSiteActivationKey(String siteActivationKey) {
		this.siteActivationKey = siteActivationKey;
	}
	
}
