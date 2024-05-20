package com.digitalfutures.app;

import java.util.Scanner;

public class ConsoleManager {

    Scanner scanner = new Scanner(System.in);

    public String getInput() {
        return scanner.nextLine();
    }

    public String collectName(Scanner sc) {
        System.out.println("Please Enter the Contacts Name");
        return sc.nextLine();
    }

    public String collectNumber(Scanner sc) {
        System.out.println("Please Enter the Contacts Phone Number");
        return sc.nextLine();
    }

    public String collectEmail(Scanner sc) {
        System.out.println("Please Enter the Contacts Email Address");
        return sc.nextLine();
    }
}
