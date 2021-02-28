package com.spring.test.demo.enmu;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author: Xiaofei
 * @DATE: 2021/2/27 12:56
 */
public enum IVMS_MGR_STATUS {
    NEW("00", "未分配"),
    ASSIGN("01", "已分配");

    private String _key;
    private String _description;

    IVMS_MGR_STATUS(String key, String description) {
        _key = key;
        _description = description;
    }

    public String getKey() {
        return _key;
    }

    public String getDescription() {
        return _description;
    }

    public static IVMS_MGR_STATUS valueOfKey(String key) {
        Optional<IVMS_MGR_STATUS> optionValue = Arrays.stream(IVMS_MGR_STATUS.values())
                .filter(item -> item.getKey().equals(key)).findFirst();
        if (optionValue.isPresent()) {
            return optionValue.get();
        }
        throw new RuntimeException("Can't find enum IVMS_MGR_STATUS for key " + key);
    }

    public static Optional<IVMS_MGR_STATUS> optionValueOfKey(String key) {
        return Arrays.stream(IVMS_MGR_STATUS.values()).filter(item -> item.getKey().equals(key)).findFirst();
    }

}
