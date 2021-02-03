package com.myproject.springmybatis;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.myproject.springmybatis.model.Person;

import java.util.List;
import java.util.Map;

/**
 * @author: Xiaofei
 * @DATE: 2021/2/3 23:53
 */
public class ExcelTest {
    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("d:/test.xlsx"));
        List<List<Object>> readAll = reader.read();
        System.out.println(readAll);
        List<Map<String,Object>> read = reader.readAll();
        System.out.println(read);

        for (Map<String,Object> map: read) {
            System.out.println(map);
            map.get("姓名");
        }
        ExcelReader reader333 = ExcelUtil.getReader("d:/test.xlsx");
        List<Person> all = reader333.readAll(Person.class);
        System.out.println(all);

    }
}
