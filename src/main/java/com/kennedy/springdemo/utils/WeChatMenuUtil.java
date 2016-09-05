package com.kennedy.springdemo.utils;

import com.kennedy.springdemo.beans.wechat.menu.WeChatMenu;

import net.sf.json.JSONObject;

/**
 * @Description: 微信菜单工具类
 * @date: 2016年9月5日 下午3:11:48
 * @author: zengt
 * @version: 1.0
 */
public class WeChatMenuUtil {

    // 创建菜单（POST）
    public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 获取菜单（GET）
    public final static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    // 删除菜单（GET）
    public final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

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
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
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
        String requestUrl = menu_get_url.replace("ACCESS_TOKEN", accessToken);
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
        String requestUrl = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
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
