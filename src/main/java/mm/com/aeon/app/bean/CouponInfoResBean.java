/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/12 13:27:34
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.bean;

import java.io.Serializable;

public class CouponInfoResBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String couponId;
	private String couponCode;
	private String couponNameMM;
	private String couponNameEN;
	private String descriptionMM;
	private String descriptionEN;
	private String specialEventMM;
	private String specialEventEN;
	private String couponAmount;
	private String goodsPrice;
	private String startTime;
	private String expiredTime;
	private String discountUnit;
	private String unuseImagePath;
	private String useImagePath;
	private String totalNum;
	private String customerId;
	private String status;
	
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
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
	public String getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}
	public String getDiscountUnit() {
		return discountUnit;
	}
	public void setDiscountUnit(String discountUnit) {
		this.discountUnit = discountUnit;
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
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
