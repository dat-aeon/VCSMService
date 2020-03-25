/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/01/31 10:58:10
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import mm.com.aeon.app.bean.AnsweredSecurityQuestionReqBean;
import mm.com.aeon.app.bean.AppUsageInfoReqBean;
import mm.com.aeon.app.bean.CheckMemberInformationReqBean;
import mm.com.aeon.app.bean.CheckMemberInformationResBean;
import mm.com.aeon.app.bean.CurrentUserInformationResBean;
import mm.com.aeon.app.bean.MemberRegistrationInfoReqBean;
import mm.com.aeon.app.bean.UpdateVerificationInfoReqBean;
import mm.com.aeon.app.bean.VerifyNewRegisteredUserInfoReqBean;
import mm.com.aeon.app.bean.VerifyNewRegisteredUserInfoResBean;
import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.common.CommonUtils;
import mm.com.aeon.app.common.SFTPConstantInfo;
import mm.com.aeon.app.common.VCSMPasswordEncoder;
import mm.com.aeon.app.controller.RegistrationController;
import mm.com.aeon.app.dao.AppUsageDetailReqDao;
import mm.com.aeon.app.dao.AppUsageInfoReqDao;
import mm.com.aeon.app.dao.CompanyInfoResDao;
import mm.com.aeon.app.dao.CustAgreementListResDao;
import mm.com.aeon.app.dao.CustSecQuestionReqDao;
import mm.com.aeon.app.dao.CustomerInfoReqDao;
import mm.com.aeon.app.dao.CustomerTypeResDao;
import mm.com.aeon.app.dao.ImportCustomerInfoResDao;
import mm.com.aeon.app.dao.MemberInfoReqDao;
import mm.com.aeon.app.dao.PasswordInfoReqDao;
import mm.com.aeon.app.dao.SecurityQuestionResDao;
import mm.com.aeon.app.dao.UserTypeResDao;
import mm.com.aeon.app.dto.CustAgreementListDto;
import mm.com.aeon.app.dto.SecurityQuestionResDto;
import mm.com.aeon.app.exception.MemberRegistrationServiceException;
import mm.com.aeon.app.repository.AppUsageDetailRepository;
import mm.com.aeon.app.repository.AppUsageInfoRepository;
import mm.com.aeon.app.repository.CompanyInfoRepository;
import mm.com.aeon.app.repository.CustAgreementListRepository;
import mm.com.aeon.app.repository.CustSecQuestionRepository;
import mm.com.aeon.app.repository.CustomerInfoRepository;
import mm.com.aeon.app.repository.CustomerTypeRepository;
import mm.com.aeon.app.repository.ImportCustomerInfoRepository;
import mm.com.aeon.app.repository.MemberInfoRepository;
import mm.com.aeon.app.repository.PasswordInfoRepository;
import mm.com.aeon.app.repository.SecurityQuestionRepository;
import mm.com.aeon.app.repository.UserTypeRepository;

@Service("REGISTRATION_SERVICE")
public class MemberRegistrationService {

	@Autowired
	SecurityQuestionRepository securityQuestionRepository;
	@Autowired
	ImportCustomerInfoRepository importCustomerInfoRepository;
	@Autowired
	CustomerInfoRepository customerInfoRepository;
	@Autowired
	PasswordInfoRepository passwordInfoRepository;
	@Autowired
	CustSecQuestionRepository custSecQuestionRepository;
	@Autowired
	AppUsageInfoRepository appUsageInfoRepository;
	@Autowired
	CustomerTypeRepository customerTypeRepository;
	@Autowired
	UserTypeRepository userTypeRepository;
	@Autowired
	CustAgreementListRepository custAgreementListRepository;
	@Autowired
	SFTPFileUploadService sftpFileUploadService;
	@Autowired
	MemberInfoRepository memberInfoRepository;
	@Autowired
	AppUsageDetailRepository appUsageDetailRepository;
	@Autowired
	CompanyInfoRepository companyInfoRepository;

	static Logger log = Logger.getLogger(RegistrationController.class);

	// Get all Security Questions List.
	@Async
	public CompletableFuture<List<SecurityQuestionResDto>> getRegistrationSecurityQuestion() throws MemberRegistrationServiceException {
		List<SecurityQuestionResDto> securityQuestionResDtoList = new ArrayList<>();
		List<SecurityQuestionResDao> securityQuestionResDaoList = new ArrayList<>();
		SecurityQuestionResDto securityQuestionResDto;
		securityQuestionResDaoList = securityQuestionRepository.findAllByDelFlag(CommonConstant.FLAG_ON);
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. register_get_security_question.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. register_get_security_question");
		}
		
		if (securityQuestionResDaoList != null) {
			for (SecurityQuestionResDao securityQuestionResDao : securityQuestionResDaoList) {
				securityQuestionResDto = new SecurityQuestionResDto();
				securityQuestionResDto.setSecQuestionId(securityQuestionResDao.getSecQuestionId());
				securityQuestionResDto.setQuestionMM(securityQuestionResDao.getQuestionMM());
				securityQuestionResDto.setQuestionEN(securityQuestionResDao.getQuestionEN());
				securityQuestionResDtoList.add(securityQuestionResDto);
			}
			return CompletableFuture.completedFuture(securityQuestionResDtoList);
		} else {
			return null;
		}
	}

	// Check existed Customer.
	@Async
	public CompletableFuture<CheckMemberInformationResBean> checkMember(CheckMemberInformationReqBean checkMemberInformationReqBean)
			throws MemberRegistrationServiceException {

		log.info("------------ checkMember() | checking whether user existed or not.");

		CheckMemberInformationResBean checkUserInfoResBean = new CheckMemberInformationResBean();
		ImportCustomerInfoResDao importCustomerInfoResDao = new ImportCustomerInfoResDao();

		// check customer info.
		Date dateOfBirth = CommonUtils.getChangeStringToDate(checkMemberInformationReqBean.getDateOfBirth());
		// String phoneNo = checkMemberInformationReqBean.getPhoneNo();
		String nrcNo = checkMemberInformationReqBean.getNrcNo();
		int delFlag = CommonConstant.FLAG_ON;

		List<ImportCustomerInfoResDao> importCustomerInfoResDaoList = null;

		importCustomerInfoResDaoList = importCustomerInfoRepository.findAllByDateOfBirthAndNrcNoAndDelFlag(dateOfBirth,
				nrcNo, delFlag);
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. check_member.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. check_member.");
		}

		if (importCustomerInfoResDaoList != null && importCustomerInfoResDaoList.size() > 0) {
			importCustomerInfoResDao = importCustomerInfoResDaoList.get(0);
			checkUserInfoResBean.setImportCustomerInfoId(importCustomerInfoResDao.getImportCustomerInfoId());
			checkUserInfoResBean.setCustomerNo(importCustomerInfoResDao.getCustomerNo());
			checkUserInfoResBean.setName(importCustomerInfoResDao.getName());
			checkUserInfoResBean.setGender(importCustomerInfoResDao.getGender());
			checkUserInfoResBean.setPhoneNo(importCustomerInfoResDao.getPhoneNo());
			checkUserInfoResBean.setNrcNo(importCustomerInfoResDao.getNrcNo());
			checkUserInfoResBean
					.setDateOfBirth(CommonUtils.getChangeDatetoString(importCustomerInfoResDao.getDateOfBirth()));
			checkUserInfoResBean.setSalary(importCustomerInfoResDao.getSalary());
			checkUserInfoResBean.setCompanyName(importCustomerInfoResDao.getCompanyName());
			checkUserInfoResBean.setTownshipAddress(importCustomerInfoResDao.getAddress());
			// Get Agreement List.
			List<CustAgreementListResDao> custAgreementListResDaoList =
					// custAgreementListRepository.findAllByImportCustomerIdAndDelFlag(checkUserInfoResBean.getImportCustomerInfoId(),CommonConstant.FLAG_ON);
					custAgreementListRepository.findAllByQrShowNotAndImportCustomerId(CommonConstant.QR_OFF,
							checkUserInfoResBean.getImportCustomerInfoId());

			List<CustAgreementListDto> custAgreementListResDtoList = new ArrayList<>();

			for (CustAgreementListResDao custAgreementListResDao : custAgreementListResDaoList) {
				CustAgreementListDto custAgreementListDto = new CustAgreementListDto();
				custAgreementListDto.setCustAgreementId(String.valueOf(custAgreementListResDao.getCustAgreementId()));
				custAgreementListDto.setAgreementNo(custAgreementListResDao.getAgreementNo());
				custAgreementListDto.setQrShow(String.valueOf(custAgreementListResDao.getQrShow()));
				custAgreementListDto.setFinancialAmt(String.valueOf(custAgreementListResDao.getFinancialAmt()));
				custAgreementListDto.setFinancialTerm(String.valueOf(custAgreementListResDao.getFinancialTerm()));
				custAgreementListDto.setImportCustomerId(String.valueOf(custAgreementListResDao.getImportCustomerId()));
				custAgreementListResDtoList.add(custAgreementListDto);
			}

			checkUserInfoResBean.setCustAgreementListResDtoList(custAgreementListResDtoList);
			return CompletableFuture.completedFuture(checkUserInfoResBean);
		} else {
			// Check on CUSTOMER_INFO table
			return null;
		}
	}

	@Async
	public CompletableFuture<Boolean> checkImportPhNoDuplicate(
			String phoneNo) {

		//String phoneNo = checkMemberInformationResBean.getPhoneNo();

		CustomerInfoReqDao customerInfoReqDao = customerInfoRepository.findAllByPhoneNo(phoneNo);

		try {
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. check_import_phone_duplication.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. check_import_phone_duplication.");
		}

		if (customerInfoReqDao != null) {
			return CompletableFuture.completedFuture(true);
		} else {
			return CompletableFuture.completedFuture(false);
		}

	}

	@Async
	public CompletableFuture<String> checkCustomerDuplicate(CheckMemberInformationReqBean checkMemberInformationReqBean) {

		boolean phoneExist = false;
		boolean nrcExist = false;

		String phoneNo = checkMemberInformationReqBean.getPhoneNo();
		String nrcNo = checkMemberInformationReqBean.getNrcNo();

		List<CustomerInfoReqDao> customerInfoReqDaoList = customerInfoRepository.findAllByPhoneNoOrNrcNo(phoneNo,
				nrcNo);
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. check_customer_duplication.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. check_customer_duplication.");
		}

		if (customerInfoReqDaoList != null && customerInfoReqDaoList.size() > 0) {
			for (CustomerInfoReqDao customerInfoReqDao : customerInfoReqDaoList) {
				if (customerInfoReqDao.getPhoneNo().equals(phoneNo)) {
					phoneExist = true;
				}
				if (customerInfoReqDao.getNrcNo().equals(nrcNo)) {
					nrcExist = true;
				}
			}

			if (phoneExist && nrcExist) {
				return CompletableFuture.completedFuture(CommonConstant.NRC_PH_DUPLICATE);
			}

			if (phoneExist) {
				return CompletableFuture.completedFuture(CommonConstant.PH_DUPLICATE);
			}

			if (nrcExist) {
				return CompletableFuture.completedFuture(CommonConstant.NRC_DUPLICATE);
			}

		} else {
			return CompletableFuture.completedFuture(CommonConstant.NO_DUPLICATE);
		}

		return CompletableFuture.completedFuture(CommonConstant.NO_DUPLICATE);
	}

	
	// Register Existed User.
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = MemberRegistrationServiceException.class)
	public CompletableFuture<CurrentUserInformationResBean> registerExistedUser(MemberRegistrationInfoReqBean memberRegInfoReqBean,
			MultipartFile imgFile) throws MemberRegistrationServiceException {

		// match agreement number.
		int importCustId = memberRegInfoReqBean.getImportCustomerId();
		// String imgName = CommonUtils.getCurrentTimeStringUsingDate();

		/*
		 * try { imgFile.transferTo(new File(imgName)); } catch (Exception e) { // TODO:
		 * handle exception throw new
		 * MemberRegistrationServiceException("rename failed"); }
		 */

		// insert registration info.
		// (1) save photo to photoPath
		String responseStatus = CommonConstant.FILE_UPLOAD_FAILED;
		String imgFileName = imgFile.getOriginalFilename();
		try {
			log.info("------------ | Uploading begin.");
			CompletableFuture<String> responeStatusString = sftpFileUploadService.uploadFile(imgFile);
			responseStatus = responeStatusString.get();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MemberRegistrationServiceException("File upload failed.");
		}

		// Throws the exception if file save failed.
		if (responseStatus.equals(CommonConstant.FILE_UPLOAD_FAILED)) {
			log.info("------------ MemberRegistrationService | (1) photo save failed.");
			throw new MemberRegistrationServiceException(responseStatus);
		}
		log.info("------------ MemberRegistrationService | (1) photo saved.");

		// (2) insert to CUSTOMER_INFO
		CustomerInfoReqDao customerInfoReqDao = new CustomerInfoReqDao();
		customerInfoReqDao.setCustomerNo(memberRegInfoReqBean.getCustomerNo());
		customerInfoReqDao.setPhoneNo(CommonUtils.modifyInvalidPhone(memberRegInfoReqBean.getPhoneNo()));
		customerInfoReqDao.setUserTypeId(memberRegInfoReqBean.getUserTypeId());
		customerInfoReqDao.setCustomerTypeId(memberRegInfoReqBean.getCustomerTypeId());
		customerInfoReqDao.setName(memberRegInfoReqBean.getName());
		customerInfoReqDao
				.setDateOfBirth(CommonUtils.getChangeStringToTimeStamp(memberRegInfoReqBean.getDateOfBirth()));
		customerInfoReqDao.setNrcNo(memberRegInfoReqBean.getNrcNo());
		customerInfoReqDao.setJoinDate(memberRegInfoReqBean.getCreatedTime());
		customerInfoReqDao.setPhotoPath(imgFileName);
		customerInfoReqDao.setCreatedBy(memberRegInfoReqBean.getCreatedBy());
		customerInfoReqDao.setCreatedTime(CommonUtils.getCurrentTimeStamp());
		
		//Get Imported Customer Info.
		String mNrcNo=customerInfoReqDao.getNrcNo();
		Date dateOfBirth=customerInfoReqDao.getDateOfBirth();
		List<ImportCustomerInfoResDao> importCustomerInfoResDaoList0 =
				importCustomerInfoRepository.findAllByDateOfBirthAndNrcNoAndDelFlag(dateOfBirth, mNrcNo, CommonConstant.FLAG_ON);
		customerInfoReqDao.setSalary(Double.parseDouble(importCustomerInfoResDaoList0.get(CommonConstant.ZERO).getSalary()));
		customerInfoReqDao.setGender(importCustomerInfoResDaoList0.get(CommonConstant.ZERO).getGender());
		customerInfoReqDao.setCompanyName(importCustomerInfoResDaoList0.get(CommonConstant.ZERO).getCompanyName());
		customerInfoReqDao.setAddress(importCustomerInfoResDaoList0.get(CommonConstant.ZERO).getAddress());
		
		customerInfoReqDao = customerInfoRepository.save(customerInfoReqDao);
		log.info("------------ CUSTOMER_INFO inserted. | customer_info_id : " + customerInfoReqDao.getCustomerId());

		// (3) insert to MEMBER_INFO
		MemberInfoReqDao memberInfoReqDao = new MemberInfoReqDao();
		memberInfoReqDao.setCustomerId(customerInfoReqDao.getCustomerId());
		memberInfoReqDao.setMemberTypeId(CommonConstant.MEMBER_TYPE_BROWN_ID); // _1
		memberInfoReqDao.setCreatedTime(CommonUtils.getCurrentTimeStamp());
		memberInfoReqDao.setCreatedBy(memberRegInfoReqBean.getCreatedBy());
		memberInfoReqDao = memberInfoRepository.save(memberInfoReqDao);
		log.info("------------ MEMBER_INFO inserted. | member_id : " + memberInfoReqDao.getMemberId());

		// (4) insert to PASSWORD_INFO
		PasswordInfoReqDao passwordInfoReqDao = new PasswordInfoReqDao();
		passwordInfoReqDao.setUserId(customerInfoReqDao.getCustomerId());
		passwordInfoReqDao.setUserTypeId(memberRegInfoReqBean.getUserTypeId());
		passwordInfoReqDao.setPassword(VCSMPasswordEncoder.encode(memberRegInfoReqBean.getPassword()));
		passwordInfoReqDao.setCreatedBy(memberRegInfoReqBean.getCreatedBy());
		passwordInfoReqDao.setCreatedTime(CommonUtils.getCurrentTimeStamp());
		passwordInfoReqDao = passwordInfoRepository.save(passwordInfoReqDao);
		log.info("------------ PASSWORD_INFO inserted. | password_id : " + passwordInfoReqDao.getPasswordId());

		// (5) insert to CUST_SEC_QUESTION
		List<AnsweredSecurityQuestionReqBean> answeredSecurityQuestionList = new ArrayList<>();
		answeredSecurityQuestionList = memberRegInfoReqBean.getSecurityAnsweredInfoList();

		for (AnsweredSecurityQuestionReqBean ansSecQuestionReqBean : answeredSecurityQuestionList) {

			SecurityQuestionResDao securityQuesReqDao = securityQuestionRepository.findAllBySecQuestionId(ansSecQuestionReqBean.getSecQuesId());
			if (securityQuesReqDao != null && securityQuesReqDao.getDelFlag() == 1) {
				securityQuesReqDao.setSecQuestionId(ansSecQuestionReqBean.getSecQuesId());
				securityQuesReqDao.setDelFlag(CommonConstant.ZERO);
				securityQuesReqDao.setUpdatedBy(memberRegInfoReqBean.getCreatedBy() + "(Customer Overwirte)");
				securityQuesReqDao.setUpdatedTime(CommonUtils.getCurrentTimeStamp());
				securityQuesReqDao = securityQuestionRepository.save(securityQuesReqDao);
				log.info("------------ SEC_QUESTION overwrite. | sec_ques_id : "
						+ ansSecQuestionReqBean.getSecQuesId());
			}

			CustSecQuestionReqDao custSecQuestionReqDao = new CustSecQuestionReqDao();
			custSecQuestionReqDao.setSecQuesId(ansSecQuestionReqBean.getSecQuesId());
			custSecQuestionReqDao.setCustomerId(customerInfoReqDao.getCustomerId());
			custSecQuestionReqDao.setAnswer(ansSecQuestionReqBean.getAnswer());
			custSecQuestionReqDao.setCreatedBy(memberRegInfoReqBean.getCreatedBy());
			custSecQuestionReqDao.setCreatedTime(CommonUtils.getCurrentTimeStamp());
			custSecQuestionReqDao = custSecQuestionRepository.save(custSecQuestionReqDao);
			log.info("------------ CUST_SEC_QUESTION inserted. | cust_sec_ques_id : "
					+ custSecQuestionReqDao.getCustSecQuesId());
		}

		// (6) insert to APP_USAGE_INFO
		AppUsageInfoReqDao appUsageInfoReqDao = new AppUsageInfoReqDao();
		AppUsageInfoReqBean appUsageInfoReqBean = new AppUsageInfoReqBean();
		appUsageInfoReqBean = memberRegInfoReqBean.getAppUsageInfo();

		appUsageInfoReqDao.setCustomerId(customerInfoReqDao.getCustomerId());
		appUsageInfoReqDao.setRegisterationDateTime(CommonUtils.getCurrentTimeStamp());
		appUsageInfoReqDao.setPhoneModel(appUsageInfoReqBean.getPhoneModel());
		appUsageInfoReqDao.setManufacture(appUsageInfoReqBean.getManufacture());
		appUsageInfoReqDao.setSdk(appUsageInfoReqBean.getSdk());
		appUsageInfoReqDao.setOsType(appUsageInfoReqBean.getOsType());
		appUsageInfoReqDao.setOsVersion(appUsageInfoReqBean.getOsVersion());
		appUsageInfoReqDao.setResolution(appUsageInfoReqBean.getResolution());
		appUsageInfoReqDao.setInstructionSet(appUsageInfoReqBean.getInstructionSet());
		appUsageInfoReqDao.setCpuArchitecture(appUsageInfoReqBean.getCpuArchitecture());
		appUsageInfoReqDao.setCreatedBy(memberRegInfoReqBean.getCreatedBy());
		appUsageInfoReqDao.setCreatedTime(CommonUtils.getCurrentTimeStamp());
		appUsageInfoReqDao.setUpdatedTime(memberRegInfoReqBean.getUpdatedTime());
		appUsageInfoReqDao = appUsageInfoRepository.save(appUsageInfoReqDao);
		log.info("------------ APP_USAGE_INFO inserted. | app_usage_id : " + appUsageInfoReqDao.getAppUsageId());

		// (7) insert to APP_USAGE_DETAIL
		AppUsageDetailReqDao appUsageDetailReqDao = new AppUsageDetailReqDao();
		appUsageDetailReqDao.setAppUsageId(appUsageInfoReqDao.getAppUsageId());
		appUsageDetailReqDao.setStartUsageDateTime(CommonUtils.getCurrentTimeStamp());
		appUsageDetailReqDao.setEndUsageDateTime(CommonUtils.getCurrentTimeStamp());
		appUsageDetailReqDao = appUsageDetailRepository.save(appUsageDetailReqDao);
		log.info("------------ APP_USAGE_DETAIL inserted. | app_usage_detail_id : "
				+ appUsageDetailReqDao.getAppUsageDetailId());

		// get current registration user info.
		CurrentUserInformationResBean currentUserInformationResBean = new CurrentUserInformationResBean();
		int userId = customerInfoReqDao.getCustomerId();
		int userTypeId = memberRegInfoReqBean.getUserTypeId();

		currentUserInformationResBean.setPassword(getCustomerPwd(userId, userTypeId));
		currentUserInformationResBean.setCustomerId(customerInfoReqDao.getCustomerId());
		currentUserInformationResBean.setCustomerTypeId(customerInfoReqDao.getCustomerTypeId());
		currentUserInformationResBean.setUserTypeId(customerInfoReqDao.getUserTypeId());
		currentUserInformationResBean.setCustomerNo(customerInfoReqDao.getCustomerNo());
		currentUserInformationResBean.setPhoneNo(customerInfoReqDao.getPhoneNo());
		currentUserInformationResBean.setName(customerInfoReqDao.getName());
		currentUserInformationResBean
				.setDateOfBirth(CommonUtils.getChangeDatetoString(customerInfoReqDao.getDateOfBirth()));
		currentUserInformationResBean.setNrcNo(customerInfoReqDao.getNrcNo());
		if(customerInfoReqDao.getPhotoPath()==null) {
			currentUserInformationResBean.setPhotoPath(CommonConstant.BLANK);
		} else {
			currentUserInformationResBean.setPhotoPath(SFTPConstantInfo.PHOTO_PATH + customerInfoReqDao.getPhotoPath());
		}
		currentUserInformationResBean.setAppUsageDetailId(Integer.toString(appUsageDetailReqDao.getAppUsageDetailId()));
		log.info("--------------------get hotline number-----------");
		CompanyInfoResDao companyInfoResDao = new CompanyInfoResDao();
		// get data from COMPANY_INFO table filtered by infoId.
		companyInfoResDao = companyInfoRepository.findAllByCompanyInfoId(CommonConstant.AEON_COMPANY_ID);
		if (companyInfoResDao != null) {
			currentUserInformationResBean.setHotlinePhone(companyInfoResDao.getHotLinePhone());
		} else {
			throw new MemberRegistrationServiceException("Hotline Number is Null");
		}

		List<CustAgreementListResDao> custAgreementListResDaoList = custAgreementListRepository
				.findAllByQrShowNotAndImportCustomerId(CommonConstant.QR_OFF, importCustId);
		List<CustAgreementListDto> custAgreementListDtolist = new ArrayList<>();
		for (CustAgreementListResDao custAgreementListResDao : custAgreementListResDaoList) {
			CustAgreementListDto custAgreementListDto = new CustAgreementListDto();
			custAgreementListDto.setAgreementNo(custAgreementListResDao.getAgreementNo());
			custAgreementListDto.setQrShow(String.valueOf(custAgreementListResDao.getQrShow()));
			custAgreementListDto.setCustAgreementId(String.valueOf(custAgreementListResDao.getCustAgreementId()));
			custAgreementListDto.setImportCustomerId(String.valueOf(custAgreementListResDao.getImportCustomerId()));
			custAgreementListDto.setFinancialAmt(String.valueOf(custAgreementListResDao.getFinancialAmt()));
			custAgreementListDto.setFinancialTerm(String.valueOf(custAgreementListResDao.getFinancialTerm()));
			custAgreementListDtolist.add(custAgreementListDto);
		}
		currentUserInformationResBean.setCustAgreementListDtoList(custAgreementListDtolist);
		log.info("------------ registration success! | customer_info_id : " + customerInfoReqDao.getCustomerId());
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. register_existed_user.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. register_existed_user.");
		}

		return CompletableFuture.completedFuture(currentUserInformationResBean);
	}

	
	// Register New User.
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = MemberRegistrationServiceException.class)
	public CompletableFuture<CurrentUserInformationResBean> registerNewUser(MemberRegistrationInfoReqBean memberRegInfoReqBean)
			throws MemberRegistrationServiceException {

		log.info("------------ registerNewUser() | register new user.");

		// insert registration info.
		// (1) insert to CUSTOMER_INFO => insert with NON_CUST_TYPE type. | with no
		// PHOTO_PATH.
		CustomerInfoReqDao customerInfoReqDao = new CustomerInfoReqDao();
		customerInfoReqDao.setCustomerNo(memberRegInfoReqBean.getCustomerNo());
		customerInfoReqDao.setPhoneNo(memberRegInfoReqBean.getPhoneNo());
		customerInfoReqDao.setUserTypeId(memberRegInfoReqBean.getUserTypeId());
		customerInfoReqDao.setCustomerTypeId(memberRegInfoReqBean.getCustomerTypeId());
		customerInfoReqDao.setName(memberRegInfoReqBean.getName());
		customerInfoReqDao.setDateOfBirth(CommonUtils.getChangeStringToDate(memberRegInfoReqBean.getDateOfBirth()));
		log.info("------------ CUSTOMER_INFO inserted. | Date of birth : " + customerInfoReqDao.getDateOfBirth());
		customerInfoReqDao.setNrcNo(memberRegInfoReqBean.getNrcNo());
		customerInfoReqDao.setJoinDate(memberRegInfoReqBean.getCreatedTime());
		customerInfoReqDao.setCreatedBy(memberRegInfoReqBean.getCreatedBy());
		customerInfoReqDao.setCreatedTime(CommonUtils.getCurrentTimeStamp());
		customerInfoReqDao = customerInfoRepository.save(customerInfoReqDao);
		log.info("------------ CUSTOMER_INFO inserted. | customer_info_id : " + customerInfoReqDao.getCustomerId());

		// (2) insert to PASSWORD_INFO => with OP_USER_TYPE
		PasswordInfoReqDao passwordInfoReqDao = new PasswordInfoReqDao();
		passwordInfoReqDao.setUserId(customerInfoReqDao.getCustomerId());
		passwordInfoReqDao.setUserTypeId(memberRegInfoReqBean.getUserTypeId());
		passwordInfoReqDao.setPassword(VCSMPasswordEncoder.encode(memberRegInfoReqBean.getPassword()));
		passwordInfoReqDao.setCreatedBy(memberRegInfoReqBean.getCreatedBy());
		passwordInfoReqDao.setCreatedTime(CommonUtils.getCurrentTimeStamp());
		passwordInfoReqDao = passwordInfoRepository.save(passwordInfoReqDao);
		log.info("------------ PASSWORD_INFO inserted. | password_id : " + passwordInfoReqDao.getPasswordId());

		// (3) insert to CUST_SEC_QUESTION
		List<AnsweredSecurityQuestionReqBean> answeredSecurityQuestionList = new ArrayList<>();
		answeredSecurityQuestionList = memberRegInfoReqBean.getSecurityAnsweredInfoList();

		for (AnsweredSecurityQuestionReqBean ansSecQuestionReqBean : answeredSecurityQuestionList) {
			
			SecurityQuestionResDao securityQuesReqDao = securityQuestionRepository.findAllBySecQuestionId(ansSecQuestionReqBean.getSecQuesId());
			if (securityQuesReqDao != null && securityQuesReqDao.getDelFlag() == 1) {
				securityQuesReqDao.setSecQuestionId(ansSecQuestionReqBean.getSecQuesId());
				securityQuesReqDao.setDelFlag(CommonConstant.ZERO);
				securityQuesReqDao.setUpdatedBy(memberRegInfoReqBean.getCreatedBy() + "(Customer Overwirte)");
				securityQuesReqDao.setUpdatedTime(CommonUtils.getCurrentTimeStamp());
				securityQuesReqDao = securityQuestionRepository.save(securityQuesReqDao);
				log.info("------------ SEC_QUESTION overwrite. | sec_ques_id : "
						+ ansSecQuestionReqBean.getSecQuesId());
			}
			CustSecQuestionReqDao custSecQuestionReqDao = new CustSecQuestionReqDao();
			custSecQuestionReqDao.setSecQuesId(ansSecQuestionReqBean.getSecQuesId());
			custSecQuestionReqDao.setCustomerId(customerInfoReqDao.getCustomerId());
			custSecQuestionReqDao.setAnswer(ansSecQuestionReqBean.getAnswer());
			custSecQuestionReqDao.setCreatedBy(memberRegInfoReqBean.getCreatedBy());
			custSecQuestionReqDao.setCreatedTime(CommonUtils.getCurrentTimeStamp());
			custSecQuestionReqDao = custSecQuestionRepository.save(custSecQuestionReqDao);
			log.info("------------ CUST_SEC_QUESTION inserted. | cust_sec_ques_id : "
					+ custSecQuestionReqDao.getCustSecQuesId());
		}

		// (4) insert to APP_USAGE_INFO
		AppUsageInfoReqDao appUsageInfoReqDao = new AppUsageInfoReqDao();
		AppUsageInfoReqBean appUsageInfoReqBean = new AppUsageInfoReqBean();
		appUsageInfoReqBean = memberRegInfoReqBean.getAppUsageInfo();

		appUsageInfoReqDao.setCustomerId(customerInfoReqDao.getCustomerId());
		appUsageInfoReqDao.setRegisterationDateTime(CommonUtils.getCurrentTimeStamp());
		appUsageInfoReqDao.setPhoneModel(appUsageInfoReqBean.getPhoneModel());
		appUsageInfoReqDao.setManufacture(appUsageInfoReqBean.getManufacture());
		appUsageInfoReqDao.setSdk(appUsageInfoReqBean.getSdk());
		appUsageInfoReqDao.setOsType(appUsageInfoReqBean.getOsType());
		appUsageInfoReqDao.setOsVersion(appUsageInfoReqBean.getOsVersion());
		appUsageInfoReqDao.setResolution(appUsageInfoReqBean.getResolution());
		appUsageInfoReqDao.setInstructionSet(appUsageInfoReqBean.getInstructionSet());
		appUsageInfoReqDao.setCpuArchitecture(appUsageInfoReqBean.getCpuArchitecture());
		appUsageInfoReqDao.setCreatedBy(memberRegInfoReqBean.getCreatedBy());
		appUsageInfoReqDao.setCreatedTime(CommonUtils.getCurrentTimeStamp());
		appUsageInfoReqDao.setUpdatedTime(memberRegInfoReqBean.getUpdatedTime());
		appUsageInfoReqDao = appUsageInfoRepository.save(appUsageInfoReqDao);
		log.info("------------ APP_USAGE_INFO inserted. | app_usage_id : " + appUsageInfoReqDao.getAppUsageId());

		// (5) insert to APP_USAGE_DETAIL
		AppUsageDetailReqDao appUsageDetailReqDao = new AppUsageDetailReqDao();
		appUsageDetailReqDao.setAppUsageId(appUsageInfoReqDao.getAppUsageId());
		appUsageDetailReqDao.setStartUsageDateTime(CommonUtils.getCurrentTimeStamp());
		appUsageDetailReqDao.setEndUsageDateTime(CommonUtils.getCurrentTimeStamp());
		appUsageDetailReqDao = appUsageDetailRepository.save(appUsageDetailReqDao);
		log.info("------------ APP_USAGE_DETAIL inserted. | app_usage_detail_id : "
				+ appUsageDetailReqDao.getAppUsageDetailId());

		// get current check user information.
		CurrentUserInformationResBean currentUserInformationResBean = new CurrentUserInformationResBean();
		int userId = customerInfoReqDao.getCustomerId();
		int userTypeId = memberRegInfoReqBean.getUserTypeId();

		currentUserInformationResBean.setPassword(getCustomerPwd(userId, userTypeId));
		currentUserInformationResBean.setCustomerId(customerInfoReqDao.getCustomerId());
		currentUserInformationResBean.setCustomerTypeId(customerInfoReqDao.getCustomerTypeId());
		currentUserInformationResBean.setUserTypeId(customerInfoReqDao.getUserTypeId());
		currentUserInformationResBean.setCustomerNo(customerInfoReqDao.getCustomerNo());
		currentUserInformationResBean.setPhoneNo(customerInfoReqDao.getPhoneNo());
		currentUserInformationResBean.setName(customerInfoReqDao.getName());
		currentUserInformationResBean
				.setDateOfBirth(CommonUtils.getChangeDatetoString(customerInfoReqDao.getDateOfBirth()));
		currentUserInformationResBean.setNrcNo(customerInfoReqDao.getNrcNo());
		currentUserInformationResBean.setPhotoPath(CommonConstant.BLANK);
		currentUserInformationResBean.setDelFlag(customerInfoReqDao.getDelFalg());
		currentUserInformationResBean.setAppUsageDetailId(Integer.toString(appUsageDetailReqDao.getAppUsageDetailId()));
		log.info("------------ registration success! | customer_info_id : " + customerInfoReqDao.getCustomerId());

		log.info("--------------------get hotline number-----------");
		CompanyInfoResDao companyInfoResDao = new CompanyInfoResDao();
		// get data from COMPANY_INFO table filtered by infoId.
		companyInfoResDao = companyInfoRepository.findAllByCompanyInfoId(CommonConstant.AEON_COMPANY_ID);
		if (companyInfoResDao != null) {
			currentUserInformationResBean.setHotlinePhone(companyInfoResDao.getHotLinePhone());
		} else {
			throw new MemberRegistrationServiceException("Hotline Number is Null");
		}
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. register_new_user.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. register_new_user.");
		}
		
		return CompletableFuture.completedFuture(currentUserInformationResBean);
	}

	
	// Verify New registered user.
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = MemberRegistrationServiceException.class)
	public CompletableFuture<CurrentUserInformationResBean> verifyMemberInfo(UpdateVerificationInfoReqBean verifyInfo,
			MultipartFile imgFile) throws MemberRegistrationServiceException {

		// update registered info.

		// (1) save photo to photoPath.
		String responseStatus = CommonConstant.FILE_UPLOAD_FAILED;
		String imgFileName = imgFile.getOriginalFilename();
		try {
			CompletableFuture<String> responeStatusString = sftpFileUploadService.uploadFile(imgFile);
			responseStatus = responeStatusString.get();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MemberRegistrationServiceException("------------ File upload failed.");
		}

		// Throws the exception if file save failed.
		if (responseStatus.equals(CommonConstant.FILE_UPLOAD_FAILED)) {
			log.info("------------ file upload failed! | thrown exception.");
			throw new MemberRegistrationServiceException(responseStatus);
		}
		log.info("------------ photo is successfully uploaded.");

		// (2) get IMPORT_CUSTOMER_INFO info.
		String customerNo = verifyInfo.getCustomerNo();
		// int importCustomerId = 0;
		int customerId = Integer.parseInt(verifyInfo.getCustomerId());

		ImportCustomerInfoResDao importInfo = importCustomerInfoRepository.findAllByCustomerNo(customerNo);
		
		//Get Name for updated_by, created_by.
		CustomerInfoReqDao customerInfoReqDao0 = 
				customerInfoRepository.findAllByCustomerId(customerId);

		// (3) update CUSTOMER_INFO.
		CustomerInfoReqDao customerInfoReqDao = new CustomerInfoReqDao();
		customerInfoReqDao = customerInfoRepository.findAllByCustomerId(customerId);
		customerInfoReqDao.setCustomerNo(customerNo);
		customerInfoReqDao.setCustomerTypeId(Integer.parseInt(CommonConstant.CUST_TYPE));
		customerInfoReqDao.setPhoneNo(CommonUtils.modifyInvalidPhone(importInfo.getPhoneNo()));
		customerInfoReqDao.setDateOfBirth(importInfo.getDateOfBirth());
		customerInfoReqDao.setNrcNo(importInfo.getNrcNo());
		customerInfoReqDao.setSalary(Double.parseDouble(importInfo.getSalary()));
		customerInfoReqDao.setCompanyName(importInfo.getCompanyName());
		customerInfoReqDao.setAddress(importInfo.getAddress());
		customerInfoReqDao.setGender(importInfo.getGender());
		customerInfoReqDao.setName(importInfo.getName());
		customerInfoReqDao.setUpdatedBy(customerInfoReqDao0.getName());
		customerInfoReqDao.setPhotoPath(imgFileName);
		customerInfoReqDao.setUpdatedTime(CommonUtils.getCurrentTimeStamp());
		customerInfoReqDao = customerInfoRepository.save(customerInfoReqDao);
		log.info("------------ update success! | customer_info_id : " + customerInfoReqDao.getCustomerId());

		// (4) insert to MEMBER_INFO with 'BROWN' type.
		MemberInfoReqDao memberInfoReqDao = new MemberInfoReqDao();
		memberInfoReqDao.setCustomerId(customerId);
		memberInfoReqDao.setMemberTypeId(CommonConstant.MEMBER_TYPE_BROWN_ID); // BROWN
		memberInfoReqDao.setCreatedTime(CommonUtils.getCurrentTimeStamp());
		memberInfoReqDao.setCreatedBy(customerInfoReqDao0.getName()); // CUSTOMER_TYPE
		memberInfoReqDao = memberInfoRepository.save(memberInfoReqDao);
		log.info("------------ insert success! | member_id : " + memberInfoReqDao.getMemberId());

		// (5) get CUST_AGREEMENT_LIST list.
		List<CustAgreementListDto> custAgreementListDtoList;
		CustAgreementListDto custAgreementListDto;
		List<CustAgreementListResDao> custAgreementListResDaoList =
				// custAgreementListRepository.findAllByImportCustomerIdAndDelFlag(importInfo.getImportCustomerInfoId(),
				// CommonConstant.FLAG_ON);
				custAgreementListRepository.findAllByQrShowNotAndImportCustomerId(CommonConstant.QR_OFF,
						importInfo.getImportCustomerInfoId());

		if (custAgreementListResDaoList != null && custAgreementListResDaoList.size() > 0) {

			custAgreementListDtoList = new ArrayList<>();

			for (CustAgreementListResDao custAgreementListResDao : custAgreementListResDaoList) {
				custAgreementListDto = new CustAgreementListDto();
				custAgreementListDto.setAgreementNo(custAgreementListResDao.getAgreementNo());
				custAgreementListDto.setQrShow(String.valueOf(custAgreementListResDao.getQrShow()));
				custAgreementListDto.setCustAgreementId(String.valueOf(custAgreementListResDao.getCustAgreementId()));
				custAgreementListDto.setImportCustomerId(String.valueOf(custAgreementListResDao.getImportCustomerId()));
				custAgreementListDto.setFinancialAmt(String.valueOf(custAgreementListResDao.getFinancialAmt()));
				custAgreementListDto.setFinancialTerm(String.valueOf(custAgreementListResDao.getFinancialTerm()));
				custAgreementListDtoList.add(custAgreementListDto);
			}

		} else {
			throw new MemberRegistrationServiceException("no customer agreement list found");
		}
		
		//get app_usage_detail_id.
		

		// get current registration user info.
		CurrentUserInformationResBean currentUserInformationResBean = new CurrentUserInformationResBean();
		int userId = Integer.parseInt(verifyInfo.getCustomerId());
		int userTypeId = 3; // CUSTOMER_TYPE

		currentUserInformationResBean.setPassword(getCustomerPwd(userId, userTypeId));
		currentUserInformationResBean.setCustomerId(customerInfoReqDao.getCustomerId());
		currentUserInformationResBean.setCustomerTypeId(customerInfoReqDao.getCustomerTypeId());
		currentUserInformationResBean.setUserTypeId(customerInfoReqDao.getUserTypeId());
		currentUserInformationResBean.setCustomerNo(customerInfoReqDao.getCustomerNo());
		currentUserInformationResBean.setPhoneNo(customerInfoReqDao.getPhoneNo());
		currentUserInformationResBean.setName(customerInfoReqDao.getName());
		currentUserInformationResBean
				.setDateOfBirth(CommonUtils.getChangeDatetoString(customerInfoReqDao.getDateOfBirth()));
		currentUserInformationResBean.setNrcNo(customerInfoReqDao.getNrcNo());
		currentUserInformationResBean.setPhotoPath(SFTPConstantInfo.PHOTO_PATH + customerInfoReqDao.getPhotoPath());
		currentUserInformationResBean.setCustAgreementListDtoList(custAgreementListDtoList);
		log.info("------------ verification success! | customer_info_id : " + customerInfoReqDao.getCustomerId());
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. verify_new_user.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. verify_new_user.");
		}

		return CompletableFuture.completedFuture(currentUserInformationResBean);
	}
	

	@Async
	public CompletableFuture<VerifyNewRegisteredUserInfoResBean> checkToVerifyMember(
			VerifyNewRegisteredUserInfoReqBean verifyNewRegUserInfoReqBean) throws MemberRegistrationServiceException {

		VerifyNewRegisteredUserInfoResBean verifyNewRegUserInfoResBean = new VerifyNewRegisteredUserInfoResBean();

		Date dateOfBirth = CommonUtils.getChangeStringToDate(verifyNewRegUserInfoReqBean.getDateOfBirth());
		String nrcNo = verifyNewRegUserInfoReqBean.getNrcNo();
		String agreementNo = verifyNewRegUserInfoReqBean.getAgreementNo();
		int customerId = Integer.parseInt(verifyNewRegUserInfoReqBean.getCustomerId());
		String customerNo;

		List<ImportCustomerInfoResDao> importCustomerInfoResDaoList = importCustomerInfoRepository
				.findAllByDateOfBirthAndNrcNoAndDelFlag(dateOfBirth, nrcNo, CommonConstant.FLAG_ON);
		
		try{
			Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
			log.info("----------------- | Thread sleep for 1 second. check_to_verify_new_user.");
		} catch (InterruptedException e) {
			// TODO: handle exception
			log.info("----------------- | Inturrepted Exception Occurred. check_to_verify_new_user.");
		}

		ImportCustomerInfoResDao importCustomerInfoResDao;
		if (importCustomerInfoResDaoList != null && importCustomerInfoResDaoList.size() > 0) {
			importCustomerInfoResDao = new ImportCustomerInfoResDao();
			importCustomerInfoResDao = importCustomerInfoResDaoList.get(0);

			// Get Customer Agreement List.
			int importCustomerId = importCustomerInfoResDao.getImportCustomerInfoId();
			List<CustAgreementListResDao> custAgreementListResDaoList =
					// custAgreementListRepository.findAllByImportCustomerIdAndDelFlag(importCustomerId,
					// CommonConstant.FLAG_ON);
					custAgreementListRepository.findAllByQrShowNotAndImportCustomerId(CommonConstant.QR_OFF,
							importCustomerId);

			if (custAgreementListResDaoList != null && custAgreementListResDaoList.size() > 0) {
				boolean isExisted = false;
				for (CustAgreementListResDao custAgreementListResDao : custAgreementListResDaoList) {
					if (custAgreementListResDao.getAgreementNo().equals(agreementNo)) {
						isExisted = true;
						break;
					}
				}

				if (isExisted) {
					customerNo = importCustomerInfoResDao.getCustomerNo();

					// Check NRC and DOB on CUSTOMER_INFO
					List<CustomerInfoReqDao> customerInfoReqDaoList = customerInfoRepository.findAllByCustomerIdNotAndNrcNo(customerId,importCustomerInfoResDao.getNrcNo());
					if (customerInfoReqDaoList != null && customerInfoReqDaoList.size() > 0) {
						verifyNewRegUserInfoResBean.setCustomerNo(CommonConstant.BLANK);
						verifyNewRegUserInfoResBean.setStatusMessage(CommonConstant.MEMBER_INVALID_ERROR);
						verifyNewRegUserInfoResBean.setStatusCode(CommonConstant.STATUS_200);
						verifyNewRegUserInfoResBean.setVerifyStatus(CommonConstant.NOT_VALID);

						return CompletableFuture.completedFuture(verifyNewRegUserInfoResBean);
					} else {
						log.info("------------ Check import phone no on customer_info");
						CustomerInfoReqDao customerInfoReqDao = customerInfoRepository
								.findAllByCustomerIdNotAndPhoneNo(customerId, importCustomerInfoResDao.getPhoneNo());

						try {
							Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
							log.info("----------------- | Thread sleep for 1 second. check_import_phone_duplication.");
						} catch (InterruptedException e) {
							// TODO: handle exception
							log.info(
									"----------------- | Inturrepted Exception Occurred. check_import_phone_duplication.");
						}

						if(customerInfoReqDao != null) {
							log.info("------------ Check import phone no on customer_info | duplicate");

							verifyNewRegUserInfoResBean.setCustomerNo(customerNo);
							verifyNewRegUserInfoResBean.setStatusMessage(CommonConstant.IMPORT_PH_DUPLICATE);
							verifyNewRegUserInfoResBean.setStatusCode(CommonConstant.STATUS_200);
							verifyNewRegUserInfoResBean.setVerifyStatus(CommonConstant.NOT_VALID);
							
						} else {log.info("------------ Check import phone no on customer_info | No duplicate");

							verifyNewRegUserInfoResBean.setCustomerNo(customerNo);
							verifyNewRegUserInfoResBean.setStatusMessage(CommonConstant.OK);
							verifyNewRegUserInfoResBean.setStatusCode(CommonConstant.STATUS_200);
							verifyNewRegUserInfoResBean.setVerifyStatus(CommonConstant.VALID);
						}
						return CompletableFuture.completedFuture(verifyNewRegUserInfoResBean);
					}
				} else {
					verifyNewRegUserInfoResBean.setCustomerNo(CommonConstant.BLANK);
					verifyNewRegUserInfoResBean.setStatusMessage(CommonConstant.AGREEMENT_NO_WRONG_ERROR);
					verifyNewRegUserInfoResBean.setStatusCode(CommonConstant.STATUS_200);
					verifyNewRegUserInfoResBean.setVerifyStatus(CommonConstant.NOT_VALID);

					return CompletableFuture.completedFuture(verifyNewRegUserInfoResBean);
					// throw new MemberRegistrationServiceException("no agreement number
					// exception");
				}

			} else {
				verifyNewRegUserInfoResBean.setCustomerNo(CommonConstant.BLANK);
				verifyNewRegUserInfoResBean.setStatusMessage(CommonConstant.AGREEMENT_NO_WRONG_ERROR);
				verifyNewRegUserInfoResBean.setStatusCode(CommonConstant.STATUS_200);
				verifyNewRegUserInfoResBean.setVerifyStatus(CommonConstant.NOT_VALID);

				return CompletableFuture.completedFuture(verifyNewRegUserInfoResBean);
				// throw new MemberRegistrationServiceException("no agreement number
				// exception");
			}
		} else {
			verifyNewRegUserInfoResBean.setCustomerNo(CommonConstant.BLANK);
			verifyNewRegUserInfoResBean.setStatusMessage(CommonConstant.MEMBER_INVALID_ERROR);
			verifyNewRegUserInfoResBean.setStatusCode(CommonConstant.STATUS_200);
			verifyNewRegUserInfoResBean.setVerifyStatus(CommonConstant.NOT_VALID);

			return CompletableFuture.completedFuture(verifyNewRegUserInfoResBean);
			// throw new MemberRegistrationServiceException("check member exception");
		}
	}
	

	// get customer_type_id.
	public int getCustomerTypeId(String curCustomerType) throws MemberRegistrationServiceException {
		int customerTypeId = 0;
		List<CustomerTypeResDao> customerTypeResDaoList = new ArrayList<>();
		customerTypeResDaoList = (List<CustomerTypeResDao>) customerTypeRepository
				.findAllByCustomerType(curCustomerType);
		for (CustomerTypeResDao customerTypeResDao : customerTypeResDaoList) {
			if (customerTypeResDao.getCustomerType().equals(curCustomerType)) {
				customerTypeId = customerTypeResDao.getCustomerTypeId();
				break;
			}
		}
		log.info("------------ | customer_type_id : " + customerTypeId);
		return customerTypeId;
	}

	// get user_type_id
	public int getUserTypeId(String curUserType) throws MemberRegistrationServiceException {
		int userTypeId = 0;
		List<UserTypeResDao> userTypeResDaoList = new ArrayList<>();
		userTypeResDaoList = (List<UserTypeResDao>) userTypeRepository.findAllByUserType(curUserType);
		for (UserTypeResDao userTypeResDao : userTypeResDaoList) {
			if (userTypeResDao.getUserType().equals(curUserType)) {
				userTypeId = userTypeResDao.getUserTypeId();
				break;
			}
		}
		log.info("------------ | user_type_id : " + userTypeId);
		return userTypeId;
	}

	// get password
	public String getCustomerPwd(int userId, int userTypeId) {
		String password = null;
		PasswordInfoReqDao passwordInfoReqDao = new PasswordInfoReqDao();
		PasswordInfoReqDao passwordInfoReqDaoList = passwordInfoRepository.findAllByUserIdAndUserTypeId(userId,
				userTypeId);
		passwordInfoReqDao = passwordInfoReqDaoList;
		password = passwordInfoReqDao.getPassword();
		// need encryptions for password.
		log.info("------------ | password : " + password);
		return password;
	}

	// check agreement no. in registration info.
	public boolean checkAgreementList(int importCustId, String agreementNo) {
		log.info("------------ | Check Agreement List.");
		boolean validationStatus = false;
		List<CustAgreementListResDao> custAgreementList = new ArrayList<>();
		// custAgreementList =
		// custAgreementListRepository.findAllByImportCustomerIdAndDelFlag(importCustId,CommonConstant.FLAG_ON);
		custAgreementList = custAgreementListRepository.findAllByQrShowNotAndImportCustomerId(CommonConstant.QR_OFF,
				importCustId);
		for (CustAgreementListResDao custAgreementListResDao : custAgreementList) {
			if (custAgreementListResDao.getAgreementNo().equals(agreementNo)) {
				validationStatus = true;
				break;
			}
		}
		return validationStatus;
	}

	public String getHotlinePhone() {
		CompanyInfoResDao companyInfoResDao = companyInfoRepository.findAllByCompanyInfoId(1);
		return companyInfoResDao.getHotLinePhone();
	}

}
