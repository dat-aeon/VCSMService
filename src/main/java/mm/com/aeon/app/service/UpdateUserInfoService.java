/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/03 18:09:11
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.com.aeon.app.bean.SecurityQAUpdateInfo;
import mm.com.aeon.app.bean.UpdateUserQAInfoReqBean;
import mm.com.aeon.app.bean.UpdateUserQAInfoResBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.common.CommonUtils;
import mm.com.aeon.app.common.VCSMPasswordEncoder;
import mm.com.aeon.app.dao.CustSecQuestionReqDao;
import mm.com.aeon.app.dao.CustomerInfoReqDao;
import mm.com.aeon.app.dao.PasswordInfoReqDao;
import mm.com.aeon.app.dao.SecQAUpdateInfoResDao;
import mm.com.aeon.app.dto.SecQAUpdateInfoResDto;
import mm.com.aeon.app.exception.UpdateUserInfoException;
import mm.com.aeon.app.repository.CustSecQuestionRepository;
import mm.com.aeon.app.repository.CustomerInfoRepository;
import mm.com.aeon.app.repository.PasswordInfoRepository;
import mm.com.aeon.app.repository.SecQAUpdateInfoRepository;

@Service("UPDATE_USER_INFO_SERVICE")
public class UpdateUserInfoService {
	
	@Autowired
	SecQAUpdateInfoRepository secQAUpdateInfoRepository;
	@Autowired
	CustSecQuestionRepository custSecQuestionRepository;
	@Autowired
	PasswordInfoRepository passwordInfoRepository;
	@Autowired
	CustomerInfoRepository customerInfoRepository;
	
	static Logger log = Logger.getLogger(UpdateUserInfoService.class);

	//Get all Security Questions List.
	@Async
	public CompletableFuture<List<SecQAUpdateInfoResDto>> getRegistrationSecurityQuestion(int customerId) throws UpdateUserInfoException {
		
		List<SecQAUpdateInfoResDao> secQAUpdateInfoResDaoList = new ArrayList<>();
		List<SecQAUpdateInfoResDto> secQAUpdateInfoResDtoList = new ArrayList<>();
		
		SecQAUpdateInfoResDto secQAUpdateInfoResDto;
		secQAUpdateInfoResDaoList = secQAUpdateInfoRepository.findSecQAUpdateInfoList(customerId);
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. security_ans_upd_req.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. security_ans_upd_req.");
		}
	
		if(secQAUpdateInfoResDaoList!=null) {
			for (SecQAUpdateInfoResDao secQAUpdateInfoResDao : secQAUpdateInfoResDaoList) {
				secQAUpdateInfoResDto = new SecQAUpdateInfoResDto();
				secQAUpdateInfoResDto.setCustSecQuesId(secQAUpdateInfoResDao.getCustSecQuesId());
				secQAUpdateInfoResDto.setAnswer(secQAUpdateInfoResDao.getAnswer());
				secQAUpdateInfoResDto.setSecQuestionId(secQAUpdateInfoResDao.getSecQuestionId());
				secQAUpdateInfoResDto.setQuestionMM(secQAUpdateInfoResDao.getQuestionMM());
				secQAUpdateInfoResDto.setQuestionEN(secQAUpdateInfoResDao.getQuestionEN());
				secQAUpdateInfoResDtoList.add(secQAUpdateInfoResDto);
			}
			return CompletableFuture.completedFuture(secQAUpdateInfoResDtoList);
		} else {
			return null;
		}
	}
	
	//update QA
	@Async
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=UpdateUserInfoException.class)
	public CompletableFuture<UpdateUserQAInfoResBean> updateQAInfo(UpdateUserQAInfoReqBean updateUserQAInfoReqBean) 
		throws UpdateUserInfoException {
		
		UpdateUserQAInfoResBean updateUserQAInfoResBean =
				new UpdateUserQAInfoResBean();
		
		//update user_info
		List<SecurityQAUpdateInfo> securityQAUpdateInfoList = new ArrayList<>();
		securityQAUpdateInfoList = updateUserQAInfoReqBean.getSecurityQAUpdateInfo();
		int customerId = Integer.parseInt(updateUserQAInfoReqBean.getCustomerId());
		int userTypeId = CommonConstant.CU_USER_TYPE_ID;
		String password = VCSMPasswordEncoder.encode(updateUserQAInfoReqBean.getPassword());
		
		//Get Name for update.
		CustomerInfoReqDao customerInfoReqDao0 = customerInfoRepository.findAllByCustomerId(customerId);
		
		//check password.
		try {
			List<PasswordInfoReqDao> passwordInfoReqDaoList = 
			passwordInfoRepository.findAllByUserIdAndUserTypeIdAndPassword(customerId, userTypeId, password);
			if(passwordInfoReqDaoList.equals(null) || passwordInfoReqDaoList.size()==0) {
				updateUserQAInfoResBean.setUpdateStatus(CommonConstant.INCORRECT_PWD);
				return CompletableFuture.completedFuture(updateUserQAInfoResBean);
			}
			log.info("---------------- password checked. | passed.");
		} catch (Exception e) {
			// TODO: handle exception
			updateUserQAInfoResBean.setUpdateStatus(CommonConstant.INCORRECT_PWD);
			log.info("---------------- password checked. | failed.");
			return CompletableFuture.completedFuture(updateUserQAInfoResBean);
		}
		
		List<CustSecQuestionReqDao> custSecQuestionReqDaoList = 
				custSecQuestionRepository.findAllByCustomerId(customerId);
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. update_secqa_info.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. update_secqa_info");
		}
		
		if(custSecQuestionReqDaoList!=null && securityQAUpdateInfoList!=null) {
			
			int reqQASize = securityQAUpdateInfoList.size();
			int ansQASize = custSecQuestionReqDaoList.size();
			
			//update in answered count.
			if(reqQASize > ansQASize) {
				for(int i=0;i<ansQASize;i++) {
					
					SecurityQAUpdateInfo securityQAUpdateInfo = securityQAUpdateInfoList.get(i);
					int custSecQuesId = Integer.parseInt(securityQAUpdateInfo.getCustSecQuesId());
					int secQuesId = Integer.parseInt(securityQAUpdateInfo.getSecQuesId());
					String answer = securityQAUpdateInfo.getAnswer();
					
					CustSecQuestionReqDao custSecQuestionReqDao = 
							new CustSecQuestionReqDao();
					
					custSecQuestionReqDao = custSecQuestionRepository.findAllByCustSecQuesId(custSecQuesId);
					custSecQuestionReqDao.setSecQuesId(secQuesId);
					custSecQuestionReqDao.setAnswer(answer);
					custSecQuestionReqDao.setUpdatedBy(customerInfoReqDao0.getName());
					custSecQuestionReqDao.setUpdatedTime(CommonUtils.getCurrentTimeStamp());
					custSecQuestionReqDao = custSecQuestionRepository.save(custSecQuestionReqDao);
					updateUserQAInfoResBean.setUpdateStatus(CommonConstant.UPDATE_OK);
					log.info("---------------- updated secQuesId["+secQuesId+"] - answer["+answer+"].");
				}
				
			} else {
				
				for (SecurityQAUpdateInfo securityQAUpdateInfo : securityQAUpdateInfoList) {
				 
					int custSecQuesId = Integer.parseInt(securityQAUpdateInfo.getCustSecQuesId());
					int secQuesId = Integer.parseInt(securityQAUpdateInfo.getSecQuesId());
					String answer = securityQAUpdateInfo.getAnswer();
					
					CustSecQuestionReqDao custSecQuestionReqDao = 
							new CustSecQuestionReqDao();
					
					custSecQuestionReqDao = custSecQuestionRepository.findAllByCustSecQuesId(custSecQuesId);
					custSecQuestionReqDao.setSecQuesId(secQuesId);
					custSecQuestionReqDao.setAnswer(answer);
					custSecQuestionReqDao.setUpdatedBy(customerInfoReqDao0.getName());
					custSecQuestionReqDao.setUpdatedTime(CommonUtils.getCurrentTimeStamp());
					custSecQuestionReqDao = custSecQuestionRepository.save(custSecQuestionReqDao);
					updateUserQAInfoResBean.setUpdateStatus(CommonConstant.UPDATE_OK);
					log.info("---------------- updated secQuesId["+secQuesId+"] - answer["+answer+"].");
				}
			}
			
			updateUserQAInfoResBean.setUpdateStatus(CommonConstant.UPDATE_OK);
			return CompletableFuture.completedFuture(updateUserQAInfoResBean);
			
		} else {
			
			//update unavailable.
			updateUserQAInfoResBean.setUpdateStatus(CommonConstant.UPDATE_NOT_OK);
			return CompletableFuture.completedFuture(updateUserQAInfoResBean);
		}
	}
}


