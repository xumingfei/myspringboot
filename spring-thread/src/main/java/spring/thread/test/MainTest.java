package spring.thread.test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class MainTest {
    public static void main(String[] args) {
        System.out.println(new Date().getTime());
        System.out.println(new Date());
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 01, 04, 10, 10, 5);
        System.out.println(localDateTime1);

        //获取本初子午线的时间
        Instant now = Instant.now();
        System.out.println(now);
        //东八区时间   偏移量
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        // java.time.format.DateTimeFormatter 格式化或者解析时间
        // 1. 预定义的标准格式  ISO_LOCAL_DATE_TIME,ISO_LOCAL_DATE,ISO_LOCAL_TIME
        // 格式化日期 --- > 字符串
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime2 = LocalDateTime.now();
        System.out.println(formatter.format(localDateTime2));
        System.out.println(localDateTime2);
        //字符串转为时间
        TemporalAccessor parse = formatter.parse("2020-01-04T11:22:20.467");
        System.out.println(parse);
        // 方式二
        // 本地化相关格式      ofLocalizedDateTime()
        // FormatStyle.LONG     /   FormatStyle.MEDIUM  /   FormatStyle.SHORT
        DateTimeFormatter dateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        System.out.println(dateTime.format(localDateTime2));
    }
}
