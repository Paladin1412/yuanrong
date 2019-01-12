package com.yuanrong.common.module.shortmessage;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSSend {
	/// <summary>
	public static int GetevenNum(double num1,double num2){  
		int s=(int)num1+(int)(Math.random()*(num2-num1));  
		return s;  
		}  
		 
	public static void main(String[] args) {
		boolean flag1 = isMobile("19910380222");

		System.out.println(StringUtils.equalsIgnoreCase("",null));
		int num1=0;
		int num2=9;
		//System.out.println("任意一个"+num1+"～"+num2+"之间的数："+GetevenNum(num1,num2));

		int n1 = GetevenNum(num1,num2);
		int n2 = GetevenNum(num1,num2);
		int n3 = GetevenNum(num1,num2);
		int n4 = GetevenNum(num1,num2);
		//int n5 = GetevenNum(num1,num2);
		//int n6 = GetevenNum(num1,num2);
		String smsCode=n1+""+n2+""+n3+""+n4;
		System.out.println(smsCode);

		String MessageCotent="验证码 "+smsCode +" 有效期为5分钟，验证码仅用于本次圆融账号注册、登录使用,如非本人操作请忽略，本条信息免费。";
		try {
			 MessageCotent = URLEncoder.encode(MessageCotent, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String phonelist="";
		//sendSMS(MessageCotent,phonelist);
		boolean flag = isMobile(phonelist);
		System.out.println(flag);
	}

	public static String sendSMS(String MessageCotent,String phonelist) {
		//System.out.println(url);
		String str=null;
		try {
			long currentTime = System.currentTimeMillis();
			currentTime=currentTime/1000;
			String PassKey = HttpUtils.sendGet("http://api.sys.xingyuanauto.com/sms/GetPassSecret?appid=10&ticket="+currentTime);
			PassKey=PassKey.replace("\"", "");
			//System.out.println(PassKey);
			
			
			//&SendUserId=''&SendUserIp=''
			String url = "http://api.sys.xingyuanauto.com/sms/SendSMS?appid=10&passkey="+PassKey+"&notecount=1&phonelist="+phonelist+"&t="+currentTime+"&noteContent="+MessageCotent+"";
			str = HttpUtils.sendGet(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return str;
		}
		
	}

	/**
	 * 手机号验证
	 * @author ：shijing
	 * 2016年12月5日下午4:34:46
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(final String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		//p = Pattern.compile("^[1][0-9][0-9]{8}$"); // 验证手机号
		p = Pattern.compile("^((\\(\\d{3}\\))|(\\d{3}\\-))?1[3,4,5,6,7,8,9][0-9]\\d{8}?$|15[89]\\d{8}?$"); // 验证手机号

		m = p.matcher(str);
		b = m.matches();
		return b;
	}
	/**
	 * 是否发送成功
	 * @author      ShiLinghuai
	 * @param
	 * @return
	 * @exception
	 * @date        2018/5/2 10:44
	 */
	public static boolean isSendSuccess(final String rst) {
		if(rst!=null && !"".equals(rst)&&!"null".equals(rst)&&rst.contains("{\"result\":\"True\",\"message\":\"")) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 获取四位随机数
	 * @author      ShiLinghuai
	 * @param
	 * @return
	 * @exception
	 * @date        2018/5/2 10:44
	 */
	public static String getRandom() {
		int num1=0;
		int num2=9;
		int n1 = GetevenNum(num1,num2);
		int n2 = GetevenNum(num1,num2);
		int n3 = GetevenNum(num1,num2);
		int n4 = GetevenNum(num1,num2);
		String smsCode=n1+""+n2+""+n3+""+n4;
		return smsCode;
	}
	/**
	 * 获取四位随机数
	 * @author      ShiLinghuai
	 * @param
	 * @return
	 * @exception
	 * @date        2018/5/2 10:44
	 */
	public static String getRandomSix() {
		int num1=0;
		int num2=9;
		int n1 = GetevenNum(num1,num2);
		int n2 = GetevenNum(num1,num2);
		int n3 = GetevenNum(num1,num2);
		int n4 = GetevenNum(num1,num2);
		int n5 = GetevenNum(num1,num2);
		int n6 = GetevenNum(num1,num2);
		String smsCode=n1+""+n2+""+n3+""+n4+""+n5+""+n6;
		return smsCode;
	}
}
