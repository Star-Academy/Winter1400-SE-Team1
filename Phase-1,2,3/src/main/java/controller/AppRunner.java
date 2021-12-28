package controller;

import exception.BaseDirectoryInvalidException;
import exception.SearchException;
import model.Document;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class AppRunner {
    private static AppRunner instance;

    private AppRunner() {
    }

    public static AppRunner getInstance() {
        if (instance == null) {
            instance = new AppRunner();
        }
        return instance;
    }

    public void startSearchEngine() {
        AppController controller = new AppController();
        try {
            controller.init();
        } catch (BaseDirectoryInvalidException | IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = getInput(scanner);
            if (input == null) break;
            searchAndDisplay(controller, input);
        }
    }

    public void searchAndDisplay(AppController controller, String input) {
        try {
            Set<Document> results = controller.search(input);
            controller.printResults(results);
        } catch (SearchException e) {
            e.printStackTrace();
        }
    }

    public String getInput(Scanner scanner) {
        String input = scanner.nextLine();
        return input.equals("*exit") ? null : input;
    }
}

