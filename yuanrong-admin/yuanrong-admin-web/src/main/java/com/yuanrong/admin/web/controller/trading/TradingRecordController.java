package com.yuanrong.admin.web.controller.trading;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumChannel;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.bean.trading.TradingRecord;
import com.yuanrong.admin.result.TradingRecordResult;
import com.yuanrong.admin.seach.TradingRecordSearch;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.ExcelUtil;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "tradingRecord_list", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(TradingRecordSearch tradingRecordSearch, BaseModel baseModel, HttpSession session) {
        tradingRecordSearch.setWebUser(getWebUser(session));
        PageInfo<TradingRecordResult> tradingRecordPageInfo = tradingRecordServicesI.tradingRecordList(tradingRecordSearch , baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(tradingRecordPageInfo.getList()) > 0){
            for (TradingRecordResult tradingRecordResult: tradingRecordPageInfo.getList()
                    ) {
                result.add(TradingRecordResult.packageTradingRecordFront(tradingRecordResult));
            }
        }
        return new ResultTemplate(tradingRecordPageInfo , result);
    }

    @RequestMapping("tradingRecord_save")
    public String save(TradingRecord user) {

        return "trading/systemUser_save";
    }

    @RequestMapping(value = "tradingRecord_saveOk", method = RequestMethod.POST)
    public void saveOk(TradingRecord theTradingRecord) {
        tradingRecordServicesI.save(theTradingRecord);
    }

    @RequestMapping("tradingRecord_update")
    public String update() {
        return "trading/tradingRecord_update";
    }

    @RequestMapping(value = "tradingRecord_updateOk", method = RequestMethod.POST)
    public void updateOk() {
    }

    /**
     * 验证TradingRecord Excel文件
     *   交易执行时间	卖方交易账号  交易所涉平台	  交易服务内容
     *  交易金额（元）  合作品牌	   买方	          数据来源
     * @param file
     * @return
     */
    @RequestMapping(value="tradingRecord_vilidate" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate vilidateTradingRecordExcel(MultipartFile file,HttpSession session){
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
            JSONArray errorList = new JSONArray();
            JSONArray rightList = new JSONArray();
            for(int i=0; i<result.size();i++){
                // 判断格式信息
                JSONObject rightInfo = new JSONObject();
                boolean formateFlag = true;
                //若格式正确，添加正确信息
                if(formateFlag){
                    rightInfo.put("tradingDate",result.get(i).get(0));//交易执行时间
                    rightInfo.put("sellerAccount",result.get(i).get(1));//卖方交易账号
                    rightInfo.put("referPlatform",result.get(i).get(2));//交易所涉平台
                    rightInfo.put("servicesContent",result.get(i).get(3));//交易服务内容
                    rightInfo.put("money",result.get(i).get(4));//交易金额（元）
                    rightInfo.put("cooPerationBrand",result.get(i).get(5));//合作品牌
                    rightInfo.put("buyerName",result.get(i).get(6));//买方
                    rightInfo.put("channelIndex", EnumChannel.前台创建.getIndex());//来源、渠道
                    rightInfo.put("heir","用户自行");//上传人
                    rightInfo.put("createdTime", DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));//创建时间
                    rightInfo.put("registeredUserInfoId",getWebUser(session).getRecID());//注册用户Id
                    rightInfo.put("nickName",registeredUserInfoService.getById(getWebUser(session).getRecID()).getNickName());
                    rightList.add(rightInfo);
                }
            }
            info.put("rightList",rightList);
            info.put("erroList",errorList);
            if(errorList == null || errorList.size()<=0){//讲正确数据信息添加到Session中
                getSession().setAttribute("face_tradingRecordList",rightList);
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
    @RequestMapping(value = "saveBatchTradingRecord",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate saveImportTradingRecord(){
        JSONArray rightList =(JSONArray)  getSession().getAttribute("face_tradingRecordList");
        if(rightList == null){
            return  new ResultTemplate("数据有问题",null );
        }
        tradingRecordServicesI.saveGetKey(rightList);
        getSession().removeAttribute("face_tradingRecordList");
        return  new ResultTemplate("",null);
    }
}
