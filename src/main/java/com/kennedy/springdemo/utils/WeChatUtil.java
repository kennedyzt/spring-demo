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
import com.kennedy.springdemo.beans.wechat.WebToken;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @Description: 微信工具类
 * @date: 2016年9月1日 上午9:39:17
 * @author: zengt
 * @version: 1.0
 */
public class WeChatUtil {
    public final static String APPID = "wxe6ec34f9a9bd3cf9";
    public final static String APPSECRET = "de69c952bcbcd7fc43191cc38d68d45b";
    public final static String PROXYADDRESS = "http://cca3c7b8.ngrok.io";

    public static Token getToken() {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
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

    public static WeChatUser getUserInfo(String accessToken, String openId) {
        WeChatUser user = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 获取用户信息
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                user = new WeChatUser();
                user.setOpenId(jsonObject.getString("openid"));
                user.setNickName(jsonObject.getString("nickname"));
                user.setSex(jsonObject.getInt("sex"));
                user.setCity(jsonObject.getString("city"));
                user.setCountry(jsonObject.getString("country"));
                user.setHeadImagUrl(jsonObject.getString("headimgurl"));
                user.setGroupId(jsonObject.getString("groupid"));
                user.setPrivilege(jsonObject.getString("privilege"));
                user.setUnionid(jsonObject.getString("unionid"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public static WebToken getWebToken(String code) {
        WebToken webToken = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code).replace("CODE", code);
        // 获取用户信息
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            webToken = new WebToken();
            webToken.setAccessToken(jsonObject.getString("access_token"));
            webToken.setExpiresIn(jsonObject.getString("expires_in"));
            webToken.setRefreshToken(jsonObject.getString("refresh_token"));
            webToken.setOpenid(jsonObject.getString("openid"));
            webToken.setScope(jsonObject.getString("scope"));
            webToken.setUnionid(jsonObject.getString("unionid"));
        }
        return webToken;
    }
}
