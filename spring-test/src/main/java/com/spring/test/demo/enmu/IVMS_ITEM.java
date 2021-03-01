package com.spring.test.demo.enmu;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author: Xiaofei
 * @DATE: 2021/3/1 22:36
 */
public enum IVMS_ITEM {

    CHK_FILES("01", "文件核查"),
    CHK_FIELD("02", "实地调查"),
    CHK_NET("03", "网络与通路调查"),
    COOPERATION("04", "其他地方合作调查"),
    OTHER("05", "其他调查");
    private String _key;
    private String _description;

    IVMS_ITEM(String key, String description) {
        _key = key;
        _description = description;
    }

    public String getKey() {
        return _key;
    }

    public String getDescription() {
        return _description;
    }

    public static IVMS_ITEM valueOfKey(String key) {
        Optional<IVMS_ITEM> optionalValue = Arrays.stream(IVMS_ITEM.values()).filter(item -> item.getKey().equals(key)).findFirst();
        if (optionalValue.isPresent()) {
            return optionalValue.get();
        }
        throw new RuntimeException("Can't find enum IVMS_ITEM for key " + key);
    }

    public static Optional<IVMS_ITEM> optionalValueOfKey(String key) {
        return Arrays.stream(IVMS_ITEM.values()).filter(item -> item.getKey().equals(key)).findFirst();
    }
}
