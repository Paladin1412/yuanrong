package com.yuanrong.common.util;

import java.util.regex.Pattern;

/**
 * @author makaidong
 * @Date 14-05-28
 */
public class Constant {
	public static final String VERIFY_CODE = "verifyCode";
	 Pattern pattern = Pattern.compile("(大波.*|脱.*衣|浪|学生.*|騒.*|护士.*|铯秀.*|骚.*|騒.*|淫.*)妹" +
			    "|包养|性交|兽交|骚妞|浪臊妇|壹夜情|奶头|嫩\\|逼|狠狠撸|迷奸|铯钕|操逼|操逼|一夜晴|裸体|熟妇|騒女|" +
			    "色女|屄图|操淫|甩奶|挿\\|逼|情色网|淫女|露b|小穴|爱爱|做爱|色情|一夜情|三陪|求插|粿聊|倮聊|铯聊|淫钕|" +
			    "騒钕|美臊妇|骚女|骚钕|色妞|荡妇|少妇|求爱| 成人视频|夜总会|援交|口交|约炮|自摸|人妻|裸聊|捆绑|大奶|虐肛|脱衣|" +
			    "包整夜|浪少|聊妞|艳舞|妹妹下面湿了|催情|毒葯|上门女|色妇|艳照|陪睡|迷魂党|迷魂葯|迷昏葯|春葯|迷葯|迷幻葯|安眠" +
			    "|蒙汗葯|失意葯|冰.*毒|小浪_逼|[假办].*证|买到银行卡|海洛因|性爱|三级小说|三级片" +
			    "|成人网站|發票|陪聊qq|骚(\\||\\s)*逼|(包|找)小(\\+|\\s)*姐|赌(\\+|\\s)*博|迷(\\+|\\s)*药|大(\\+|\\s)*麻|k(\\+|\\s)*粉|催(\\+|\\s)*情|" +
			    "迷(\\+|\\s)*药|春(\\+|\\s)*药|大(\\+|\\s)*麻|冰(\\+|\\s)*毒|开.*发票|东京热|鸡巴|黄网|性爱|色\\|妇|成人小说|求\\|爱女" +
			    "|李宗瑞|成人网|激吻|聊籹|私处|摸美女胸|曹b|撸过|美女露毛|巨乳|全身光光|脱光衣服|泷泽萝拉|黄图|肏|偷拍|色图|色爱图|淫荡|臊妇|道射|嫫籹|色妹" +
			    "|被奸|禁图|秀波女|没穿衣服|波霸妹|阴道|兽魔色欲|屁股图");
	public static String path_index = "/index";// 首页

	public static String path_list = "/list/index";// 列表
	public static String path_list_user = "/list/index_user";// 列表
	public static String map = "/map/";// 列表
	public static String path_content = "/content/index";// 内容
	
	public static String path_commons_404 = "/commons/404";
	public static String path_new = "new";
	public static String path_tui = "tui";
	public static String list_tui = "list_tui";
	public static String list_new = "list_new";
	
	public static String id_desc = "public_time desc,id desc";
	public static String id_asc= "public_time asc,id asc";
	
	public static String houzhui = ".html";
	public static Integer page_size_index_pachong=50;
	public static Integer page_size_index=20;
	public static Integer page_size_content_cat_new=20;
	 
	
	public static Integer page_size_list=20;
	public static Integer page_size_index_show_total=20;
	public static Integer page_size_list_show_total=20;

	public static String SUCCESS = "success";// 成功
	
	/**
	 * 请求执行开始时间
	 */
	public static final String START_TIME = "_start_time";
	
	public static String FAIL = "fail";// 失败
	public static String SUCCESS_MSG = "查询成功！";// 成功返回消息
	public static String FAIL_MSG = "查询失败！";// 失败返回消息
	public static String FAIL_NULL_MSG = "查询无数据或失败！";//
	public static String DOWN_MSG = "数据存在已数据，请直接下载！";
	
	public static String cookieMdcName="cookieMdcName";
	public static String sessionMdcName="sessionMdcName";
	public static String mdc_version="1.0";
	public static String zhongxian="_";
	public static String xiahuaxian="_";
	public static String xiegang="/";
	public static String fanxiegang="\\";
	public static String maohao=":";
	public static String tab="\t";

	public static String enter="\r\n";
	
	public static Integer is_true=1;
	public static Integer is_false=1;
	

	public static String uuapHost = "http://itebeta.baidu.com:8100";// uuaphost
	public static String getUuapHost() {
		return uuapHost;
	}

	public static void setUuapHost(String uuapHost) {
		Constant.uuapHost = uuapHost;
	}

	public static String getDomain() {
		return domain;
	}

	public static void setDomain(String domain) {
		Constant.domain = domain;
	}

	public static String domain = "http://127.0.0.1:8089";// mdc域名
	public static String domain_m="http://m.makaidong.com";
	
	public static String getDomain_m() {
		return domain_m;
	}

	public static void setDomain_m(String domain_m) {
		Constant.domain_m = domain_m;
	}
 
	/**
	 * 路径分隔符
	 */
	public static final String SPT = "/";
	/**
	 * 索引页
	 */
	public static final String INDEX = "index";
	/**
	 * 默认模板
	 */
	public static final String DEFAULT = "default";
	/**
	 * UTF-8编码
	 */
	public static final String UTF8 = "UTF-8";
	/**
	 * 提示信息
	 */
	public static final String MESSAGE = "message";
	/**
	 * cookie中的JSESSIONID名称
	 */
	public static final String JSESSION_COOKIE = "JSESSIONID";
	/**
	 * url中的jsessionid名称
	 */
	public static final String JSESSION_URL = "jsessionid";
	/**
	 * HTTP POST请求
	 */
	public static final String POST = "POST";
	/**
	 * HTTP GET请求
	 */
	public static final String GET = "GET";
	
	/**
	 * 工作流状态定义 : 1,通过 2,拒绝 3,审批中
	 */
	public static final Integer WORKFLOW_STATUS_ACCEPT = 1;
	public static final Integer WORKFLOW_STATUS_DENY = 2;
	public static final Integer WORKFLOW_STATUS_INPROCESS = 3;
	
	/**
	 * 工作流种类定义 : 1,数据权限申请  2,订阅流程
	 *   3:BPM数据权限申请   4: BPM订阅申请
	 */
	public static final Integer WORKFLOW_TYPE_DATA = 1;
	public static final Integer WORKFLOW_TYPE_SUBSCRIBE = 2;
	public static final Integer WORKFLOW_TYPE_BPM_DATA = 3;
	public static final Integer WORKFLOW_TYPE_BMP_SUBSCRIBE = 4;
	
	/**
	 * BPM工作流相关配置 
	 */
	public static String BPM_GWFPUserName = "NUOMI_MDC";
	public static String BPM_GWFPUserPass = "nuomimdc";
	public static String BPM_DATA_PROCESS_PACKAGE_ID = "NUOMI_MDC_pkg_8866";
	public static String BPM_DATA_PROCESS_DEFINE_ID = "NUOMI_MDC_pkg_8866_prs1";
	public static String BPM_URL_CREATE_PROCESS = "http://it.baidu.com/gwfp/api/rest/process/create";
	public static String BPM_URL_COMPLETE_ACTIVITY = "http://it.baidu.com/gwfp/api/rest/activity/complete";
	public static String BPM_URL_GET_PROCESS_ACTIVITIES = "http://it.baidu.com/gwfp/api/rest/process/activities";
	
	public static String getBPM_GWFPUserName() {
		return BPM_GWFPUserName;
	}
	
	public static void setBPM_GWFPUserName(String bPM_GWFPUserName) {
		Constant.BPM_GWFPUserName = bPM_GWFPUserName;
	}
	
	public static String getBPM_GWFPUserPass() {
		return BPM_GWFPUserPass;
	}

	public static void setBPM_GWFPUserPass(String bPM_GWFPUserPass) {
		Constant.BPM_GWFPUserPass = bPM_GWFPUserPass;
	}

	public static String getBPM_DATA_PROCESS_PACKAGE_ID() {
		return BPM_DATA_PROCESS_PACKAGE_ID;
	}

	public static void setBPM_DATA_PROCESS_PACKAGE_ID(String bPM_DATA_PROCESS_PACKAGE_ID) {
		Constant.BPM_DATA_PROCESS_PACKAGE_ID = bPM_DATA_PROCESS_PACKAGE_ID;
	}

	public static String getBPM_DATA_PROCESS_DEFINE_ID() {
		return BPM_DATA_PROCESS_DEFINE_ID;
	}

	public static void setBPM_DATA_PROCESS_DEFINE_ID(String bPM_DATA_PROCESS_DEFINE_ID) {
		Constant.BPM_DATA_PROCESS_DEFINE_ID = bPM_DATA_PROCESS_DEFINE_ID;
	}

	public static String getBPM_URL_CREATE_PROCESS() {
		return BPM_URL_CREATE_PROCESS;
	}

	public static void setBPM_URL_CREATE_PROCESS(String bPM_URL_CREATE_PROCESS) {
		Constant.BPM_URL_CREATE_PROCESS = bPM_URL_CREATE_PROCESS;
	}

	public static String getBPM_URL_COMPLETE_ACTIVITY() {
		return BPM_URL_COMPLETE_ACTIVITY;
	}

	public static void setBPM_URL_COMPLETE_ACTIVITY(String bPM_URL_COMPLETE_ACTIVITY) {
		Constant.BPM_URL_COMPLETE_ACTIVITY = bPM_URL_COMPLETE_ACTIVITY;
	}

	public static String getBPM_URL_GET_PROCESS_ACTIVITIES() {
		return BPM_URL_GET_PROCESS_ACTIVITIES;
	}

	public static void setBPM_URL_GET_PROCESS_ACTIVITIES(String bPM_URL_GET_PROCESS_ACTIVITIES) {
		Constant.BPM_URL_GET_PROCESS_ACTIVITIES = bPM_URL_GET_PROCESS_ACTIVITIES;
	}

	public final static String orderby = "orderby";
	public static String spark_url = "spark_url";

	public static String spark_driver = "jdbc_driver";
	public static String admin_msg = "jdbc_driver";
	
	public static String getAdmin_msg() {
		return admin_msg;
	}

	public static void setAdmin_msg(String admin_msg) {
		Constant.admin_msg = admin_msg;
	}

	public static String douhao = ",";
	public static String spark_data_path = "./data/";
	public static Long spark_data_max_size = 10000L;
	public static Long getSpark_data_max_size() {
		return spark_data_max_size;
	}

	public static void setSpark_data_max_size(Long spark_data_max_size) {
		Constant.spark_data_max_size = spark_data_max_size;
	}

	public static String getSpark_data_ext() {
		return spark_data_ext;
	}

	public static void setSpark_data_ext(String spark_data_ext) {
		Constant.spark_data_ext = spark_data_ext;
	}

	//spark数据下载后缀
	public static String spark_data_ext ="";
	public static String getMdc_version() {
		return mdc_version;
	}

	public static void setMdc_version(String mdc_version) {
		Constant.mdc_version = mdc_version;
	}


	//数据下载变量
	public final static String DATA_DOWN_HOST = "10.107.134.23";
	public final static int DATA_DOWN_PORT = 8230;
	public final static String DATA_DOWN_PATH = "/mdc/serviceQuery/receiveQueryMsg";
	public final static String DATA_DOWN_POI_USERNAME = "poi";
	public final static String DATA_DOWN_POI_PASSWD = "service_poi";
	
   
	public final static String USER_SESSON_KEY = "USER_SESSION";// 用户会话key
	public final static String USER_MENU_KEY = "USER_MENU";// 用户的菜单列表key

	public final static Integer STATUS_ENABLE = 1;// 正常
	public final static Integer STATUS_DISABLE = 0;// 禁用

	public final static Integer BOOLEAN_YES = 1;
	public final static Integer BOOLEAN_NO = 0;

	public final static Integer PAGE_SIZE = 10;// 分页中每页最大数据量
	public final static String ROLE_PATIENT = "patient";
	public final static String ROLE_DOCTOR = "doctor";
	public final static String ROLE_NURSE = "nurse";
	public final static String ROLE_ADMIN = "hospital_admin";
	public final static String ROLE_MONITOR_ADMIN = "monitor_admin";
	public  static String spark_data_zip = "";
	


	public static String getSpark_data_zip() {
		return spark_data_zip;
	}

	public static void setSpark_data_zip(String spark_data_zip) {
		Constant.spark_data_zip = spark_data_zip;
	}

	//public  static String AES_URL_KEY = "f5253d3e727685745c59b697babcc905"; // url加密key
	public  static String AES_URL_KEY = "34B678V2Y4E6"; // url加密key
	public  static String AES_IV = "082587654";
	public static String getAES_IV() {
		return AES_IV;
	}

	public static void setAES_IV(String aES_IV) {
		AES_IV = aES_IV;
	}

	public static String spark_password="mdc_spark_password";
	
	public static String domain_local;
	public static String proj_name;
	public static String spark_url_1;
	public static String spark_url_2;
	public static String getSpark_url_1() {
		return spark_url_1;
	}

	public static void setSpark_url_1(String spark_url_1) {
		Constant.spark_url_1 = spark_url_1;
	}

	public static String getSpark_url_2() {
		return spark_url_2;
	}

	public static void setSpark_url_2(String spark_url_2) {
		Constant.spark_url_2 = spark_url_2;
	}

	public static String getSpark_url_3() {
		return spark_url_3;
	}

	public static void setSpark_url_3(String spark_url_3) {
		Constant.spark_url_3 = spark_url_3;
	}

	public static String spark_url_3;
	public static String from_mdc=" -- <span[ style='color:red;'>from mdc web]</span>";

	public static String getFrom_mdc() {
		return from_mdc;
	}

	public static void setFrom_mdc(String from_mdc) {
		Constant.from_mdc = from_mdc;
	}

	public static String getProj_name() {
		return proj_name;
	}

	public static void setProj_name(String proj_name) {
		Constant.proj_name = proj_name;
	}

	public static String getDomain_local() {
		return domain_local;
	}

	public static void setDomain_local(String domain_local) {
		Constant.domain_local = domain_local;
	}

	public static String getAES_URL_KEY() {
		return AES_URL_KEY;
	}

	public static void setAES_URL_KEY(String AES_URL_KEY) {
		Constant.AES_URL_KEY = AES_URL_KEY;
	}

	public final static String LOGS_FUNCTION_LOGIN = "user_login"; // 日志记录登录方法名
	public static final String CREATE_FAIL = "create failed";
	public static final int page_size_list_show_total_index = 200;

	public void init() {

		//System.out.println("init = " + spark_url + " " + spark_driver + " " + ROLE_MONITOR_ADMIN);
	}

	public void close() {
		//System.out.println("close = " + spark_url + " " + spark_driver + " " + LOGS_FUNCTION_LOGIN);
	}

	public static String getSpark_url() {
		return spark_url;
	}

	public static void setSpark_url(String spark_url) {
		Constant.spark_url = spark_url;
	}

	public static String getSpark_driver() {
		return spark_driver;
	}

	public static void setSpark_driver(String spark_driver) {
		Constant.spark_driver = spark_driver;
	}

	public static String getSpark_data_path() {
		return spark_data_path;
	}

	public static void setSpark_data_path(String spark_data_path) {
		Constant.spark_data_path = spark_data_path;
	}
	
	public static String esHost = "10.107.134.24";
	public static int esPort = 8700;
	public static String esUserName = "superuser";
	public static String esPassword = "superman";
	public static String USER_INFO="user_name";
	public static String flag="json";


	
	
	public static String getEsHost() {
        return esHost;
	}

	public static void setEsHost(String esHost) {
        Constant.esHost = esHost;
	}
	
	public static int getEsPort() {
        return esPort;
    }
	
	public static void setEsPort(int esPort) {
        Constant.esPort = esPort;
    }
	
	public static String getEsUserName() {
        return esUserName;
    }

    public static void setEsUserName(String esUserName) {
        Constant.esUserName = esUserName;
    }
	
    public static String getEsPassword() {
        return esPassword;
    }

    public static void setEsPassword(String esPassword) {
        Constant.esPassword = esPassword;
    }

}
