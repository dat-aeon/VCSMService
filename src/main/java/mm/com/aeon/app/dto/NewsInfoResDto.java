package mm.com.aeon.app.dto;

import java.io.Serializable;
import java.util.List;

public class NewsInfoResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6852660550651956669L;
	private String statusCode;
	private String statusMessage;
	private List<NewsInfoDto> newsInfoDtoList;

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

	public List<NewsInfoDto> getNewsInfoDtoList() {
		return newsInfoDtoList;
	}

	public void setNewsInfoDtoList(List<NewsInfoDto> newsInfoDtoList) {
		this.newsInfoDtoList = newsInfoDtoList;
	}

}
