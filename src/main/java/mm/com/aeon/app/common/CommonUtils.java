/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/01 12:01:29
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

	/**
     * 
     * Get current Timestamp.
     * 
     * @return current Timestamp
     */
    public static Timestamp getCurrentTimeStamp() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        return timestamp;
    }
    
    /**
     * 
     * Get Timestamp From String.
     * 
     * @return Timestamp
     */
    public static Timestamp getChangeStringToTimeStamp(String date) {

        Timestamp timestamp = null;
        try {
            // Long time = System.currentTimeMillis();
            date = date.replace("/", "-");
            date = date.concat(" 00:00:00");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(date);
            timestamp = new Timestamp(parsedDate.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return timestamp;
    }
    

    /**
     * 
     * Get Timestamp From String.
     * 
     * @return Timestamp
     */
    public static Timestamp changeStringToTimeStamp(String timestampStr) {

        Timestamp timestamp = null;
        try {
            // Long time = System.currentTimeMillis();
            //date = date.replace("/", "-");
            //date = date.concat(" 00:00:00");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(timestampStr);
            timestamp = new Timestamp(parsedDate.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return timestamp;
    }
    /**
     * 
     * @param date
     * @return
     */
    public static Date getChangeStringToDate(String dateString) {
    	try {
    		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
    		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    		String changeDate = formatter2.format(formatter1.parse(dateString));
    		Date date = formatter2.parse(changeDate);  
    		return date;
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		return null;
		}
    }
    
    public static String getChangeDatetoString(Date date) {
    	try {
    		String date1=new SimpleDateFormat("yyyy-MM-dd").format(date);  
    		return date1;
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		return null;
		}
    }
    
    /**
     * 
     * @param timestamp
     * @return
     */
    public static String getChangeTimestampToString (Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String string = dateFormat.format(timestamp);
        return string;
    }
    

    /**
     * 
     * @param timestamp
     * @return
     */
    public static String getChangeTimestampToDMYString (Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String string = dateFormat.format(timestamp);
        return string;
    }
    
    /**
     * 
     * @return
     */
    public static String getCurrentTimeStringUsingDate() {
	    Date date = new Date();
	    String strDateFormat = "yyyyMMddhhmmss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
	    return formattedDate;
	}
    
    
    /**
     * 
     * @param password
     * @return
     */
    public static String checkPasswordStrength(String password) {

    	String status = CommonConstant.PWD_WEAK;
    	
    	int digit=0;
    	int character=0;
    	int specialChar=0;
    	
    	//classify alphanumeric and character counts.
    	for(int i=0;i<password.length();i++) {
    		char c = password.charAt(i);
    		if(Character.isLetter(c)) {
    			character++;
    		} else if(Character.isDigit(c)){
    			digit++;
    		} else {
    			specialChar++;
    		}
    	}
    	if(digit>0 && character>0 && specialChar>0) {
    		return CommonConstant.PWD_STRONG;
    	}
    	return status;
    }
    
    /**
     * 
     * @param phoneNo
     * @return
     */
    public static String modifyInvalidPhone(String phoneNo) {
    	String modPhoneNo=phoneNo;
    	String phoneStartChar = modPhoneNo.substring(0, 1);
    	if(phoneStartChar.equals("9")) {
    		modPhoneNo=CommonConstant.ZERO+modPhoneNo;
    		return modPhoneNo;
    	}
    	return modPhoneNo;
    }
    
}
