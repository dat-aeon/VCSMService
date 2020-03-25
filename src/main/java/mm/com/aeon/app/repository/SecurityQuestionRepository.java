/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/31 11:00:41
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.SecurityQuestionResDao;

@Repository
public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestionResDao, Long> {
	SecurityQuestionResDao findAllBySecQuestionId(int secQuesId);
	List<SecurityQuestionResDao> findAllByDelFlag(int delFlag);
}
