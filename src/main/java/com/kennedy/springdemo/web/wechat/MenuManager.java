package com.kennedy.springdemo.web.wechat;

import java.io.UnsupportedEncodingException;

import com.kennedy.springdemo.beans.wechat.menu.Button;
import com.kennedy.springdemo.beans.wechat.menu.ClickButton;
import com.kennedy.springdemo.beans.wechat.menu.MenuType;
import com.kennedy.springdemo.beans.wechat.menu.WeChatMenu;
import com.kennedy.springdemo.utils.WeChatMenuUtil;
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
        result = WeChatMenuUtil.deleteMenu(access_token);
        if (result) {
            System.out.println(("菜单删除成功！"));
        } else {
            System.out.println("菜单删除失败，错误码：" + result);
        }
        result = WeChatMenuUtil.createMenu(getMenu(), access_token);
        if (result) {
            System.out.println("菜单创建成功！");
        } else {
            System.out.println("菜单创建失败，错误码：" + result);
        }
        jsonString = WeChatMenuUtil.getMenu(access_token);
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
        ClickButton clickButton = new ClickButton("百度", MenuType.CLICK.getType(), "baidu");
        weChatMenu.setButton(new Button[] { clickButton });
        return weChatMenu;
    }

}
