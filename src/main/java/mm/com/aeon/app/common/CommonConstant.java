/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/24 9:45:05
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.common;

public class CommonConstant {

	public static final String DATE_FORMAT = "yyyy/MM/dd";
	public static final int ZERO = 0;
	
	//access key
	public static final String site_activation_key = "1234567";
	
	//information
	public static final int AEON_COMPANY_ID = 1; //let infoId=1.
	
	//Registration
	public static final String CUST_TYPE = "1";
	public static final String NON_CUST_TYPE = "2";
	
	public static final int CUST_TYPE_ID = 1;
	public static final int NON_CUST_TYPE_ID = 2;
	
	public static final String OP_USER_TYPE = "1";
	public static final String ADMIN_USER_TYPE = "2";
	public static final String CU_USER_TYPE = "3";
	
	public static final int OP_USER_TYPE_ID = 1;
	public static final int ADMIN_USER_TYPE_ID = 2;
	public static final int CU_USER_TYPE_ID = 3;
	
	public static final String CUSTOMER_INFO_STATUS = "0";
	
	public static final int MEMBER_TYPE_BROWN_ID = 1;
	
	public static final String BLANK="";
	
	//Photo upload
	public static final String FILE_UPLOAD_SUCCESS = "FILE_UPLOAD_SUCCESS";
	public static final String FILE_UPLOAD_FAILED = "FILE_UPLOAD_FAILED";
	
	//User Info. update
	public static final String UPDATE_OK = "UPDATE_OK";
	public static final String INCORRECT_PWD = "INCORRECT_PWD";
	public static final String CORRECT_PWD = "CORRECT_PWD";
	public static final String UPDATE_NOT_OK = "UPDATE_NOT_OK";
	
	//Flag
	public static final int FLAG_ON = 0;
	public static final int FLAG_OFF = 1;
	
	//cust agreement]
	public static final int QR_OFF=0;
	
	// OTP data
	public static final String MAIN_URL = " http://172.16.112.55/aeonsmsgatewayapi/sendsmsto?";

    public static final String PHONE_URL = "phone=";

    public static final String PHONE_95 = "95";

    public static final String USER_NAME_URL = "&username=aeon";

    public static final String PASSWORD_URL = "&password=ae1940on122";
    
    public static final String UNICODE_URL = "unicode=true";

    public static final String MESSAGE_URL = "&message=";
    
    public static final String PROJECT_ID_URL = "&prjId=VCS";

    public static final String OPEN_BRACKET = "[";

    public static final String CLOSE_BRACKET = "]";
    
    // Register
    public static final String MEMBER = "MEMBER";
    
    public static final String NON_MEMBER = "NON_MEMBER";
    
    public static final String DUPLICATE_DATA = "DUPLICATE_DATA";
    
    public static final String VALID_MEMBER = "VALID";
    
    public static final String STATUS_200 = "200";
    
    public static final String STATUS_500 = "500";
    
    // Duplication Status
    public static final String NRC_PH_DUPLICATE = "NRC_PH_DUPLICATE";
    
    public static final String PH_DUPLICATE = "PH_DUPLICATE";
    
    public static final String NRC_DUPLICATE = "NRC_DUPLICATE";
    
    public static final String NO_DUPLICATE = "NO_DUPLICATE";

    public static final String IMPORT_PH_DUPLICATE = "IMPORT_PH_DUPLICATE";
    
    public static final String ANSWER_WRONG_ERROR = "ANSWER_WRONG_ERROR";
    
    public static final String AGREEMENT_NO_WRONG_ERROR = "AGREEMENT_NO_WRONG_ERROR";

    public static final String MEMBER_INVALID_ERROR = "MEMBER_INVALID_ERROR";
    
    public static final String PH_OR_NRC_INVALID = "PH_OR_NRC_INVALID";
    
    public static final String PASSWORD_UNMATCH = "PASSWORD_UNMATCH";
    
    public static final String COUPON_REDEEM = "COUPON_REDEEM";
    
    public static final String OK = "OK";
    
    //Member Verification
    public static final String VALID = "VALID";
    public static final String NOT_VALID = "NOT_VALID";
    
    //Password status
    public static final String PWD_STRONG = "PWD_STRONG";
    public static final String PWD_WEAK = "PWD_WEAK";
    public static final String PWD_NORMAL = "PWD_NORMAL";
    
    
   //Coupon Status
    public static final String COUPON_USED_STATUS = "2";
    
    //async-info.
    public static final long ASYNC_SLEEP_TIME = 1000L;//1min.
    
    //version config.
    public static final String MUST_UPDATE="MUST_UPDATE";
    public static final String SHOULD_UPDATE="SHOULD_UPDATE";
    public static final String ERROR = "ERROR";
    public static final String APP_STORE_URL = "https://itunes.apple.com/us/app/msec-mobile-trading/id1273961122?ls=1&mt=8";
    
    
}
