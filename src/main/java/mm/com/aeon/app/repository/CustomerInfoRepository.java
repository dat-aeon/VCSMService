/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/01 10:51:40
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.CustomerInfoReqDao;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfoReqDao, Long> {
	CustomerInfoReqDao findAllByCustomerId(int customerId);
	CustomerInfoReqDao findAllByPhoneNoAndNrcNo(String phoneNo, String nrcNo);
	CustomerInfoReqDao findAllByPhoneNo(String phoneNo);
	CustomerInfoReqDao findAllByCustomerIdNotAndPhoneNo(int customerId, String phoneNo);
	List<CustomerInfoReqDao> findAllByPhoneNoOrNrcNo(String phoneNo, String nrcNo);
	List<CustomerInfoReqDao> findAllByCustomerIdNotAndNrcNo(int customerId, String nrcNo);
}
