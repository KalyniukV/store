package com.example.store.form;

import com.example.store.util.CommandAsker;
import com.example.store.util.ValidationHelper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateProductForm {
    public static String ASK_NAME = "Enter name: ";
    public static String ASK_PRICE = "Enter price: ";

    private final CommandAsker commandAsker;


    private String name;
    private Integer price;

    public void fillOutTheForm() {
        name();
        price();
    }

    private void name() {
        name = commandAsker.ask(ASK_NAME);
    }

    private void price() {
        String price = commandAsker.ask(ASK_PRICE);
        if (ValidationHelper.isNumeric(price)) {
            this.price = Integer.valueOf(price);
        } else {
            System.out.println("Enter only number or \"cancel\" for cancel operation");
            price();
        }
    }

}
