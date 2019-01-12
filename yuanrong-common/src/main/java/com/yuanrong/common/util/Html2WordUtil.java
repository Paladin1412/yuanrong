package com.yuanrong.common.util;

import cn.hutool.core.io.FileUtil;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;

/*
 *@author songwq
 *@date 2018/7/9
 *@time 19:57
 *@description
 */
public class Html2WordUtil {
    /**
     * @param content word内容   fileName  生成的文件名
     * @author songwq
     * @date 2018/7/9
     * @description 將html转word
     */
    public static void htmlToWord(String content, String fileName, HttpServletRequest request, HttpServletResponse response) {
        try {
            fileName = fileName.replaceAll("/","");
            fileName = fileName.replaceAll("\\|","");
            content = content.replaceAll("<link rel=\"stylesheet\" type=\"text/css\" href=\"/static/ueditor/themes/iframe.css\">", "");
            byte b[] = content.getBytes("utf-8");  //这里是必须要设置编码的，不然导出中文就会乱码。
            ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中
            /*
             * 关键地方
             * 生成word格式
             */
            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
            //输出文件
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");//导出word格式
            response.addHeader("Content-Disposition", "attachment;filename=" +
                    new String( (fileName + ".doc").getBytes(),
                            "iso-8859-1"));

            OutputStream ostream = response.getOutputStream();
            //OutputStream fileOutputStream = new FileOutputStream(new File("D:\\a\\"+fileName+".doc"));
            //poifs.writeFilesystem(fileOutputStream);
            poifs.writeFilesystem(ostream);
            bais.close();
            //fileOutputStream.close();
            ostream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param content word内容   fileName  生成的文件流
     * @author songwq
     * @date 2018/10/17
     * @description 將html转word
     */
    public static POIFSFileSystem html2Word(String content, String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {

        fileName = fileName.replaceAll("/","");
        fileName = fileName.replaceAll("\\|","");
        content = content.replaceAll("<link rel=\"stylesheet\" type=\"text/css\" href=\"/static/ueditor/themes/iframe.css\">", "");
        byte b[] = content.getBytes();  //这里是必须要设置编码的，不然导出中文就会乱码。
        ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中
        /*
         * 关键地方
         * 生成word格式
         */
        POIFSFileSystem poifs = new POIFSFileSystem();
        DirectoryEntry directory = poifs.getRoot();
        DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
//        FileUtil.writeBytes(b,"C:\\tmp\\"+fileName+".doc");
        return poifs;
    }
}
