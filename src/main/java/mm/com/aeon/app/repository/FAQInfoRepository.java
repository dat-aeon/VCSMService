/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 12:58:31
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.FAQInfoResDao;

@Repository
public interface FAQInfoRepository extends JpaRepository<FAQInfoResDao, Long> {
	List<FAQInfoResDao> findAllByCategoryIdAndDelFlag(int categoryId, int delFlag);
}
