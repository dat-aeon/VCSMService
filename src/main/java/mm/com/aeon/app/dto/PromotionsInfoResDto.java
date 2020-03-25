package mm.com.aeon.app.dto;

import java.io.Serializable;
import java.util.List;

public class PromotionsInfoResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6306596725996993827L;
	private String statusCode;
	private String statusMessage;
	private List<PromotionsInfoDto> promotionsInfoDtoList;

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

	public List<PromotionsInfoDto> getPromotionsInfoDtoList() {
		return promotionsInfoDtoList;
	}

	public void setPromotionsInfoDtoList(List<PromotionsInfoDto> promotionsInfoDtoList) {
		this.promotionsInfoDtoList = promotionsInfoDtoList;
	}

}
