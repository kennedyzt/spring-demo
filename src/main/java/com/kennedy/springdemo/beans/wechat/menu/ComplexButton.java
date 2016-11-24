package com.kennedy.springdemo.beans.wechat.menu;

/**
 * @Description: 综合菜单
 * @date: 2016年11月24日 下午5:35:11
 * @author: zengt
 * @version: 1.0
 */
public class ComplexButton extends Button {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;

    }
}
