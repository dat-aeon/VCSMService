/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/31 14:02:17
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IMPORT_CUSTOMER_INFO")
public class ImportCustomerInfoResDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IMPORT_CUSTOMER_INFO_ID")
	private int importCustomerInfoId;
	@Column(name="MEMBERCARD_ID")
	private String membercardId;
	@Column(name="CUSTOMER_NO")
	private String customerNo;
	@Column(name="PHONE_NO")
	private String phoneNo;
	@Column(name="NAME")
	private String name;
	@Column(name="DOB")
	private Date dateOfBirth;
	@Column(name="NRC_NO")
	private String nrcNo;
	@Column(name="SALARY")
	private String salary;
	@Column(name="COMPANY_NAME")
	private String companyName;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="GENDER")
	private int gender;
	@Column(name="DEL_FLAG")
	private int delFlag;
	@Column(name="CREATED_TIME")
	private Timestamp createdTime;
	@Column(name="UPDATED_TIME")
	private Timestamp updatedTime;
	
	public int getImportCustomerInfoId() {
		return importCustomerInfoId;
	}
	public void setImportCustomerInfoId(int importCustomerInfoId) {
		this.importCustomerInfoId = importCustomerInfoId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMembercardId() {
		return membercardId;
	}
	public void setMembercardId(String membercardId) {
		this.membercardId = membercardId;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getNrcNo() {
		return nrcNo;
	}
	public void setNrcNo(String nrcNo) {
		this.nrcNo = nrcNo;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
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
}

