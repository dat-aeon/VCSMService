/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 11:19:05
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mm.com.aeon.app.bean.FAQInfo;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.dao.CompanyInfoResDao;
import mm.com.aeon.app.dao.FAQCategoryResDao;
import mm.com.aeon.app.dao.FAQInfoResDao;
import mm.com.aeon.app.dao.NrcCodeInfoResDao;
import mm.com.aeon.app.dto.CompanyInfoResDto;
import mm.com.aeon.app.dto.FAQInfoResDto;
import mm.com.aeon.app.exception.InformationServiceException;
import mm.com.aeon.app.repository.CompanyInfoRepository;
import mm.com.aeon.app.repository.FAQCategoryRepository;
import mm.com.aeon.app.repository.FAQInfoRepository;
import mm.com.aeon.app.repository.NrcCodeInfoRepository;

@Service("INFO_SERVICE")
public class InformationService {
	
	@Autowired
	CompanyInfoRepository companyInfoRepository;
	@Autowired
	FAQInfoRepository faqInfoRepository;
	@Autowired
	NrcCodeInfoRepository nrcCodeInfoRepository;
	@Autowired
	FAQCategoryRepository faqCategoryRepository;
	
	static Logger log = Logger.getLogger(InformationService.class);

	//Get a Company Info.
	@Async
	public CompletableFuture<CompanyInfoResDto> getCompanyInfo() throws InformationServiceException {
		CompanyInfoResDao companyInfoResDao = new CompanyInfoResDao();
		CompanyInfoResDto companyInfoResDto;
		//get data from COMPANY_INFO table filtered by infoId.
		companyInfoResDao = companyInfoRepository.findAllByCompanyInfoId(CommonConstant.AEON_COMPANY_ID);
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. about_us.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred.");
		}
		
		if(companyInfoResDao != null) {
			companyInfoResDto = new CompanyInfoResDto();
			companyInfoResDto.setCompanyInfoId(companyInfoResDao.getCompanyInfoId());
			companyInfoResDto.setAddressEn(companyInfoResDao.getAddressEn());
			companyInfoResDto.setAddressMm(companyInfoResDao.getAddressMm());
			companyInfoResDto.setHotLinePhone(companyInfoResDao.getHotLinePhone());
			companyInfoResDto.setWebAddress(companyInfoResDao.getWebAddress());
			companyInfoResDto.setSocialMediaAddress(companyInfoResDao.getSocialMediaAddress());
			companyInfoResDto.setAboutCompanyEn(companyInfoResDao.getAboutCompanyEn());
			companyInfoResDto.setAboutCompanyMm(companyInfoResDao.getAboutCompanyMm());
			return CompletableFuture.completedFuture(companyInfoResDto);
		} else {
			return null;
		}
	}
	
	
	//Get All FAQ Info.
	@Async
	public CompletableFuture<List<FAQInfo>> getFAQInfo() throws InformationServiceException {
		
		FAQInfo faqInfoResBean;
		List<FAQInfo> faqInfoResBeanList = new ArrayList<>();
		//get all faq_category.
		List<FAQCategoryResDao> faqCategoryResDaoList = faqCategoryRepository.findAllByDelFlag(CommonConstant.FLAG_ON);
		log.info("----------------- faqCategoryResDaoList : " + faqCategoryResDaoList.size());
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. faq_info.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred.");
		}
		
		
		if(faqCategoryResDaoList!=null && faqCategoryResDaoList.size()>0) {
			
			for (FAQCategoryResDao faqCategoryResDao : faqCategoryResDaoList) {
				
				int categoryId = faqCategoryResDao.getCategoryId();
				log.info("----------------- categoryId : " + categoryId);
				
				faqInfoResBean = new FAQInfo();
				faqInfoResBean.setFaqCategory(faqCategoryResDao.getCategory());
				
				List<FAQInfoResDao> faqInfoResDaoList = new ArrayList<>();
				List<FAQInfoResDto> faqInfoResDtoList;
				FAQInfoResDto faqInfoResDto;
				
				//Get all FAQ question list.
				faqInfoResDaoList = faqInfoRepository.findAllByCategoryIdAndDelFlag(categoryId,CommonConstant.FLAG_ON);
				log.info("----------------- faqInfoResDaoList : " + faqInfoResDaoList.size());
				
				faqInfoResDtoList = new ArrayList<>();
				for (FAQInfoResDao faqInfoResDao : faqInfoResDaoList) {
					log.info("----------------- faqInfoResDao.categoryId : " + faqInfoResDao.getCategoryId());
					faqInfoResDto = new FAQInfoResDto();
					faqInfoResDto.setFaqId(faqInfoResDao.getFaqId());
					faqInfoResDto.setQuestionMM(faqInfoResDao.getQuestionMM());
					faqInfoResDto.setQuestionEN(faqInfoResDao.getQuestionEN());
					faqInfoResDto.setAnswerMM(faqInfoResDao.getAnswerMM());
					faqInfoResDto.setAnswerEN(faqInfoResDao.getAnswerEN());
					faqInfoResDto.setCategoryId(faqInfoResDao.getCategoryId());
					faqInfoResDtoList.add(faqInfoResDto);
				}
				faqInfoResBean.setFaqInfoResInfoList(faqInfoResDtoList);
				faqInfoResBeanList.add(faqInfoResBean);
			}
			
		} else {
			return null;
		}
		
		return CompletableFuture.completedFuture(faqInfoResBeanList);
	}
	
	//Get NRC Info.
	@Async
	public CompletableFuture<List<NrcCodeInfoResDao>> getNrcInfo() throws InformationServiceException{
		
		List<NrcCodeInfoResDao> nrcCodeInfoResDaoList = nrcCodeInfoRepository.findAllNrc();
		
		try {
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. get_nrc.");
		} catch (Exception e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred.");
		}
		
		return CompletableFuture.completedFuture(nrcCodeInfoResDaoList);
	}
	
}

