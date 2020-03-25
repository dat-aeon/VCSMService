/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/12 15:05:40
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SHOP_COUPON")
public class ShopCouponReqDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SHOP_COUPON_ID")
	private int shopCouponId;
	@Column(name="BRANCH_ID")
	private int branchId;
	@Column(name="SHOP_ID")
	private int shopId;
	@Column(name="COUPON_ID")
	private int couponId;
	@Column(name="COUPON_PASSWORD")
	private String couponPassword;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="UPDATED_TIME")
	private Timestamp updatedTime;
	
	public int getShopCouponId() {
		return shopCouponId;
	}
	public void setShopCouponId(int shopCouponId) {
		this.shopCouponId = shopCouponId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public String getCouponPassword() {
		return couponPassword;
	}
	public void setCouponPassword(String couponPassword) {
		this.couponPassword = couponPassword;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}

