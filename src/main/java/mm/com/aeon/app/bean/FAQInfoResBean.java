package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

public class FAQInfoResBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	private String statusMessage;
	private List<FAQInfo> faqInfoList;
	
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
	public List<FAQInfo> getFaqInfoList() {
		return faqInfoList;
	}
	public void setFaqInfoList(List<FAQInfo> faqInfoList) {
		this.faqInfoList = faqInfoList;
	}
}
