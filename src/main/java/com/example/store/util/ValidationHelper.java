package com.example.store.util;

import java.util.regex.Pattern;

public class ValidationHelper {

    public static void createProductValidation(String name, Integer price) {
        StringBuffer error = new StringBuffer();
        if (name.isBlank()) {
            error.append("Name could not be blank");
        }


    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(strNum).matches();
    }
}
