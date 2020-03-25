/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/03 14:50:49
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

import mm.com.aeon.app.bean.ResetPasswordConfirmedInfoReqBean;
import mm.com.aeon.app.bean.ResetPasswordReqBean;
import mm.com.aeon.app.bean.ResetPwdAnsweredSecQuesReqBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.common.CommonUtils;
import mm.com.aeon.app.common.VCSMPasswordEncoder;
import mm.com.aeon.app.dao.CustSecQuestionReqDao;
import mm.com.aeon.app.dao.CustomerInfoReqDao;
import mm.com.aeon.app.dao.PasswordInfoReqDao;
import mm.com.aeon.app.dao.SecurityQuestionResDao;
import mm.com.aeon.app.dto.SecurityQuestionResDto;
import mm.com.aeon.app.exception.ResetPasswordServiceException;
import mm.com.aeon.app.repository.CustSecQuestionRepository;
import mm.com.aeon.app.repository.CustomerInfoRepository;
import mm.com.aeon.app.repository.PasswordInfoRepository;
import mm.com.aeon.app.repository.SecurityQuestionRepository;

@Service("RESET_PASSWORD_SERVICE")
public class ResetPasswordService {

	@Autowired
	SecurityQuestionRepository securityQuestionRepository;
	@Autowired
	CustomerInfoRepository customerInfoRepository;
	@Autowired
	CustSecQuestionRepository custSecQuestionRepository;
	@Autowired
	PasswordInfoRepository passwordInfoRepository;
	
	static Logger log = Logger.getLogger(ResetPasswordService.class);
	
	//Get all Security Questions List.
	@Async
	public CompletableFuture<List<SecurityQuestionResDto>> getRegistrationSecurityQuestion() throws ResetPasswordServiceException {
		List<SecurityQuestionResDto> securityQuestionResDtoList = new ArrayList<>();
		List<SecurityQuestionResDao> securityQuestionResDaoList = new ArrayList<>();
		SecurityQuestionResDto securityQuestionResDto;
		securityQuestionResDaoList = securityQuestionRepository.findAllByDelFlag(CommonConstant.FLAG_ON);
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. get_sq_for_reset_pwd.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. get_sq_for_reset_pwd");
		}
		
		if(securityQuestionResDaoList!=null) {
			for (SecurityQuestionResDao securityQuestionResDao : securityQuestionResDaoList) {
				securityQuestionResDto = new SecurityQuestionResDto();
				securityQuestionResDto.setSecQuestionId(securityQuestionResDao.getSecQuestionId());
				securityQuestionResDto.setQuestionMM(securityQuestionResDao.getQuestionMM());
				securityQuestionResDto.setQuestionEN(securityQuestionResDao.getQuestionEN());
				securityQuestionResDtoList.add(securityQuestionResDto);
			}
			return CompletableFuture.completedFuture(securityQuestionResDtoList);
		} else {
			return null;
		}
	}
	
	//Get customer Id.
	@Async
	public CompletableFuture<CustomerInfoReqDao> getCustomerInfo(ResetPasswordConfirmedInfoReqBean resetPwdConfInfoReqBean) throws ResetPasswordServiceException {
		String phoneNo = resetPwdConfInfoReqBean.getPhoneNo();
		String nrcNo = resetPwdConfInfoReqBean.getNrcNo();
		CustomerInfoReqDao customerInfoReqDao = customerInfoRepository.findAllByPhoneNoAndNrcNo(phoneNo, nrcNo);
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. get_customer_info.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. get_customer_info");
		}
		
		return CompletableFuture.completedFuture(customerInfoReqDao);
	}
	
	
	//Get answered security question list.
	@Async
	public CompletableFuture<List<CustSecQuestionReqDao>> getAnsweredSecQuesList(int customerId) 
		throws ResetPasswordServiceException {
		List<CustSecQuestionReqDao> custSecQuestionReqDaoList = new ArrayList<>();
		custSecQuestionReqDaoList = custSecQuestionRepository.findAllByCustomerId(customerId);
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. get_answered_secq.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. get_answered_secq.");
		}
		
		return CompletableFuture.completedFuture(custSecQuestionReqDaoList);
	}
	
	//Match answered security question.
	public boolean matchAnsweredSecQuestion(
		List<CustSecQuestionReqDao> custSecQuestionReqDaoList, List<ResetPwdAnsweredSecQuesReqBean> resetPwdAnsweredSecQuesList) 
		throws ResetPasswordServiceException {
		
		boolean resetAnsCorrect=false;
		log.info("------------ MATCH ANSWER | "+ resetPwdAnsweredSecQuesList.size()+"--------"+custSecQuestionReqDaoList.size() +"----------");
		
		if(custSecQuestionReqDaoList!=null && resetPwdAnsweredSecQuesList!=null) {
			
			int reqQASize = resetPwdAnsweredSecQuesList.size();
			int ansQASize = custSecQuestionReqDaoList.size();
			
			if(reqQASize > ansQASize) {
				
				for(int i=0;i<ansQASize;i++) {
					
					ResetPwdAnsweredSecQuesReqBean resetPwdAnsweredSecQuesReqBean = 
							resetPwdAnsweredSecQuesList.get(i);
					int secQuesId = resetPwdAnsweredSecQuesReqBean.getSecQuesId();
					String answer = resetPwdAnsweredSecQuesReqBean.getAnswer();
					boolean ansIdExisted=false;
					
					for (CustSecQuestionReqDao custSecQuestionReqDao : custSecQuestionReqDaoList) { //request_site
						int resetSecQuesId = custSecQuestionReqDao.getSecQuesId();
						String resetAns = custSecQuestionReqDao.getAnswer();
						
						if(secQuesId!=resetSecQuesId) {
							log.info("------------ NOT MATCH ID | " + secQuesId + resetSecQuesId);
							continue;
						} else {
							
							//match answer after id is matched.
							if(answer.equals(resetAns)) {
								resetAnsCorrect=true;
							}
							
							//id is existed.
							ansIdExisted=true;
							log.info("------------ CHECK ANSWER | " + answer + resetAns + resetAnsCorrect);
						}
						
						//for answer is false | reset 'resetAnsCorrect' value.
						if(!resetAnsCorrect) {
							return false;
						} else {
							resetAnsCorrect=false;
						}
						
					}
					
					//for no more id matched.
					if(!ansIdExisted) {
						return false;
					}
					log.info("------------ MATCH ANSWER 1|  ------------------" + resetAnsCorrect);
				}
				
				log.info("------------ MATCH ANSWER 2|  ------------------" + resetAnsCorrect);
				return true;
				
			} else {
				
				for (CustSecQuestionReqDao custSecQuestionReqDao : custSecQuestionReqDaoList) { //answered_site
					int secQuesId = custSecQuestionReqDao.getSecQuesId();
					String answer = custSecQuestionReqDao.getAnswer();
					boolean ansIdExisted=false;
					
					for (ResetPwdAnsweredSecQuesReqBean ResetPwdAnsweredSecQuesReqBean : resetPwdAnsweredSecQuesList) { //request_site
						int resetSecQuesId = ResetPwdAnsweredSecQuesReqBean.getSecQuesId();
						String resetAns = ResetPwdAnsweredSecQuesReqBean.getAnswer();
						
						if(secQuesId!=resetSecQuesId) {
							log.info("------------ NOT MATCH ID | " + secQuesId + resetSecQuesId);
							continue;
						} else {
							
							//match answer after id is matched.
							if(answer.equals(resetAns)) {
								resetAnsCorrect=true;
							}
							
							//id is existed.
							ansIdExisted=true;
							log.info("------------ CHECK ANSWER | " + answer + resetAns + resetAnsCorrect);
						}
						
						//for answer is false | reset 'resetAnsCorrect' value.
						if(!resetAnsCorrect) {
							return false;
						} else {
							resetAnsCorrect=false;
						}
					}
					
					//for no more id matched.
					if(!ansIdExisted) {
						return false;
					}
					log.info("------------ MATCH ANSWER 1|  ------------------" + resetAnsCorrect);
				}
				
				log.info("------------ MATCH ANSWER 2|  ------------------" + resetAnsCorrect);
				return true;
			}
			
		} else {
			return false;
		}
	}
	
	
	//change password.
	@Async
	public CompletableFuture<Integer> updatePassword(ResetPasswordReqBean resetPasswordReqBean) 
			throws ResetPasswordServiceException {
		
		int returnCustomerId=0;
		int userId = resetPasswordReqBean.getCustomerId();
		int userTypeId = resetPasswordReqBean.getUserTypeId();
		
		PasswordInfoReqDao passwordInfoReqDao = new PasswordInfoReqDao();
		passwordInfoReqDao = passwordInfoRepository.findAllByUserIdAndUserTypeId(userId, userTypeId);
		passwordInfoReqDao.setPassword(VCSMPasswordEncoder.encode(resetPasswordReqBean.getPassword()));
		//Get Name for setting updated by.
				CustomerInfoReqDao customerInfoReqDao0 = 
						customerInfoRepository.findAllByCustomerId(passwordInfoReqDao.getUserId());
		passwordInfoReqDao.setUpdatedBy(customerInfoReqDao0.getName());
		passwordInfoReqDao.setUpdatedTime(CommonUtils.getCurrentTimeStamp());
		passwordInfoReqDao = passwordInfoRepository.save(passwordInfoReqDao);
		returnCustomerId = passwordInfoReqDao.getUserId();
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. update_password.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. update_password");
		}
		
		return CompletableFuture.completedFuture(returnCustomerId);
	}
}
