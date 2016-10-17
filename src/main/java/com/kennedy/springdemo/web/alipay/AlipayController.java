package com.kennedy.springdemo.web.alipay;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;

@Controller
@RequestMapping("/alipay")
public class AlipayController {
    private static final String APP_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKxavPxbYcdDWhWYY7GP26pWkup6NI4U6q/rcMwiLYL1eVj6rosHBbzcIUlG96bxxt9klTlrIw35XTtDHtFEvhC8RQ9txv9rRsZkiAMGiDRz1eQ7KqFM9Zn3bgdY2+1WclCgH9l+uVgUAYsDUAozO0skAhpQ2miPxj0fhcEuUbCNAgMBAAECgYEApMO9pNXLHI2jgb+Dlow655UJr8QRl74hOPmvhEYsEeSPE+PZliipcIlT/NQuDddWUdq62QA1q7c2TF/3BwFbW9t5iVXDtEwJJeUX8MVjBkx1VfYUAkaSZvitV/7glBsCbxDbUKPwZcqOzfutgvE25CQVeDQS5qLRoL8G/1JUKEECQQDjFkseMNd1m8Y74q4vGY93TRN+46xllkWUx5q1Z2lONxuXZlQHPIgWrn7tZQoVg2szCrrNTDFEYi34Fx+SUvz9AkEAwkx7YFkBxp1IpUxEQjlXnAzZRQo9S5xJ0XwqvqRS0g0VKDHKWHcrraioVUV4CGXERBqwNnMqwVxBp+jDpDGe0QJAW/8OE38J6nVfC6e7lQ0v18771O37S3RjX/C1NkRcnvkoTl+ALyZQo4+xkA7c1PZWmWkBYGY7l06G/PixK6A/KQJAZGvDGHjoQg7ojQZuAQxCC5VYdiZkp7+PJ4ZVUVL8uzOny65wNmjhWk/hMLFG6JAwcEKa8Il1f5p/FJlpBGPhAQJBAMS7zXRPR3LoUKAlWwGP8sTOXfs8+D6Kw890Sw+XJ1P9+PA/RQjnTHn7qFA+XPWvCiQf/F0lwXAo5GULs7ItzIc=";
    private static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsWrz8W2HHQ1oVmGOxj9uqVpLqejSOFOqv63DMIi2C9XlY+q6LBwW83CFJRvem8cbfZJU5ayMN+V07Qx7RRL4QvEUPbcb/a0bGZIgDBog0c9XkOyqhTPWZ924HWNvtVnJQoB/ZfrlYFAGLA1AKMztLJAIaUNpoj8Y9H4XBLlGwjQIDAQAB";
    private static final String APP_ID = "2016101702205715";

    @RequestMapping("/init")
    public void init(HttpServletResponse httpResponse) throws IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY);
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();// 创建API对应的request
        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");// 在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" + "    \"out_trade_no\":\"20150320010101002\"," + "    \"total_amount\":88.88," + "    \"subject\":\"Iphone6 16G\"," + "    \"seller_id\":\"2088123456789012\","
            + "    \"product_code\":\"QUICK_WAP_PAY\"" + "  }");// 填充业务参数
        try {
            String form = alipayClient.pageExecute(alipayRequest).getBody();
            // 调用SDK生成表单
            httpResponse.setContentType("text/html;charset=utf-8");
            httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/returnurl")
    public String returnUrl() {
        return "/alipay/returnurl";
    }

    @RequestMapping("/notifyurl")
    public String notifyUrl() {
        return "/alipay/notifyurl";
    }

}
