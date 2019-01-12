package com.yuanrong.admin.server.controller.upload;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.YRFastDFSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/exs_upload",method = RequestMethod.POST)
	@ResponseBody
	private ResultTemplate imageByFile(MultipartFile file){
		if(file == null){
			return new ResultTemplate("文件为空",null);
		}
		try {
			JSONObject result = new JSONObject();
			String filePath = YRFastDFSUtil.upload(file.getInputStream());
			result.put("fileUrl", filePath);
			return new ResultTemplate("" , result);
		}catch (Exception e){
			logger.error("FastDFS文件上传，失败原因",e);
			return new ResultTemplate("上传失败，请稍后重试！" ,null);
		}

	}

	@RequestMapping(value="/exs_uploadToken")
	@ResponseBody
	public String getUploadToken(){
		return uploadServicesI.getUploadToken();
	}
}
