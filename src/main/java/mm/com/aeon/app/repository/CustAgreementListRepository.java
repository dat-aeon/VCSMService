/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/02 18:58:31
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.CustAgreementListResDao;

@Repository
public interface CustAgreementListRepository extends JpaRepository<CustAgreementListResDao, Long>{
	List<CustAgreementListResDao> findAllByQrShowNotAndImportCustomerId(int qrShow, int importCustomerId);
}
