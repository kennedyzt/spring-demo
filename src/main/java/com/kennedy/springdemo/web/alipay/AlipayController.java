package com.kennedy.springdemo.web.alipay;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;

@Controller
@RequestMapping("/alipay")
public class AlipayController {
    private static final String APP_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMCZWTcrlXaX6gksAKgCRijNWUvYTyP/8QUWyJARBufJDx8fCUZe1qLzUm8CvpCYZiH9fVW6lJAfdqUj1C7Ou2p6MGZisWFfzM6FU4AmksBYwNUH7kcZaJ4iKUbRfrGn3KOQD6WnZbuPAIDGufRsflrNCbBctwR6QqtYJuE46rEnAgMBAAECgYEAntpccVqrTkOCLspySCCICYNFkX7513F0M4RVLC4/CdIVfM+2cKosUf5rEVCsKMrqAkL3q0vW2QB6dT6V9X1hqcSP31l6kRQA5UzW8fF0s+zKK/6l9WBKtXHRllWXefhZcwX3pR5skJCjPxO/0uHWtQKj7W7EebEMiFttstULRCECQQDee+KAGcgCXI4ibD4afuQ8vN3D4rNHoDQL+kVActvL8QpcPjZfpUhTZBw58I6lUH2oFHNNnhWUImTEhPTSJ8NpAkEA3ZzzARyEZCnqaQCN1LER1kqkbd+z9PFL/dha/udLYQ1ZpeLnyGlcGyHdPNHaY7mZsoDTwV7aeFW/fLDoLGKODwJAclU5xdj55vTHejskAxu4kNoCIRtMRH+4n3siwYcFGx4o09SIvshCjdBBjSjpNV1S5eB0jKuzrSerny4wXhtfGQJANdVacnqq7moAN2GbEn+xBY08RDSDUo0LGK7l6+Xjub+0d0eXZmexqCWhyJRxqKf9Xg9NYTvZdkHeMjwHKkEGoQJAb41Gb051JPmi4K0tC3T9KQYAhx0NwOlMjWVXAZkYgTDzI9sBYKkZYibo+ALmgLnXfYtBMM5USJNw1rq1JBrDhg==";
    private static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAmVk3K5V2l+oJLACoAkYozVlL2E8j//EFFsiQEQbnyQ8fHwlGXtai81JvAr6QmGYh/X1VupSQH3alI9QuzrtqejBmYrFhX8zOhVOAJpLAWMDVB+5HGWieIilG0X6xp9yjkA+lp2W7jwCAxrn0bH5azQmwXLcEekKrWCbhOOqxJwIDAQAB";
    private static final String APP_ID = "2016101702205715";

    @RequestMapping("/init")
    public void init(HttpServletResponse httpResponse) throws IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY);
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();// 创建API对应的request类
        request.setBizContent("{" + "    \"out_trade_no\":\"20150320010101002\"," + "    \"total_amount\":88.88," + "    \"subject\":\"Iphone6 16G\"," + "    \"store_id\":\"NJ_001\","
            + "    \"timeout_express\":\"90m\"}");// 设置业务参数
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            System.out.println(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        // TODO 根据response中的结果继续业务逻辑处理
    }

    @RequestMapping("/returnurl")
    public String returnUrl() {
        return "/demo/alipay/returnurl";
    }

    @RequestMapping("/notifyurl")
    public String notifyUrl() {
        return "/demo/alipay/notifyurl";
    }

    @RequestMapping("/index")
    public String index() {
        return "/demo/alipay/index";
    }

    @RequestMapping("/alipayapi")
    public String alipayapi(HttpServletRequest request) throws IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", "GBK", ALIPAY_PUBLIC_KEY);
        AlipayTradePrecreateRequest request1 = new AlipayTradePrecreateRequest();
        request1.setBizContent("{" + "    \"out_trade_no\":\"20150320010101001\"," + "    \"scene\":\"bar_code\"," + "    \"auth_code\":\"28763443825664394\"," + "    \"subject\":\"Iphone6 16G\","
            + "    \"store_id\":\"NJ_001\"," + "    \"timeout_express\":\"2m\"," + "    \"total_amount\":88.88," + "  }"); // 设置业务参数
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute(request1);
            System.out.println(response);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "/demo/alipay/alipayapi";
    }
}
