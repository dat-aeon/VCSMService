/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/04 15:36:52
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.com.aeon.app.bean.CouponInfoResBean;
import mm.com.aeon.app.bean.CouponUseInfoReqBean;
import mm.com.aeon.app.bean.CouponUseInfoResBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.common.CommonUtils;
import mm.com.aeon.app.common.SFTPConstantInfo;
import mm.com.aeon.app.common.VCSMPasswordEncoder;
import mm.com.aeon.app.controller.RegistrationController;
import mm.com.aeon.app.dao.CouponInfoResDao;
import mm.com.aeon.app.dao.CustomerCouponReqDao;
import mm.com.aeon.app.dao.CustomerInfoReqDao;
import mm.com.aeon.app.dao.NewsInfoResDao;
import mm.com.aeon.app.dao.PromotionsInfoResDao;
import mm.com.aeon.app.dto.NewsInfoDto;
import mm.com.aeon.app.dto.PromotionsInfoDto;
import mm.com.aeon.app.exception.CouponInfoServiceException;
import mm.com.aeon.app.exception.InformationServiceException;
import mm.com.aeon.app.repository.CouponInfoRepository;
import mm.com.aeon.app.repository.CustomerCouponRepository;
import mm.com.aeon.app.repository.CustomerInfoRepository;
import mm.com.aeon.app.repository.NewsInfoRepository;
import mm.com.aeon.app.repository.PromotionsInfoRepository;
import mm.com.aeon.app.repository.ShopCouponRepository;

@Service("EVENTS_NEWS_COUPON_SERVICE")
public class EventsAndNewsCouponInfoService {

	@Autowired
	private CouponInfoRepository couponInfoRepository;

	@Autowired
	private CustomerCouponRepository customerCouponRepository;

	@Autowired
	private ShopCouponRepository shopCouponRepository;

	@Autowired
	private CustomerInfoRepository customerInfoRepository;

	@Autowired
	private NewsInfoRepository newsInfoRepository;

	@Autowired
	private PromotionsInfoRepository promotionsInfoRepository;

	static Logger log = Logger.getLogger(RegistrationController.class);

	@Async
	public CompletableFuture<List<NewsInfoDto>> getNewsInfoList() throws InformationServiceException {
		List<NewsInfoResDao> newsInfoResDaoList = new ArrayList<NewsInfoResDao>();
		// get data from NEWS_INFO table.
		newsInfoResDaoList = newsInfoRepository.findAll();

		try {
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. about_us.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred.");
		}

		List<NewsInfoDto> newsInfoResDtoList = new ArrayList<NewsInfoDto>();

		for (NewsInfoResDao newsInfoResDao : newsInfoResDaoList) {
			NewsInfoDto newsInfoResDto = new NewsInfoDto();
			newsInfoResDto.setNewsInfoId(newsInfoResDao.getNewsInfoId());
			newsInfoResDto.setTitleEng(newsInfoResDao.getTitleEng());
			newsInfoResDto.setTitleMyn(newsInfoResDao.getTitleMyn());
			newsInfoResDto.setContentEng(newsInfoResDao.getContentEng());
			newsInfoResDto.setContentMyn(newsInfoResDao.getContentMyn());
			newsInfoResDto.setPublishedFromDate(newsInfoResDao.getPublishedFromDate());
			newsInfoResDto.setPublishedToDate(newsInfoResDao.getPublishedToDate());
			newsInfoResDto.setImagePath(newsInfoResDao.getImagePath());
			newsInfoResDto.setLongitude(newsInfoResDao.getLongitude());
			newsInfoResDto.setLatitude(newsInfoResDao.getLatitude());
			newsInfoResDtoList.add(newsInfoResDto);

		}
		return CompletableFuture.completedFuture(newsInfoResDtoList);

	}

	@Async
	public CompletableFuture<List<PromotionsInfoDto>> getPromotionsInfoList() throws InformationServiceException {
		List<PromotionsInfoResDao> promotionsInfoResDaoList = new ArrayList<PromotionsInfoResDao>();
		// get data from PROMOTIONS_INFO table.
		promotionsInfoResDaoList = promotionsInfoRepository.findAll();

		try {
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. about_us.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred.");
		}

		List<PromotionsInfoDto> promotionsInfoResDtoList = new ArrayList<PromotionsInfoDto>();

		for (PromotionsInfoResDao promotionsInfoResDao : promotionsInfoResDaoList) {
			PromotionsInfoDto promotionsInfoDto = new PromotionsInfoDto();
			promotionsInfoDto.setPromotionsInfoId(promotionsInfoResDao.getPromotionsInfoId());
			promotionsInfoDto.setTitleEng(promotionsInfoResDao.getTitleEng());
			promotionsInfoDto.setTitleMyn(promotionsInfoResDao.getTitleMyn());
			promotionsInfoDto.setContentEng(promotionsInfoResDao.getContentEng());
			promotionsInfoDto.setContentMyn(promotionsInfoResDao.getContentMyn());
			promotionsInfoDto.setPublishedFromDate(promotionsInfoResDao.getPublishedFromDate());
			promotionsInfoDto.setPublishedToDate(promotionsInfoResDao.getPublishedToDate());
			promotionsInfoDto.setImagePath(SFTPConstantInfo.PROMO_PHOTO_PATH + promotionsInfoResDao.getImagePath());
			promotionsInfoDto.setLongitude(promotionsInfoResDao.getLongitude());
			promotionsInfoDto.setLatitude(promotionsInfoResDao.getLatitude());
			promotionsInfoResDtoList.add(promotionsInfoDto);

		}
		return CompletableFuture.completedFuture(promotionsInfoResDtoList);

	}

	// Get Coupon Information.
	@Async
	public CompletableFuture<List<CouponInfoResBean>> getCouponInfo(int customerId) throws CouponInfoServiceException {

		List<CouponInfoResBean> couponInfoResBeanList = new ArrayList<CouponInfoResBean>();
		List<CouponInfoResDao> couponInfoResDaoList = new ArrayList<CouponInfoResDao>();

		couponInfoResDaoList = couponInfoRepository.findAllByCustomerId(customerId);

		try {
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. get_coupon_info.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred.");
		}

		if (couponInfoResDaoList != null && couponInfoResDaoList.size() > 0) {
			for (CouponInfoResDao couponInfoResDao : couponInfoResDaoList) {
				CouponInfoResBean couponInfoResBean = new CouponInfoResBean();
				couponInfoResBean.setCouponId(Integer.toString(couponInfoResDao.getCouponId()));
				couponInfoResBean.setCouponCode(couponInfoResDao.getCouponCode());
				couponInfoResBean.setCouponNameMM(couponInfoResDao.getCouponNameMM());
				couponInfoResBean.setCouponNameEN(couponInfoResDao.getCouponNameEN());
				couponInfoResBean.setDescriptionMM(couponInfoResDao.getDescriptionMM());
				couponInfoResBean.setDescriptionEN(couponInfoResDao.getDescriptionEN());
				couponInfoResBean.setSpecialEventMM(couponInfoResDao.getSpecialEventMM());
				couponInfoResBean.setSpecialEventEN(couponInfoResDao.getSpecialEventEN());
				// couponInfoResBean.setCouponAmount(Integer.toString(couponInfoResDao.getCouponAmount()));
				couponInfoResBean.setGoodsPrice(Integer.toString(couponInfoResDao.getGoodsPrice()));
				couponInfoResBean
						.setStartTime(CommonUtils.getChangeTimestampToDMYString(couponInfoResDao.getStartTime()));
				couponInfoResBean
						.setExpiredTime(CommonUtils.getChangeTimestampToDMYString(couponInfoResDao.getExpiredTime()));
				couponInfoResBean.setDiscountUnit(couponInfoResDao.getDiscountUnit());
				couponInfoResBean
						.setUnuseImagePath(SFTPConstantInfo.COUPON_PHOTO_PATH + couponInfoResDao.getUnuseImagePath());
				couponInfoResBean.setUseImagePath(couponInfoResDao.getUseImagePath());
				couponInfoResBean.setTotalNum(Integer.toString(couponInfoResDao.getTotalNum()));
				couponInfoResBean.setCustomerId(Integer.toString(couponInfoResDao.getCustomerId()));
				couponInfoResBean.setStatus(couponInfoResDao.getStatus());

				int couponAmount = couponInfoResDao.getCouponAmount();
				int goodsPrice = couponInfoResDao.getGoodsPrice();
				int paidCouponAmt = goodsPrice - couponAmount;
				couponInfoResBean.setCouponAmount(Integer.toString(paidCouponAmt));

				couponInfoResBeanList.add(couponInfoResBean);
			}
			return CompletableFuture.completedFuture(couponInfoResBeanList);
		} else {
			return CompletableFuture.completedFuture(couponInfoResBeanList);
		}
	}

	// Update used coupon information.
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = CouponInfoServiceException.class)
	public CompletableFuture<CouponUseInfoResBean> updateUsedCoupon(CouponUseInfoReqBean couponUseInfoReqBean)
			throws CouponInfoServiceException {

		int customerId = Integer.parseInt(couponUseInfoReqBean.getCustomerId());
		int couponId = Integer.parseInt(couponUseInfoReqBean.getCouponId());
		// Encrypt the password string.
		// Base64 codec = new Base64();
		String couponPassword = VCSMPasswordEncoder.encode(couponUseInfoReqBean.getCouponPassword());
		log.info("------------ COUPON Encode PASSWORD | original: " + couponUseInfoReqBean.getCouponPassword()
				+ " , password : " + couponPassword);

		// (1) match password to SHOP_COUPON.
		// ShopCouponReqDao shopCouponReqDao = new ShopCouponReqDao();
		List<String> passwordList = shopCouponRepository.findAllByCouponIdAndShopId(couponId);

		try {
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. update_coupon_info.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred.");
		}

		if (passwordList != null && passwordList.size() > 0) {
			log.info("------------ COUPON PASSWORD LIST | password : " + passwordList);

			if (passwordList.contains(couponPassword)) {

				// Get Name for update.
				CustomerInfoReqDao customerInfoReqDao0 = customerInfoRepository.findAllByCustomerId(customerId);

				// (2) update CUSTOMER_COUPON.
				CustomerCouponReqDao customerCouponReqDao = new CustomerCouponReqDao();
				customerCouponReqDao = customerCouponRepository.findAllByCouponIdAndCustomerId(couponId, customerId);
				customerCouponReqDao.setStatus(CommonConstant.COUPON_USED_STATUS);// 2=used
																					// status.
				customerCouponReqDao.setUpdatedBy(customerInfoReqDao0.getName());// 3=customer
																					// type
																					// user.
				customerCouponReqDao.setUpdatedTime(CommonUtils.getCurrentTimeStamp());
				customerCouponReqDao = customerCouponRepository.save(customerCouponReqDao);
				log.info("------------ CUSTOMER_COUPON updated. | customer_coupon_id : "
						+ customerCouponReqDao.getCustomerCouponId());

				// (3) set Response data.
				CouponUseInfoResBean couponUseInfoResBean = new CouponUseInfoResBean();
				couponUseInfoResBean.setStatusCode(CommonConstant.STATUS_200);
				couponUseInfoResBean.setStatusMessage(CommonConstant.OK);
				couponUseInfoResBean.setCouponId(Integer.toString(customerId)); // for
																				// set
																				// unused
																				// image.
				return CompletableFuture.completedFuture(couponUseInfoResBean);
			} else {

				CouponUseInfoResBean couponUseInfoResBean = new CouponUseInfoResBean();
				couponUseInfoResBean.setStatusCode(CommonConstant.STATUS_200);
				couponUseInfoResBean.setStatusMessage(CommonConstant.INCORRECT_PWD);
				couponUseInfoResBean.setCouponId(Integer.toString(customerId)); // for
																				// set
																				// unused
																				// image.
				return CompletableFuture.completedFuture(couponUseInfoResBean);
			}
		} else {

			CouponUseInfoResBean couponUseInfoResBean = new CouponUseInfoResBean();
			couponUseInfoResBean.setStatusCode(CommonConstant.STATUS_200);
			couponUseInfoResBean.setStatusMessage(CommonConstant.COUPON_REDEEM);
			couponUseInfoResBean.setCouponId(Integer.toString(customerId)); // for
																			// set
																			// unused
																			// image.
			return CompletableFuture.completedFuture(couponUseInfoResBean);
		}
	}
}
