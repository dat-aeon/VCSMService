/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/12 15:13:36
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.ShopCouponReqDao;

@Repository
public interface ShopCouponRepository extends JpaRepository<ShopCouponReqDao, Long> {
	String couponPassInfoQuery = "SELECT COUPON_PASSWORD "
			+ "FROM VCS.SHOP_COUPON "
			+ "WHERE "
			+ "COUPON_ID=:couponId";
	@Query(value=couponPassInfoQuery, nativeQuery=true)
	List<String> findAllByCouponIdAndShopId(@Param("couponId") int couponId);
}
