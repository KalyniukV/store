package com.example.store.print;

import com.example.store.util.Printer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrinterTest {

    ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    public void test() {
        List<List<String>> dataList = new LinkedList<>();
        dataList.add(List.of("qqqq", "wwwwwwwww", "eeeee"));
        dataList.add(List.of("a", "ssss", "dddddddddddddddddddd"));
        dataList.add(List.of("zz", "xxxxxxxxxx", ""));

        Printer printer = new Printer();
        printer.addHeader(List.of("first", "second", "third"));
        printer.addData(dataList);
        printer.print();

        String table = "first | second     | third                | \r\n" +
                       "___________________________________________\r\n" +
                       "qqqq  | wwwwwwwww  | eeeee                | \r\n" +
                       "a     | ssss       | dddddddddddddddddddd | \r\n" +
                       "zz    | xxxxxxxxxx |                      | \r\n" +
                       "___________________________________________";

        assertEquals(table, outputStreamCaptor.toString().trim());
    }


}