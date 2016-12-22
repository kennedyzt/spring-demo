package com.kennedy.springdemo.web.wechat.pay;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kennedy.springdemo.beans.Goods;
import com.kennedy.springdemo.beans.wechat.order.UnifiedOrder;
import com.kennedy.springdemo.utils.PayCommonUtil;
import com.kennedy.springdemo.utils.WeChatUtil;

@Controller
@RequestMapping("/pay")
public class PayController {
    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public String toPay() {
        return "/wechat/pay/pay";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public String pay(Goods goods) {
        UnifiedOrder order = new UnifiedOrder(WeChatUtil.APPID, WeChatUtil.MCH_ID, WeChatUtil.getUUID(), "123456", goods.getRemark(), "1001", 1, "192.168.10.101",
            WeChatUtil.PROXYADDRESS + "/pay/notifyurl", "JSAPI");

        try {
            SortedMap<Object, Object> unifiedParams = new TreeMap<Object, Object>();
            unifiedParams.put("appid", WeChatUtil.APPID); // 必须
            unifiedParams.put("mch_id", WeChatUtil.MCH_ID); // 必须
            unifiedParams.put("out_trade_no", "1001"); // 必须
            unifiedParams.put("product_id", "10001");
            unifiedParams.put("body", goods.getRemark()); // 必须
            unifiedParams.put("total_fee", "1"); // 必须
            unifiedParams.put("nonce_str", WeChatUtil.getUUID()); // 必须
            unifiedParams.put("spbill_create_ip", "192.168.10.101"); // 必须
            unifiedParams.put("trade_type", "NATIVE"); // 必须
            unifiedParams.put("notify_url", WeChatUtil.PROXYADDRESS + "/pay/notifyurl");// 异步通知url
            String sign0 = PayCommonUtil.createSign("UTF-8", unifiedParams, "chengdusipingruanjiansoft2016666");
            unifiedParams.put("sign", sign0); // 签名
            String resXml = WeChatUtil.buildUnifiedOrder(unifiedParams);
            // 统一下单响应
            SortedMap<Object, Object> reParams = PayCommonUtil.xmlConvertToMap(resXml);
            if (null != reParams.get("code_url")) {
                return reParams.get("code_url").toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public static SortedMap<Object, Object> convertBean(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        SortedMap<Object, Object> returnMap = new TreeMap<Object, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
}
