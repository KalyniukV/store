package com.example.store.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Printer {
    private List<String> headers;
    private List<List<String>> dataList;
    private Map<Integer, Integer> cellLengthMap = new HashMap<>();
    private int headersTotalCount = 0;

    public void addHeader(List<String> headers) {
        this.headers = headers;
    }

    public void addData(List dataList) {
        this.dataList = dataList;
    }

    public void print() {
        lengthCell(headers);
        dataList.forEach(this::lengthCell);
        headersTotalCount -= 1;

        System.out.println();
        if (headers != null) {
            System.out.printf((pattern()) + "%n", headers.toArray());
            System.out.println("_".repeat(headersTotalCount));
        }

        if (dataList != null && dataList.size() > 0) {
            dataList.forEach(d -> {
                System.out.printf((pattern()) + "%n", d.toArray());
            });
            System.out.println("_".repeat(headersTotalCount));
        }
        System.out.println();
    }

    private String pattern() {
        String result = "";
        for (int i = 0; i < cellLengthMap.size(); i++) {
            Integer length = cellLengthMap.get(i);
            result += patternLength(length);
        }
        return result;
    }

    private String patternLength(Integer l) {
        return "%-" + l + "s | ";
    }

    private void lengthCell(List<String> dataList) {
        if (dataList == null) {
            return;
        }
        for (int i = 0; i < dataList.size(); i++) {
            int incomeLength = dataList.get(i).length();
            if (cellLengthMap.containsKey(i)) {
                Integer length = cellLengthMap.get(i);
                if (incomeLength > length) {
                    cellLengthMap.put(i, incomeLength);
                    headersTotalCount = (headersTotalCount - length + incomeLength);
                }
            } else {
                cellLengthMap.put(i, incomeLength);
                headersTotalCount += incomeLength + 3;
            }
        }
    }
}
