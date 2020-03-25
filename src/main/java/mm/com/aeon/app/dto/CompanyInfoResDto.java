/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:48:10
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dto;

import java.io.Serializable;

public class CompanyInfoResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	private String statusMessage;
	private int companyInfoId;
	private String addressEn;
	private String addressMm;
	private String hotLinePhone;
	private String webAddress;
	private String socialMediaAddress;
	private String aboutCompanyEn;
	private String aboutCompanyMm;
	
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
	public int getCompanyInfoId() {
		return companyInfoId;
	}
	public void setCompanyInfoId(int companyInfoId) {
		this.companyInfoId = companyInfoId;
	}
	public String getHotLinePhone() {
		return hotLinePhone;
	}
	public void setHotLinePhone(String hotLinePhone) {
		this.hotLinePhone = hotLinePhone;
	}
	public String getWebAddress() {
		return webAddress;
	}
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
	public String getSocialMediaAddress() {
		return socialMediaAddress;
	}
	public void setSocialMediaAddress(String socialMediaAddress) {
		this.socialMediaAddress = socialMediaAddress;
	}
	public String getAddressEn() {
		return addressEn;
	}
	public void setAddressEn(String addressEn) {
		this.addressEn = addressEn;
	}
	public String getAddressMm() {
		return addressMm;
	}
	public void setAddressMm(String addressMm) {
		this.addressMm = addressMm;
	}
	public String getAboutCompanyEn() {
		return aboutCompanyEn;
	}
	public void setAboutCompanyEn(String aboutCompanyEn) {
		this.aboutCompanyEn = aboutCompanyEn;
	}
	public String getAboutCompanyMm() {
		return aboutCompanyMm;
	}
	public void setAboutCompanyMm(String aboutCompanyMm) {
		this.aboutCompanyMm = aboutCompanyMm;
	}
}
