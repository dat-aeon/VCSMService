/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/01 11:17:20
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.AppUsageInfoReqDao;

@Repository
public interface AppUsageInfoRepository extends JpaRepository<AppUsageInfoReqDao, Long> {
	AppUsageInfoReqDao findAllByAppUsageId(int appUsageId);
	AppUsageInfoReqDao findAllByCustomerId(int customerId);
}
