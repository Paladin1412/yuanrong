
/*
*
*上传
*
**/
//获取token
var qnTokenUrl = domain + '/upload/uploadToken';

//上传domain（上传时使用）
var qnUploadUrl = 'http://up-z1.qiniup.com/';

//http://files.yuanrongbank.com/

//上传文件存储地址（拼接file name时使用）
var qnStorageDomain = 'http://cdn.yuanrongbank.com/';

//登录可见
var loginAvailable = '登录可见';

// 公用方法
var commonFun = {
	//创作者——图文、短视频详情页面名称匹配
	pageNameReg: function (name){
		if(name){
			var reg = new RegExp(/\b(author_images)\b|\b(video_auther)\b|\b(content_bank)\b|\b(publish_demand)\b|\b(graphics_writer)\b|\b(ip_trade)\b|\b(ip_trade_distribution)\b|\b(ip_evaluation)\b|\b(about_us)\b|\b(ip_ranking_article)\b|\b(ip_ranking_creation)\b|\b(article_detail)\b|\b(ip_data_description)\b|\b(demand_hall)\b|\b(cart_author)\b|\b(cart_article)\b|\b(cart_distribution)\b|\b(original_work)\b|\b(demand_publish)\b|\b(author_detail)\b|\b(unifiedOrder)\b|\b(unifiedorder)\b|\b(paySuccess)\b|\b(paysuccess)\b|\b(enterDetailPage)\b|\b(segment_account)\b|\b(play_around)\b/ig);
			var matchName = '';
			if(name != ''){
				var name = name.toLowerCase();
				var newName = name.replace(/_[0-9]/ig, '');
				matchName = newName.match(reg);
				return matchName;
			}
		}
	},
	//获取url中的页面名称
	getUrlPageName: function (url) {
		var curUrl = window.location.pathname;
		var dotPostion = curUrl.indexOf('.');
		if(dotPostion != -1){
			var splitArr = curUrl.split('/'),
			splitArrLen = splitArr.length,
			pageName = splitArr[splitArrLen-1];
			return pageName;
		} else {
			return null;
		}
	},
	//去除input前后空格
	trim: function (str) {
		return String(str).replace(/^\s*/, '').replace(/\s*$/, '');
	},
	//设置Cookies
	setCookie: function (name, value, expir) {
		var cookie = name + '=' + encodeURIComponent(value);
		if (expir !== void 0) {
			var now = new Date();
			now.setDate(now.getDate() + ~~expir);
			cookie += '; expires=' + now.toGMTString();
		}
		cookie += '';
		document.cookie = cookie;
	},
	//获取Cookies
	getCookie: function (name) {
		var ret = new RegExp('(?:^|[^;])' + name + '=([^;]+)').exec(document.cookie);
		return ret ? decodeURIComponent(ret[1]) : '';
	},
	/**
	* function:        		获取url？后面的参数
	* @param ''    			''
	* @returns{string}  	
	*/
	getUrlParam: function () {
		var url = location.search;
		// console.log(url,',,url')
		if(url){
			var theRequest = new Object();
			if (url.indexOf("?") != -1) {
				var str = url.substr(1);
				strs = str.split("&");
				for (var i = 0; i < strs.length; i++) {
					// theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
					theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
				}
			}
			return theRequest;
		}
	},
	/**
	* function:        		ajax请求
	* @param url    		请求地址
	* @param type    		请求类型
	* @param AjaxData    	传递参数，类型为对象
	* @param fnSuccess    	成功回调
	* @param fnError    	错误回调
	* @returns{string}  	
	*/
	
	// X-Requested-With:XMLHttpRequest,
	commonAjax: function (url, type, AjaxData, fnSuccess, fnError) {
		$.ajax({
			url: url,
			type: type,
			dataType: 'json',
			cache: true,
			xhrFields: {
				withCredentials: true
			},
			headers: {
				'X-Requested-With': 'XMLHttpRequest'
			},
			crossDomain: true,
			traditional: true,
			data: AjaxData,
			success: fnSuccess,
			error: fnError
		})
	},	
	/**
	* function:        		给后台传参时，去掉空的键名
	* @param json			json为传入的给后台的json数据 
	* @returns{string}   	返回值为对象
	*/
	delEmptyData: function(json) {
		for (var i in json) {
			if (json[i] === '' || json[i] === undefined || json[i] === null) {
				delete json[i];
			}
			if(typeof json[i] === "object" && Object.prototype.toString.call(json[i]).toLowerCase() == "[object object]" && !json[i].length){
				for (var k in json[i]) {
					if (json[i][k] === '' || json[i][k] === undefined || json[i][k] === null) {
						delete json[i][k];
					}
				}
				if(jQuery.isEmptyObject(json[i])){
					delete json[i];
				}
			}
		}
		return json;
	},
	/**
	* function:        列表数字千分位转化
	* @param number    被转化数字
	* @param places    保留小数位数,默认保留两位小数位
	* @param symbol    以什么符号开头,默认是'¥ '
	* @returns{string} 返回值为转换后的货币字符串
	*/
	formatNumber: function (number, places, symbol) {
		number = number || 0;
		places = !isNaN(places = Math.abs(places)) ? places : 2;//Math.abs();返回绝对值
		symbol = symbol !== undefined ? symbol : "¥";
		thousand = ",";
		decimal = ".";
		var negative = number < 0 ? "-" : "",
			i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
			j = (j = i.length) > 3 ? j % 3 : 0,
			decimalNum = '';
			if(places){ 
				if(Math.abs(number - i).toFixed(places).slice(2) == 0){
					decimalNum = '';
				} else {
					decimalNum = decimal + Math.abs(number - i).toFixed(places).slice(2);
				}
			} else {
				decimalNum = '';
			}
		var numberFormat = symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + decimalNum;
		return numberFormat;
	},
	//获取userAgent
	getUserAgentFun: function(){
		var au = navigator.userAgent;
		return {     //移动终端浏览器版本信息
			trident: au.indexOf('Trident') > -1, //IE内核
			presto: au.indexOf('Presto') > -1, //opera内核
			webKit: au.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
			gecko: au.indexOf('Gecko') > -1 && au.indexOf('KHTML') == -1, //火狐内核
			mobile: !!au.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
			ios: !!au.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
			android: au.indexOf('Android') > -1 || au.indexOf('Linux') > -1, //android终端或uc浏览器
			iPhone: au.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
			iPad: au.indexOf('iPad') > -1, //是否iPad
			webApp: au.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
		}
	}(),
	// 验证手机号
	telValid: function (val) {
		var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[4-9]{1})|(18[0-9]{1})|(199)|(166))+\d{8})$/;
		if (reg.test(val)) {
			return true;
		}else{
			return false;
		}
	},
	// 验证QQ号
	qqValid: function (val) {
		var reg = /^[1-9][0-9]{4,12}$/;
		if(reg.test(val)){
			return true;
		}else{
			return false;
		}
	},
	// 邮箱验证
	mailValid: function (val) {
		var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(reg.test(val)){
			return true;
		}else{
			return false;
		}
	},
	//验证汉字
	chineseValid: function (val) {
		var reg = /^[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*$/;///^[\u4E00-\u9FFF]+$/g
		if(reg.test(val)){
			return true;
		}else{
			return false;
		}
	},
	//身份证
	idCardValid: function (val) {
		var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;
		if(reg.test(val)){
			return true;
		}else{
			return false;
		}
	}
}


