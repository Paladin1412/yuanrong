package com.yuanrong.admin.server.controller.order;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.order.SnapshotYrProduction;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
/**
 * 作品快照的controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class SnapshotYrProductionController extends BaseController {
    private static final Logger logger = Logger.getLogger(SnapshotYrProductionController.class);

    @RequestMapping( value = "snapshotYrProduction_saveOk" , method = RequestMethod.POST)
    public void saveOk(SnapshotYrProduction theSnapshotYrProduction){
        snapshotYrProductionServicesI.save(theSnapshotYrProduction);
    }

}
