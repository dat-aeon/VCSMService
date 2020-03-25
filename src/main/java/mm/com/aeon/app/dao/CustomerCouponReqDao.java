/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/12 14:09:21
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
@Table(name="CUSTOMER_COUPON")
public class CustomerCouponReqDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSTOMER_COUPON_ID")
	private int customerCouponId;
	@Column(name="COUPON_ID")
	private int couponId;
	@Column(name="CUSTOMER_ID")
	private int customerId;
	@Column(name="STATUS")
	private String status;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="UPDATED_TIME")
	private Timestamp updatedTime;
	
	public int getCustomerCouponId() {
		return customerCouponId;
	}
	public void setCustomerCouponId(int customerCouponId) {
		this.customerCouponId = customerCouponId;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
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
