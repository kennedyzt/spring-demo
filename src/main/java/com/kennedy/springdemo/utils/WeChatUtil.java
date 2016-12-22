package com.kennedy.springdemo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.kennedy.springdemo.beans.wechat.ErrorCodeModel;
import com.kennedy.springdemo.beans.wechat.Token;
import com.kennedy.springdemo.beans.wechat.WeChatUser;
import com.kennedy.springdemo.beans.wechat.WebToken;
import com.kennedy.springdemo.beans.wechat.kf.KfInfo;
import com.kennedy.springdemo.beans.wechat.menu.WeChatMenu;
import com.kennedy.springdemo.beans.wechat.message.SendAllMsg;
import com.kennedy.springdemo.beans.wechat.message.Text;
import com.kennedy.springdemo.beans.wechat.message.TextMessage;
import com.kennedy.springdemo.beans.wechat.order.UnifiedOrder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @Description: 微信工具类
 * @date: 2016年9月1日 上午9:39:17
 * @author: zengt
 * @version: 1.0
 */
public class WeChatUtil {
    // -----------------------------------需配置---------------------------------------------------------
    public final static String APPID = "wxf80f6cff0b60cc82";
    public final static String APPSECRET = "6670fc66e4cbe9cd4da983a524846203";
    public final static String DEVELOPER_ID = "gh_90761ed424a7"; // 开发者微信号
    public final static String MCH_ID = "1423332802"; // 商户号
    // 动态代理地址
    public final static String PROXYADDRESS = "http://kennedyzt.vicp.io/portal/";
    // -----------------------------------配置結束--------------------------------------------------------
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
    // 添加客服
    public final static String KF_ADD_URL = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";

    /** ----------------------支付模块------------------------------------ */
    public final static String UNIFIE_DORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

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
     * @param accessToken
     * @return
     * @Description: 查询菜单列表
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
     * @Description: 刪除菜单
     * @param accessToken
     * @return
     * @author: zengt
     * @date: 2016年12月6日 下午2:23:12
     */
    public static boolean deleteMenu(String accessToken) {
        boolean result = false;
        String requestUrl = MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
        // 拼接请求地址
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

    // 群发消息
    public static JSONObject sendMsg(String accessToken, SendAllMsg sendAllMsg) {
        // 拼接请求地址
        String requestUrl = SEND_MSG_URL.replace("ACCESS_TOKEN", accessToken);
        return httpRequest(requestUrl, "POST", JSONObject.fromObject(sendAllMsg).toString());
    }

    public static JSONObject previewMsg(String accessToken, String openId) {
        // 拼接请求地址
        String requestUrl = PREVIEW_MSG_URL.replace("ACCESS_TOKEN", accessToken);
        // 获取用户信息
        SendAllMsg sendAllMsg = new SendAllMsg();
        sendAllMsg.setTouser(openId);
        Text text = new Text();
        text.setContent("群发消息测试");
        sendAllMsg.setText(text);
        sendAllMsg.setMsgtype("text");
        return httpRequest(requestUrl, "POST", JSONObject.fromObject(sendAllMsg).toString());
    }

    // 添加微信客服
    public static ErrorCodeModel addKF() {
        ErrorCodeModel errorCodeModel = new ErrorCodeModel();
        // 拼接请求地址
        String requestUrl = KF_ADD_URL.replace("ACCESS_TOKEN", WeChatUtil.getToken().getAccessToken());
        KfInfo kfInfo = new KfInfo("客服组@oc4sNw4W1UxqW0zC3-PFLgBhvYeI", "王涛", "251314taowang");
        JSONObject jsonObject = httpRequest(requestUrl, "POST", JSONObject.fromObject(kfInfo).toString());
        if (null != jsonObject) {
            try {
                errorCodeModel.setErrcode(jsonObject.getString("errcode"));
                errorCodeModel.setErrmsg(jsonObject.getString("errmsg"));
            } catch (Exception e) {
            }
        }
        return errorCodeModel;
    }

    public static void sendMsgToUser(String fromUserName, SendAllMsg sendAlMsg) {

    }

    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 对象到xml的处理
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write(text);
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    public static Map<String, Object> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, Object> map = new HashMap<String, Object>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            if (e.elements().size() > 0) {
                map.put(e.getName(), e.elements());
            } else {
                map.put(e.getName(), e.getText());
            }
        }

        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    /** --------------------------支付模块---------------------------- */
    public static String buildUnifiedOrder(SortedMap<Object, Object> unifiedParams) {
        String requestXML = PayCommonUtil.getRequestXml(unifiedParams);
        return HttpUtil.postData(UNIFIE_DORDER_URL, requestXML);
    }
}
