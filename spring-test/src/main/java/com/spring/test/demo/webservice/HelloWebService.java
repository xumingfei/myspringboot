package com.spring.test.demo.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author: Xiaofei
 * @DATE: 2020/12/31 16:07
 */
@WebService(name = "HelloWebService",targetNamespace = "http://demo.test.spring.com")
public interface HelloWebService {
    @WebMethod
    public String hello(String param);
}
