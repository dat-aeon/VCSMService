package mm.com.aeon.app.bean;

import java.io.Serializable;
import java.util.List;

import mm.com.aeon.app.dto.SecurityQuestionResDto;

public class ResetPwdSecQAResBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String statusCode;
	private String statusMessage;
	private int numOfSecQues;
	private int numOfAnsChar;
	List<SecurityQuestionResDto> securityQuestionResDtoList;
	
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
	public List<SecurityQuestionResDto> getSecurityQuestionResDtoList() {
		return securityQuestionResDtoList;
	}
	public void setSecurityQuestionResDtoList(List<SecurityQuestionResDto> securityQuestionResDtoList) {
		this.securityQuestionResDtoList = securityQuestionResDtoList;
	}
}
