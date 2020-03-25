/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/05 14:15:19
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mm.com.aeon.app.dao.FAQCategoryResDao;

public interface FAQCategoryRepository extends JpaRepository<FAQCategoryResDao, Long> {

	List<FAQCategoryResDao> findAllByDelFlag(int delFlag);
}
