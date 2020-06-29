package com.myproject.springmybatis.util.baidu.ai.api.auth;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/11 17:10
 */
public class Sample {

    //设置APPID/AK/SK   api-key有效期一个月.
    public static final String APP_ID = "20349900";
    public static final String API_KEY = "dVZxWisqAm1nIuCbILQtdZGU";
    public static final String SECRET_KEY = "c5znXO8WFwxZjq4Oj4yrd0aPyqhFdutc";
    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:\\Users\\Xiaofei\\Desktop\\桌面文档\\蔺凯伊\\微信图片_20200607081832.jpg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
    }

}
