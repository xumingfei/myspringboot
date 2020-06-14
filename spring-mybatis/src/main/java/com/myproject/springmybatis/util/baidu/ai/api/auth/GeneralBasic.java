package com.myproject.springmybatis.util.baidu.ai.api.auth;

import java.net.URLEncoder;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/11 16:33
 * 文字识别
 */
public class GeneralBasic {
    public static String generalBasic() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
        try {
            // 本地文件路径
            String filePath = "C:\\Users\\Xiaofei\\Desktop\\桌面文档\\蔺凯伊\\微信图片_20200607081832.jpg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.dd2ea33838b726712315e1a4523fc0a0.2592000.1594456300.282335-20349900";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        GeneralBasic.generalBasic();
    }
}
