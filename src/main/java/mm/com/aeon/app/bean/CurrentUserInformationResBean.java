/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/31 11:40:12
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.dto.CustAgreementListDto;

public class CurrentUserInformationResBean implements Serializable {

	/**
	 * Keep current login user informations.
	 */
	private static final long serialVersionUID = 1L;

	private String statusCode = CommonConstant.BLANK;
	private String statusMessage = CommonConstant.BLANK;
	private int customerId;
	private String customerNo = CommonConstant.BLANK;
	private String phoneNo = CommonConstant.BLANK;
	private int customerTypeId;
	private int userTypeId;
	private String name = CommonConstant.BLANK;
	private String dateOfBirth = CommonConstant.BLANK;
	private String nrcNo = CommonConstant.BLANK;
	private String photoPath = CommonConstant.BLANK;
	private int delFlag;
	private String password = CommonConstant.BLANK;
	private String appUsageDetailId = CommonConstant.BLANK;
	private String hotlinePhone = CommonConstant.BLANK;
	private List<CustAgreementListDto> custAgreementListDtoList;
	
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
	public List<CustAgreementListDto> getCustAgreementListDtoList() {
		return custAgreementListDtoList;
	}
	public void setCustAgreementListDtoList(List<CustAgreementListDto> custAgreementListDtoList) {
		this.custAgreementListDtoList = custAgreementListDtoList;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getCustomerTypeId() {
		return customerTypeId;
	}
	public void setCustomerTypeId(int customerTypeId) {
		this.customerTypeId = customerTypeId;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
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
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	public String getAppUsageDetailId() {
		return appUsageDetailId;
	}
	public void setAppUsageDetailId(String appUsageDetailId) {
		this.appUsageDetailId = appUsageDetailId;
	}
	public String getHotlinePhone() {
		return hotlinePhone;
	}
	public void setHotlinePhone(String hotlinePhone) {
		this.hotlinePhone = hotlinePhone;
	}
}
