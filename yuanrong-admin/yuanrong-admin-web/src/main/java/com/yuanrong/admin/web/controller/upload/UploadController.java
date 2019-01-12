package com.yuanrong.admin.web.controller.upload;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.Auth;
import com.yuanrong.admin.bean.resources.UserImages;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.SystemParam;
import com.yuanrong.common.util.YRFastDFSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/upload",method = RequestMethod.POST)
	@ResponseBody
	private ResultTemplate imageByFile(MultipartFile file) throws IOException {
		if(file == null){
			return new ResultTemplate("文件为空");
		}
		JSONObject result = new JSONObject();
		result.put("fileUrl", YRFastDFSUtil.upload(file.getInputStream()));
		return new ResultTemplate("" , result);
	}

	@RequestMapping(value="/uploadToken")
	@ResponseBody
	public String getUploadToken(){
		return uploadServicesI.getUploadToken();
	}

	@RequestMapping(value="/saveImage",method = RequestMethod.POST)
	@ResponseBody
	public ResultTemplate saveImage(String imgUrl, HttpSession session){
		//保存图片用户图片关系
		UserImages userImages = new UserImages();
		userImages.setImgUrl(imgUrl);
		userImages.setWebUser(getWebUser(session));
		userImagesServicesI.save(userImages);
		return new ResultTemplate();
	}
}
