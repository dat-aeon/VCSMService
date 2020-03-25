package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

public class CouponInformationResBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	private String statusMessage;
	List<CouponInfoResBean> couponInfoResBeanList;
	
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
	public List<CouponInfoResBean> getCouponInfoResBeanList() {
		return couponInfoResBeanList;
	}
	public void setCouponInfoResBeanList(List<CouponInfoResBean> couponInfoResBeanList) {
		this.couponInfoResBeanList = couponInfoResBeanList;
	}
}
