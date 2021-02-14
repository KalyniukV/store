package com.example.store.util;

import java.util.regex.Pattern;

public class ValidationHelper {

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(strNum).matches();
    }
}
