package com.kennedy.springdemo.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.kennedy.springdemo.beans.wechat.AccessToken;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @Description: 微信工具类
 * @date: 2016年9月1日 上午9:39:17
 * @author: zengt
 * @version: 1.0
 */
public class WeChatUtil {
    protected final static String appId = "wxe6ec34f9a9bd3cf9";
    protected final static String appSecret = "de69c952bcbcd7fc43191cc38d68d45b";

    public static AccessToken getAccessToken() throws ConnectException, Exception {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        AccessToken token = null;
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                token = new AccessToken();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                token = null;
            }
        }
        return token;
    }

    /**
     * 发起https请求并获取结果
     */
    protected static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) throws ConnectException, Exception {
        StringBuffer buffer = new StringBuffer();
        URL url = new URL(requestUrl);
        HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
        httpUrlConn.setDoOutput(true);
        httpUrlConn.setDoInput(true);
        httpUrlConn.setUseCaches(false);
        httpUrlConn.setRequestMethod(requestMethod);
        if ("GET".equalsIgnoreCase(requestMethod))
            httpUrlConn.connect();
        if (null != outputStr) {
            OutputStream outputStream = httpUrlConn.getOutputStream();
            outputStream.write(outputStr.getBytes("UTF-8"));
            outputStream.close();
        }
        InputStream inputStream = httpUrlConn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        httpUrlConn.disconnect();
        return JSONObject.fromObject(buffer.toString());
    }
}
