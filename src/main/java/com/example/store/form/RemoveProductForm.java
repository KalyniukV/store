package com.example.store.form;

import com.example.store.exception.PasswordException;
import com.example.store.util.CommandAsker;
import com.example.store.util.PasswordHelper;
import com.example.store.util.ValidationHelper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RemoveProductForm {
    public static final String ASK_FOR_REMOVE = "Enter product id for remove. Type \"all\" to remove all products: ";
    public static final String ASK_REMOVE_BY_ID = "For id enter only number. Or type exit for cancel";
    public static final String ASK_PASSWORD = "For remove enter password: ";

    private final CommandAsker commandAsker;
    private String id;

    public void fillOutTheForm() throws PasswordException {
        String command = commandAsker.ask(ASK_FOR_REMOVE);
        if ("all".equals(command.trim())) {
            id = command;
        } else {
            id(command.trim());
        }
        passwordValidation();
    }

    private void id(String command) {
        if (ValidationHelper.isNumeric(command)) {
            id = command;
        } else {
            command = commandAsker.ask(ASK_REMOVE_BY_ID);
            id(command);
        }
    }

    private void passwordValidation() throws PasswordException {
        Integer createdPassword = PasswordHelper.createPassword();
        String enterPassword = commandAsker.ask(ASK_PASSWORD + "\"" + createdPassword + "\"");
        if (!enterPassword.equals(createdPassword.toString())) {
            throw new PasswordException(enterPassword);
        }
    }
}
