package com.common.utils;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 网络请求工具类
 *
 * @author linilq
 */
public class HttpUtils {
    private static final String URL_PARAM_CONNECT_FLAG = "&";


    public static String sendGet(String url, Map<String, String> paramMap, int timeOut) throws Exception {
        String result = "";
        BufferedReader in = null;
        String urlName = url;

        if (paramMap != null) {
            String param = getUrlParams(paramMap);
            if (urlName.contains("?")) {
                urlName = url + "&" + param;
            } else {
                urlName = url + "?" + param;
            }
        }

        try {
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setConnectTimeout(timeOut);
            conn.setReadTimeout(timeOut);
            conn.setUseCaches(false);
            // 建立实际的连接
            conn.connect();
            // 获取所有响应头字段
//			Map<String, List<String>> map = conn.getHeaderFields();
//			 遍历所有的响应头字段
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！urlName=" + urlName);
            throw e;
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求,默认读写超时时间为5s
     *
     * @param url      发送请求的URL
     * @param paramMap 请求参数
     * @return URL所代表远程资源的响应
     */
    public static String sendGet(String url, Map<String, String> paramMap) throws Exception {
        return sendGet(url, paramMap, 5 * 1000);
    }


    public static String sendPost3(String url, Map<String, String> params) {
        URL u = null;
        HttpURLConnection con = null;
        // 构建请求参数
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            for (Entry<String, String> e : params.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb.substring(0, sb.length() - 1);
        }
        System.out.println("send_url:" + url);
        System.out.println("send_data:" + sb.toString());
        // 尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            //// POST 只能为大写，严格限制，post会不识别
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            osw.write(sb.toString());
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        // 读取返回内容
        StringBuffer buffer = new StringBuffer();
        try {
            //一定要有返回值，否则无法把请求发送给server端。
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }


    public static String getUrlParams(Map<String, String> map) {
        if (null == map || map.keySet().size() == 0) {
            return ("");
        }
        StringBuffer url = new StringBuffer();
        Set<String> keys = map.keySet();
        for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
            String key = String.valueOf(i.next());
            if (map.containsKey(key)) {
                Object val = map.get(key);
                String str = val != null ? val.toString() : "";
                try {
                    //这里的编码不能随便改动！！！外面的请求参数会依赖这里来处理的！！！！
                    str = URLEncoder.encode(str, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                url.append(key).append("=").append(str).
                        append(URL_PARAM_CONNECT_FLAG);
            }
        }
        String strURL = "";
        strURL = url.toString();
        if (URL_PARAM_CONNECT_FLAG.equals("" + strURL.charAt(strURL.length() - 1))) {
            strURL = strURL.substring(0, strURL.length() - 1);
        }
        return (strURL);
    }

    /**
     * 向指定URL发送POST方法的请求，超时时常改为自定义
     *
     * @param url     发送请求的URL
     * @param content 请求参数，请求参数应该是
     * @return URL所代表远程资源的响应
     */
    public static String sendPost(String url, String content, int timeOut) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(timeOut);
            conn.setReadTimeout(timeOut);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(content);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
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

    /**
     * 请求Base64内容
     * 此时无需解析，无等号，直接拼接内容到url
     *
     * @param url
     * @param base64
     * @return
     */
    public static String sendGetBase64(String url, String base64) {
        String result = "";
        BufferedReader in = null;
        String urlName = url;
        if (StringUtil.checkNotNull(base64))
            urlName = url + "?" + base64;

        try {
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(5 * 1000);
            conn.setUseCaches(false);
            // 建立实际的连接
            conn.connect();
            // 获取所有响应头字段
//          Map<String, List<String>> map = conn.getHeaderFields();
//           遍历所有的响应头字段
//          for (String key : map.keySet()) {
//              System.out.println(key + "--->" + map.get(key));
//          }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！urlName=" + urlName);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, Map<String, String> header) {
        PrintWriter out = null;
        BufferedReader in = null;
        URLConnection conn = null;
        StringBuilder jsonStr = new StringBuilder();

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = realUrl.openConnection();
            conn.setConnectTimeout(20000);
            conn.setRequestProperty("Content-length", String.valueOf(param.length()));
            //添加header信息
            if (header != null) {
                for (Entry<String, String> entry : header.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数body
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            InputStreamReader reader = new InputStreamReader(conn.getInputStream(), "utf-8");
            char[] buff = new char[1024];
            int length = 0;
            while ((length = reader.read(buff)) != -1) {
                String result = new String(buff, 0, length);
                jsonStr.append(result);
            }
        } catch (Exception e) {
            LogUtil.write2file("发送 POST 请求出现异常！信息：" + e.getMessage() + "---请求URL：" + url, "SendPost");
        } finally {
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

        return jsonStr.toString();

    }


    public static String sendGetWithHeadParam(String url, String param, Map<String, String> header) throws Exception {
        String result = "";
        BufferedReader in = null;
        String finalUrl = url;
        finalUrl = url + "?" + param;

        try {
            URL realUrl = new URL(finalUrl);

            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            if (header != null) {
                for (Entry<String, String> entry : header.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(5 * 1000);
            conn.setUseCaches(false);
            // 建立实际的连接
            conn.connect();
            // 获取所有响应头字段
//			Map<String, List<String>> map = conn.getHeaderFields();
//			 遍历所有的响应头字段
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！urlName=" + finalUrl);
            throw e;
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
