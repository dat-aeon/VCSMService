package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

import mm.com.aeon.app.dto.SecQAUpdateInfoResDto;

public class SecQAUpdateInfoResBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	private String statusMessage;
	private int numOfSecQues;
	private int numOfAnsChar;
	private List<SecQAUpdateInfoResDto> secQAUpdateInfoResDtoList;
	
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
	public List<SecQAUpdateInfoResDto> getSecQAUpdateInfoResDtoList() {
		return secQAUpdateInfoResDtoList;
	}
	public void setSecQAUpdateInfoResDtoList(List<SecQAUpdateInfoResDto> secQAUpdateInfoResDtoList) {
		this.secQAUpdateInfoResDtoList = secQAUpdateInfoResDtoList;
	}
	public int getNumOfSecQues() {
		return numOfSecQues;
	}
	public void setNumOfSecQues(int numOfSecQues) {
		this.numOfSecQues = numOfSecQues;
	}
	public int getNumOfAnsChar() {
		return numOfAnsChar;
	}
	public void setNumOfAnsChar(int numOfAnsChar) {
		this.numOfAnsChar = numOfAnsChar;
	}
}
