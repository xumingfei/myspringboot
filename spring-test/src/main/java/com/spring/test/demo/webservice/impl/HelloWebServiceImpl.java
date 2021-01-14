package com.spring.test.demo.webservice.impl;

import com.spring.test.demo.webservice.HelloWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @author: Xiaofei
 * @DATE: 2020/12/31 16:08
 */
@WebService(serviceName = "HelloWebService",targetNamespace = "http://demo.test.spring.com",endpointInterface = "com.spring.test.demo.webservice.HelloWebService")
@Service
public class HelloWebServiceImpl implements HelloWebService {
    private static final Logger logger = LoggerFactory.getLogger(HelloWebServiceImpl.class);
    @Override
    public String hello(String param) {
        logger.info(param);
        return "HelloWebService";
    }
}
