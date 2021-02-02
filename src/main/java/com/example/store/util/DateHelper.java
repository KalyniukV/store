package com.example.store.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public static String dateWithoutSeconds(String date) {
        return date.replaceAll("(:\\d{2}(\\.\\d+))?", "");
    }

    public static String dateWithoutSeconds(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }
}
