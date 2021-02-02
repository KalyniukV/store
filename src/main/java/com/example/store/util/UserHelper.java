package com.example.store.util;

import java.util.Random;

public class UserHelper {

    public static Integer getUserId() {
        return new Random()
                .ints(1, 10)
                .findFirst()
                .getAsInt();
    }

}
