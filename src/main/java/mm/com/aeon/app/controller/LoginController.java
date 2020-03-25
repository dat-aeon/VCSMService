/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:08:29
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import mm.com.aeon.app.bean.CurrentUserInformationResBean;
import mm.com.aeon.app.bean.LoginInfoReqBean;
import mm.com.aeon.app.bean.LogoutInfoReqBean;
import mm.com.aeon.app.bean.LogoutInfoResBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.common.CommonURL;
import mm.com.aeon.app.service.LoginControlService;

@RestController
@RequestMapping(value = CommonURL.LOGIN_CONTROLLER_URL)
public class LoginController {

	@Autowired
	LoginControlService loginControlService;

	static Logger log = Logger.getLogger(LoginController.class);

	@PostMapping(value = CommonURL.DO_LOGIN_URL)
	public CurrentUserInformationResBean doLogin(@RequestBody(required = false) String loginInfo) {
		CurrentUserInformationResBean currUserInfoResBean = new CurrentUserInformationResBean();

		try {
			// get requested login info.
			LoginInfoReqBean loginInfoReqBean = new LoginInfoReqBean();
			loginInfoReqBean = new Gson().fromJson(loginInfo, LoginInfoReqBean.class);
			// check info and get current user.
			currUserInfoResBean = loginControlService.login(loginInfoReqBean).get();
			return currUserInfoResBean;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
			currUserInfoResBean.setStatusCode("500");
			currUserInfoResBean.setStatusMessage("Server has error.");
			return currUserInfoResBean;
		}
	}

	@PostMapping(value = CommonURL.DO_LOGOUT_URL)
	public LogoutInfoResBean doLogout(@RequestBody(required = false) String logoutInfo) {
		LogoutInfoResBean logoutInfoResBean = new LogoutInfoResBean();

		try {
			// get requested login info.
			LogoutInfoReqBean logoutInfoReqBean = new LogoutInfoReqBean();
			logoutInfoReqBean = new Gson().fromJson(logoutInfo, LogoutInfoReqBean.class);

			// check info and get current user.
			logoutInfoResBean = loginControlService.logout(logoutInfoReqBean).get();
			return logoutInfoResBean;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logoutInfoResBean.setStatusCode(CommonConstant.STATUS_500);
			logoutInfoResBean.setStatusMessage(e.getMessage());
			log.error(e.getMessage());
			return logoutInfoResBean;
		}
	}
}
