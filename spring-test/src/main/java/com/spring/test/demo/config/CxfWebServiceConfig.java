package com.spring.test.demo.config;

import com.spring.test.demo.webservice.HelloWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: Xiaofei
 * @DATE: 2020/12/31 16:12
 */
@Configuration
public class CxfWebServiceConfig {
    @Autowired
    private Bus bus;

    @Autowired
    private HelloWebService helloWebService;

    @Bean
    public EndpointImpl endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, helloWebService);
        endpoint.publish("/helloWebService");
        return endpoint;
    }
}
