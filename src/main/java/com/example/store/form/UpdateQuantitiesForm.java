package com.example.store.form;

import com.example.store.util.CommandAsker;
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

        orderId = Integer.valueOf(formData[0]);
        productId = Integer.valueOf(formData[1]);
        quantity = Integer.valueOf(formData[2]);
    }
}
