package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mm.com.aeon.app.dao.MobileVersionConfigResDao;

@Repository
public interface MobileVersionConfigRepository extends JpaRepository<MobileVersionConfigResDao, Long>{
	List<MobileVersionConfigResDao> findAllByVersionNo(String versionNo);
}
