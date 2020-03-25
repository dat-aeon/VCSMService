/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/09 16:39:13
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

public class NewMemberRegisterationInfoReqBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dateOfBirth;
	private String nrcNo;
	private String phoneNo;
	private String name;
	private String password;
	private List<AnsweredSecurityQuestionReqBean> securityAnsweredInfoList;
	private AppUsageInfoReqBean appUsageInfo;
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getNrcNo() {
		return nrcNo;
	}
	public void setNrcNo(String nrcNo) {
		this.nrcNo = nrcNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AnsweredSecurityQuestionReqBean> getSecurityAnsweredInfoList() {
		return securityAnsweredInfoList;
	}
	public void setSecurityAnsweredInfoList(List<AnsweredSecurityQuestionReqBean> securityAnsweredInfoList) {
		this.securityAnsweredInfoList = securityAnsweredInfoList;
	}
	public AppUsageInfoReqBean getAppUsageInfo() {
		return appUsageInfo;
	}
	public void setAppUsageInfo(AppUsageInfoReqBean appUsageInfo) {
		this.appUsageInfo = appUsageInfo;
	}
}
