package com.example.store.util;

import java.util.Random;

public class PasswordHelper {

    public static Integer createPassword() {
        return new Random()
                .ints(100, 999)
                .findFirst()
                .getAsInt();
    }

}
