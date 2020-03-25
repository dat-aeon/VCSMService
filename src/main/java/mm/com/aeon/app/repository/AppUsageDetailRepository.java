/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/12 15:59:20
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mm.com.aeon.app.dao.AppUsageDetailReqDao;

public interface AppUsageDetailRepository extends JpaRepository<AppUsageDetailReqDao, Long> {
	
	AppUsageDetailReqDao findAllByAppUsageDetailId(int appUsageDetailId);
}
