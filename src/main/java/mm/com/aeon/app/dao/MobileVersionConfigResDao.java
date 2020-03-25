package mm.com.aeon.app.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MOBILE_VER_CONFIG")
public class MobileVersionConfigResDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MOBILE_VER_CONFIG")
	@SequenceGenerator(
			name="SEQ_MOBILE_VER_CONFIG",
			sequenceName="SEQ_MOBILE_VER_CONFIG_ID",
			allocationSize=1)
	@Column(name="ID")
	private int id;
	@Column(name="VERSION_NO")
	private String versionNo;
	@Column(name="FORCE_UPD_FLAG")
	private int forceUpdFlag;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="UPDATED_TIME")
	private Timestamp updatedTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public int getForceUpdFlag() {
		return forceUpdFlag;
	}
	public void setForceUpdFlag(int forceUpdFlag) {
		this.forceUpdFlag = forceUpdFlag;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
