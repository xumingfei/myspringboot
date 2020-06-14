package com.myproject.springmybatis.util.baidu.ai.api.auth;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/11 16:42
 * 身份证识别
 */

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class IdCard {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String idcard() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        try {
            // 本地文件路径
            String filePath = "C:\\Users\\Xiaofei\\Desktop\\桌面文档\\蔺凯伊\\微信图片_20200607081832.jpg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "id_card_side=" + "front" + "&image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.dd2ea33838b726712315e1a4523fc0a0.2592000.1594456300.282335-20349900";

            String result = HttpUtil.post(url, accessToken, param);
            Map<String,Map<String,Object>> map = GsonUtils.fromJson(result, HashMap.class);
            Map<String, Object> word = map.get("words_result");
            Map<String,Object> words = (Map<String, Object>) word.get("住址");
            System.out.println(word.get("住址"));;
//            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        IdCard.idcard();
    }
}
