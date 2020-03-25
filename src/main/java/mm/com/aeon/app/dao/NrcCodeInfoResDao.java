/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/05 13:57:19
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class NrcCodeInfoResDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="STATE_ID")
	private int stateId;
	@Column(name="TOWNSHIP_CODE_LIST")
	private String townshipCodeList;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getTownshipCodeList() {
		return townshipCodeList;
	}

	public void setTownshipCodeList(String townshipCodeList) {
		this.townshipCodeList = townshipCodeList;
	}
}


