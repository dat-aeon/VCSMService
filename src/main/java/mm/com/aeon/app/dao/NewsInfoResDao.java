package mm.com.aeon.app.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NEWS_INFO")
public class NewsInfoResDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -622943879131225749L;

	@Id
	@Column(name = "NEWS_INFO_ID")
	private Integer newsInfoId;

	@Column(name = "TITLE_ENG")
	private String titleEng;

	@Column(name = "TITLE_MYN")
	private String titleMyn;

	@Column(name = "CONTENT_ENG")
	private String contentEng;

	@Column(name = "CONTENT_MYN")
	private String contentMyn;

	@Column(name = "PUBLISHED_FROM_DATE")
	private Date publishedFromDate;

	@Column(name = "PUBLISHED_TO_DATE")
	private Date publishedToDate;

	@Column(name = "IMAGE_PATH")
	private String imagePath;

	@Column(name = "LONGITUDE")
	private String longitude;

	@Column(name = "LATITUDE")
	private String latitude;

	@Column(name = "DEL_FLAG")
	private int delFlag;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "CREATED_TIME")
	private Timestamp createdTime;

	@Column(name = "UPDATED_TIME")
	private Timestamp updatedTime;

	public Integer getNewsInfoId() {
		return newsInfoId;
	}

	public void setNewsInfoId(Integer newsInfoId) {
		this.newsInfoId = newsInfoId;
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

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

}
