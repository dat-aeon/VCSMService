/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 11:16:02
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mm.com.aeon.app.dao.CompanyInfoResDao;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfoResDao, Long> {
	CompanyInfoResDao findAllByCompanyInfoId(int infoId);
}
