/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/24 9:45:05
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.common;

public class CommonURL {

	// Project name
	public static final String PROJECT_MAIN_URL = "/assm";
	// Login URL
	public static final String LOGIN_CONTROLLER_URL = PROJECT_MAIN_URL + "/login";
	public static final String DO_LOGIN_URL = "/do_login";
	public static final String DO_LOGOUT_URL = "/do_logout";

	// Register URL
	public static final String REGISTER_CONTROLLER_URL = PROJECT_MAIN_URL + "/registration";
	public static final String GET_SEC_QUESTTION_URL = "/get_security_questions";
	public static final String CHECK_MEMBER_URL = "/check_member";
	public static final String REGISTER_NEW_URL = "/register_new";
	public static final String REIGSTER_EXISTED_URL = "/register_existed";
	public static final String CHECK_VERIFY_NEW_MEMBER_URL = "/check_verify_new_member";
	public static final String VERIFY_ANSWER_URL = "/verify_answer";
	public static final String VERIFY_NEW_MEMBER_URL = "/verify_new_member";
	public static final String OTP_REQUEST_URL = "/otp_request";

	// Reset Password URL
	public static final String RESET_PASSWORD_CONTROLLER_URL = PROJECT_MAIN_URL + "/resetpassword";
	public static final String CONFIRM_INFO_URL = "/confirm_info";
	public static final String PASSWORD_CHANGE_URL = "/password_change";

	// Update Information URL
	public static final String UPDATE_USER_INFO_CONTROLLER_URL = PROJECT_MAIN_URL + "/updateuserinfo";
	public static final String UPDATE_SECURITY_QAS_URL = "/update_security_qas";

	// Version Update URL
	public static final String VERSION_CONFIG_CONTROLLER_URL = PROJECT_MAIN_URL + "/versionconfig";
	public static final String UPDATE_STATUS_URL = "/update_status";
	
	// Information URL
	public static final String EVENTS_AND_NEWS_URL = PROJECT_MAIN_URL + "/eventsandnews";
	public static final String SHOW_COUPON_INFO_URL = "/show_coupon_info";
	public static final String USE_COUPON_INFO_URL = "/use_coupon_info";
	
	// OTP URL
	public static final String MAIN_URL = "http://172.16.112.55/aeonsmsgatewayapi/sendsmsto?";
	public static final String PHONE_URL = "phone=";
	public static final String PHONE_95 = "95";
	public static final String USER_NAME_URL = "&username=aeon";
	public static final String PASSWORD_URL = "&password=ae1940on122";
	public static final String UNICODE_URL = "&unicode=true";
	public static final String MESSAGE_URL = "&message=";
	public static final String PROJECT_ID_URL = "&prjId=VCS";

	// App Store URL
	public static final String APP_STORE_URL = "http://itunes.apple.com/lookup?bundleId=mm.com.aeonmicrofinance.vcsaeon.AEONVCS,https://itunes.apple.com/us/app/aeon-myanmar-app/id1462606788?mt=8";
    
}
