package com.spring.test.demo.enmu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: Xiaofei
 * @DATE: 2021/3/1 22:44
 */
public enum IVMS_SUB_ITEM {
    CHK_FILES_01("0101", "身份资料核查", "01"),
    CHK_FILES_02("0102", "医疗资料核查", "01"),
    CHK_FILES_03("0103", "医院排查", "01"),
    CHK_FILES_04("0104", "财务资料排查", "01");


    private String _key;
    private String _description;
    private String _parent;
    IVMS_SUB_ITEM(String key, String description, String parent) {
        _key = key;
        _description = description;
        _parent = parent;
    }

    public String getKey() {
        return _key;
    }

    public String getDescription() {
        return _description;
    }

    public String getParent() {
        return _parent;
    }

    public static IVMS_SUB_ITEM valueOfKey(String key) {
        Optional<IVMS_SUB_ITEM> optionalValue = Arrays.stream(IVMS_SUB_ITEM.values())
                .filter(item -> item.getKey().equals(key)).findFirst();
        if (optionalValue.isPresent())
            return optionalValue.get();
        throw new RuntimeException("aaaa");
    }


    public static List<IVMS_SUB_ITEM> valueOfParent(String parent){
       return Arrays.stream(IVMS_SUB_ITEM.values()).filter(item -> item.getParent().equals(parent)).collect(Collectors.toList());
    }

    public static Optional<IVMS_SUB_ITEM> optionalValueOfKey(String key) {
        return Arrays.stream(IVMS_SUB_ITEM.values()).filter(item -> item.getKey().equals(key)).findFirst();
    }
}
