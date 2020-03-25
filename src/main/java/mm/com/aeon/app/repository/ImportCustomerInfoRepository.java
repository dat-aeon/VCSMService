/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/31 14:21:49
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.ImportCustomerInfoResDao;

@Repository
public interface ImportCustomerInfoRepository extends JpaRepository<ImportCustomerInfoResDao, Long> {
	List<ImportCustomerInfoResDao> findAllByDateOfBirthAndNrcNoAndDelFlag(Date dateOfBirth, String nrcNo, int delFlag);
	ImportCustomerInfoResDao findAllByCustomerNoAndDelFlag(String customerNo, int delFlag);
	ImportCustomerInfoResDao findAllByCustomerNo(String customerNo);
}
