package com.itheima.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {

    private String datePattern = "yyyy-MM-dd";

    public Date convert(String source) {
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException("无效的日期格式，请使用这种格式:"+datePattern);
        }
    }
}
