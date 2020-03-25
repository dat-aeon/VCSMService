/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/12 15:54:57
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="APP_USAGE_DETAIL")
public class AppUsageDetailReqDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_APP_USAGE_DETAIL")
	@SequenceGenerator(
			name="SEQ_APP_USAGE_DETAIL",
			sequenceName="SEQ_APP_USAGE_DETAIL_ID",
			allocationSize=1)
	@Column(name="APP_USAGE_DETAIL_ID")
	private int appUsageDetailId;
	@Column(name="APP_USAGE_ID")
	private int appUsageId;
	@Column(name="START_USAGE_DATE_TIME")
	private Timestamp startUsageDateTime;
	@Column(name="END_USAGE_DATE_TIME")
	private Timestamp endUsageDateTime;
	
	public int getAppUsageDetailId() {
		return appUsageDetailId;
	}
	public void setAppUsageDetailId(int appUsageDetailId) {
		this.appUsageDetailId = appUsageDetailId;
	}
	public int getAppUsageId() {
		return appUsageId;
	}
	public void setAppUsageId(int appUsageId) {
		this.appUsageId = appUsageId;
	}
	public Timestamp getStartUsageDateTime() {
		return startUsageDateTime;
	}
	public void setStartUsageDateTime(Timestamp startUsageDateTime) {
		this.startUsageDateTime = startUsageDateTime;
	}
	public Timestamp getEndUsageDateTime() {
		return endUsageDateTime;
	}
	public void setEndUsageDateTime(Timestamp endUsageDateTime) {
		this.endUsageDateTime = endUsageDateTime;
	}
	
}
