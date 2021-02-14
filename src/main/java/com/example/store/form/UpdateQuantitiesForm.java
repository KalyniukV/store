package com.example.store.form;

import com.example.store.util.CommandAsker;
import com.example.store.util.ValidationHelper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateQuantitiesForm {

    public static final String ASK_FOR_UPDATE = "enter through space order id, product id, new quantity: ";

    private final CommandAsker commandAsker;

    private Integer orderId;
    private Integer productId;
    private Integer quantity;

    public void fillOutTheForm() {
        String update = commandAsker.ask(ASK_FOR_UPDATE);
        String[] formData = update.split("\\s+");

        try {
            orderId = numberValidation(formData[0], "orderId");
            productId = numberValidation(formData[1], "productId");
            quantity = numberValidation(formData[2], "quantity");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            fillOutTheForm();
        }

    }

    private Integer numberValidation (String value, String name) {
        if (ValidationHelper.isNumeric(value)) {
           return Integer.valueOf(value);
        } else {
            throw new NumberFormatException(name + " is not numeric. Enter only number or \"cancel\" for cancel operation");
        }
    }
}
