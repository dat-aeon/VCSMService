/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:07:32
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import mm.com.aeon.app.bean.CheckMemberInformationReqBean;
import mm.com.aeon.app.bean.CheckMemberInformationResBean;
import mm.com.aeon.app.bean.CurrentUserInformationResBean;
import mm.com.aeon.app.bean.CustomerInfoBean;
import mm.com.aeon.app.bean.ExistedMemberRegisterationInfoReqBean;
import mm.com.aeon.app.bean.JsonHeaderBean;
import mm.com.aeon.app.bean.MemberRegistrationInfoReqBean;
import mm.com.aeon.app.bean.NewMemberRegisterationInfoReqBean;
import mm.com.aeon.app.bean.OTPInfoReqBean;
import mm.com.aeon.app.bean.OTPInfoResBean;
import mm.com.aeon.app.bean.RegSecurityQuestionInfoResBean;
import mm.com.aeon.app.bean.ResetPwdAnsweredSecQuesReqBean;
import mm.com.aeon.app.bean.UpdateVerificationInfoReqBean;
import mm.com.aeon.app.bean.VerifyNewRegisteredUserInfoReqBean;
import mm.com.aeon.app.bean.VerifyNewRegisteredUserInfoResBean;
import mm.com.aeon.app.bean.VerifyQAInfoReqBean;
import mm.com.aeon.app.bean.VerifyQAInfoResBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.common.CommonURL;
import mm.com.aeon.app.common.CommonUtils;
import mm.com.aeon.app.dao.AppConfigResDao;
import mm.com.aeon.app.dao.CustSecQuestionReqDao;
import mm.com.aeon.app.dto.SecurityQuestionResDto;
import mm.com.aeon.app.repository.AppConfigRepository;
import mm.com.aeon.app.repository.CustomerTypeRepository;
import mm.com.aeon.app.repository.UserTypeRepository;
import mm.com.aeon.app.service.MemberRegistrationService;
import mm.com.aeon.app.service.ResetPasswordService;

@RestController
@RequestMapping(value = CommonURL.REGISTER_CONTROLLER_URL)
public class RegistrationController {

	@Autowired
	MemberRegistrationService memberRegistrationService;
	@Autowired
	CustomerTypeRepository customerTypeRepository;
	@Autowired
	UserTypeRepository userTypeRepository;
	@Autowired
	AppConfigRepository appConfigRepository;
	@Autowired
	ResetPasswordService resetPasswordService;

	static Logger log = Logger.getLogger(RegistrationController.class);

	@PostMapping(value = CommonURL.GET_SEC_QUESTTION_URL)
	public RegSecurityQuestionInfoResBean getSecurityQuestionForRegistration(
			@RequestBody(required = false) String siteActivationKey) {

		RegSecurityQuestionInfoResBean regSecQuesInfoResBean = new RegSecurityQuestionInfoResBean();

		try {

			// Get Security Questions Info.
			List<SecurityQuestionResDto> securityQuestionResDtoList = memberRegistrationService
					.getRegistrationSecurityQuestion().get();
			List<AppConfigResDao> appConfigResDaoList = appConfigRepository.findAll();

			log.info("------------ /get_security_questions");
			log.info("------------ JSON | " + siteActivationKey);

			// Get AppConfig settings info.
			AppConfigResDao appConfigResDao = new AppConfigResDao();
			appConfigResDao = appConfigResDaoList.get(0);

			// Set data to ResponseBean.
			regSecQuesInfoResBean.setNumOfSecQues(appConfigResDao.getNumOfSecQuestion());
			regSecQuesInfoResBean.setNumOfAnsChar(appConfigResDao.getNumOfAnsChar());
			regSecQuesInfoResBean.setSecurityQuestionResDtoList(securityQuestionResDtoList);

			regSecQuesInfoResBean.setStatusCode("200");
			regSecQuesInfoResBean.setStatusMessage("success");
			return regSecQuesInfoResBean;

		} catch (Exception e) {
			// TODO: handle exception
			log.info("get_security_questions error :" + e.getMessage());
			regSecQuesInfoResBean.setStatusCode("500");
			regSecQuesInfoResBean.setStatusMessage(e.getMessage());
			return regSecQuesInfoResBean;
		}
	}

	@PostMapping(value = CommonURL.CHECK_MEMBER_URL)
	public CustomerInfoBean checkAndRegisterMember(@RequestBody(required = false) String memRegisterInfo) {

		log.info("------------ /check_member");
		log.info("------------ JSON | " + memRegisterInfo);

		CustomerInfoBean customerInfoBean = new CustomerInfoBean();

		try {
			// Get data from registration form.
			CheckMemberInformationReqBean checkMemberInformationReqBean = new CheckMemberInformationReqBean();
			checkMemberInformationReqBean = new Gson().fromJson(memRegisterInfo, CheckMemberInformationReqBean.class);
			log.info("------------ JSON Converted.");

			// Check password strength.
			String passwordStrength = CommonUtils.checkPasswordStrength(checkMemberInformationReqBean.getPassword());
			if (passwordStrength.equals(CommonConstant.PWD_WEAK)) {
				customerInfoBean.setStatusCode(CommonConstant.STATUS_200);
				customerInfoBean.setHotlinePhone("");
				customerInfoBean.setMessage(CommonConstant.PWD_WEAK);
				customerInfoBean.setStatusMessage(CommonConstant.PWD_WEAK);
				customerInfoBean.setCheckMemInfoResBean(new CheckMemberInformationResBean());
				return customerInfoBean;
			}

			// Set current user information data.
			CheckMemberInformationResBean checkMemberInformationResBean = new CheckMemberInformationResBean();
			checkMemberInformationResBean = memberRegistrationService.checkMember(checkMemberInformationReqBean).get();
			// get Hotline Phone.
			String hotlinePhone = memberRegistrationService.getHotlinePhone();
			log.info("------------ Hotline Phone | " + hotlinePhone);

			if (checkMemberInformationResBean != null) {
				log.info("------------ Checked! | Register user already existed.");

				String check_status = memberRegistrationService.checkCustomerDuplicate(checkMemberInformationReqBean)
						.get();

				if (check_status.equals(CommonConstant.NO_DUPLICATE)) {
					
					log.info("------------ Check import phone no on customer_info");

					Boolean isPhoneDuplicate = memberRegistrationService
							.checkImportPhNoDuplicate(checkMemberInformationResBean.getPhoneNo()).get();

					if (isPhoneDuplicate) {
						log.info("------------ Check import phone no on customer_info | duplicate");

						customerInfoBean.setHotlinePhone(hotlinePhone);
						customerInfoBean.setMessage(CommonConstant.IMPORT_PH_DUPLICATE);
						customerInfoBean.setStatusCode(CommonConstant.STATUS_200);
						customerInfoBean.setStatusMessage(CommonConstant.DUPLICATE_DATA);
						customerInfoBean.setCheckMemInfoResBean(new CheckMemberInformationResBean());

					} else {
						log.info("------------ Check import phone no on customer_info | no duplicate");

						customerInfoBean.setHotlinePhone(hotlinePhone);
						customerInfoBean.setMessage(CommonConstant.MEMBER);
						customerInfoBean.setStatusCode(CommonConstant.STATUS_200);
						customerInfoBean.setStatusMessage("success");
						customerInfoBean.setCheckMemInfoResBean(checkMemberInformationResBean);
					}
				} else {
					log.info("------------ customer phone is duplicate on customer_info");

					customerInfoBean.setHotlinePhone(hotlinePhone);
					customerInfoBean.setMessage(check_status);
					customerInfoBean.setStatusCode(CommonConstant.STATUS_200);
					customerInfoBean.setStatusMessage(CommonConstant.DUPLICATE_DATA);
					customerInfoBean.setCheckMemInfoResBean(new CheckMemberInformationResBean());

				}

				return customerInfoBean;

			} else {

				log.info("------------ Checked! | Register user does not exist.");
				String check_status = memberRegistrationService.checkCustomerDuplicate(checkMemberInformationReqBean)
						.get();

				if (check_status.equals(CommonConstant.NO_DUPLICATE)) {
					customerInfoBean.setHotlinePhone(hotlinePhone);
					customerInfoBean.setMessage(CommonConstant.NON_MEMBER);
					customerInfoBean.setStatusCode(CommonConstant.STATUS_200);
					customerInfoBean.setStatusMessage("success");
					customerInfoBean.setCheckMemInfoResBean(new CheckMemberInformationResBean());
				} else {
					customerInfoBean.setHotlinePhone(hotlinePhone);
					customerInfoBean.setMessage(check_status);
					customerInfoBean.setStatusCode(CommonConstant.STATUS_200);
					customerInfoBean.setStatusMessage(CommonConstant.DUPLICATE_DATA);
					customerInfoBean.setCheckMemInfoResBean(new CheckMemberInformationResBean());
				}

				return customerInfoBean;
			}

		} catch (Exception e) {
			// TODO: handle exception
			log.info("------------ exception in checkAndRegisterMember() method.");
			e.printStackTrace();
			customerInfoBean.setStatusCode(CommonConstant.STATUS_500);
			customerInfoBean.setMessage(CommonConstant.NON_MEMBER);
			customerInfoBean.setMessage(e.getMessage());
			return customerInfoBean;
		}
	}

	@PostMapping(value = CommonURL.REGISTER_NEW_URL)
	public CurrentUserInformationResBean registerNewMember(@RequestBody(required = false) String memRegisterInfo) {

		log.info("------------ /register_new");
		log.info("------------ JSON | " + memRegisterInfo);

		NewMemberRegisterationInfoReqBean newMemberRegisterationInfoReqBean = new NewMemberRegisterationInfoReqBean();
		CurrentUserInformationResBean currentUserInformationResBean = new CurrentUserInformationResBean();
		
		try {
			// Get data from registration form.
			newMemberRegisterationInfoReqBean = new Gson().fromJson(memRegisterInfo,
					NewMemberRegisterationInfoReqBean.class);
			log.info("------------ JSON Converted.");

			MemberRegistrationInfoReqBean memberRegistrationInfoReqBean = new MemberRegistrationInfoReqBean();
			memberRegistrationInfoReqBean.setDateOfBirth(newMemberRegisterationInfoReqBean.getDateOfBirth());
			memberRegistrationInfoReqBean.setNrcNo(newMemberRegisterationInfoReqBean.getNrcNo());
			memberRegistrationInfoReqBean.setPhoneNo(newMemberRegisterationInfoReqBean.getPhoneNo());
			memberRegistrationInfoReqBean.setName(newMemberRegisterationInfoReqBean.getName());
			memberRegistrationInfoReqBean.setPassword(newMemberRegisterationInfoReqBean.getPassword());
			memberRegistrationInfoReqBean
					.setSecurityAnsweredInfoList(newMemberRegisterationInfoReqBean.getSecurityAnsweredInfoList());
			memberRegistrationInfoReqBean.setAppUsageInfo(newMemberRegisterationInfoReqBean.getAppUsageInfo());
			memberRegistrationInfoReqBean.setCustomerType(CommonConstant.NON_CUST_TYPE);
			memberRegistrationInfoReqBean.setCustomerTypeId(CommonConstant.NON_CUST_TYPE_ID); // NON_MEMBER
			memberRegistrationInfoReqBean.setUserTypeId(3); // CUSTOMER_TYPE
			memberRegistrationInfoReqBean.setCreatedBy(newMemberRegisterationInfoReqBean.getName());// customer type
																									// user.
			memberRegistrationInfoReqBean.setCreatedTime(CommonUtils.getCurrentTimeStamp());
			memberRegistrationInfoReqBean.setUpdatedTime(CommonUtils.getCurrentTimeStamp());
			log.info("------------ Data all binded to memberRegistrationInfoReqBean.");

			// save data
			currentUserInformationResBean = memberRegistrationService.registerNewUser(memberRegistrationInfoReqBean)
					.get();
			log.info("------------ Register New User done.");

			if (currentUserInformationResBean != null) {
				log.info("------------ Registered and return current user info.");
				currentUserInformationResBean.setStatusCode("200");
				currentUserInformationResBean.setStatusMessage("success");
				return currentUserInformationResBean;
			}

			// Else
			log.info("------------ return null.");
			currentUserInformationResBean = new CurrentUserInformationResBean();
			currentUserInformationResBean.setStatusCode("500");
			currentUserInformationResBean.setStatusMessage("no current user info");
			return currentUserInformationResBean;

		} catch (Exception e) {
			// TODO: handle exception
			currentUserInformationResBean.setStatusCode("500");
			currentUserInformationResBean.setStatusMessage(e.getMessage());
			return currentUserInformationResBean;
		}
	}

	@PostMapping(value = CommonURL.REIGSTER_EXISTED_URL)
	public CurrentUserInformationResBean registerOldMember(@RequestPart(name = "param_data") String memRegisterInfo,
			@RequestPart(name = "img") MultipartFile imgFile) {

		log.info("------------ /register_existed");
		log.info("------------ JSON | " + memRegisterInfo);
		log.info("------------ Img Name : " + imgFile.getOriginalFilename());
		log.info("------------ Img Size : " + imgFile.getSize());

		CurrentUserInformationResBean currUserInfoResBean = new CurrentUserInformationResBean();
		ExistedMemberRegisterationInfoReqBean existedMemberRegisterationInfoReqBean = new ExistedMemberRegisterationInfoReqBean();
		try {

			// put registration requested data.
			existedMemberRegisterationInfoReqBean = new Gson().fromJson(memRegisterInfo,
					ExistedMemberRegisterationInfoReqBean.class);
			log.info("------------ JSON Converted.");

			MemberRegistrationInfoReqBean memberRegistrationInfoReqBean = new MemberRegistrationInfoReqBean();

			memberRegistrationInfoReqBean.setName(existedMemberRegisterationInfoReqBean.getName());
			memberRegistrationInfoReqBean.setDateOfBirth(existedMemberRegisterationInfoReqBean.getDateOfBirth());
			memberRegistrationInfoReqBean.setNrcNo(existedMemberRegisterationInfoReqBean.getNrcNo());
			memberRegistrationInfoReqBean.setPhoneNo(existedMemberRegisterationInfoReqBean.getPhoneNo());
			memberRegistrationInfoReqBean.setPassword(existedMemberRegisterationInfoReqBean.getPassword());
			memberRegistrationInfoReqBean
					.setImportCustomerId(existedMemberRegisterationInfoReqBean.getImportCustomerId());
			memberRegistrationInfoReqBean.setCustomerNo(existedMemberRegisterationInfoReqBean.getCustomerNo());
			memberRegistrationInfoReqBean.setPhotoPath(existedMemberRegisterationInfoReqBean.getPhotoPath());
			memberRegistrationInfoReqBean
					.setSecurityAnsweredInfoList(existedMemberRegisterationInfoReqBean.getSecurityAnsweredInfoList());
			memberRegistrationInfoReqBean.setAppUsageInfo(existedMemberRegisterationInfoReqBean.getAppUsageInfo());
			memberRegistrationInfoReqBean.setCustomerType(CommonConstant.CUST_TYPE);
			memberRegistrationInfoReqBean.setCustomerTypeId(CommonConstant.CUST_TYPE_ID); // MEMBER
			memberRegistrationInfoReqBean.setUserTypeId(CommonConstant.CU_USER_TYPE_ID); // USER_TYPE_ID
			memberRegistrationInfoReqBean.setCreatedBy(existedMemberRegisterationInfoReqBean.getName());// customer type
																										// user.
			memberRegistrationInfoReqBean.setCreatedTime(CommonUtils.getCurrentTimeStamp());
			memberRegistrationInfoReqBean.setUpdatedTime(CommonUtils.getCurrentTimeStamp());

			log.info("------------ Data all binded to memberRegistrationInfoReqBean.");

			currUserInfoResBean = memberRegistrationService.registerExistedUser(memberRegistrationInfoReqBean, imgFile)
					.get();
			currUserInfoResBean.setStatusCode("200");
			currUserInfoResBean.setStatusMessage("success");

			return currUserInfoResBean;

		} catch (Exception e) {
			// TODO: handle exception
			currUserInfoResBean.setStatusCode("500");
			currUserInfoResBean.setStatusMessage(e.getMessage());
			return currUserInfoResBean;
		}
	}

	@PostMapping(value = CommonURL.CHECK_VERIFY_NEW_MEMBER_URL)
	public VerifyNewRegisteredUserInfoResBean checkVerifyNewMember(
			@RequestBody(required = false) String checkVerifyUserInfo) {

		log.info("------------ /check_verify_new_member");
		log.info("------------ JSON | " + checkVerifyUserInfo);

		VerifyNewRegisteredUserInfoResBean verifyNewRegUserInfoResBean = new VerifyNewRegisteredUserInfoResBean();

		try {

			VerifyNewRegisteredUserInfoReqBean verifyNewRegUserInfoReqBean = new VerifyNewRegisteredUserInfoReqBean();
			verifyNewRegUserInfoReqBean = new Gson().fromJson(checkVerifyUserInfo,
					VerifyNewRegisteredUserInfoReqBean.class);

			verifyNewRegUserInfoResBean = memberRegistrationService.checkToVerifyMember(verifyNewRegUserInfoReqBean)
					.get();

			return verifyNewRegUserInfoResBean;

		} catch (Exception e) {
			// TODO: handle exception
			verifyNewRegUserInfoResBean.setStatusCode(CommonConstant.STATUS_500);
			verifyNewRegUserInfoResBean.setStatusMessage(e.getMessage());
			verifyNewRegUserInfoResBean.setVerifyStatus(CommonConstant.NOT_VALID);
			verifyNewRegUserInfoResBean.setCustomerNo(CommonConstant.BLANK);
			return verifyNewRegUserInfoResBean;
		}
	}

	@PostMapping(value = CommonURL.VERIFY_ANSWER_URL)
	public VerifyQAInfoResBean confirmInfo(@RequestBody(required = false) String verifyAnswerInfo) {

		log.info("------------ /confirm_info");
		log.info("------------ JSON | " + verifyAnswerInfo);

		VerifyQAInfoReqBean verifyQAInfoReqBean = new VerifyQAInfoReqBean();
		verifyQAInfoReqBean = new Gson().fromJson(verifyAnswerInfo, VerifyQAInfoReqBean.class);

		log.info("------------ object | " + verifyQAInfoReqBean.getSecQuesList());

		VerifyQAInfoResBean verifyQAInfoResBean = new VerifyQAInfoResBean();

		try {

			// get customerId by phoneNo and nrcNo.
			List<CustSecQuestionReqDao> custSecQuestionReqDaoList = new ArrayList<CustSecQuestionReqDao>();
			custSecQuestionReqDaoList = resetPasswordService
					.getAnsweredSecQuesList(Integer.parseInt(verifyQAInfoReqBean.getCustomerId())).get();

			// match cust_sec_question with answered_ques.
			log.info("------------ object QA String| " + verifyQAInfoReqBean.getSecQuesList());
			List<ResetPwdAnsweredSecQuesReqBean> resetPwdAnsweredSecQuesList = new ArrayList<ResetPwdAnsweredSecQuesReqBean>();
			resetPwdAnsweredSecQuesList = verifyQAInfoReqBean.getSecQuesList();
			log.info("------------ object | " + resetPwdAnsweredSecQuesList.size());
			boolean answersCorrect = resetPasswordService.matchAnsweredSecQuestion(custSecQuestionReqDaoList,
					resetPwdAnsweredSecQuesList);

			if (answersCorrect) {

				verifyQAInfoResBean.setStatusCode(CommonConstant.STATUS_200);
				verifyQAInfoResBean.setStatusMessage(CommonConstant.OK);
				return verifyQAInfoResBean;

			} else {

				verifyQAInfoResBean.setStatusCode(CommonConstant.STATUS_200);
				verifyQAInfoResBean.setStatusMessage(CommonConstant.ANSWER_WRONG_ERROR);
				return verifyQAInfoResBean;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			verifyQAInfoResBean.setStatusCode(CommonConstant.STATUS_500);
			verifyQAInfoResBean.setStatusMessage(e.getMessage());
			return verifyQAInfoResBean;
		}
	}

	@PostMapping(value = CommonURL.VERIFY_NEW_MEMBER_URL)
	public CurrentUserInformationResBean registerNewMember(@RequestPart(name = "param_data") String verifyInfo,
			@RequestPart(name = "img") MultipartFile imgFile) {

		log.info("------------ /verify_new_member");
		log.info("------------ JSON | " + verifyInfo);
		log.info("------------ Img Name : " + imgFile.getOriginalFilename());
		log.info("------------ Img Size : " + imgFile.getSize());

		CurrentUserInformationResBean currUserInfoResBean = new CurrentUserInformationResBean();

		try {

			// put verify requested data.
			UpdateVerificationInfoReqBean updateVerifyInfoReqBean = new UpdateVerificationInfoReqBean();
			updateVerifyInfoReqBean = new Gson().fromJson(verifyInfo, UpdateVerificationInfoReqBean.class);

			currUserInfoResBean = memberRegistrationService.verifyMemberInfo(updateVerifyInfoReqBean, imgFile).get();
			currUserInfoResBean.setStatusCode("200");
			currUserInfoResBean.setStatusMessage("success");
			return currUserInfoResBean;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			currUserInfoResBean.setStatusCode("500");
			currUserInfoResBean.setStatusMessage(e.getMessage());
			return currUserInfoResBean;
		}
	}

	@PostMapping(value = CommonURL.OTP_REQUEST_URL)
	public OTPInfoResBean otpSend(@RequestBody(required = false) String otpInfo) {

		log.info("------------ /opt_request");
		log.info("------------ JSON | " + otpInfo);

		OTPInfoResBean otpInfoResBean = new OTPInfoResBean();

		try {

			// put verify requested data.
			OTPInfoReqBean otpInfoReqBean = new OTPInfoReqBean();
			otpInfoReqBean = new Gson().fromJson(otpInfo, OTPInfoReqBean.class);

			Random r = new Random();
			String randomNumber = String.format("%04d", (Object) Integer.valueOf(r.nextInt(10000)));
			String phoneNo = otpInfoReqBean.getPhoneNo().substring(1, otpInfoReqBean.getPhoneNo().length());
			log.info("OTP code :::::::::::" + randomNumber + ", phone No : " + phoneNo);

			// JsonHeaderBean jsonHeaderBean =
			// connectURL(otpInfoReqBean.getPhoneNo(),randomNumber);

			// if (jsonHeaderBean != null) {

			otpInfoResBean.setStatusCode("200");
			otpInfoResBean.setStatusMessage("success");
			otpInfoResBean.setOtpCode(randomNumber);
			// }
			return otpInfoResBean;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			otpInfoResBean.setStatusCode("500");
			otpInfoResBean.setStatusMessage(e.getMessage());
			otpInfoResBean.setOtpCode(CommonConstant.BLANK);
			return otpInfoResBean;
		}
	}

	// Async
	private JsonHeaderBean connectURL(String phoneNo, String otpCode) throws IOException, JSONException {

		String url = CommonURL.MAIN_URL + CommonURL.PHONE_URL + CommonURL.PHONE_95 + phoneNo
				+ CommonURL.USER_NAME_URL + CommonURL.PASSWORD_URL + CommonURL.UNICODE_URL
				+ CommonURL.MESSAGE_URL;

		String message = "AEON Member OTP Code : " + otpCode;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url + URLEncoder.encode(message, "UTF-8") + CommonConstant.PROJECT_ID_URL);

		// add request header
		HttpResponse response = client.execute(request);
		int status = response.getStatusLine().getStatusCode();
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";

		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();

		JsonHeaderBean jsonHeaderBean = null;

		if (status == 200) {

			jsonHeaderBean = new Gson().fromJson(result.toString(), JsonHeaderBean.class);

		}
		return jsonHeaderBean;
	}

}
