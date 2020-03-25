/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/31 15:27:17
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;
import mm.com.aeon.app.dto.CustAgreementListDto;

public class CheckMemberInformationResBean implements Serializable {

	/**
	 * Response data for checking before registration | This can be extended.
	 */
	private static final long serialVersionUID = 1L;
	
	private int importCustomerInfoId;
	private String customerNo;
	private String name;
	private int gender;
	private String phoneNo;
	private String nrcNo;
	private String dateOfBirth;
	private String salary;
	private int age;
	private String companyName;
	private String townshipAddress;
	private String status;
	private List<CustAgreementListDto> custAgreementListResDtoList;
	
	public List<CustAgreementListDto> getCustAgreementListResDtoList() {
		return custAgreementListResDtoList;
	}
	public void setCustAgreementListResDtoList(List<CustAgreementListDto> custAgreementListResDtoList) {
		this.custAgreementListResDtoList = custAgreementListResDtoList;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
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
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTownshipAddress() {
		return townshipAddress;
	}
	public void setTownshipAddress(String townshipAddress) {
		this.townshipAddress = townshipAddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
