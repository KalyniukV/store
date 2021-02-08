package com.example.store;

import com.example.store.exception.CancelException;
import com.example.store.exception.ExecutorNotFoundException;
import com.example.store.executor.Executor;
import com.example.store.executor.ExecutorFactory;
import com.example.store.util.CommandAsker;
import com.example.store.util.Printer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Component
@Profile("production")
public class Application implements CommandLineRunner {
    @Autowired
    CommandAsker commandAsker;
    @Autowired
    ApplicationContext context;

    @Override
    @Transactional
    public void run(String... args) {
        mainMenu();

        while (true) {
            String command = commandAsker.ask("\nMain menu.\nEnter name and command: ");
            if (command.equals("exit")) {
                break;
            }

            String[] executeArr = command.split("\\s+");
            execute(executeArr);
        }
    }

    private void execute(String[] executeArr) {
        try {
            ExecutorFactory executorFactory = (ExecutorFactory) context.getBean(executeArr[0] + "ExecutorFactory");
            Executor executor = executorFactory.getExecutor(executeArr[1]);
            executor.execute();
        } catch (BeansException e) {
            System.out.println("Wrong name: " + executeArr[0] + "\n");
        } catch (ExecutorNotFoundException | CancelException e) {
            System.out.println(e.getMessage());
        }
    }


    private void mainMenu() {
        String head = String.format("%-9s%-30s%-36s%s", "name", "|", "command", "|");
        String separate = "_".repeat(75);
        String info = "enter [name] [command] For exit from application enter \"exit\"\n";

        List<List<String>> dataList = new LinkedList<>();
        dataList.add(List.of("products","create", "remove", "show_all", "show_in_order"));
        dataList.add(List.of("orders", "create", "update_quantity", "show_orders_entry", "show_orders_info"));

        System.out.println(head);
        System.out.print(separate);

        Printer printer = new Printer();
        printer.addData(dataList);
        printer.print();

        System.out.print(info);
    }
}
