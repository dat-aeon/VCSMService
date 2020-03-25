/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/30 9:21:24
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import mm.com.aeon.app.bean.CouponInfoReqBean;
import mm.com.aeon.app.bean.CouponInfoResBean;
import mm.com.aeon.app.bean.CouponInformationResBean;
import mm.com.aeon.app.bean.CouponUseInfoReqBean;
import mm.com.aeon.app.bean.CouponUseInfoResBean;
import mm.com.aeon.app.common.CommonURL;
import mm.com.aeon.app.dto.NewsInfoDto;
import mm.com.aeon.app.dto.NewsInfoResDto;
import mm.com.aeon.app.dto.PromotionsInfoDto;
import mm.com.aeon.app.dto.PromotionsInfoResDto;
import mm.com.aeon.app.service.EventsAndNewsCouponInfoService;

@RestController
@RequestMapping(value = CommonURL.EVENTS_AND_NEWS_URL)
public class EventsAndNewsController {

	@Autowired
	EventsAndNewsCouponInfoService eventsAndNewsCouponInfoService;

	static Logger log = Logger.getLogger(EventsAndNewsController.class);

	// Get-Coupons
	@PostMapping(value = CommonURL.SHOW_COUPON_INFO_URL)
	public CouponInformationResBean showCouponInfo(@RequestBody(required = false) String couponInfo) {

		log.info("------------ /show_coupon_info");
		log.info("------------ JSON | " + couponInfo);

		CouponInformationResBean couponInformationResBean = new CouponInformationResBean();

		try {

			// check siteActivationKey.
			CouponInfoReqBean couponInfoReqBean = new CouponInfoReqBean();
			couponInfoReqBean = new Gson().fromJson(couponInfo, CouponInfoReqBean.class);

			int customerId = Integer.parseInt(couponInfoReqBean.getCustomerId());

			List<CouponInfoResBean> couponInfoResBeanList = eventsAndNewsCouponInfoService.getCouponInfo(customerId).get();
			couponInformationResBean.setCouponInfoResBeanList(couponInfoResBeanList);
			couponInformationResBean.setStatusCode("200");
			couponInformationResBean.setStatusMessage("success");
			return couponInformationResBean;
			// return 2 photos. used and not used.

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			couponInformationResBean.setStatusCode("500");
			couponInformationResBean.setStatusMessage(e.getMessage());
			return couponInformationResBean;
		}
	}

	// Use-Coupons
	@PostMapping(value = CommonURL.USE_COUPON_INFO_URL)
	public CouponUseInfoResBean useCouponInfo(@RequestBody(required = false) String couponUseInfo) {

		log.info("------------ /use_coupon_info");
		log.info("------------ JSON | " + couponUseInfo);

		try {

			// check siteActivationKey.
			CouponUseInfoReqBean couponUseInfoReqBean = new CouponUseInfoReqBean();
			couponUseInfoReqBean = new Gson().fromJson(couponUseInfo, CouponUseInfoReqBean.class);
			return eventsAndNewsCouponInfoService.updateUsedCoupon(couponUseInfoReqBean).get();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			CouponUseInfoResBean couponUseInfoResBean = new CouponUseInfoResBean();
			couponUseInfoResBean.setStatusCode("500");
			couponUseInfoResBean.setStatusMessage(e.getMessage());
			return couponUseInfoResBean;
		}
	}

	@PostMapping(value = "/news-info")
	public NewsInfoResDto getNewsInfoList() {
		NewsInfoResDto newsInfoResDto = new NewsInfoResDto();
		try {
			List<NewsInfoDto> newsInfoDtoList = eventsAndNewsCouponInfoService.getNewsInfoList().get();
			newsInfoResDto.setNewsInfoDtoList(newsInfoDtoList);
			newsInfoResDto.setStatusCode("200");
			newsInfoResDto.setStatusMessage("success");
			return newsInfoResDto;
			
		} catch (Exception e) {
			// TODO: handle exception
			newsInfoResDto.setStatusCode("500");
			newsInfoResDto.setStatusMessage(e.getMessage());
			return newsInfoResDto;
		}
	}

	@PostMapping(value = "/promotions-info")
	public PromotionsInfoResDto getPromotionInfoList() {
		PromotionsInfoResDto promotionsInfoResDto = new PromotionsInfoResDto();
		try {
			List<PromotionsInfoDto> promotionsInfoDtoList = eventsAndNewsCouponInfoService.getPromotionsInfoList().get();
			promotionsInfoResDto.setPromotionsInfoDtoList(promotionsInfoDtoList);
			promotionsInfoResDto.setStatusCode("200");
			promotionsInfoResDto.setStatusMessage("success");
			return promotionsInfoResDto;
		} catch (Exception e) {
			// TODO: handle exception
			promotionsInfoResDto.setStatusCode("500");
			promotionsInfoResDto.setStatusMessage(e.getMessage());
			return promotionsInfoResDto;
		}
	}

	// Promotions
	// News
}
