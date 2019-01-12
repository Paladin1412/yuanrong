package com.yuanrong.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertiesTools {

	private static Logger logger = LoggerFactory.getLogger(PropertiesTools.class);

	public static Properties PROPERTIES = new Properties();

	public static String getClassFilePath(Class classObject) throws UnsupportedEncodingException {
		String basePath = classObject.getProtectionDomain().getCodeSource().getLocation().getPath();
		basePath = URLDecoder.decode(basePath, "utf-8");
		if (basePath.endsWith(".jar")) {
			basePath = basePath.substring(0, basePath.lastIndexOf("/") + 1);
		}
		File f = new File(basePath);
		basePath = f.getAbsolutePath();
		return basePath;
	}

	/**
	 * @param config_path
	 *            资源文件名
	 * @return 从资源文件中读取配置文件路径
	 */
	public static String getFolderPath(String config_path) {
		String filePath = null;
		InputStream in;
		try {
			// 返回jar包所在路径
			/*filePath = getClassFilePath(PropertiesTools.class);
			// 用于处理web路径，获取根目录
			if (filePath.indexOf("/WEB-INF/classes") != -1) {
				filePath = filePath.substring(0, filePath.indexOf("/WEB-INF/classes") + "/WEB-INF/classes".length());
			}
			logger.info("filePath = " + filePath);
			filePath = filePath + config_path;
			logger.info("filePath = " + filePath);*/
			in = new BufferedInputStream(new FileInputStream(config_path));
			PROPERTIES.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return filePath;
		}

	}
	public static void getFolderPath(InputStream in) {
		try {
			PROPERTIES.load(in);
			in.close();
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}
}
