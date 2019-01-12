package com.yuanrong.admin.web.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.resources.UserImages;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.YRFastDFSUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;


/**
 * Created by zhonghang on 2018/5/26.
 */
@Controller
@RequestMapping("ueditor")
public class UEditorController extends BaseController {
    private static final Logger logger = Logger.getLogger(UEditorController.class);
    /**
     * ueditor文件上传（上传到外部服务器）
     * @param request
     * @param response
     * @param action
     */
    @ResponseBody
    @RequestMapping(value="/ueditorUpload", method={RequestMethod.GET, RequestMethod.POST})
    public ResultTemplate editorUpload(HttpServletRequest request, HttpServletResponse response, String action , MultipartFile upfile) {
        try {
            if("config".equals(action)){    //如果是初始化
                org.springframework.core.io.Resource res = new ClassPathResource("ueditorConfig.json");
                InputStream is = null;
                response.setHeader("Content-Type" , "text/html");
                is = new FileInputStream(res.getFile());
                StringBuffer sb = new StringBuffer();
                byte[] b = new byte[1024];
                int length=0;
                while(-1!=(length=is.read(b))){
                    sb.append(new String(b,0,length,"utf-8"));
                }
                String result = sb.toString().replaceAll("/\\*(.|[\\r\\n])*?\\*/","");
                JSONObject json = JSON.parseObject(result);
                is.close();
                return new ResultTemplate(json);
            }else if("uploadimage".equals(action)){    //如果是上传图片、视频、和其他文件
                try {
                    JSONObject jo = new JSONObject();
                    long size = upfile.getSize();    //文件大小
                    String originalFilename = upfile.getOriginalFilename();  //原来的文件名
                    InputStream inputStream = upfile.getInputStream();
                    FileInputStream fileInputStream = (FileInputStream) inputStream;
                    String imgUrl = YRFastDFSUtil.upload(fileInputStream);
                    jo.put("state", "SUCCESS");
                    jo.put("original", originalFilename);//原来的文件名
                    jo.put("size", size); //文件大小
                    jo.put("title", originalFilename); //随意，代表的是鼠标经过图片时显示的文字
                    jo.put("url", imgUrl);//这里的url字段表示的是上传后的图片在图片服务器的完整地址（http://ip:端口/***/***/***.jpg）
                    jo.put("type", FilenameUtils.getExtension(upfile.getOriginalFilename())); //文件后缀名
                    //保存图片用户图片关系
                    UserImages userImages = new UserImages();
                    userImages.setImgUrl(imgUrl);
                    userImages.setWebUser(getWebUser(request.getSession()));
                    userImagesServicesI.save(userImages);
                    return  new ResultTemplate(jo.toString());
                }catch (Exception e) {
                    logger.error(e);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return new ResultTemplate("处理失败");
    }
}
