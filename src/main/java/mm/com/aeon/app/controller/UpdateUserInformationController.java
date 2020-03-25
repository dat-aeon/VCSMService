/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/03 18:02:03
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

import mm.com.aeon.app.bean.SecQAUpdateInfoResBean;
import mm.com.aeon.app.bean.UpdateUserInfoReqBean;
import mm.com.aeon.app.bean.UpdateUserQAInfoReqBean;
import mm.com.aeon.app.bean.UpdateUserQAInfoResBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.common.CommonURL;
import mm.com.aeon.app.dao.AppConfigResDao;
import mm.com.aeon.app.dto.SecQAUpdateInfoResDto;
import mm.com.aeon.app.repository.AppConfigRepository;
import mm.com.aeon.app.service.UpdateUserInfoService;

@RestController
@RequestMapping(value = CommonURL.UPDATE_USER_INFO_CONTROLLER_URL)
public class UpdateUserInformationController {

	@Autowired
	UpdateUserInfoService updateUserInfoService;

	@Autowired
	AppConfigRepository appConfigRepository;

	List<SecQAUpdateInfoResDto> SecQAUpdateInfoResDtoList;

	static Logger log = Logger.getLogger(UpdateUserInformationController.class);

	@PostMapping(value = CommonURL.GET_SEC_QUESTTION_URL)
	public SecQAUpdateInfoResBean getSecurityQuestionForRegistration(
			@RequestBody(required = false) String updateUserInfo) {

		log.info("------------ /get_security_questions");
		log.info("------------ JSON | " + updateUserInfo);

		SecQAUpdateInfoResBean secQAUpdateInfoResBean = new SecQAUpdateInfoResBean();

		try {

			UpdateUserInfoReqBean updateUserInfoReqBean = new UpdateUserInfoReqBean();
			updateUserInfoReqBean = new Gson().fromJson(updateUserInfo, UpdateUserInfoReqBean.class);
			int customerId = Integer.parseInt(updateUserInfoReqBean.getCustomerId());
			SecQAUpdateInfoResDtoList = new ArrayList<SecQAUpdateInfoResDto>();
			SecQAUpdateInfoResDtoList = updateUserInfoService.getRegistrationSecurityQuestion(customerId).get();
			if (SecQAUpdateInfoResDtoList != null && SecQAUpdateInfoResDtoList.size() > 0) {

				List<AppConfigResDao> appConfigResDaoList = appConfigRepository.findAll();
				// Get AppConfig settings info.
				AppConfigResDao appConfigResDao = new AppConfigResDao();
				appConfigResDao = appConfigResDaoList.get(0);

				secQAUpdateInfoResBean.setSecQAUpdateInfoResDtoList(SecQAUpdateInfoResDtoList);
				secQAUpdateInfoResBean.setNumOfSecQues(appConfigResDao.getNumOfSecQuestion());
				secQAUpdateInfoResBean.setNumOfAnsChar(appConfigResDao.getNumOfAnsChar());
				secQAUpdateInfoResBean.setStatusCode("200");
				secQAUpdateInfoResBean.setStatusMessage("success");
				return secQAUpdateInfoResBean;
			} else {
				secQAUpdateInfoResBean.setStatusCode("500");
				secQAUpdateInfoResBean.setStatusMessage("no answered security questions");
				return secQAUpdateInfoResBean;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			secQAUpdateInfoResBean.setStatusCode("500");
			secQAUpdateInfoResBean.setStatusMessage(e.getMessage());
			return secQAUpdateInfoResBean;
		}
	}

	//
	@PostMapping(value = CommonURL.UPDATE_SECURITY_QAS_URL)
	public UpdateUserQAInfoResBean updateUserInfo(@RequestBody(required = false) String updateUserQAInfo) {

		log.info("------------ /update_security_qas");
		log.info("------------ JSON | " + updateUserQAInfo);

		UpdateUserQAInfoResBean updateUserQAInfoResBean = new UpdateUserQAInfoResBean();

		try {

			// request data
			UpdateUserQAInfoReqBean updateUserQAInfoReqBean = new UpdateUserQAInfoReqBean();

			updateUserQAInfoReqBean = new Gson().fromJson(updateUserQAInfo, UpdateUserQAInfoReqBean.class);

			updateUserQAInfoResBean = updateUserInfoService.updateQAInfo(updateUserQAInfoReqBean).get();
			updateUserQAInfoResBean.setStatusCode(CommonConstant.STATUS_200);
			updateUserQAInfoResBean.setStatusMessage("success");
			return updateUserQAInfoResBean;

		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			updateUserQAInfoResBean.setStatusCode(CommonConstant.STATUS_500);
			updateUserQAInfoResBean.setStatusMessage(e.getMessage());
			return updateUserQAInfoResBean;
		}
	}
}
