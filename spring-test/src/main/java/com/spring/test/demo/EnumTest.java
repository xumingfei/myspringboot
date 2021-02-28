package com.spring.test.demo;

import com.spring.test.demo.enmu.IVMS_MGR_STATUS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Xiaofei
 * @DATE: 2021/2/27 13:03
 */
public class EnumTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnumTest.class);
    public static void main(String[] args) {
        LOGGER.warn(IVMS_MGR_STATUS.NEW.getDescription());
        LOGGER.info("[{}]",IVMS_MGR_STATUS.optionValueOfKey("00").get());
        LOGGER.error("[{}]",IVMS_MGR_STATUS.optionValueOfKey("00").get());
    }
}
