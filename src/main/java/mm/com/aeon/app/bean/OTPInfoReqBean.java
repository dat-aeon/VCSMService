package mm.com.aeon.app.bean;

import java.io.Serializable;

public class OTPInfoReqBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String phoneNo;
	
	private String siteActivationKey;
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getSiteActivationKey() {
		return siteActivationKey;
	}
	public void setSiteActivationKey(String siteActivationKey) {
		this.siteActivationKey = siteActivationKey;
	}
	
	
}