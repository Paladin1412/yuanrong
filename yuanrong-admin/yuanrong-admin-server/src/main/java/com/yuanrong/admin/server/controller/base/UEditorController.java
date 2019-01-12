package com.yuanrong.admin.server.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.server.controller.BaseController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


/**
 * Created by zhonghang on 2018/5/26.
 */
@Controller
@RequestMapping("ueditor")
public class UEditorController extends BaseController {
    private static final Logger logger = Logger.getLogger(UEditorController.class);
  /*  @Value(value="${ueditor}")    //后台图片保存地址
    private String ueditor;

    @Value(value="${uploadHost}")
    private String uploadHost;    //项目host路径
*/


    /**
     * ueditor文件上传（上传到外部服务器）
     * @param request
     * @param response
     * @param action
     */
    @ResponseBody
    @RequestMapping(value="/exs_ueditorUpload", method={RequestMethod.GET, RequestMethod.POST})
    public ResultTemplate editorUpload(HttpServletRequest request, HttpServletResponse response, String action , MultipartFile upfile) {
        response.setContentType("application/json");
        try {
            if("config".equals(action)){    //如果是初始化
                org.springframework.core.io.Resource res = new ClassPathResource("ueditorConfig.json");
                InputStream is = null;
                response.setHeader("Content-Type" , "text/html");
                try {
                    is = new FileInputStream(res.getFile());
                    StringBuffer sb = new StringBuffer();
                    byte[] b = new byte[1024];
                    int length=0;
                    while(-1!=(length=is.read(b))){
                        sb.append(new String(b,0,length,"utf-8"));
                    }
                    String result = sb.toString().replaceAll("/\\*(.|[\\r\\n])*?\\*/","");
                    JSONObject json = JSON.parseObject(result);
                    return new ResultTemplate(json.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else if("uploadimage".equals(action) || "uploadvideo".equals(action) || "uploadfile".equals(action)|| "catchimage".equals(action)){    //如果是上传图片、视频、和其他文件
                try {
                   /* MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
                    MultipartHttpServletRequest Murequest = resolver.resolveMultipart(request);
                    Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
*/
//                    for(MultipartFile pic: upfile){
                        JSONObject jo = new JSONObject();
                        long size = upfile.getSize();    //文件大小
                        String originalFilename = upfile.getOriginalFilename();  //原来的文件名
                        InputStream inputStream = upfile.getInputStream();
                        FileInputStream fileInputStream = (FileInputStream) inputStream;
//                        YRFastDFSUtil yrFastDFSUtil = new YRFastDFSUtil();
//                        String filePath = getRequest().getSession().getServletContext().getRealPath("/WEB-INF/classes/conf/fdfs_client.properties" );
                        String imgUrl = YRFastDFSUtil.upload(fileInputStream);
                        jo.put("state", "SUCCESS");
                        jo.put("original", originalFilename);//原来的文件名
                        jo.put("size", size); //文件大小
                        jo.put("title", originalFilename); //随意，代表的是鼠标经过图片时显示的文字
                        jo.put("type", FilenameUtils.getExtension(upfile.getOriginalFilename())); //文件后缀名
                        jo.put("url", imgUrl);//这里的url字段表示的是上传后的图片在图片服务器的完整地址（http://ip:端口/***/***/***.jpg）
                        return  new ResultTemplate(jo.toString());
//                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }else if("listimage".equals(action)){
                return  new ResultTemplate("获取失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new ResultTemplate("获取失败");
    }
}
