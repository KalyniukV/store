package com.example.store.util;

import com.example.store.exception.CancelException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class CommandAsker {
    private final Scanner scanner;
    private final PrintStream out;

    public CommandAsker(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public String ask(String message) {
        out.println(message);
        String answer = scanner.nextLine();
        if (answer.equals("cancel")) {
            throw new CancelException();
        }
        return answer;
    }
}
