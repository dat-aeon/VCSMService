/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/12 12:26:33
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.CouponInfoResDao;

@Repository
public interface CouponInfoRepository extends JpaRepository<CouponInfoResDao, Long> {
	String couponInfoQuery = "SELECT ci.COUPON_ID,ci.COUPON_CODE,ci.COUPON_NAME_MM,ci.COUPON_NAME_EN,ci.DESCRIPTION_MM,ci.DESCRIPTION_EN,ci.COUPON_AMOUNT,ci.GOODS_PRICE,"
			+ "ci.START_TIME,ci.EXPIRE_TIME,ci.DISCOUNT_UNIT,ci.SPECIAL_EVENT_MM,ci.SPECIAL_EVENT_EN,ci.UNUSE_IMAGE_PATH,ci.USE_IMAGE_PATH,ci.TOTAL_NO,cc.CUSTOMER_ID,cc.STATUS "
			+ "FROM VCS.COUPON_INFO ci "
			+ "JOIN VCS.CUSTOMER_COUPON cc ON cc.COUPON_ID=ci.COUPON_ID "
			+ "WHERE "
			+ "cc.STATUS='1' "
			+ "AND cc.CUSTOMER_ID=:customerId "
			+ "AND ci.DEL_FLAG=0 "
			+ "AND ci.START_TIME <= now() " 
			+ "AND ci.EXPIRE_TIME >= now()";
	@Query(value=couponInfoQuery, nativeQuery=true)
	List<CouponInfoResDao> findAllByCustomerId(@Param("customerId") int customerId);
}
