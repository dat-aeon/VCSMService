package mm.com.aeon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mm.com.aeon.app.dao.NewsInfoResDao;

public interface NewsInfoRepository extends JpaRepository<NewsInfoResDao, Long> {
	String newsInfoListQuery = "SELECT NI.NEWS_INFO_ID, NI.TITLE_ENG, NI.TITLE_MYN, NI.CONTENT_ENG, NI.CONTENT_MYN, NI.PUBLISHED_FROM_DATE, NI.PUBLISHED_TO_DATE, NI.IMAGE_PATH, NI.LONGITUDE, NI.LATITUDE, NI.DEL_FLAG, NI.CREATED_BY, NI.UPDATED_BY, NI.CREATED_TIME, NI.UPDATED_TIME FROM news_info NI WHERE NI.DEL_FLAG = 0 AND NI.PUBLISHED_FROM_DATE <= now() AND NI.PUBLISHED_TO_DATE >= now()";

	@Query(value = newsInfoListQuery, nativeQuery = true)
	List<NewsInfoResDao> findAll();

}
