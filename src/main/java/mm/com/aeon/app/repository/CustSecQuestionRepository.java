/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/01 13:50:19
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.CustSecQuestionReqDao;

@Repository
public interface CustSecQuestionRepository extends JpaRepository<CustSecQuestionReqDao, Long> {
	//CustSecQuestionReqDao findAllByCustomerId(int customerId);
	List<CustSecQuestionReqDao> findAllByCustomerId(int customerId);
	CustSecQuestionReqDao findAllByCustSecQuesId(int custSecQuesId);
}
