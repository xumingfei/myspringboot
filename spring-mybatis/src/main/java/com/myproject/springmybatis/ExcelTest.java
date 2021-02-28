package com.myproject.springmybatis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.sql.SqlBuilder;
import com.myproject.springmybatis.model.Person;

/**
 * @author: Xiaofei
 * @DATE: 2021/2/3 23:53
 */
public class ExcelTest {
    public static void main(String[] args) {
        /*ExcelReader reader = ExcelUtil.getReader(FileUtil.file("d:/test.xlsx"));
        List<List<Object>> readAll = reader.read();
        System.out.println(readAll);
        List<Map<String, Object>> read = reader.readAll();
        System.out.println(read);

        for (Map<String, Object> map : read) {
            System.out.println(map);
            map.get("姓名");
        }
        ExcelReader reader333 = ExcelUtil.getReader("d:/test.xlsx");
        List<Person> all = reader333.readAll(Person.class);
        System.out.println(all);
        System.out.println(SystemUtil.getRuntimeInfo());
        ;

        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

//图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write("d:/line.png");
//输出code
        Console.log(lineCaptcha.getCode());
//验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");

//重新生成验证码
        lineCaptcha.createCode();
        lineCaptcha.write("d:/line.png");
//新的验证码
        Console.log(lineCaptcha.getCode());
//验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");
        IdUtil.getSnowflake(3, 3);

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.stream().forEach(str -> System.out.println(str));
        list.stream().forEach(System.out::println);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "2", "3",23));
        personList.add(new Person(2L, "2", "3",23));
        personList.add(new Person(3L, "2", "3",23));
        personList.add(new Person(4L, "2", "3",23));
        Optional<Person> first = personList.stream().filter(item -> item.getId() == 1L).findFirst();
        System.out.println(first.get());

         */
        Person person = new Person();
        person.setCreateTime(DateUtil.now());
        person.setAge(12);
        person.setGrade("aa");
        person.setMobile("18888888888");
        Entity entity = Entity.parseWithUnderlineCase(person).setTableName("PERSON");
        entity.remove("create_time");
        entity.addFieldNames("createTime");
        entity.set("create_time", DateUtil.now());
        entity.put("create_time",DateUtil.now());
        SqlBuilder sqlBuilder = SqlBuilder.create().insert(entity);
        System.out.println(sqlBuilder.build());
    }
}
