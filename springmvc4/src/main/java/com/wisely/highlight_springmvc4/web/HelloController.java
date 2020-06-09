package com.wisely.highlight_springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/index")
//    @ResponseBody
    public String hello() {
        return "index";
    }

    @RequestMapping("index2")
    public String redirect(){
        return "redirect:index";
    }

    @RequestMapping("/forward")
    public String forward(){
        return "forward:hello2";
    }

    @RequestMapping("hello2")
    @ResponseBody
    public String hello2(){
        return "hello2";
    }

    @RequestMapping("/eeee444")
    public String hello3(){
        String str = "123";
        String s = "222222";
        return null;
    }

}
