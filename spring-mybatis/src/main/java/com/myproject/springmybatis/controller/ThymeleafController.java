package com.myproject.springmybatis.controller;

import com.myproject.springmybatis.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/9 9:54
 * //ModelAndView中添加属性或对象   thymeleaf页面无法解析,
 * Map,ModelMap,Model三种对象添加对象可以解析
 * ModelMap通过put方法增加的属性在html中会有红色下划线,通过 addAttribute 方法则可以解析
 * Map方法通过put可以解析
 * Model通过addAttribute可以解析
 */
@Controller
public class ThymeleafController {
    @RequestMapping("thymeleaf")
    public String thymeleaf(ModelMap map, Map map1, ModelAndView mv, Model model){
        map.addAttribute("arg", "1");
        map.addAttribute("arg2", "2");
        map1.put("arg3", 333);
        model.addAttribute("user", new Person(2L, "lisi", "'123'",17));
        map.addAttribute("thObject",new Person(2L, "lisi", "'123'",20));
//        map1.put("user",new Person(2L, "lisi", "'123'",20));
        model.addAttribute("model", "model属性2222");
        model.addAttribute("name","<span style='color:pink'>Jerry</span>");
        map1.put("date", new Date());
        mv.addObject("aaa", "bbb");

        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Person p = new Person();
            p.setId(Long.valueOf(i+1));
            p.setPassword("i");
            p.setUserName("张三"+i);
            p.setAge(new Random().nextInt(20));
            p.setMobile("18832828888");
            list.add(p);
        }
        map.put("persons", list);
        return "thymeleaf";
    }

    @RequestMapping("/thymeleaf/list")
    public String list(Map map) {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Person p = new Person();
            p.setId(Long.valueOf(i));
            p.setPassword("i");
            p.setUserName("张三"+i);
            p.setAge(new Random().nextInt(20));
            p.setMobile("18832828888");
            list.add(p);
        }
        map.put("persons", list);
        return "thymeleaf";
    }
}
