package mm.com.aeon.app.dto;

import java.io.Serializable;
import java.util.Date;

public class PromotionsInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5223502278422000816L;
	private Integer promotionsInfoId;
	private String titleEng;
	private String titleMyn;
	private String contentEng;
	private String contentMyn;
	private Date publishedFromDate;
	private Date publishedToDate;
	private String imagePath;
	private String longitude;
	private String latitude;

	public Integer getPromotionsInfoId() {
		return promotionsInfoId;
	}

	public void setPromotionsInfoId(Integer promotionsInfoId) {
		this.promotionsInfoId = promotionsInfoId;
	}

	public String getTitleEng() {
		return titleEng;
	}

	public void setTitleEng(String titleEng) {
		this.titleEng = titleEng;
	}

	public String getTitleMyn() {
		return titleMyn;
	}

	public void setTitleMyn(String titleMyn) {
		this.titleMyn = titleMyn;
	}

	public String getContentEng() {
		return contentEng;
	}

	public void setContentEng(String contentEng) {
		this.contentEng = contentEng;
	}

	public String getContentMyn() {
		return contentMyn;
	}

	public void setContentMyn(String contentMyn) {
		this.contentMyn = contentMyn;
	}

	public Date getPublishedFromDate() {
		return publishedFromDate;
	}

	public void setPublishedFromDate(Date publishedFromDate) {
		this.publishedFromDate = publishedFromDate;
	}

	public Date getPublishedToDate() {
		return publishedToDate;
	}

	public void setPublishedToDate(Date publishedToDate) {
		this.publishedToDate = publishedToDate;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
