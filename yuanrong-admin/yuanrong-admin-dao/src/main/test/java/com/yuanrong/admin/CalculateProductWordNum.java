package com.yuanrong.admin;

import cn.hutool.core.io.FileUtil;
import com.yuanrong.common.util.ExcelUtil;
import com.yuanrong.common.util.FilterHtmlUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhonghang on 2018/08/15.
 */
public class CalculateProductWordNum {
    public static void main(String[] args) throws IOException {
        List<List<String>> result =  ExcelUtil.read("C:\\Users\\zhongh\\Desktop\\","无标题" , "xlsx",0);
        for (List<String> ele: result ) {
            int id = Integer.parseInt(ele.get(0));
            int wordNum = FilterHtmlUtil.Html2Text(ele.get(1)).length();
            int imgNum = ele.get(1).split("<img").length -1 ;
            FileUtil.appendString(id +"\t" + wordNum + "\t" + imgNum +"\n" , "d:\\test.txt" , "utf-8");
            System.out.println(id +"\t" + wordNum + "\t" + imgNum);
        }
    }
}
