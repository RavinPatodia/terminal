package com.langmy.terminal.common.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 向Ftp服务器上传下载文件
 * 
 * @author lxj
 *
 */
public class FtpTransfer {
	
	private static Logger logger = LoggerFactory.getLogger(FtpTransfer.class);
	
	/** 
	*  上传
	*  
	* @param url 
	*            FTP服务器hostname 
	* @param port 
	*            FTP服务器端口 
	* @param username 
	*            FTP登录账号 
	* @param password 
	*            FTP登录密码 
	* @param path 
	*            FTP服务器保存目录 
	* @param filename 
	*            上传到FTP服务器上的文件名 
	* @param input 
	*            输入流 
	* @return 成功返回true，否则返回false 
	*/  
	public static boolean uploadFile(String url, int port, String username,  
	            String password, String path, String filename, InputStream input)  
	            throws Exception {  
		boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    ftp.connect(url,port);// 连接FTP服务器  
	    int reply;  
	    ftp.login(username, password);// 登录
	    // 设置PassiveMode传输  
	    ftp.enterLocalPassiveMode();  
	    // 设置以二进制流的方式传输  
	    ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);  
	    ftp.setFileType(FTP.BINARY_FILE_TYPE);  
	    reply = ftp.getReplyCode();  
	    if (!FTPReply.isPositiveCompletion(reply)) {  
	    	ftp.disconnect();  
	        logger.error("----------->>>连接ftp服务器失败");  
	        throw new Exception("----------->>>连接ftp服务器失败");  
	    }  
	    logger.info("-----连接ftp服务器成功");  
	    boolean isChangeWork = ftp.changeWorkingDirectory(path);  
	    if (!isChangeWork) {  
	    	boolean isMade = ftp.makeDirectory(path);  
	        if (!isMade) {  
	        	throw new IOException("ftp 上传文件创建目录失败");  
	        }  
	        isChangeWork = ftp.changeWorkingDirectory(path);  
	    }  
	    ftp.storeFile(filename, input);  
	    ftp.logout();  
	    success = true;  
	    logger.info("----------->>>文件上传成功");  
	    if (ftp.isConnected()) {  
	    	try {  
	    		ftp.disconnect();  
	        }catch (IOException ioe) {  
	        	logger.error("----------->>>ftp连接关闭失败 " + ioe.getMessage());  
	        }  
	    }  
	    return success;  
    }  
	 
	/** 
	 * 下载，下载还没用到
	*  
	* @param url 
	*            FTP服务器hostname 
	* @param port 
	*            FTP服务器端口 
	* @param username 
	*            FTP登录账号 
	* @param password 
	*            FTP登录密码 
	* @param path 
	*            FTP服务器保存目录 
	* @param filename 
	*            FTP服务器下载上的文件名 
	* @param input 
	*            输入流 
	* @return 成功返回true，否则返回false 
	*/  
	public static boolean downloadFile(String url, int port, String username,  
	          String password, String path, String filename, OutputStream out)  
	          throws Exception {  
		boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    ftp.connect(url, port);// 连接FTP服务器  
	    int reply;  
        // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	    ftp.login(username, password);// 登录  
	    // 设置PassiveMode传输  
	    ftp.enterLocalPassiveMode();  
	    // 设置以二进制流的方式传输  
	    ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);  
	    ftp.setFileType(FTP.BINARY_FILE_TYPE);  
	    reply = ftp.getReplyCode();  
	    if (!FTPReply.isPositiveCompletion(reply)) {  
	    	ftp.disconnect();  
	    	logger.error("----------->>>连接ftp服务器失败");  
            // throw new Exception("----------->>>连接ftp服务器失败");  
	    }  
        logger.info("-----连接ftp服务器成功");  
	    boolean isChangeWork = ftp.changeWorkingDirectory(path);  
	    if (!isChangeWork) {  
	    	throw new IOException("ftp 目录不存在");  
	    }  
	    InputStream input = ftp.retrieveFileStream(filename);  
	    int buf = -1;  
	    while ((buf = input.read()) != -1) {  
	    	out.write(buf);  
	    }  
	    out.flush();  
	    input.close();  
	    ftp.logout();  
	    logger.info("----------->>>文件下载成功");  
	    if (ftp.isConnected()) {  
	    	try {  
	    		ftp.disconnect();  
	        } catch (IOException ioe) {  
	        	logger.error("----------->>>ftp连接关闭失败 " + ioe.getMessage());  
	        }  
	    }  
        return success;  
	}  

}
