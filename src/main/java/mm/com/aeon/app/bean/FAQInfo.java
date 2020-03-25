/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/05 14:08:07
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

import mm.com.aeon.app.dto.FAQInfoResDto;

public class FAQInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String faqCategory;
	private List<FAQInfoResDto> faqInfoResInfoList;
	
	public String getFaqCategory() {
		return faqCategory;
	}
	public void setFaqCategory(String faqCategory) {
		this.faqCategory = faqCategory;
	}
	public List<FAQInfoResDto> getFaqInfoResInfoList() {
		return faqInfoResInfoList;
	}
	public void setFaqInfoResInfoList(List<FAQInfoResDto> faqInfoResInfoList) {
		this.faqInfoResInfoList = faqInfoResInfoList;
	}
	
}
