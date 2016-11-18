package com.kennedy.springdemo.web.alipay;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.mysql.fabric.Server;

@Controller
@RequestMapping("/alipay")
public class AlipayController {
    private static final String APP_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALWJqajUqBwam3UB1FKt/LSNk+knyvbpyd6YmjvvObRKquGnPnsZplvk92KwVDQlM9/jyfKolG8AFErWsbZBgXU/9cLtF4kpAkxomEnT/ZAJ21z+gR0e+9CZbhROJDW5VstPrh7FYtGlttOFemNErxIrHDltcOVN0AUymPmU10+5AgMBAAECgYEApMmbqlevaiN07heFBrBM7hB+LW0jvlKmHltj1ffn55LH3yITg2bbLht/vKPXK6aBmkCJ9q20X7YwtWaB1rFLsv1uR2FD+WSRybKpfQlcTj6yQrPyomd92gdGsYfX9QYZFO9AOZhq6Axp5+X1KLJmOO2pYkQEwOmolxd/L2rR8mkCQQDgvwZJ5E1Pl9qedvoDbJ1yXeS/lS7s7SHI9NhSLJRUfdAemLSpSgF6eOJzSzGS4X32fxj1CP1Tzst71G7/DjTHAkEAzshqWB6yJKPZPwkaX5VC3DO/xf6jsn2qZPVNTopIAbe0PzyAY6HEJgkE4AnUngV47GdsM47l7Sy19ybVBcjXfwJBAL7cexK1d5Joe5inoZrW2r8NTf4FS1yZ5V8rz6m5gh5e2ieht4ss9iR0FJuk9+ys4rQ7K46sm7ZYoCYBOmIQRnECQBFzkp4LQSeceGo8f3BPrYveBjJUkkYvGuFYXoThYMBcW6b2mTNPZLl9C19JFRudBJ6W/+e+CKOioVzRdyr8z0sCQGArgl9myoJ2lJjXT6cWYWXujQtjKqB8JGQIvX6SCSbmLF4eJacBltKWnNMIcd18+8lxYziZw7lJSzi/UtdYQU4=";
    private static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1iamo1KgcGpt1AdRSrfy0jZPpJ8r26cnemJo77zm0Sqrhpz57GaZb5PdisFQ0JTPf48nyqJRvABRK1rG2QYF1P/XC7ReJKQJMaJhJ0/2QCdtc/oEdHvvQmW4UTiQ1uVbLT64exWLRpbbThXpjRK8SKxw5bXDlTdAFMpj5lNdPuQIDAQAB";
    private static final String APP_ID = "2016072400104698";

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
        return "/alipay/returnurl";
    }

    @RequestMapping("/notifyurl")
    public String notifyUrl() {
        return "/alipay/notifyurl";
    }

}
