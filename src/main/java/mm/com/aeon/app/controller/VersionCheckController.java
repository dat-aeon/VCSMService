package mm.com.aeon.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import mm.com.aeon.app.bean.MobileVersionConfigReqBean;
import mm.com.aeon.app.bean.MobileVersionConfigResBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.common.CommonURL;
import mm.com.aeon.app.dao.MobileVersionConfigResDao;
import mm.com.aeon.app.service.MobileVersionConfigService;

@RestController
@RequestMapping(value = CommonURL.VERSION_CONFIG_CONTROLLER_URL)
public class VersionCheckController {

	@Autowired
	MobileVersionConfigService mobileVersionConfigService;

	MobileVersionConfigResBean mobileVerConfigResBean;
	MobileVersionConfigReqBean mobileVerConfigReqBean;
	MobileVersionConfigResDao mobileVerConfigResDao;

	static Logger log = Logger.getLogger(VersionCheckController.class);

	@RequestMapping(value = CommonURL.UPDATE_STATUS_URL)
	public MobileVersionConfigResBean checkUpdateStatus(@RequestBody(required = false) String curVerInfo) {

		log.info("JSON : " + curVerInfo);

		mobileVerConfigResBean = new MobileVersionConfigResBean();
		mobileVerConfigReqBean = new Gson().fromJson(curVerInfo, MobileVersionConfigReqBean.class);

		try {
			mobileVerConfigResDao = mobileVersionConfigService.checkMobileVerConfig(mobileVerConfigReqBean).get();
			mobileVerConfigResBean.setStatusCode(CommonConstant.STATUS_200);
			mobileVerConfigResBean.setStatusMessage(CommonURL.APP_STORE_URL);

			if (mobileVerConfigResDao != null) {

				int updateStatus = mobileVerConfigResDao.getForceUpdFlag();

				log.info("STATUS : " + updateStatus);
				if (updateStatus == CommonConstant.FLAG_OFF) {
					log.info("----------------- | Version Must Update :" + updateStatus);
					mobileVerConfigResBean.setForceUpdFlag(CommonConstant.MUST_UPDATE);
					return mobileVerConfigResBean;
				} else {
					log.info("----------------- | Version Should Update: " + updateStatus);
					mobileVerConfigResBean.setForceUpdFlag(CommonConstant.SHOULD_UPDATE);
					return mobileVerConfigResBean;
				}

			} else {
				log.info("----------------- | Version don't have on DB.");
				mobileVerConfigResBean.setForceUpdFlag(CommonConstant.SHOULD_UPDATE);
				return mobileVerConfigResBean;
			}

		} catch (Exception e) {
			// TODO: handle exception
			log.info("----------------- | Version Update Error" + e.getMessage());
			mobileVerConfigResBean.setForceUpdFlag(CommonConstant.ERROR);
			mobileVerConfigResBean.setStatusCode(CommonConstant.STATUS_500);
			mobileVerConfigResBean.setStatusMessage(e.getMessage());
			return mobileVerConfigResBean;
		}
	}
}