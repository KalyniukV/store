package com.example.store.print;

import com.example.store.util.Printer;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class PrinterTest {

    Printer printer = new Printer();

    @Test
    public void test() {
        printer.addHeader(List.of("first", "second", "third"));

        List<List<String>> dataList = new LinkedList<>();
        dataList.add(List.of("qqqq", "wwwwwwwww", "eeeee"));
        dataList.add(List.of("a", "ssss", "dddddddddddddddddddd"));
        dataList.add(List.of("zz", "xxxxxxxxxx", ""));

        printer.addData(dataList);
        printer.print();
    }


}