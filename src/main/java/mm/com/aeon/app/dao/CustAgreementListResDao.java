/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:43:58
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
@Table(name="CUST_AGREEMENT_LIST")
public class CustAgreementListResDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CUST_AGREEMENT_ID")
	private int custAgreementId;
	@Column(name="IMPORT_CUSTOMER_ID")
	private int importCustomerId;
	@Column(name="AGREEMENT_NO")
	private String agreementNo;
	@Column(name="DEL_FLAG")
	private int delFlag;
	@Column(name="QR_SHOW")
	private int qrShow;
	@Column(name="FINANCIAL_STATUS")
	private String financialStatus;
	@Column(name="FINANCIAL_AMT")
	private int financialAmt;
	@Column(name="FINANCIAL_TERM")
	private int financialTerm;
	@Column(name="CREATED_TIME")
	private Timestamp createdTime;
	@Column(name="UPDATED_TIME")
	private Timestamp updatedTime;
	
	public int getCustAgreementId() {
		return custAgreementId;
	}
	public void setCustAgreementId(int custAgreementId) {
		this.custAgreementId = custAgreementId;
	}
	public int getImportCustomerId() {
		return importCustomerId;
	}
	public void setImportCustomerId(int importCustomerId) {
		this.importCustomerId = importCustomerId;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
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
	public int getQrShow() {
		return qrShow;
	}
	public void setQrShow(int qrShow) {
		this.qrShow = qrShow;
	}
	public String getFinancialStatus() {
		return financialStatus;
	}
	public void setFinancialStatus(String financialStatus) {
		this.financialStatus = financialStatus;
	}
	public int getFinancialAmt() {
		return financialAmt;
	}
	public void setFinancialAmt(int financialAmt) {
		this.financialAmt = financialAmt;
	}
	public int getFinancialTerm() {
		return financialTerm;
	}
	public void setFinancialTerm(int financialTerm) {
		this.financialTerm = financialTerm;
	}
}
