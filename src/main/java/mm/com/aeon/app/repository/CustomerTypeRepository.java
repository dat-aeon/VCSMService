/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/01 14:37:33
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.CustomerTypeResDao;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerTypeResDao, Long>{
	List<CustomerTypeResDao> findAllByCustomerType(String customerType);
}
