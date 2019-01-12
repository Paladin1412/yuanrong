package com.yuanrong.common;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.common.util.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by zhonghang on 2018/5/18.
 */
public class AllController {
    private static final Logger logger = Logger.getLogger(AllController.class);
    /**
     * 获得session
     *
     * @return
     */
    public HttpSession getSession() {
        return session;
    }

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        this.request.setAttribute("mdc_version" , "V1.7.0.370");
    }

    public  void download(String fileName,Workbook wb) {
        try {
            String agent = request.getHeader("USER-AGENT");
            if (agent != null && agent.indexOf("MSIE") == -1) {
                String enableFileName = "=?UTF-8?B?" + new String(Base64.encodeBase64(fileName.getBytes("UTF-8"))) + "?=";
                response.setHeader("Content-Disposition", "attachment; filename=" + enableFileName);
            } else {
                response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            }
            wb.write(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 下载作品——word
     * @param fileName
     * @param poifs
     */
    public  void download(String fileName, POIFSFileSystem poifs) {
        try {
            String agent = request.getHeader("USER-AGENT");
            if(agent.toLowerCase().contains("safari") && !agent.contains("chrome")) {
                //处理safari的乱码问题
                byte[] bytesName = fileName.getBytes("UTF-8");
                fileName = new String(bytesName, "ISO-8859-1");
                response.setHeader("content-disposition", "attachment;fileName=" + fileName);
            }else if (agent != null && agent.indexOf("MSIE") == -1) {
                String enableFileName = "=?UTF-8?B?" + new String(Base64.encodeBase64(fileName.getBytes("UTF-8"))) + "?=";
                response.setHeader("Content-Disposition", "attachment; filename=" + enableFileName);
            } else {
                response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            }
            poifs.writeFilesystem(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
