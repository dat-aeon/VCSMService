package mm.com.aeon.app.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mm.com.aeon.app.bean.MobileVersionConfigReqBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.dao.MobileVersionConfigResDao;
import mm.com.aeon.app.exception.MobileVersionConfigException;
import mm.com.aeon.app.repository.MobileVersionConfigRepository;

@Service("MOBILE_VERSION_CONFIG_SERVICE")
public class MobileVersionConfigService {
	
	@Autowired
	MobileVersionConfigRepository mobileVersionConfigRepository;
	
	static Logger log = Logger.getLogger(MobileVersionConfigService.class);
	
	List<MobileVersionConfigResDao> mobileVersionConfigResDaoList;

	@Async
	public CompletableFuture<MobileVersionConfigResDao> checkMobileVerConfig(MobileVersionConfigReqBean mobVerConfigReqBean) 
	throws MobileVersionConfigException {
		String curVersion = mobVerConfigReqBean.getCurVersion();
		if(curVersion!=null) {
			try{
				Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
				log.info("----------------- | Thread sleep for 1 second. get_coupon_info.");
			} catch (InterruptedException e) {
				// TODO: handle exception
				log.info("----------------- | Inturrepted Exception Occurred.");
			}
			mobileVersionConfigResDaoList = mobileVersionConfigRepository.findAllByVersionNo(curVersion);
			if(mobileVersionConfigResDaoList!=null&&mobileVersionConfigResDaoList.size()>0) {
				return CompletableFuture.completedFuture(mobileVersionConfigResDaoList.get(CommonConstant.ZERO));
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
