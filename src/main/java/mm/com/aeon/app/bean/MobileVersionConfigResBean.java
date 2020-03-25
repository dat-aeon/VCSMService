package mm.com.aeon.app.bean;

import java.io.Serializable;

public class MobileVersionConfigResBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String forceUpdFlag;
	private String statusCode;
	private String statusMessage;
	
	public String getForceUpdFlag() {
		return forceUpdFlag;
	}
	public void setForceUpdFlag(String forceUpdFlag) {
		this.forceUpdFlag = forceUpdFlag;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
