package com.spring.test.demo;

import com.spring.test.demo.enmu.IVMS_MGR_STATUS;

/**
 * @author: Xiaofei
 * @DATE: 2021/2/27 13:03
 */
public class EnumTest {
    public static void main(String[] args) {
        System.out.println(IVMS_MGR_STATUS.NEW.getDescription());
        System.out.println(IVMS_MGR_STATUS.optionValueOfKey("00").get());
    }
}
