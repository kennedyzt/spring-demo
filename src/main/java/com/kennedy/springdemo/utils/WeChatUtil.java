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
import com.kennedy.springdemo.beans.wechat.menu.WeChatMenu;

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
    // 动态代理地址
    public final static String PROXYADDRESS = "http://kennedyzt.vicp.io/";
    // 创建菜单（POST）
    public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 获取菜单（GET）
    public final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    // 删除菜单（GET）
    public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

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
                user.setErrcode(jsonObject.getString("errcode"));
                user.setErrmsg(jsonObject.getString("errmsg"));
            } catch (Exception e) {
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
            try {
                webToken = new WebToken();
                webToken.setAccessToken(jsonObject.getString("access_token"));
                webToken.setExpiresIn(jsonObject.getString("expires_in"));
                webToken.setRefreshToken(jsonObject.getString("refresh_token"));
                webToken.setOpenid(jsonObject.getString("openid"));
                webToken.setScope(jsonObject.getString("scope"));
                try {
                    webToken.setUnionid(jsonObject.getString("unionid"));
                } catch (JSONException e) {

                }
            } catch (Exception e) {
                e.printStackTrace();
                webToken = null;
            }
        }
        return webToken;
    }

    /**
     * @Description: 创建菜单
     * @param menu
     * @param accessToken
     * @return
     * @author: zengt
     * @date: 2016年9月5日 下午3:15:27
     */
    public static boolean createMenu(WeChatMenu menu, String accessToken) {
        boolean result = false;
        String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
        String jsonMenu = JSONObject.fromObject(menu).toString();
        JSONObject jsonObject = WeChatUtil.httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            if (0 == errorCode) {
                result = true;
            } else {
                result = false;
            }
        }

        return result;
    }

    /**
     * @Description: 查询菜单列表
     * @param accessToken
     * @return
     * @author: zengt
     * @date: 2016年9月5日 下午3:16:48
     */
    public static String getMenu(String accessToken) {
        String result = null;
        String requestUrl = MENU_GET_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = WeChatUtil.httpRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            result = jsonObject.toString();
        }
        return result;
    }

    /**
     * 뇜꽉데
     * @param accessToken 틴聯
     * @return true냥묘 false呵겨
     */
    public static boolean deleteMenu(String accessToken) {
        boolean result = false;
        String requestUrl = MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
        // 랙폅GET헝헹뇜꽉데
        JSONObject jsonObject = WeChatUtil.httpRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            if (0 == errorCode) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }
}
