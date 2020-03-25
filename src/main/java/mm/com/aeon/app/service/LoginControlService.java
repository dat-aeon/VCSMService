/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/05 18:15:21
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mm.com.aeon.app.bean.CurrentUserInformationResBean;
import mm.com.aeon.app.bean.LoginInfoReqBean;
import mm.com.aeon.app.bean.LogoutInfoReqBean;
import mm.com.aeon.app.bean.LogoutInfoResBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.common.CommonUtils;
import mm.com.aeon.app.common.SFTPConstantInfo;
import mm.com.aeon.app.common.VCSMPasswordEncoder;
import mm.com.aeon.app.dao.AppUsageDetailReqDao;
import mm.com.aeon.app.dao.AppUsageInfoReqDao;
import mm.com.aeon.app.dao.CompanyInfoResDao;
import mm.com.aeon.app.dao.CustAgreementListResDao;
import mm.com.aeon.app.dao.CustomerInfoReqDao;
import mm.com.aeon.app.dao.ImportCustomerInfoResDao;
import mm.com.aeon.app.dao.PasswordInfoReqDao;
import mm.com.aeon.app.dto.CustAgreementListDto;
import mm.com.aeon.app.repository.AppUsageDetailRepository;
import mm.com.aeon.app.repository.AppUsageInfoRepository;
import mm.com.aeon.app.repository.CompanyInfoRepository;
import mm.com.aeon.app.repository.CustAgreementListRepository;
import mm.com.aeon.app.repository.CustomerInfoRepository;
import mm.com.aeon.app.repository.ImportCustomerInfoRepository;
import mm.com.aeon.app.repository.PasswordInfoRepository;

@Service("LOGIN_CONTROL_SERVICE")
public class LoginControlService {

	@Autowired
	MemberRegistrationService memberRegistrationService;
	@Autowired
	CustomerInfoRepository customerInfoRepository;
	@Autowired
	PasswordInfoRepository passwordInfoRepository;
	@Autowired
	CustAgreementListRepository custAgreementListRepository;
	@Autowired
	ImportCustomerInfoRepository importCustomerInfoRepository;
	@Autowired
	AppUsageInfoRepository appUsageInfoRepository;
	@Autowired
	AppUsageDetailRepository appUsageDetailRepository;
	@Autowired
	CompanyInfoRepository companyInfoRepository;

	static Logger log = Logger.getLogger(LoginControlService.class);
	private List<PasswordInfoReqDao> passwordInfoReqDaoList;

	@Async
	public CompletableFuture<CurrentUserInformationResBean> login(LoginInfoReqBean loginInfoReqBean) throws LoginException {

		CurrentUserInformationResBean currentUserInformationResBean = new CurrentUserInformationResBean();

		// match user login info.
		String phoneNo = loginInfoReqBean.getPhoneNo();
		String password = VCSMPasswordEncoder.encode(loginInfoReqBean.getPassword());
		int userTypeId = 3; // CU_USER_TYPE

		// get customer_info.
		CustomerInfoReqDao customerInfoResDao = customerInfoRepository.findAllByPhoneNo(phoneNo);
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. do_login.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. do_login");
		}
		
		if(customerInfoResDao != null) {
			int userId = customerInfoResDao.getCustomerId();
	
			passwordInfoReqDaoList = passwordInfoRepository.findAllByUserIdAndUserTypeIdAndPassword(userId, userTypeId,
					password);
			
			log.info("password Info List ::::::::::::::::::::" + passwordInfoReqDaoList.size());
	
			if (passwordInfoReqDaoList != null && passwordInfoReqDaoList.size() > 0) {
	
				// get current user info.
				currentUserInformationResBean.setStatusCode("200");
				currentUserInformationResBean.setStatusMessage("Success");
				currentUserInformationResBean.setPassword(passwordInfoReqDaoList.get(0).getPassword());
				currentUserInformationResBean.setCustomerId(customerInfoResDao.getCustomerId());
				currentUserInformationResBean.setCustomerTypeId(customerInfoResDao.getCustomerTypeId());
				currentUserInformationResBean.setUserTypeId(customerInfoResDao.getUserTypeId());
				currentUserInformationResBean.setCustomerNo(customerInfoResDao.getCustomerNo());
				currentUserInformationResBean.setPhoneNo(customerInfoResDao.getPhoneNo());
				currentUserInformationResBean.setName(customerInfoResDao.getName());
				currentUserInformationResBean
						.setDateOfBirth(CommonUtils.getChangeDatetoString(customerInfoResDao.getDateOfBirth()));
				currentUserInformationResBean.setNrcNo(customerInfoResDao.getNrcNo());
				if(customerInfoResDao.getPhotoPath()==null) {
					currentUserInformationResBean.setPhotoPath(CommonConstant.BLANK);
				} else {
					currentUserInformationResBean.setPhotoPath(SFTPConstantInfo.PHOTO_PATH+customerInfoResDao.getPhotoPath());
				}
				currentUserInformationResBean.setDelFlag(customerInfoResDao.getDelFalg());
	
				if(currentUserInformationResBean.getCustomerNo() != null) {
					log.info("import customer info ::::::::::::::::::::" + currentUserInformationResBean.getCustomerNo());
					
					ImportCustomerInfoResDao  importCusInfo = importCustomerInfoRepository.findAllByCustomerNo(currentUserInformationResBean.getCustomerNo());
					
					if(importCusInfo != null) {
						
						 List<CustAgreementListResDao> custAgreementListResDaoList = 
								 //custAgreementListRepository.findAllByImportCustomerIdAndDelFlag(importCusInfo.getImportCustomerInfoId(),CommonConstant.FLAG_ON); 
								 custAgreementListRepository.findAllByQrShowNotAndImportCustomerId(CommonConstant.QR_OFF,importCusInfo.getImportCustomerInfoId());
						 List<CustAgreementListDto> custAgreementListDtolist = new ArrayList<CustAgreementListDto>(); 
						 
						 for (CustAgreementListResDao custAgreementListResDao :custAgreementListResDaoList) { 
							 
								CustAgreementListDto custAgreementListDto = new CustAgreementListDto();
								custAgreementListDto.setAgreementNo(custAgreementListResDao.getAgreementNo());
								custAgreementListDto.setQrShow(String.valueOf(custAgreementListResDao.getQrShow()));
								custAgreementListDto.setFinancialAmt(String.valueOf(custAgreementListResDao.getFinancialAmt()));
								custAgreementListDto.setFinancialTerm(String.valueOf(custAgreementListResDao.getFinancialTerm()));
								custAgreementListDto.setCustAgreementId(String.valueOf(custAgreementListResDao.getCustAgreementId()));
								custAgreementListDto.setImportCustomerId(String.valueOf(custAgreementListResDao.getImportCustomerId())); 
								custAgreementListDtolist.add(custAgreementListDto); 
						 }
						  currentUserInformationResBean.setCustAgreementListDtoList(custAgreementListDtolist);
					} 
				}else {
					log.info("------------ login success! | import_customer_info not exist.");
				}
	
				log.info("------------ login success! | customer_info_id : " + customerInfoResDao.getCustomerId());
				
				log.info("-----------login time insert---------");
				AppUsageInfoReqDao appUsageDao = appUsageInfoRepository.findAllByCustomerId(userId);
				// insert to APP_USAGE_DETAIL
				AppUsageDetailReqDao appUsageDetailReqDao = new AppUsageDetailReqDao();
				appUsageDetailReqDao.setAppUsageId(appUsageDao.getAppUsageId());
				appUsageDetailReqDao.setStartUsageDateTime(CommonUtils.getCurrentTimeStamp());
				appUsageDetailReqDao.setEndUsageDateTime(CommonUtils.getCurrentTimeStamp());
				appUsageDetailReqDao = appUsageDetailRepository.save(appUsageDetailReqDao);
				log.info("------------ APP_USAGE_DETAIL inserted. | app_usage_detail_id : " + appUsageDetailReqDao.getAppUsageDetailId());
				currentUserInformationResBean.setAppUsageDetailId(Integer.toString(appUsageDetailReqDao.getAppUsageDetailId()));
				
				log.info("--------------------get hotline number-----------");
				CompanyInfoResDao companyInfoResDao = new CompanyInfoResDao();
				//get data from COMPANY_INFO table filtered by infoId.
				companyInfoResDao = companyInfoRepository.findAllByCompanyInfoId(CommonConstant.AEON_COMPANY_ID);
				if(companyInfoResDao != null) {
					currentUserInformationResBean.setHotlinePhone(companyInfoResDao.getHotLinePhone());
				} else {
					throw new LoginException("Hotline Number is Null");
				}
				return CompletableFuture.completedFuture(currentUserInformationResBean);
	
			} else {
				currentUserInformationResBean.setStatusCode("500");
				currentUserInformationResBean.setStatusMessage("Password does not match.");
				
				return CompletableFuture.completedFuture(currentUserInformationResBean);
			}
		} else {
			currentUserInformationResBean.setStatusCode("500");
			currentUserInformationResBean.setStatusMessage("Phone No does not exist.");
			
			return CompletableFuture.completedFuture(currentUserInformationResBean);
		}
	}

	@Async
	public CompletableFuture<LogoutInfoResBean> logout(LogoutInfoReqBean logoutInfo)  throws LoginException {
		
		LogoutInfoResBean logoutInfoResBean = new LogoutInfoResBean();
		
		// update to APP_USAGE_DETAIL
		AppUsageDetailReqDao appUsageDetailReqDao = new AppUsageDetailReqDao();
		appUsageDetailReqDao = appUsageDetailRepository.findAllByAppUsageDetailId(Integer.parseInt(logoutInfo.getAppUsageDetailId()));
		
		if(appUsageDetailReqDao != null) {
			appUsageDetailReqDao.setEndUsageDateTime(CommonUtils.changeStringToTimeStamp(logoutInfo.getLogoutTime()));
			appUsageDetailReqDao = appUsageDetailRepository.save(appUsageDetailReqDao);
			log.info("------------ APP_USAGE_DETAIL updated. | app_usage_detail_id : " + appUsageDetailReqDao.getAppUsageDetailId());
			
			logoutInfoResBean.setStatusCode(CommonConstant.STATUS_200);
			logoutInfoResBean.setStatusMessage(CommonConstant.OK);
			
		} else {
			log.info("------------ APP_USAGE_DETAIL update failed.");
			logoutInfoResBean.setStatusCode(CommonConstant.STATUS_500);
			logoutInfoResBean.setStatusMessage(CommonConstant.UPDATE_NOT_OK);
			
		}
		
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. do_logout.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. do_logout");
		}
		return CompletableFuture.completedFuture(logoutInfoResBean);
	}
}
