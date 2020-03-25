package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mm.com.aeon.app.dao.PromotionsInfoResDao;

public interface PromotionsInfoRepository extends JpaRepository<PromotionsInfoResDao, Long> {

	String promotionsInfoListQuery = "SELECT PI.PROMOTIONS_INFO_ID, PI.TITLE_ENG, PI.TITLE_MYN, PI.CONTENT_ENG, PI.CONTENT_MYN, PI.PUBLISHED_FROM_DATE, PI.PUBLISHED_TO_DATE, PI.IMAGE_PATH, PI.LONGITUDE, PI.LATITUDE, PI.DEL_FLAG, PI.CREATED_BY, PI.UPDATED_BY, PI.CREATED_TIME, PI.UPDATED_TIME FROM promotions_info PI WHERE PI.DEL_FLAG = 0 AND PI.PUBLISHED_FROM_DATE <= now() AND PI.PUBLISHED_TO_DATE >= now()";

	@Query(value = promotionsInfoListQuery, nativeQuery = true)
	List<PromotionsInfoResDao> findAll();

}
