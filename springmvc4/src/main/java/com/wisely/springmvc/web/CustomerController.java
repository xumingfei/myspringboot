package com.wisely.springmvc.web;

import com.wisely.springmvc.pojo.Customer;
import com.wisely.springmvc.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/10 14:11
 */
@Controller
public class CustomerController {

    @RequestMapping("/testJson")
    @ResponseBody
    public Customer testJson(@RequestBody Customer customer) {
        System.out.println(customer);
        return customer;
    }

    @RequestMapping("/form")
    @ResponseBody
    public Customer formSubmit(Customer customer) {
        System.out.println(customer);
        return customer;
    }

    /**
     * RESTful风格请求,接收方式为get
     */
    @GetMapping("/customer/{id}")
    @ResponseBody
    public Result<Customer> selectCustomer(@PathVariable("id") Integer id) {
        System.out.println(id);
        Result<Customer> result = new Result();
        Customer customer = new Customer();
        if (id == 10) {
            customer.setLoginname("zhangsan");
            result.setData(customer);
        }

//        try {
//            int i=5/0;
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setMsg("发生异常");
//        }
        result.setCode("200");
        result.setMsg("查询成功");
        System.out.println(result);
        return result;
    }
}
