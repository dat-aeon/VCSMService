/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:43:58
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dto;

import java.io.Serializable;

public class CustAgreementListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String custAgreementId;
	private String importCustomerId;
	private String agreementNo = "";
	private String qrShow;
	private String financialAmt;
	private String financialTerm;
	
	public String getCustAgreementId() {
		return custAgreementId;
	}
	public void setCustAgreementId(String custAgreementId) {
		this.custAgreementId = custAgreementId;
	}
	public String getImportCustomerId() {
		return importCustomerId;
	}
	public void setImportCustomerId(String importCustomerId) {
		this.importCustomerId = importCustomerId;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getQrShow() {
		return qrShow;
	}
	public void setQrShow(String qrShow) {
		this.qrShow = qrShow;
	}
	public String getFinancialAmt() {
		return financialAmt;
	}
	public void setFinancialAmt(String financialAmt) {
		this.financialAmt = financialAmt;
	}
	public String getFinancialTerm() {
		return financialTerm;
	}
	public void setFinancialTerm(String financialTerm) {
		this.financialTerm = financialTerm;
	}
}
