package com.kennedy.springdemo.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.kennedy.springdemo.beans.wechat.Token;
import com.kennedy.springdemo.beans.wechat.WeChatUser;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @Description: 微信工具类
 * @date: 2016年9月1日 上午9:39:17
 * @author: zengt
 * @version: 1.0
 */
public class WeChatUtil {
    public final static String appId = "wxe6ec34f9a9bd3cf9";
    public final static String appSecret = "de69c952bcbcd7fc43191cc38d68d45b";

    public static Token getToken() {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        Token token = null;
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                token = new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("获取accessToken次数超限制");
            }
        }
        return token;
    }

    /**
     * 发起https请求并获取结果
     */
    protected static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = new StringBuffer();
        try {
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
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.fromObject(buffer.toString());
    }

    protected static WeChatUser getUserInfo(String accessToken, String openId) {
        WeChatUser user = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 获取用户信息
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                user = new WeChatUser();
                user.setSubscribe(jsonObject.getBoolean("subscribe"));
                user.setOpenId(jsonObject.getString("openid"));
                user.setNickName(jsonObject.getString("nickname"));
                user.setSex(jsonObject.getInt("sex"));
                user.setCity(jsonObject.getString("city"));
                user.setCountry(jsonObject.getString("country"));
                user.setProvince(jsonObject.getString("province"));
                user.setLanguage(jsonObject.getString("language"));
                user.setHeadImagUrl(jsonObject.getString("headimgurl"));
                user.setSubscribeTime(jsonObject.getString("subscribetime"));
                user.setUnionId(jsonObject.getString("unionid"));
                user.setRemark(jsonObject.getString("remark"));
                user.setGroupId(jsonObject.getString("groupid"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
