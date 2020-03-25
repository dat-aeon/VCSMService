/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/05 13:59:45
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.NrcCodeInfoResDao;

@Repository
public interface NrcCodeInfoRepository extends JpaRepository<NrcCodeInfoResDao, Long> {

	String nrcCodeInfoQuery = "SELECT SD.STATE_ID, ARRAY_AGG(TOWNSHIP_CODE) AS TOWNSHIP_CODE_LIST"
			+ " FROM VCS.STATE_DIVISION_INFO SD " 
			+ " RIGHT JOIN (SELECT * FROM VCS.TOWNSHIP_INFO ORDER BY TOWNSHIP_CODE) AS T "
			+ " ON SD.STATE_ID = T.STATE_ID"  
			+ " GROUP BY SD.STATE_ID"
			+ " ORDER BY SD.STATE_ID";

	@Query(value = nrcCodeInfoQuery, nativeQuery = true)
	List<NrcCodeInfoResDao> findAllNrc();
}
