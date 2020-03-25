/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/01 10:58:59
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.PasswordInfoReqDao;

@Repository
public interface PasswordInfoRepository extends JpaRepository<PasswordInfoReqDao, Long>{
	PasswordInfoReqDao findAllByUserIdAndUserTypeId(int userId, int userTypeId);
	List<PasswordInfoReqDao> findAllByUserIdAndUserTypeIdAndPassword(int userId, int userTypeId, String password);
}
