package com.yuanrong.common.util;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.common.oss.CloudStorageConfig;
import com.yuanrong.common.oss.CloudStorageFactory;
import com.yuanrong.common.oss.CloudStorageServiceAbstract;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/*
 *@author songwq
 *@date 2018/9/28
 *@time 21:03
 *@description
 */
public class Word2String {
    //https://blog.csdn.net/xiaobudaics/article/details/51744936
    private static final Logger logger = Logger.getLogger(Word2String.class);

    /**
     * 封装word导入结果
     * @param cloudStorageConfig
     * @return
     */
    public static JSONObject packageResult(CloudStorageConfig cloudStorageConfig,MultipartFile file) throws IOException, TransformerException, ParserConfigurationException {
        JSONObject returnObject = new JSONObject();
        String html="";
        String qiniuyunFilePath = "";
        //初始化
        CloudStorageServiceAbstract ossServices = CloudStorageFactory.build(cloudStorageConfig);
        if(!(file.getOriginalFilename().endsWith(".docx")
                ||  file.getOriginalFilename().endsWith(".DOCX")
                ||  file.getOriginalFilename().endsWith(".doc")
                ||  file.getOriginalFilename().endsWith(".DOC"))){
            returnObject.put("error","导入文件的格式不对，导入的文件必须是word格式的文档");
            return  returnObject;
        }

        //文档上传七牛云
        qiniuyunFilePath = ossServices.upload(file.getInputStream(),System.currentTimeMillis()+"/"+file.getOriginalFilename());

        logger.info("七牛云文档地址---》"+qiniuyunFilePath);
        //将上传的word文件转化为string
        //String html= Word2String.getFile(sourceFilePath,files[i].getOriginalFilename(), saveImgPath);
        html= Word2String.getFile(file.getInputStream(),file.getOriginalFilename(),ossServices);
        html = html.replaceAll("white-space:pre-wrap","");//删除此样式
        logger.info("文档内容---》"+html);

        returnObject.put("qiniuyunFilePath",qiniuyunFilePath);
        returnObject.put("html",html);
        return returnObject;
    }

    /**
     *@author songwq
     *        sourceFilePath 原文件路径
     *        saveImgPath 目标图片存储路径
     *        fileName 文件名称
     *@date 2018/10/15
     *@description
     */
    public static String getFile(InputStream is,String fileName,CloudStorageServiceAbstract ossServices) throws IOException, ParserConfigurationException, TransformerException {
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        String content="";
        if (fileName.endsWith(".docx") || fileName.endsWith(".DOCX")) {
            content = Word2String.docx(is, name + ".html",ossServices);
        } else {
            content = Word2String.doc(is, name + ".html",ossServices);
        }

        content = content.replaceAll("&ldquo", "“");
        content = content.replaceAll("&rdquo", "”");
        content = content.replaceAll("&nbsp", " ");
        content = content.replaceAll("&#39", "'");
        content = content.replaceAll("&rsquo", "’");
        content = content.replaceAll("&mdash", "—");
        content = content.replaceAll("&ndash", "–");
        return content;
//        }
    }
    /**
     * 转换docx	 * @param filePath	 * @param fileName	 * @param htmlName	 * @throws Exception
     */
    public static String docx(InputStream in, String htmlName,CloudStorageServiceAbstract ossServices) throws IOException {
        XWPFDocument document = new XWPFDocument(in);

        // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
        XHTMLOptions options = XHTMLOptions.create().indent( 4 );

        final String[] abc = new String[1];
        options.setImageManager(new ModifyImagePath(null,null,ossServices));

        options.setIgnoreStylesIfUnused(false);
        options.setFragment(true);

        //也可以使用字符数组流获取解析的内容
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(document, baos, options);//获取文件内容
        String content ="<html><body>"+baos.toString()+"</body></html>";//不加这两个标签ueditor没有格式

        if(content!=null && content.length()>0){
            content=decode(content);
        }
        baos.close();
        return content;

    }
    /**
     * 转换doc	 * @param filePath	 * @param fileName	 * @param htmlName	 * @throws Exception
     */
    public static String doc(InputStream input,  String htmlName,CloudStorageServiceAbstract ossServices) throws IOException, ParserConfigurationException, TransformerException {
        HWPFDocument wordDocument = new HWPFDocument(input);
//        XSSFWorkbook xssfwork=new XSSFWorkbook(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        //解析word文档
        //设置图片存放的位置
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                return ossServices.upload(content,System.currentTimeMillis()+"/"+suggestedName);
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();

//        File htmlFile = new File(htmlName);
//        OutputStream outStream = new FileOutputStream(htmlFile);
        DOMSource domSource = new DOMSource(htmlDocument);
//        StreamResult streamResult = new StreamResult(outStream);

        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");

        //serializer.transform(domSource, streamResult);
        serializer.transform(domSource, result);
        String content = writer.getBuffer().toString();
        if(content!=null && content.length()>0){
            content=decode(content);
        }
//        outStream.close();
//        String content = splitContext(htmlName);
        // 删除生成的html文件
        //htmlFile.delete();
        return content;
    }

    /**
     *@author songwq
     *@param
     *@date 2018/10/9
     *@description 将&#开头的编码换转成中文
     */
    public static String decode(String str){
        String[] tmp = str.split(";&#|&#|;");
        StringBuffer sb = new StringBuffer("");
        for (int i=0; i<tmp.length; i++ ){
            if (tmp[i].matches("\\d{5}")){
                sb.append((char)Integer.parseInt(tmp[i]));
            } else {
                sb.append(tmp[i]);
            }
        }
        return sb.toString();
    }
}
