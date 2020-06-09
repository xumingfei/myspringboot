package com.myproject.springaop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.myproject.springaop")
@EnableAspectJAutoProxy
public class AopConfig {

}
