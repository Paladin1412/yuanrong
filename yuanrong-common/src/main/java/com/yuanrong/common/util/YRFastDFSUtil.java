package com.yuanrong.common.util;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class  YRFastDFSUtil {

	public static String group_name = "group4";
	public static String img_path = "http://img4.qcdqcdn.com/";

	public static StorageClient storageClient = null;
	public static String ip = "192.168.3.71";
	public static NameValuePair nvp[] = new NameValuePair[] { new NameValuePair("age", "18"),
			new NameValuePair("sex", "male") };

//	public String confByBatyArray(byte[] byteArray) {
//
//		// System.out.println(config_path);
//		String imgUrl = null;
//		try {
//			String config_path = "/lib.conf/fdfs_client.lib.conf";
//			config_path = PropertiesTools.getFolderPath(config_path);
//			ClientGlobal.init(config_path);
//			group_name = PropertiesTools.PROPERTIES.getProperty("group");
//			img_path = PropertiesTools.PROPERTIES.getProperty("img.path");
//			ip = PropertiesTools.PROPERTIES.getProperty("ip");
//
//			TrackerClient tracker = new TrackerClient();
//			TrackerServer trackerServer = tracker.getConnection();
//			StorageServer storageServer = null;
//			storageClient = new StorageClient(trackerServer, storageServer);
//
//			YRFastDFSUtil yRFastDFSUtil = new YRFastDFSUtil();
//			String fileIds1[] = storageClient.upload_file(group_name, byteArray, "png", nvp);
//			System.out.println(fileIds1.length);
//			System.out.println("组名：" + fileIds1[0]);
//			System.out.println("路径: " + fileIds1[1]);
//			imgUrl = ip + "/" + fileIds1[0] + "/" + fileIds1[1];
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return imgUrl;
//	}

	/**
	 * 读取本地图片
	 * 
	 * @param fileInputStream
	 * @return
	 */
	public static String getImgLocal(InputStream fileInputStream , InputStream propertiesFile) {

		// System.out.println(config_path);
		String imgUrl = null;
		try {
			  PropertiesTools.getFolderPath(propertiesFile);
			PropertiesTools.PROPERTIES.setProperty("fastdfs.tracker_servers" ,PropertiesTools.PROPERTIES.getProperty("tracker_server") );
			ClientGlobal.initByProperties(PropertiesTools.PROPERTIES);

			group_name = PropertiesTools.PROPERTIES.getProperty("group");
			img_path = PropertiesTools.PROPERTIES.getProperty("img.path");
			ip = PropertiesTools.PROPERTIES.getProperty("ip");

			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			storageClient = new StorageClient(trackerServer, storageServer);

			YRFastDFSUtil yRFastDFSUtil = new YRFastDFSUtil();
			String fileIds1[] = storageClient.upload_file(group_name, yRFastDFSUtil.getBytesByFile(fileInputStream), "png",nvp);
//			System.out.println(fileIds1.length);
//			System.out.println("组名：" + fileIds1[0]);
//			System.out.println("路径: " + fileIds1[1]);
			imgUrl = img_path + "/" + fileIds1[0] + "/" + fileIds1[1];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgUrl;
	}

//	public byte[] getBytes(String filePath) {
//		byte[] buffer = null;
//		try {
//			File file = new File(filePath);
//			FileInputStream fis = new FileInputStream(file);
//			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
//			byte[] b = new byte[1000];
//			int n;
//			while ((n = fis.read(b)) != -1) {
//				bos.write(b, 0, n);
//			}
//			fis.close();
//			bos.close();
//			buffer = bos.toByteArray();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return buffer;
//	}


	public byte[] getBytesByFile(FileInputStream fis) {
		return getBytesByFile(fis);
	}

    public byte[] getBytesByFile(InputStream fis) {
        byte[] buffer = null;
        ByteArrayOutputStream bos = null;
        try {
//			File file = new File(filePath);
//			FileInputStream fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        buffer = bos.toByteArray();
        return buffer;
    }


	public static String  upload(InputStream inputStream) throws IOException {
		String profile = SpringContextUtil.getApplicationContext().getEnvironment().getActiveProfiles()[0];
		String configFile;
		if("production".equals(profile)){
			configFile = "/conf/fdfs_client_ol.properties";
		}else{
			configFile="/conf/fdfs_client.properties";
		}
		InputStream propertiesInputStream = YRFastDFSUtil.class.getResource(configFile).openConnection().getInputStream();
		String imgUrl = YRFastDFSUtil.getImgLocal(inputStream,propertiesInputStream);
		return imgUrl;
	}
}
