package com.kennedy.springdemo.web.wechat;

import java.io.UnsupportedEncodingException;

import com.kennedy.springdemo.beans.wechat.menu.Button;
import com.kennedy.springdemo.beans.wechat.menu.ClickButton;
import com.kennedy.springdemo.beans.wechat.menu.MenuType;
import com.kennedy.springdemo.beans.wechat.menu.ViewButton;
import com.kennedy.springdemo.beans.wechat.menu.WeChatMenu;
import com.kennedy.springdemo.utils.WeChatUtil;

/**
 * @Description: 菜单管理
 * @date: 2016年9月5日 下午1:59:26
 * @author: zengt
 * @version: 1.0
 */
public class MenuManager {
    public static void main(String[] args) throws UnsupportedEncodingException {
        boolean result = false;
        String jsonString = "";
        String access_token = WeChatUtil.getToken().getAccessToken();
        result = WeChatUtil.deleteMenu(access_token);
        if (result) {
            System.out.println(("菜单删除成功！"));
        } else {
            System.out.println("菜单删除失败，错误码：" + result);
        }
        result = WeChatUtil.createMenu(getMenu(), access_token);
        if (result) {
            System.out.println("菜单创建成功！");
        } else {
            System.out.println("菜单创建失败，错误码：" + result);
        }
        jsonString = WeChatUtil.getMenu(access_token);
        System.out.println("菜单查询结果:" + jsonString);
    }

    /**
     * @Description: TODO
     * @return
     * @throws UnsupportedEncodingException
     * @author: zengt
     * @date: 2016年9月5日 下午3:19:30
     */
    private static WeChatMenu getMenu() {
        WeChatMenu weChatMenu = new WeChatMenu();

        ClickButton clickButton = new ClickButton("ClickButton", MenuType.CLICK.getType(), "baidu");
        // 拼接请求地址
        String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        requestUrl = requestUrl.replace("APPID", WeChatUtil.APPID).replace("REDIRECT_URI", WeChatUtil.PROXYADDRESS + "/springdemo/wechat/getuserinfo").replace("SCOPE", "snsapi_userinfo")
            .replace("STATE", "123456");
        ViewButton viewButton1 = new ViewButton("网页授权", MenuType.VIEW.getType(), requestUrl);
        weChatMenu.setButton(new Button[] { clickButton, viewButton1 });

        return weChatMenu;
    }

}
