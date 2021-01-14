package com.spring.test.demo.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Xiaofei
 * @DATE: 2020/12/31 16:35
 */
public class ClientTest {
    private static final Logger logger = LoggerFactory.getLogger(ClientTest.class);
    public static void main(String[] args) {
        JaxWsDynamicClientFactory dynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = dynamicClientFactory.createClient("http://localhost:8111/services/helloWebService?wsdl");
        Object[] objects = new Object[0];
        try {
            objects = client.invoke("hello","234");
            System.out.println(objects[0]);
            logger.info("返回数据："+objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
