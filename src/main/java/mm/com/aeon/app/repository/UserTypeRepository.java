/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/01 14:44:34
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.UserTypeResDao;

@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeResDao, Long> {
	List<UserTypeResDao> findAllByUserType(String userType);
}
