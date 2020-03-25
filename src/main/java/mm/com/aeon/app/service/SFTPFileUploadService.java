/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/02 17:38:45
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.com.aeon.app.service;

import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

import mm.com.aeon.app.common.CommonConstant;
import mm.com.aeon.app.common.SFTPConstantInfo;

@Service("SFTP_FILE_UPLOAD_SERVICE")
public class SFTPFileUploadService {
	
	String serverAddress = SFTPConstantInfo.SFTP_SERVER_ADDRESS;
	int serverPort = SFTPConstantInfo.SFTP_SERVER_PORT;
	String username = SFTPConstantInfo.SFTP_USERNAME;
	String password = SFTPConstantInfo.SFTP_PASSWORD;
	
	static Logger log = Logger.getLogger(SFTPFileUploadService.class);
	
	@Async("asyncExecutor")
	public CompletableFuture<String> uploadFile(MultipartFile imgFile) 
			throws JSchException, SftpException, IOException, SocketTimeoutException, NoRouteToHostException {
		
			Session session = null;
			ChannelSftp channelSftp = null;
			String uploadStatus = CommonConstant.FILE_UPLOAD_SUCCESS;
			
			try {
				
				//(1) Connect to Host Command Prompt.
				JSch jsch = new JSch();
				session = jsch.getSession(username, serverAddress, serverPort);
				session.setPassword(password);
				Properties config = new Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config);
				session.connect();
				channelSftp = (ChannelSftp) session.openChannel(SFTPConstantInfo.CHANNEL_TYPE_SFTP);
				channelSftp.connect();
				channelSftp.cd(SFTPConstantInfo.ROOT_DIR);
				log.info("-------- | connnected to host.");
				
				//(2) Define destination path.
				String[] destinationPath = SFTPConstantInfo.destinationPath;
				List<String> destinationPathList = 
						new ArrayList<String>(Arrays.asList(destinationPath));
				
				//(3) Check and Create Directory to file save destination.
				for (String directory : destinationPathList) {
					//get current working directory.
					String currentDir = channelSftp.pwd();
					SftpATTRS sftpATTRS = null;
					
					//check directory already existed.
					try {
						if(currentDir.equals(SFTPConstantInfo.ROOT_DIR)) {
							sftpATTRS = channelSftp.stat(directory);
							log.info("command sftp : stat "+directory);
						} else {
							sftpATTRS = channelSftp.stat(currentDir+"/"+directory);
							log.info("command sftp : stat "+currentDir+"/"+directory);
						}
					} catch (Exception e) {
						// TODO: handle exception
						//directory not found.
						continue;
					} finally {
						//make directory or change to directory.
						if(sftpATTRS != null) { //if directory existed.
							channelSftp.cd(directory);
							log.info("command sftp : cd "+directory);
						} else { //if directory does not exist.
							channelSftp.mkdir(directory);
							channelSftp.cd(directory);
							log.info("command sftp : cd "+directory);
						}
					}
				}
				log.info("-------- | changed to destionation directory.");
				
				//Thread sleep (1s) for other process load.
				try {
					log.info("Thread Sleeping .....");
					Thread.sleep(CommonConstant.ASYNC_SLEEP_TIME);
				} catch (Exception e){
					e.printStackTrace();
				}
				
				//(3) Save the file to destination path.
				try {
					channelSftp.put(imgFile.getInputStream(), imgFile.getOriginalFilename(), ChannelSftp.APPEND);
					log.info("save image ["+imgFile.getOriginalFilename()+"]");
					channelSftp.chmod(Integer.parseInt(SFTPConstantInfo.ACCESS_MODE_READONLY,8), imgFile.getOriginalFilename());
					log.info("command : chmod 444 "+imgFile.getOriginalFilename());
					log.info("upload image ["+imgFile.getOriginalFilename()+"] success.");
					uploadStatus=CommonConstant.FILE_UPLOAD_SUCCESS;
				} catch (Exception e) {
					// TODO: handle exception
					log.info("upload image ["+imgFile.getOriginalFilename()+"] failed.");
				}
				
				channelSftp.disconnect();
				log.info("SFTP Channel disconnected.");
				
				session.disconnect();
				log.info("Session disconnected.");
				
				//(4) Set upload status.
				return CompletableFuture.completedFuture(uploadStatus);
				
			} finally {
				
				if (channelSftp != null && channelSftp.isConnected()) {
					channelSftp.disconnect();
					log.info("SFTP Channel disconnected.");
				}
				
				if (session != null && session.isConnected()) {
				 session.disconnect();
				 log.info("Session disconnected.");
				}
				
				log.info("Upload process finished.");
		  }
	}
}
