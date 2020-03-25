/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 8:57:39
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import mm.com.aeon.app.bean.FAQInfo;
import mm.com.aeon.app.bean.FAQInfoResBean;
import mm.com.aeon.app.bean.HotlineInfoResBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.dao.NrcCodeInfoResDao;
import mm.com.aeon.app.dto.CompanyInfoResDto;
import mm.com.aeon.app.service.InformationService;

@RestController
@RequestMapping(value="/vcsm/information")
public class InformationController {

	static Logger log = Logger.getLogger(InformationController.class);
	
	@Autowired
	InformationService informationService;
	
	@PostMapping(value="/about_us")
	public CompanyInfoResDto getCompanyInfo(
			@RequestBody(required = false) String siteActivationKey) {
		CompanyInfoResDto companyInfoResDto = new CompanyInfoResDto();
		try {
			companyInfoResDto = informationService.getCompanyInfo().get();
			companyInfoResDto.setStatusCode("200");
			companyInfoResDto.setStatusMessage("success");
			return companyInfoResDto;
		} catch (Exception e) {
			// TODO: handle exception
			companyInfoResDto.setStatusCode("500");
			companyInfoResDto.setStatusMessage(e.getMessage());
			return companyInfoResDto;
		}
	}
	
	@PostMapping(value="/faq_info")
	public FAQInfoResBean getFAQInfo(
			@RequestBody(required = false) String siteActivationKey) {
		FAQInfoResBean faqInfoResBean = new FAQInfoResBean();
		try {
			List<FAQInfo> faqInfoList = informationService.getFAQInfo().get();
			faqInfoResBean.setFaqInfoList(faqInfoList);
			faqInfoResBean.setStatusCode("200");
			faqInfoResBean.setStatusMessage("success");
			return faqInfoResBean;
		} catch (Exception e) {
			// TODO: handle exception
			faqInfoResBean.setStatusCode("500");
			faqInfoResBean.setStatusMessage(e.getMessage());
			return faqInfoResBean;
		}
	}
	
	@PostMapping(value="/nrc_info")
	public String getNrcInfo(
			@RequestBody(required = false) String siteActivationKey) {
		try {
				String nrcInfo = null;
				List<NrcCodeInfoResDao> resData = informationService.getNrcInfo().get();
//				Map<Integer, String> dataBean = new HashMap<>();
//				for (NrcCodeInfoResDao resDto : resData) {
//					log.info("resData :::::::::::::" + resDto.getTownshipCodeList());
//					if (resDto != null && resDto.getTownshipCodeList().trim().length() > 5) {
//						String nrcList = resDto.getTownshipCodeList().substring(1,
//								resDto.getTownshipCodeList().length() - 1);
//						log.info("nrcList :::::::::::::" + nrcList);
//						nrcList = nrcList.replaceAll("\"", CommonConstant.BLANK);
//						dataBean.put(resDto.getStateId(), new Gson().toJson(nrcList, String.class));
//					}
//				}
				
				List<String> dataBean = new ArrayList<String>();
				log.info("resData :::::::::::::" + resData.size());
				for (NrcCodeInfoResDao resDto : resData) {
					if (resDto != null && resDto.getTownshipCodeList().trim().length() > 5) {
						String nrcList = resDto.getTownshipCodeList().substring(1,
								resDto.getTownshipCodeList().length() - 1);
						nrcList = nrcList.replaceAll("\"", CommonConstant.BLANK);
						dataBean.add(new Gson().toJson(nrcList, String.class));
					}
				
				}
				nrcInfo = new Gson().toJson(dataBean);
				log.info("nrcInfo size:::::::::::::" + dataBean.size());
				return nrcInfo;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping(value="/hotline")
	public HotlineInfoResBean getHotlineNo(
			@RequestBody(required = false) String siteActivationKey) {
		HotlineInfoResBean hotlineInfoResBean = new HotlineInfoResBean();
		try {
			CompanyInfoResDto companyInfoResDto = informationService.getCompanyInfo().get();
			hotlineInfoResBean.setHotLinePhone(companyInfoResDto.getHotLinePhone());
			hotlineInfoResBean.setStatusCode("200");
			hotlineInfoResBean.setStatusMessage("success");
			return hotlineInfoResBean;
		} catch (Exception e) {
			// TODO: handle exception
			hotlineInfoResBean.setStatusCode("500");
			hotlineInfoResBean.setStatusMessage(e.getMessage());
			return hotlineInfoResBean;
		}
	}
}
