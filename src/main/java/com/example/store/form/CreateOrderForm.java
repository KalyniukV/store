package com.example.store.form;

import com.example.store.exception.CancelException;
import com.example.store.util.CommandAsker;
import com.example.store.util.Printer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CreateOrderForm {
    public static final String ASK_PRODUCTS_ID_QUANTITY = "Enter product id and quantity";
    public static final String ASK_CONFIRM = "Confirm y/n";

    private final CommandAsker commandAsker;

    private Map<Integer, Integer> productsMap = new HashMap<>();

    public void fillOutTheForm() {
        System.out.println("[id] [quantity],...,[id] [quantity] example 1 15, 2 4");
        String productsOrder = commandAsker.ask(ASK_PRODUCTS_ID_QUANTITY);

        String[] productsIdAndQuantity = productsOrder.split(",");

        for (String prods : productsIdAndQuantity) {
            List<String> prodsList = Arrays.stream(prods.split("\\s+"))
                    .collect(Collectors.toList());
            prodsList.remove("");

           if (prodsList.size() != 2) {
               continue;
           }

           Integer id = Integer.valueOf(prodsList.get(0).trim());
           Integer quantity = Integer.valueOf(prodsList.get(1).trim());
           productsMap.put(id, quantity);
        }

        confirm();
    }

    private void confirm() {
        productsInfo();

        String confirm = commandAsker.ask(ASK_CONFIRM);
        if ("n".equalsIgnoreCase(confirm)) {
            throw new CancelException();
        }
    }

    private void productsInfo() {
        System.out.println("You crete new order items with params:");

        List<List<String>> dataList = new LinkedList<>();
        for (Integer key : productsMap.keySet()) {
            dataList.add(List.of(key.toString(), productsMap.get(key).toString()));
        }

        Printer printer = new Printer();
        printer.addHeader(List.of("product id", "quantity"));
        printer.addData(dataList);
        printer.print();
    }


    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(strNum).matches();
    }
}
