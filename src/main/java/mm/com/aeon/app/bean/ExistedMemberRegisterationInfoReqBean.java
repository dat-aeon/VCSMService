/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/09 16:51:18
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

public class ExistedMemberRegisterationInfoReqBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String dateOfBirth;
	private String nrcNo;
	private String phoneNo;
	private String password;
	private int importCustomerId;
	private String customerNo;
	private String photoPath;
	private List<AnsweredSecurityQuestionReqBean> securityAnsweredInfoList;
	private AppUsageInfoReqBean appUsageInfo;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getImportCustomerId() {
		return importCustomerId;
	}
	public void setImportCustomerId(int importCustomerId) {
		this.importCustomerId = importCustomerId;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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
