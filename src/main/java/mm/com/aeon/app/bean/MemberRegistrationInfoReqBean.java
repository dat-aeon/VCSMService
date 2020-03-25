/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/31 11:45:57
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class MemberRegistrationInfoReqBean implements Serializable {

	/**
	 * Keep requested registration form data.
	 */
	private static final long serialVersionUID = 1L;
	
	private String dateOfBirth;
	private String nrcNo;
	private String phoneNo;
	private String customerType; //1|2
	private int customerTypeId;
	private int userTypeId;
	private String createdBy;
	private String customerNo;
	private String name;
	private String password;
	private String photoPath;
	private String agreementNo;
	private int importCustomerId;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private List<AnsweredSecurityQuestionReqBean> securityAnsweredInfoList;
	private AppUsageInfoReqBean appUsageInfo;
	
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public int getImportCustomerId() {
		return importCustomerId;
	}
	public void setImportCustomerId(int importCustomerId) {
		this.importCustomerId = importCustomerId;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public int getCustomerTypeId() {
		return customerTypeId;
	}
	public void setCustomerTypeId(int customerTypeId) {
		this.customerTypeId = customerTypeId;
	}
	public AppUsageInfoReqBean getAppUsageInfo() {
		return appUsageInfo;
	}
	public void setAppUsageInfo(AppUsageInfoReqBean appUsageInfo) {
		this.appUsageInfo = appUsageInfo;
	}
	public List<AnsweredSecurityQuestionReqBean> getSecurityAnsweredInfoList() {
		return securityAnsweredInfoList;
	}
	public void setSecurityAnsweredInfoList(List<AnsweredSecurityQuestionReqBean> securityAnsweredInfoList) {
		this.securityAnsweredInfoList = securityAnsweredInfoList;
	}
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
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
}
