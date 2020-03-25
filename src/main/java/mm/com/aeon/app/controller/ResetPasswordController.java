/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:10:40
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

import mm.com.aeon.app.bean.ResetPasswordConfirmedInfoReqBean;
import mm.com.aeon.app.bean.ResetPasswordConfirmedInfoResBean;
import mm.com.aeon.app.bean.ResetPasswordReqBean;
import mm.com.aeon.app.bean.ResetPwdAnsweredSecQuesReqBean;
import mm.com.aeon.app.bean.ResetPwdSecQAResBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.common.CommonURL;
import mm.com.aeon.app.common.CommonUtils;
import mm.com.aeon.app.dao.AppConfigResDao;
import mm.com.aeon.app.dao.CustSecQuestionReqDao;
import mm.com.aeon.app.dao.CustomerInfoReqDao;
import mm.com.aeon.app.dto.SecurityQuestionResDto;
import mm.com.aeon.app.repository.AppConfigRepository;
import mm.com.aeon.app.repository.CustSecQuestionRepository;
import mm.com.aeon.app.service.ResetPasswordService;

@RestController
@RequestMapping(value = CommonURL.RESET_PASSWORD_CONTROLLER_URL)
public class ResetPasswordController {

	@Autowired
	ResetPasswordService resetPasswordService;
	@Autowired
	CustSecQuestionRepository cstSecQuestionRepository;
	@Autowired
	AppConfigRepository appConfigRepository;

	ResetPasswordConfirmedInfoReqBean resetPwdConfInfoReqBean;
	ResetPasswordConfirmedInfoResBean resetPwdConfInfoResBean;
	List<CustSecQuestionReqDao> custSecQuestionReqDaoList;
	List<ResetPwdAnsweredSecQuesReqBean> resetPwdAnsweredSecQuesList;

	static Logger log = Logger.getLogger(ResetPasswordController.class);

	@PostMapping(value = CommonURL.GET_SEC_QUESTTION_URL)
	public ResetPwdSecQAResBean getSecurityQuestionForRegistration(
			@RequestBody(required = false) String siteActivationKey) {

		log.info("------------ /get_security_questions");
		log.info("------------ JSON | " + siteActivationKey);
		ResetPwdSecQAResBean resetPwdSecQAResBean = new ResetPwdSecQAResBean();
		try {

			List<SecurityQuestionResDto> securityQuestionResDtoList = new ArrayList<>();
			List<AppConfigResDao> appConfigResDaoList = appConfigRepository.findAll();

			securityQuestionResDtoList = resetPasswordService.getRegistrationSecurityQuestion().get();
			if (securityQuestionResDtoList != null && securityQuestionResDtoList.size() > 0) {

				// Get AppConfig settings info.
				AppConfigResDao appConfigResDao = new AppConfigResDao();
				appConfigResDao = appConfigResDaoList.get(0);
				resetPwdSecQAResBean.setNumOfSecQues(appConfigResDao.getNumOfSecQuestion());
				resetPwdSecQAResBean.setNumOfAnsChar(appConfigResDao.getNumOfAnsChar());

				resetPwdSecQAResBean.setStatusCode("200");
				resetPwdSecQAResBean.setStatusMessage("success");
				resetPwdSecQAResBean.setSecurityQuestionResDtoList(securityQuestionResDtoList);
				return resetPwdSecQAResBean;
			} else {
				resetPwdSecQAResBean.setStatusCode("500");
				resetPwdSecQAResBean.setStatusMessage("no security question");
				return resetPwdSecQAResBean;
			}
		} catch (Exception e) {
			// TODO: handle exception
			resetPwdSecQAResBean.setStatusCode("500");
			resetPwdSecQAResBean.setStatusMessage(e.getMessage());
			return resetPwdSecQAResBean;
		}
	}

	@PostMapping(value = CommonURL.CONFIRM_INFO_URL)
	public ResetPasswordConfirmedInfoResBean confirmInfo(@RequestBody(required = false) String pwdResetInfo) {

		log.info("------------ /confirm_info");
		log.info("------------ JSON | " + pwdResetInfo);

		resetPwdConfInfoReqBean = new ResetPasswordConfirmedInfoReqBean();
		resetPwdConfInfoReqBean = new Gson().fromJson(pwdResetInfo, ResetPasswordConfirmedInfoReqBean.class);

		log.info("------------ object | " + resetPwdConfInfoReqBean.getNrcNo() + resetPwdConfInfoReqBean.getPhoneNo());

		ResetPasswordConfirmedInfoResBean resetPasswordConfirmedInfoResBean = new ResetPasswordConfirmedInfoResBean();

		try {

			// get customerId by phoneNo and nrcNo.
			CustomerInfoReqDao customerInfoReqDao = resetPasswordService.getCustomerInfo(resetPwdConfInfoReqBean).get();

			if (customerInfoReqDao != null) {
				// get cust_sec_question by customerId.
				custSecQuestionReqDaoList = new ArrayList<>();
				custSecQuestionReqDaoList = resetPasswordService
						.getAnsweredSecQuesList(customerInfoReqDao.getCustomerId()).get();

				// match cust_sec_question with answered_ques.
				resetPwdAnsweredSecQuesList = new ArrayList<ResetPwdAnsweredSecQuesReqBean>();
				// resetPwdAnsweredSecQuesList =
				// resetPwdConfInfoReqBean.getResetPwdAnsweredSecQuesList();
				log.info("------------ object | " + resetPwdConfInfoReqBean.getResetPwdAnsweredSecQuesList());
				resetPwdAnsweredSecQuesList = resetPwdConfInfoReqBean.getResetPwdAnsweredSecQuesList();
				log.info("------------ object | " + resetPwdAnsweredSecQuesList.size());
				boolean answersCorrect = resetPasswordService.matchAnsweredSecQuestion(custSecQuestionReqDaoList,
						resetPwdAnsweredSecQuesList);

				if (answersCorrect) {

					resetPasswordConfirmedInfoResBean.setCustomerId(customerInfoReqDao.getCustomerId());
					resetPasswordConfirmedInfoResBean.setUserTypeId(customerInfoReqDao.getUserTypeId());
					resetPasswordConfirmedInfoResBean.setStatusCode(CommonConstant.STATUS_200);
					resetPasswordConfirmedInfoResBean.setStatusMessage(CommonConstant.OK);
					return resetPasswordConfirmedInfoResBean;

				} else {

					resetPasswordConfirmedInfoResBean.setStatusCode(CommonConstant.STATUS_200);
					resetPasswordConfirmedInfoResBean.setStatusMessage(CommonConstant.ANSWER_WRONG_ERROR);
					return resetPasswordConfirmedInfoResBean;
				}
			} else {
				resetPasswordConfirmedInfoResBean.setStatusCode(CommonConstant.STATUS_200);
				resetPasswordConfirmedInfoResBean.setStatusMessage(CommonConstant.PH_OR_NRC_INVALID);
				return resetPasswordConfirmedInfoResBean;
			}

		} catch (Exception e) {
			// TODO: handle exception
			resetPasswordConfirmedInfoResBean.setStatusCode(CommonConstant.STATUS_500);
			resetPasswordConfirmedInfoResBean.setStatusMessage(e.getMessage());
			return resetPasswordConfirmedInfoResBean;
		}
	}

	@PostMapping(value = CommonURL.PASSWORD_CHANGE_URL)
	public ResetPasswordConfirmedInfoResBean changePassword(@RequestBody(required = false) String pwdChangeInfo) {

		log.info("------------ /password_change");
		log.info("------------ JSON | " + pwdChangeInfo);

		try {

			// Response object.
			ResetPasswordConfirmedInfoResBean resetPwdConfInfoResBean = new ResetPasswordConfirmedInfoResBean();

			// Requested object.
			ResetPasswordReqBean resetPasswordReqBean = new ResetPasswordReqBean();
			resetPasswordReqBean = new Gson().fromJson(pwdChangeInfo, ResetPasswordReqBean.class);

			String passwordStrength = CommonUtils.checkPasswordStrength(resetPasswordReqBean.getPassword());
			if (passwordStrength.equals(CommonConstant.PWD_WEAK)) {
				resetPwdConfInfoResBean.setCustomerId(CommonConstant.ZERO);
				resetPwdConfInfoResBean.setStatusCode("200");
				resetPwdConfInfoResBean.setStatusMessage(CommonConstant.PWD_WEAK);
				return resetPwdConfInfoResBean;
			}

			int customerId = resetPasswordService.updatePassword(resetPasswordReqBean).get();
			resetPwdConfInfoResBean.setCustomerId(customerId);
			resetPwdConfInfoResBean.setStatusCode("200");
			resetPwdConfInfoResBean.setStatusMessage("success");
			return resetPwdConfInfoResBean;

		} catch (Exception e) {
			// TODO: handle exception
			resetPwdConfInfoResBean.setStatusCode("500");
			resetPwdConfInfoResBean.setStatusMessage(e.getMessage());
			return resetPwdConfInfoResBean;
		}
	}
}
