/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/04 11:48:20
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.SecQAUpdateInfoResDao;

@Repository
public interface SecQAUpdateInfoRepository extends JpaRepository<SecQAUpdateInfoResDao, Long> {

	String query="SELECT CSQ.CUST_SEC_QUES_ID,CSQ.ANSWER,SQ.SEC_QUES_ID,SQ.QUESTION_MYAN,SQ.QUESTION_ENG "
			+ "FROM VCS.CUST_SEC_QUESTION CSQ, VCS.SECURITY_QUESTION SQ "
			+ "WHERE CSQ.SEC_QUES_ID=SQ.SEC_QUES_ID "
			+ "AND CSQ.CUSTOMER_ID=:customerId AND CSQ.DEL_FLAG=0";
	
	@Query(value=query, nativeQuery=true)
	List<SecQAUpdateInfoResDao> findSecQAUpdateInfoList(@Param("customerId") int customerId);
	
}
