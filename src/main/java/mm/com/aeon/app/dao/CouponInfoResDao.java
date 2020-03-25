/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:57:30
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CouponInfoResDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="COUPON_ID")
	private int couponId;
	
	@Column(name="COUPON_CODE")
	private String couponCode;
	
	@Column(name="COUPON_NAME_MM")
	private String couponNameMM;

	@Column(name="COUPON_NAME_EN")
	private String couponNameEN;
	
	@Column(name="DESCRIPTION_MM")
	private String descriptionMM;

	@Column(name="DESCRIPTION_EN")
	private String descriptionEN;

	@Column(name="SPECIAL_EVENT_MM")
	private String specialEventMM;

	@Column(name="SPECIAL_EVENT_EN")
	private String specialEventEN;
	
	@Column(name="COUPON_AMOUNT")
	private int couponAmount;
	
	@Column(name="GOODS_PRICE")
	private int goodsPrice;
	
	@Column(name="START_TIME")
	private Timestamp startTime;
	
	@Column(name="EXPIRE_TIME")
	private Timestamp expiredTime;
	
	@Column(name="DISCOUNT_UNIT")
	private String discountUnit;
	
	@Column(name="UNUSE_IMAGE_PATH")
	private String unuseImagePath;
	
	@Column(name="USE_IMAGE_PATH")
	private String useImagePath;
	
	@Column(name="TOTAL_NO")
	private int totalNum;
	
	@Column(name="CUSTOMER_ID")
	private int customerId;
	
	@Column(name="STATUS")
	private String status;
	
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponNameMM() {
		return couponNameMM;
	}
	public void setCouponNameMM(String couponNameMM) {
		this.couponNameMM = couponNameMM;
	}
	public String getCouponNameEN() {
		return couponNameEN;
	}
	public void setCouponNameEN(String couponNameEN) {
		this.couponNameEN = couponNameEN;
	}
	public String getDescriptionMM() {
		return descriptionMM;
	}
	public void setDescriptionMM(String descriptionMM) {
		this.descriptionMM = descriptionMM;
	}
	public String getDescriptionEN() {
		return descriptionEN;
	}
	public void setDescriptionEN(String descriptionEN) {
		this.descriptionEN = descriptionEN;
	}
	public String getSpecialEventMM() {
		return specialEventMM;
	}
	public void setSpecialEventMM(String specialEventMM) {
		this.specialEventMM = specialEventMM;
	}
	public String getSpecialEventEN() {
		return specialEventEN;
	}
	public void setSpecialEventEN(String specialEventEN) {
		this.specialEventEN = specialEventEN;
	}
	public int getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(int couponAmount) {
		this.couponAmount = couponAmount;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Timestamp expiredTime) {
		this.expiredTime = expiredTime;
	}
	public String getUnuseImagePath() {
		return unuseImagePath;
	}
	public void setUnuseImagePath(String unuseImagePath) {
		this.unuseImagePath = unuseImagePath;
	}
	public String getUseImagePath() {
		return useImagePath;
	}
	public void setUseImagePath(String useImagePath) {
		this.useImagePath = useImagePath;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getDiscountUnit() {
		return discountUnit;
	}
	public void setDiscountUnit(String discountUnit) {
		this.discountUnit = discountUnit;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
