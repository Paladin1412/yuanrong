package com.yuanrong.common.util;

//import com.base.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;


public class Tools {
	private static String BASIC_DATA_SOURCE = null;
	public static Properties PROPERTIES = new Properties();     
	protected transient static Logger log = LoggerFactory.getLogger(Tools.class);
	public static boolean isNull( String type_str) {
		Boolean flag= false;
		if(type_str==null|| "null".equals(type_str)||"".equals(type_str)){
			flag=true;
		
		}
		return flag;
	}
	public static void putUserName(HttpServletRequest request, Map<String, Object> map) {
		HttpSession session = request.getSession();
		//String userName = String.valueOf(session.getAttribute(Constant.sessionMdcName));
		//map.put("userName", userName);
	}
	public static void initBaseConf_(String config) {
		if (BASIC_DATA_SOURCE == null) {
			synchronized(Tools.class) {
				//log.info("init config");
				
		        InputStream in;
				try {
					//返回jar包所在路
					String filePath = FileUtil.getClassFilePath(Tools.class);
					//用于处理web路径，获取根目录
					if (filePath.indexOf("/WEB-INF/classes") != -1) {
						filePath = filePath.substring(0, filePath.indexOf("/WEB-INF/classes") + "/WEB-INF/classes".length());
					}
					log.debug("filePath = " + filePath);
					filePath = filePath + config;
					log.debug("filePath = " + filePath);
					
					in = new BufferedInputStream (new FileInputStream(filePath));
					PROPERTIES.load(in);
					in.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
	public static void initBaseConf(String config) {
		if (BASIC_DATA_SOURCE == null) {
			synchronized(Tools.class) {
				//log.info("initConnectionPool");
				
		        InputStream in;
				try {
					//返回jar包所在路
					String filePath = FileUtil.getClassFilePath(Tools.class);
					//用于处理web路径，获取根目录
					if (filePath.indexOf("/WEB-INF/classes") != -1) {
						filePath = filePath.substring(0, filePath.indexOf("/WEB-INF/classes") + "/WEB-INF/classes".length());
					}
					//log.info("filePath = " + filePath);
					filePath = filePath + config;
					//log.info("filePath = " + filePath);
					
					in = new BufferedInputStream (new FileInputStream(filePath));
					PROPERTIES.load(in);
					in.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				
			}
		}
	}

	
	public static String  getDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
		String format = df.format(new Date());
		System.out.println();// new Date()为获取当前系统时间
		return format;
	}
	/**
	 * 获取配置文件
	 * @return
	 */
	public static Properties getProperties(@SuppressWarnings("rawtypes") Class classObject){
		Properties basicConfig = new Properties();
		InputStream in;
		try {
			//返回jar包所在路径
			String filePath = FileUtil.getClassFilePath(classObject);
			//用于处理web路径，获取根目录
			if (filePath.indexOf("/WEB-INF/classes") != -1) {
				filePath = filePath.substring(0,
						filePath.indexOf("/WEB-INF/classes") + "/WEB-INF/classes".length());
			}
			in = new BufferedInputStream(new FileInputStream(filePath + "/config.properties"));
//			in = new BufferedInputStream(new FileInputStream("./lib.conf/config.properties"));
			basicConfig.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return basicConfig;
	}


	

}
