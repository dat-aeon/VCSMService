/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/12 14:13:50
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.CustomerCouponReqDao;

@Repository
public interface CustomerCouponRepository extends JpaRepository<CustomerCouponReqDao, Long> {
	CustomerCouponReqDao findAllByCouponIdAndCustomerId(int couponId, int customerId);
}
