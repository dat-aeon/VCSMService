/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:40:20
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="APP_USAGE_INFO")
public class AppUsageInfoReqDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_APP_USAGE_INFO")
	@SequenceGenerator(
			name="SEQ_APP_USAGE_INFO",
			sequenceName="SEQ_APP_USAGE_INFO_ID",
			allocationSize=1)
	@Column(name="APP_USAGE_ID")
	private int appUsageId;
	@Column(name="CUSTOMER_ID")
	private int customerId;
	@Column(name="REGISTRATION_DATE_TIME")
	private Timestamp registerationDateTime;
	@Column(name="PHONE_MODEL")
	private String phoneModel;
	@Column(name="MANUFACTURE")
	private String manufacture;
	@Column(name="SDK")
	private String sdk;
	@Column(name="OS_TYPE")
	private String osType;
	@Column(name="OS_VERSION")
	private String osVersion;
	@Column(name="RESOLUTION")
	private String resolution;
	@Column(name="INSTRUCTION_SET")
	private String instructionSet;
	@Column(name="CPU_ARCHITECTURE")
	private String cpuArchitecture;
	@Column(name="DEL_FLAG")
	private int delFlag;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="CREATED_TIME")
	private Timestamp createdTime;
	@Column(name="UPDATED_TIME")
	private Timestamp updatedTime;
	
	public int getAppUsageId() {
		return appUsageId;
	}
	public void setAppUsageId(int appUsageId) {
		this.appUsageId = appUsageId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Timestamp getRegisterationDateTime() {
		return registerationDateTime;
	}
	public void setRegisterationDateTime(Timestamp registerationDateTime) {
		this.registerationDateTime = registerationDateTime;
	}
	public String getPhoneModel() {
		return phoneModel;
	}
	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getSdk() {
		return sdk;
	}
	public void setSdk(String sdk) {
		this.sdk = sdk;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getInstructionSet() {
		return instructionSet;
	}
	public void setInstructionSet(String instructionSet) {
		this.instructionSet = instructionSet;
	}
	public String getCpuArchitecture() {
		return cpuArchitecture;
	}
	public void setCpuArchitecture(String cpuArchitecture) {
		this.cpuArchitecture = cpuArchitecture;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
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
	
}
