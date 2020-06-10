package com.wisely.springmvc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/8 10:59
 */
@RestController
public class MvcController {
    @GetMapping("/annotation")
    public Map<String, Object> requestParam(
            @RequestParam("int_val") Integer intVal, @RequestParam("long_val") Long longVal
            , @RequestParam("str_val") String strVal
    ) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("intVal", intVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("strVal", strVal);
        return paramsMap;
    }

    @GetMapping("/requestArr")
    public Map<String, Object> requestArray(int[] intArr, Long[] longArr, String[] strArr) {
        Map<String, Object> map = new HashMap<>();
        map.put("intArr", intArr);
        map.put("longArr", longArr);
        map.put("strArr", strArr);
        return map;
    }

}
