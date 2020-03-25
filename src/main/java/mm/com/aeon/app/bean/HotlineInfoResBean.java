package mm.com.aeon.app.bean;

import java.io.Serializable;

public class HotlineInfoResBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	private String statusMessage;
	private String hotLinePhone;
	
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
	public String getHotLinePhone() {
		return hotLinePhone;
	}
	public void setHotLinePhone(String hotLinePhone) {
		this.hotLinePhone = hotLinePhone;
	}
}