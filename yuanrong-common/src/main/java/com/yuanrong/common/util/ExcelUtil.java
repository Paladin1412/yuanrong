package com.yuanrong.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.*;

/**
 * Created by zhonghang on 2017/2/27.
 */
public class ExcelUtil {

    public static void createdExcel(String path, String fileName , String sheetName, List<String> title , Vector<Vector<Object>> content){
        writer(path,fileName.replace(".xlsx","").replace(".xls","") , "xlsx" , sheetName , title , content);
    }



    private static void writer(String path, String fileName, String fileType , String sheetName, List<String> title , Vector<Vector<Object>> content) {
        File file = new File(path);
        if(!file .exists()  && !file .isDirectory()){
            file.mkdir();
        }
        //创建文件流
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(path + fileName +"."+ fileType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                //写入数据
                created(null ,fileType, sheetName, title, content).write(stream);
                //关闭文件流
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Workbook created(Workbook wb,String fileType , String sheetName, List<String> title , Vector<Vector<Object>> content) {
//创建工作文档对象
        if(wb == null){
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook();
            } else if(fileType.equals("xlsx")) {
                wb = new SXSSFWorkbook();
            } else {
                new RuntimeException("您的文档格式不正确");
            }
        }

        //创建sheet对象
        Sheet sheet1 = (Sheet) wb.createSheet(sheetName);
        CellStyle contextstyle = wb.createCellStyle();
        //写入标题
        Row rowTitle = (Row) sheet1.createRow(0);

        //设置加粗
        CellStyle setBorder = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        setBorder.setFont(font);
        for (int j = 0; j < title.size(); j++) {
            Cell cell = rowTitle.createCell(j);
            cell.setCellValue(title.get(j) );
            cell.setCellStyle(setBorder);
        }

        //循环写入行数据
        for (int i = 1; i <=content.size(); i++) {
            Row row = (Row) sheet1.createRow(i);
            //循环写入列数据
            for (int j = 0; j < title.size(); j++) {
                Cell contentCell = row.createCell(j);
//                contentCell.setCellValue(content.get(i-1)[j]);
                Object data ;
                if(j < content.get(i-1).size()){
                    data = content.get(i-1).get(j);
                }else{
                    data = "";
                }

                Boolean isNum = false;//data是否为数值型
                Boolean isInteger=false;//data是否为整数
                Boolean isPercent=false;//data是否为百分数
                if (data != null || "".equals(data)) {
                    //判断data是否为数值型
                    isNum = data.toString().matches("^(-?\\d+)(\\.\\d+)?$");
                    //判断data是否为整数（小数部分是否为0）
                    isInteger=data.toString().matches("^[-\\+]?[\\d]*$");
                    //判断data是否为百分数（是否包含“%”）
                    isPercent=data.toString().contains("%");
                }

                //如果单元格内容是数值类型，涉及到金钱（金额、本、利），则设置cell的类型为数值型，设置data的类型为数值类型
                if (isNum && !isPercent) {
                    DataFormat df =  wb.createDataFormat(); // 此处设置数据格式
                    if (isInteger) {
                        contentCell.setCellValue(data.toString());
//                        contextstyle.setDataFormat(df.getFormat("#,#0"));//数据格式只显示整数
                    }else{
                        contextstyle.setDataFormat(df.getFormat("#,##0.000"));//保留两位小数点
                        // 设置单元格格式
                        contentCell.setCellStyle(contextstyle);
                        // 设置单元格内容为double类型
                        contentCell.setCellValue(Double.parseDouble(data.toString()));
                    }

                } else {
                    contentCell.setCellStyle(contextstyle);
                    // 设置单元格内容为字符型
                    contentCell.setCellValue(data == null ? "" : data.toString());
                }


            }
        }
        return wb;
    }

    public static List<List<String>> read(String path, String fileName, String fileType) throws IOException {
        return read(path , fileName , fileType , 0);
    }
    public static List<List<String>> read(String path, String fileName, String fileType , int sheetNum) throws IOException {
        InputStream stream = new FileInputStream(path+fileName+"."+fileType);
        Workbook wb ;
        if (fileType.equals("xls")) {
            wb = new HSSFWorkbook(stream);
        }else if (fileType.equals("xlsx")) {
            wb = new XSSFWorkbook(stream);
        }else {
            new RuntimeException("您输入的excel格式不正确");
            return null;
        }

        Sheet sheet = wb.getSheetAt(sheetNum);


       /* for (Row row : sheet) {
            List<String> ro = new ArrayList<String>();
            for (Cell cell : row) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                ro.add(cell.getStringCellValue());
            }
            result.add(ro);
        }*/
        return readSheet(sheet);
    }

    public static List<List<String>> read(MultipartFile file , int sheetNum) throws IOException {
        InputStream stream = file.getInputStream();
        Workbook wb = new XSSFWorkbook(stream);
        Sheet sheet = wb.getSheetAt(sheetNum);
        return readSheet(sheet);
    }

    private static List<List<String>> readSheet(Sheet sheet){
        List<List<String>> result = new ArrayList<List<String>>();
        for(int i = (sheet.getFirstRowNum()); i <=(sheet.getPhysicalNumberOfRows()-1); i++){
            Row firstRow = sheet.getRow(0);
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> ro = new ArrayList<String>();
            for (int j = firstRow.getFirstCellNum(); j <= firstRow.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if(cell == null){
                    ro.add("");
                    continue;
                }else{
                    int cellType = cell.getCellType();
                    if(cellType == Cell.CELL_TYPE_BLANK){
                        ro.add("");
                    }else{
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        ro.add(cell.getStringCellValue());
                    }
                }
            }
            result.add(ro);
        }
        return result;
    }



    /**
     * 验证IP文件格式
     * userID        Ip名称      IP粉丝        IP简介        IP全网发布报价（元）
     * 必填           必填        选填          选填                 选填
     * 00001       陈翔六点半   5678888888    视频原创PGC             1
     * CellType 类型 值
     * CELL_TYPE_NUMERIC 数值型 0
     * CELL_TYPE_STRING 字符串型 1
     * CELL_TYPE_FORMULA 公式型 2
     * CELL_TYPE_BLANK 空值 3
     * CELL_TYPE_BOOLEAN 布尔型 4
     * CELL_TYPE_ERROR 错误 5
     * @param pathFile
     * @return
     * @throws IOException
     */
    public static Map<String, Object> vilidateFormat(String pathFile , String fileType) throws IOException{
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray erroList = new JSONArray();
        List<List<String>> result = new ArrayList<List<String>>();
        try {
            InputStream stream = new FileInputStream(pathFile);
            Workbook wb = null;

            if (fileType.equals(".xls")) {
                wb = new HSSFWorkbook(stream);
            }else if (fileType.equals(".xlsx")) {
                wb = new XSSFWorkbook(stream);
            }
            Sheet sh = wb.getSheetAt(0);
            for(int i = sh.getFirstRowNum(); i <=(sh.getPhysicalNumberOfRows()-1); i++){
                Row firstRow = sh.getRow(0);
                Row row = sh.getRow(i);
                if (row == null) {
                    continue;
                }
                List<String> ro = new ArrayList<String>();
                for (int j = firstRow.getFirstCellNum(); j < firstRow.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if((j == 0 || j == 1) && (cell==null || "".equals(cell))){
                        if(j == 0){
                            JSONObject erroInfo = new JSONObject();
                            erroInfo.put("row", i+1);
                            erroInfo.put("column", j+1);
                            erroInfo.put("erroMsg", "userID必填，不能为空");
                            erroList.add(erroInfo);
                        }
                        if(j == 1){
                            JSONObject erroInfo = new JSONObject();
                            erroInfo.put("row", i+1);
                            erroInfo.put("column", j+1);
                            erroInfo.put("erroMsg", "Ip名称必填，不能为空");
                            erroList.add(erroInfo);
                        }
                    }
                    if(cell == null){
                        ro.add("");
                        continue;
                    }else{
                        int cellType = cell.getCellType();
                        if(cellType == Cell.CELL_TYPE_BLANK){   //空值
                            ro.add("");
                        }else{
                            if(cellType == Cell.CELL_TYPE_STRING && i !=0){ //数值型(表头除外)
                                if(j==2){
                                    JSONObject erroInfo = new JSONObject();
                                    erroInfo.put("row", i+1);
                                    erroInfo.put("column", j+1);
                                    erroInfo.put("erroMsg", "IP粉丝为数值类型");
                                    erroList.add(erroInfo);
                                }
                                if(j==4){
                                    JSONObject erroInfo = new JSONObject();
                                    erroInfo.put("row", i+1);
                                    erroInfo.put("column", j+1);
                                    erroInfo.put("erroMsg", "IP全网发布报价（元）为数值类型");
                                    erroList.add(erroInfo);
                                }
                            }
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            ro.add(cell.getStringCellValue());
                        }
                    }
                }
                result.add(ro);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(erroList !=null && erroList.size() > 0){
            map.put("erroList",erroList);
        }
        map.put("result",result);
        return map;
    }

}
