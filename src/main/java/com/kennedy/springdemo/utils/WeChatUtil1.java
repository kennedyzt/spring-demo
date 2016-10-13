package com.kennedy.springdemo.utils;

import com.kennedy.springdemo.beans.wechat.Token;
import com.kennedy.springdemo.beans.wechat.WeChatUser;
import com.kennedy.springdemo.beans.wechat.WebToken;
import com.kennedy.springdemo.beans.wechat.menu.WeChatMenu;
import com.kennedy.springdemo.beans.wechat.message.Filter;
import com.kennedy.springdemo.beans.wechat.message.SendAllMsg;
import com.kennedy.springdemo.beans.wechat.message.Text;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

/**
 * @Description: 微信工具类
 * @date: 2016年9月1日 上午9:39:17
 * @author: zengt
 * @version: 1.0
 */
public class WeChatUtil1 {
    public final static String APPID = "wxff920ddc80fc60c7";
    public final static String APPSECRET = "93f1703b06f7a5524a69acaf320f86a3";
    // 动态代理地址
    public final static String PROXYADDRESS = "http://kennedyzt.vicp.io/";
    // 创建菜单（POST）
    public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 获取菜单（GET）
    public final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    // 删除菜单（GET）
    public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    // 分组发送消息（POST）
    public final static String SEND_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
    // 消息预览
    public final static String PREVIEW_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";

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
                token.setErrcode(jsonObject.getString("errcode"));
                token.setErrmsg(jsonObject.getString("errmsg"));
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
     * @param menu
     * @param accessToken
     * @return
     * @Description: 创建菜单
     * @author: zengt
     * @date: 2016年9月5日 下午3:15:27
     */
    public static boolean createMenu(WeChatMenu menu, String accessToken) {
        boolean result = false;
        String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
        String jsonMenu = JSONObject.fromObject(menu).toString();
        JSONObject jsonObject = WeChatUtil1.httpRequest(url, "POST", jsonMenu);

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
     * @param accessToken
     * @return
     * @Description: 查询菜单列表
     * @author: zengt
     * @date: 2016年9月5日 下午3:16:48
     */
    public static String getMenu(String accessToken) {
        String result = null;
        String requestUrl = MENU_GET_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = WeChatUtil1.httpRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            result = jsonObject.toString();
        }
        return result;
    }

    /**
     * 뇜꽉데
     *
     * @param accessToken 틴聯
     * @return true냥묘 false呵겨
     */
    public static boolean deleteMenu(String accessToken) {
        boolean result = false;
        String requestUrl = MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
        // 拼接请求地址
        JSONObject jsonObject = WeChatUtil1.httpRequest(requestUrl, "GET", null);
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

    public static String sendMsg(String accessToken) {
        // 拼接请求地址
        String requestUrl = SEND_MSG_URL.replace("ACCESS_TOKEN", accessToken);
        // 获取用户信息
        SendAllMsg sendAllMsg = new SendAllMsg();
        Filter filter = new Filter();
        filter.setIs_to_all(true);
        sendAllMsg.setFilter(filter);
        Text text = new Text();
        text.setContent("群发消息测试");
        sendAllMsg.setText(text);
        sendAllMsg.setMsgtype("text");
        JSONObject jsonObject = httpRequest(requestUrl, "POST", JSONObject.fromObject(sendAllMsg).toString());
        return "";
    }

    public static String previewMsg(String accessToken, String openId) {
        // 拼接请求地址
        String requestUrl = PREVIEW_MSG_URL.replace("ACCESS_TOKEN", accessToken);
        // 获取用户信息
        SendAllMsg sendAllMsg = new SendAllMsg();
        sendAllMsg.setTouser(openId);
        Text text = new Text();
        text.setContent("群发消息测试");
        sendAllMsg.setText(text);
        sendAllMsg.setMsgtype("text");
        JSONObject jsonObject = httpRequest(requestUrl, "POST", JSONObject.fromObject(sendAllMsg).toString());
        return "";
    }
}