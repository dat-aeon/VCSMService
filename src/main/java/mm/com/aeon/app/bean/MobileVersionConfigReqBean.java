package mm.com.aeon.app.bean;

import java.io.Serializable;

public class MobileVersionConfigReqBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String curVersion;

	public String getCurVersion() {
		return curVersion;
	}

	public void setCurVersion(String curVersion) {
		this.curVersion = curVersion;
	}
	
}
