package com.example.myapplication124124124124;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Functions {
    public String getData(){
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    public static String numbers(String source) {
        StringBuilder result = new StringBuilder(100);
        for (char ch : source.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                result.append(ch);
            }
        }

        return result.toString();
    }
}
