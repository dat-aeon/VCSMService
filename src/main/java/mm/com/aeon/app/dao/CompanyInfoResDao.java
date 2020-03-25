/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:48:10
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COMPANY_INFO")
public class CompanyInfoResDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="COMPANY_INFO_ID")
	private int companyInfoId;
	@Column(name="ADDRESS_ENG")
	private String addressEn;
	@Column(name="ADDRESS_MYAN")
	private String addressMm;
	@Column(name="HOTLINE_PHONE")
	private String hotLinePhone;
	@Column(name="WEB_ADDRESS")
	private String webAddress;
	@Column(name="SOCIAL_MEDIA_ADDRESS")
	private String socialMediaAddress;
	@Column(name="ABOUT_COMPANY_ENG")
	private String aboutCompanyEn;
	@Column(name="ABOUT_COMPANY_MYAN")
	private String aboutCompanyMm;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="CREATED_TIME")
	private Timestamp createdTime;
	@Column(name="UPDATED_TIME")
	private Timestamp updatedTime;
	
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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
