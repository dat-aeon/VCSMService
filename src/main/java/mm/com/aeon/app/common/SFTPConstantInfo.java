/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/02 17:40:06
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.common;

public class SFTPConstantInfo {
	
	public static final String CHANNEL_TYPE_SFTP = "sftp";
	public static final String CHANNEL_TYPE_EXEC = "exec";
	public static final int SFTP_SERVER_PORT = 22;
	
	public static final String SFTP_SERVER_ADDRESS = "172.16.112.57";
	//public static final String SFTP_SERVER_ADDRESS = "10.1.9.69";
	public static final String SFTP_USERNAME = "root";
	public static final String SFTP_PASSWORD = "123@dat";
	public static final String BASE_PATH = "mnt";
	
	public static final String[] couponDestinationPath = {BASE_PATH,"jboss-eap-6.4","standalone","deployments","VCS.war","PhotoImage","CouponImage"};
	public static final String[] destinationPath = {BASE_PATH,"jboss-eap-6.4","standalone","deployments","VCS.war","PhotoImage","ProfileImage"};
	
	public static final String PHOTO_PATH = "https://ass.aeoncredit.com.mm/VCS/PhotoImage/ProfileImage/";
	public static final String COUPON_PHOTO_PATH = "https://ass.aeoncredit.com.mm/VCS/PhotoImage/CouponImage/";
	public static final String PROMO_PHOTO_PATH = "https://ass.aeoncredit.com.mm/VCS/PhotoImage/PromoImage/";
	//public static final String PHOTO_PATH = "https://ass.aeoncredit.com.mm/VCS/PhotoImage/ProfileImage/";
	//public static final String COUPON_PHOTO_PATH = "https://ass.aeoncredit.com.mm/VCS/PhotoImage/CouponImage/";
	public static final String BACKUP_PATH = "backup";
	public static final String BASE_ROOT_PATH = "/"+BASE_PATH+"/photo/";
	public static final String BASE_BACKUP_PATH = "/"+BASE_PATH+"/backup/";
	public static final String ROOT_DIR = "/";
	public static final String ROOT = "./";
	
	public static final String CMD_SEPARATOR = "&&";
	
	public static final String ACCESS_MODE_ALL = "777";
	public static final String ACCESS_MODE_READONLY = "444";
}

