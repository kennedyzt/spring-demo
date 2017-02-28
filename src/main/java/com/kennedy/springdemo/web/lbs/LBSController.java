package com.kennedy.springdemo.web.lbs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("lbs")
public class LBSController {
    private static final String GETLOCURL = "http://api.cellocation.com/cell/?mcc=MCC&mnc=MNC&lac=LAC&ci=CI&output=OUTPUT";

    @RequestMapping("/toloc")
    public String toLoc() {
        return "/lbs/loc";

    }

    /**
     * 混合定位接口 基于临近基站和WIFI热点的高精度混合基站定位
     */
    @RequestMapping("/loc")
    @ResponseBody
    public String getLoc(@RequestParam("mcc") String mcc, @RequestParam("mnc") String mnc, @RequestParam("lac") String lac, @RequestParam("ci") String ci, @RequestParam("coord") String coord,
                         @RequestParam("output") String output, HttpServletResponse response) {
        String requestUrl = GETLOCURL.replace("MCC", mcc).replace("MNC", mnc).replace("LAC", lac).replace("CI", ci).replace("COORD", coord).replace("OUTPUT", output);
        String jsonObject = sendGet(requestUrl);
        response.setContentType("text/plain;charset=UTF-8");
        return jsonObject;
    }

    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
