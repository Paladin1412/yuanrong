package com.yuanrong.common.module.shortmessage;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HttpUtils {
    protected static final int SOCKET_TIMEOUT = 10000; // 10S
    protected static final String GET = "GET";
    protected static final String POST = "POST";
  //public static final Log log = LogFactory.getLog(HttpUtils.class);
  	public static final String TAG = "HttpUtils";
  	//public static CloseableHttpClient httpClient = HttpClients.createDefault();
  	public static CloseableHttpClient httpclient = null;
  	public static HttpClientContext context = new HttpClientContext();

  	private HttpUtils() {
  		
  	}
     public static void main(String args[]){
  	   String pushUrl="http://data.zz.baidu.com/urls?site=makaidong.com&token=4z7ZHRbgerXFwAId";
  	   String myUrl="http://makaidong.com/sutao/6603_8906139.html";
         long currentTime = System.currentTimeMillis();
         currentTime=currentTime/1000;
  	   String json = HttpUtils.sendGet("http://api.sys.xingyuanauto.com/sms/GetPassSecret?appid=10&ticket="+currentTime);//执行推送方法
  	   if(json==null||"null".equals(json)||"".equals(json)||!json.contains("success")){
  		   System.out.println(json);
  	   }else{
  		   System.out.println(json);
  	   }
  	  
     }
  	public static String sendGet(String url) {
  		SSLContextBuilder builder = new SSLContextBuilder();
  	    try {
  			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
  		
  	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
  	            builder.build());
  	     httpclient = HttpClients.custom().setSSLSocketFactory(
  	            sslsf).build();
  	    
  	    } catch (Exception e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  	    }
  		CloseableHttpResponse response = null;
  		String content = null;
  		try {
  			 if(url!=null &&!"".equals(url)&&!"null".equals(url)){

  					HttpGet get = new HttpGet(url);
  				   //  Log.error(url);
  				     
  					response = httpclient.execute(get, context);
  					Header[] allHeaders = response.getAllHeaders();
  					for(Header h :allHeaders)
  					{
  						
  					//	System.out.println(h.getName()+"\t"+h.getValue());
  					}	
  					HttpEntity entity = response.getEntity();
  					content = EntityUtils.toString(entity);
  					//System.out.println(TAG + "GET:" + content);
  					EntityUtils.consume(entity);
  					return content;
  			 }
  		} catch (Exception e) {
  			e.printStackTrace();
  			
  			return null;
  		} finally {
  			if (response != null) {
  				try {
  					response.close();
  				} catch (IOException e) {
  					e.printStackTrace();
  				}
  			}
  		}
  		return content;
  		
  	}

  	public static String sendPost(String url, List<NameValuePair> nvps) {
  		
  		SSLContextBuilder builder = new SSLContextBuilder();
  	    try {
  			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
  		
  	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
  	            builder.build());
  	     httpclient = HttpClients.custom().setSSLSocketFactory(
  	            sslsf).build();
  	    } catch (Exception e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  			return null;
  	    }
  		CloseableHttpResponse response = null;
  		String content = null;
  		try {
  			 if(url!=null &&!"".equals(url)&&!"null".equals(url)){
  			// 　HttpClient中的post请求包装类
  			HttpPost post = new HttpPost(url);
  			// Log.error(url);
  			// nvps是包装请求参数的list
  			if (nvps != null) {
  				post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
  			}
  			// 执行请求用execute方法，content用来帮我们附带上额外信息
  			response = httpclient.execute(post, context);
  			Header[] allHeaders = response.getAllHeaders();
  			for(Header h :allHeaders)
  			{
  				
  				//System.out.println(h.getName()+"\t"+h.getValue());
  			}			// 得到相应实体、包括响应头以及相应内容
  			HttpEntity entity = response.getEntity();
  			// 得到response的内容
  			content = EntityUtils.toString(entity);
  			//System.out.println(TAG + "POST:" + content+"\t"+allHeaders);
  			// 　关闭输入流
  			EntityUtils.consume(entity);
  			return content;
  			 }
  		} catch (Exception e) {
  			e.printStackTrace();
  			return null;
  		} finally {
  			if (response != null) {
  				try {
  					response.close();
  				} catch (IOException e) {
  					e.printStackTrace();
  				}
  			}
  		}
  		return content;
  	
  	}
  	

      /**
       * 向指定URL发送GET方法的请求
       *
       * @param url 发送请求的URL
       * @return URL 所代表远程资源的响应结果
       */
      public static String sendGet2(String url) {
          return sendGet(url, "");
      }

      /**
       * 向指定URL发送GET方法的请求
       *
       * @param url   发送请求的URL
       * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
       * @return URL 所代表远程资源的响应结果
       */
      public static String sendGet(String url, String param) {
          Map<String, String> headers = new HashMap<String, String>();
          headers.put("accept", "*/*");
          headers.put("connection", "Keep-Alive");
          headers.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
          return sendGet(url, param, headers);
      }

      /**
       * 向指定URL发送GET方法的请求
       *
       * @param url     发送请求的URL
       * @param param   请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
       * @param headers 请求头参数，为Key：Value键值对
       * @return URL 所代表远程资源的响应结果
       */
      public static String sendGet(String url, String param, Map<String, String> headers) {
          String result = "";
          BufferedReader in = null;
          try {
              String urlNameString = url;
              if (param != null && param.length() > 0) {
                  urlNameString = urlNameString + "?" + param;
              }
              URL realUrl = new URL(urlNameString);
              // 打开和URL之间的连接
              URLConnection connection = realUrl.openConnection();
              // 设置通用的请求属性
              if (headers != null) {
                  Set<String> keySet = headers.keySet();
                  for (String key : keySet) {
                      connection.setRequestProperty(key, headers.get(key));
                  }
              }
              // 建立实际的连接
              connection.connect();
              // 获取所有响应头字段
              Map<String, List<String>> map = connection.getHeaderFields();
              // 遍历所有的响应头字段
              for (String key : map.keySet()) {
                  System.out.println(key + "--->" + map.get(key));
              }
              // 定义 BufferedReader输入流来读取URL的响应
              in = new BufferedReader(new InputStreamReader(
                      connection.getInputStream()));
              String line;
              while ((line = in.readLine()) != null) {
                  result += line;
              }
          } catch (Exception e) {
              System.out.println("发送GET请求出现异常！" + e);
              e.printStackTrace();
          }
          // 使用finally块来关闭输入流
          finally {
              try {
                  if (in != null) {
                      in.close();
                  }
              } catch (Exception e2) {
                  e2.printStackTrace();
              }
          }
          return result;
      }

      /**
       * 向指定 URL 发送POST方法的请求
       *
       * @param url 发送请求的 URL
       * @return 所代表远程资源的响应结果
       */
      public static String sendPost(String url) {
          return sendPost2(url, null);
      }

      /**
       * 向指定 URL 发送POST方法的请求
       *
       * @param url   发送请求的 URL
       * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
       * @return 所代表远程资源的响应结果
       */
      public static String sendPost2(String url, String param) {
          Map<String, String> headers = new HashMap<String, String>();
          headers.put("accept", "*/*");
          headers.put("connection", "Keep-Alive");
          headers.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
          return sendPost(url, param, headers);
      }

      /**
       * 向指定 URL 发送POST方法的请求
       *
       * @param url     发送请求的 URL
       * @param param   请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
       * @param headers 请求头参数，为Key：Value键值对
       * @return 所代表远程资源的响应结果
       */
      public static String sendPost(String url, String param, Map<String, String> headers) {
          PrintWriter out = null;
          BufferedReader in = null;
          String result = "";
          try {
              URL realUrl = new URL(url);
              // 打开和URL之间的连接
              URLConnection conn = realUrl.openConnection();
              // 设置通用的请求属性
              if (headers != null) {
                  Set<String> keySet = headers.keySet();
                  for (String key : keySet) {
                      conn.setRequestProperty(key, headers.get(key));
                  }
              }
              // 发送POST请求必须设置如下两行
              conn.setDoOutput(true);
              conn.setDoInput(true);
              // 获取URLConnection对象对应的输出流
              out = new PrintWriter(conn.getOutputStream());
              // 发送请求参数
              out.print(param);
              // flush输出流的缓冲
              out.flush();
              // 定义BufferedReader输入流来读取URL的响应
              in = new BufferedReader(
                      new InputStreamReader(conn.getInputStream()));
              String line;
              while ((line = in.readLine()) != null) {
                  result += line;
              }
          } catch (Exception e) {
              System.out.println("发送 POST 请求出现异常！" + e);
              e.printStackTrace();
          }
          //使用finally块来关闭输出流、输入流
          finally {
              try {
                  if (out != null) {
                      out.close();
                  }
                  if (in != null) {
                      in.close();
                  }
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
          }
          return result;
      }
    public static String send(String host, Map<String, String> params,String method) {
    	 HttpURLConnection conn = null;
    	InputStream is =null;
		BufferedReader br =null;
        try {
            // 设置SSLContext
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] { myX509TrustManager }, null);

            String sendUrl = getUrlWithQueryString(host, params);

            // System.out.println("URL:" + sendUrl);

            URL uri = new URL(sendUrl); // 创建URL对象
            conn = (HttpURLConnection) uri.openConnection();
            if (conn instanceof HttpsURLConnection) {
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslcontext.getSocketFactory());
            }

            conn.setConnectTimeout(SOCKET_TIMEOUT); // 设置相应超时
            if(method.equals("POST")){
            	 conn.setRequestMethod(POST);
            }else{
            	conn.setRequestMethod(GET);
            }
            
            int statusCode = conn.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Http错误码：" + statusCode);
            }
            
            // 读取服务器的数据
            is = conn.getInputStream();
             br = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }

            String text = builder.toString();

         
            return text;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }finally{
        	   close(br); // 关闭数据流
               close(is); // 关闭数据流
               conn.disconnect(); // 断开连接

        }

        return null;
    }

    public static String getUrlWithQueryString(String url, Map<String, String> params) {
        if (params == null) {
            return url;
        }

        StringBuilder builder = new StringBuilder(url);
        if (url.contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        int i = 0;
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null) { // 过滤空的key
                continue;
            }

            if (i != 0) {
                builder.append('&');
            }

            builder.append(key);
            builder.append('=');
           // builder.append(encode(value));
            builder.append(value);
            i++;
        }

        return builder.toString();
    }

    protected static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对输入的字符串进行URL编码, 即转换为%20这种形式
     * 
     * @param input 原文
     * @return URL编码. 如果编码失败, 则返回原文
     */
    public static String encode(String input) {
        if (input == null) {
            return "";
        }

        try {
            return URLEncoder.encode(input, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return input;
    }

    private static TrustManager myX509TrustManager = new X509TrustManager() {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    };

}
