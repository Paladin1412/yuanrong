package com.yuanrong.admin.server.controller.trading;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumChannel;
import com.yuanrong.admin.Enum.EnumUserRoleLicenseStatus;
import com.yuanrong.admin.bean.account.ShortVideoPlatformInfo;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.bean.trading.TradingRecord;
import com.yuanrong.admin.result.TradingRecordResult;
import com.yuanrong.admin.seach.TradingRecordSearch;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.ExcelUtil;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 交易记录的controller
 * Created MDA
 */
@Controller
@RequestMapping("trading")
public class TradingRecordController extends BaseController {
    private static final Logger logger = Logger.getLogger(TradingRecordController.class);
    /**
    * 交易记录列表
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/8/20 16:35
    */
    @RequestMapping( value = "tradingRecord_list" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(TradingRecordSearch tradingRecordSearch , BaseModel baseModel){
        PageInfo<TradingRecordResult> tradingRecordPageInfo = tradingRecordServicesI.tradingRecordList(tradingRecordSearch , baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(tradingRecordPageInfo.getList()) > 0){
            for (TradingRecordResult tradingRecordResult: tradingRecordPageInfo.getList()
                 ) {
                result.add(TradingRecordResult.packageTradingRecord(tradingRecordResult));
            }
        }
        return new ResultTemplate(tradingRecordPageInfo , result);
    }

    /**
    * 交易记录详情
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/8/20 16:36
    */
    @RequestMapping("tradingRecord_getByID")
    public String getByID(TradingRecord user){
        return "trading/systemUser_save";
    }
    /**
    * 交易记录更改
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/8/20 16:36
    */
    @RequestMapping("tradingRecord_update")
    @ResponseBody
    public ResultTemplate update(TradingRecordSearch tradingRecord){
        if(tradingRecord.getTradingRecordId()==null){
            return new ResultTemplate("id为0！",null);
        }
        tradingRecordServicesI.update(tradingRecord);
        return new ResultTemplate("",null);
    }
    /**
    *批量操作
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/8/20 16:36
    */
    @RequestMapping("tradingRecord_batch")
    @ResponseBody
    public ResultTemplate batch(TradingRecordSearch tradingRecordSearch){
        if(tradingRecordSearch.getTodo()==null){
            return new ResultTemplate("TODO参数未传！",null);
        }
        if(tradingRecordSearch.getTradingRecordIds()==null){
            return new ResultTemplate("ID未传",null);
        }else {
            if(tradingRecordSearch.getTradingRecordIds().length<=0){
                return new ResultTemplate("ID未传",null);
            }
        }
        //开始业务处理
        tradingRecordServicesI.batch(tradingRecordSearch);
        return new ResultTemplate("",null);
    }
    /**
     * 验证TradingRecord Excel文件
     *   用户简称	    交易执行时间	卖方交易账号  交易所涉平台	  交易服务内容
     *  交易金额（元）  合作品牌	   买方	          数据来源	     上传人
     * @param file
     * @return
     */
    @RequestMapping(value="exs_tradingRecord_vilidate" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate vilidateTradingRecordExcel(MultipartFile file){
        if(file == null){
            return new ResultTemplate("文件信息为空！",null);
        }

        try {
            String fileName = file.getOriginalFilename();
            if(!".xlsx".equals(fileName.substring(fileName.indexOf(".")))){
                return new ResultTemplate("文件格式有问题！" , null);
            }
            List<List<String>> result = ExcelUtil.read(file,0);
            if(CollectionUtil.size(result)<=1){
                return new ResultTemplate("数据不存在！",null);
            }
            result.remove(0);//删除表头
//            result.remove(0);//删除提示信息
            JSONObject info = new JSONObject();
            JSONArray rightList = new JSONArray();
            JSONArray errorList = new JSONArray();
            AdminUser adminUser =(AdminUser) getSession().getAttribute("admin");
            for(int i=0; i<result.size();i++){
                // 判断格式信息
                JSONObject rightInfo = new JSONObject();
                boolean formateFlag = true;
                //若格式正确，添加正确信息
                if(formateFlag){
                    rightInfo.put("nickName",result.get(i).get(0));//用户简称
                    rightInfo.put("tradingDate",result.get(i).get(1));//交易执行时间
                    rightInfo.put("sellerAccount",result.get(i).get(2));//卖方交易账号
                    rightInfo.put("referPlatform",result.get(i).get(3));//交易所涉平台
                    rightInfo.put("servicesContent",result.get(i).get(4));//交易服务内容
                    rightInfo.put("money",result.get(i).get(5));//交易金额（元）
                    rightInfo.put("cooPerationBrand",result.get(i).get(6));//合作品牌
                    rightInfo.put("buyerName",result.get(i).get(7));//买方
                    rightInfo.put("channelIndex", EnumChannel.后台创建.getIndex());//来源、渠道
                    rightInfo.put("heir",adminUserServicesI.getById(adminUser.getRecID()).getRealName());//上传人
                    rightInfo.put("createdTime", DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));//创建时间
                    rightList.add(rightInfo);
                }
            }
            info.put("rightList",rightList);
            info.put("erroList",errorList);
            if(errorList == null || errorList.size()<=0){//讲正确数据信息添加到Session中
                getSession().setAttribute("tradingRecordList",rightList);
            }
            return new ResultTemplate("",info);
        } catch (Exception e) {
            return  new ResultTemplate("文件读取失败！",null);
        }
    }

    /**
     * 批量插入交易记录tradingRecord(通过Excel)
     * @return
     */
    @RequestMapping(value = "exs_saveBatchTradingRecord",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate saveImportTradingRecord(){
        JSONArray rightList =(JSONArray)  getSession().getAttribute("tradingRecordList");
        if(rightList == null){
            return  new ResultTemplate("数据有问题",null );
        }
        tradingRecordServicesI.saveGetKey(rightList);
        getSession().removeAttribute("tradingRecordList");
        return  new ResultTemplate("",null);
    }
    /**
     *数据来源
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/8/20 16:36
     */
    @RequestMapping("exs_tradingRecord_channel")
    @ResponseBody
    public ResultTemplate channel(){
        return new ResultTemplate("", EnumChannel.getMapInfo());
    }
    /**
     *涉及平台
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/8/20 16:36
     */
    @RequestMapping("exs_platform")
    @ResponseBody
    public ResultTemplate plat(){
        List<ShortVideoPlatformInfo> result = platformIPAccountServicesI.getPlatFormInfo();
        if(CollectionUtil.size(result) > 0){

            return new ResultTemplate("" , ShortVideoPlatformInfo.packToTrading(result));
        }else{
            return new ResultTemplate("" , null);
        }    }
}
