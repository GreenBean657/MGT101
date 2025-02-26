package org.BankingSystem.views;

import org.BankingSystem.controllers.UserLoginController;

import java.util.Scanner;

public class UserLoginView {

    private static Scanner scanner = new Scanner(System.in);
    static UserLoginController controller = new UserLoginController();
    static UserMenuView view = new UserMenuView();

    public static void loginScreen() {
        while (true) {
            System.out.println("Welcome! Choose an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    public static void register() {
        String username;
        String password;

            do {
                System.out.println("Enter your Username: ");
                username = scanner.nextLine();

                if (!controller.registerUser(username)) {
                    System.out.println("Username is already in use. Please try again.");

                } else {
                    System.out.println("Username is registered successfully.");
                    break;
                }
            } while (true);

            do {
                System.out.println("Enter your Password: ");
                password = scanner.nextLine();

                if (!controller.registerPassword(password)) {
                    System.out.println("Password is less then 3 digits, please try again.");
                } else {
                    System.out.println("Password is valid.");
                    break;
                }
            } while (true);

        System.out.println("You are now registered successfully, please login.");
        controller.register(username, password);
        loginScreen();



    }

    private static void login() {

        //TODO Need to add features after you login

        String username;

        do {
            System.out.println("Enter your Username: ");
            username = scanner.nextLine();

            if (!controller.loginUsername(username)) {
                System.out.println("This username cannot be found. Please try again.");
            }else {
                System.out.println("Username was found successfully.");
                break;
            }
        }
        while (true);

        do {
            System.out.println("Enter your Password: ");
            String password = scanner.nextLine();
            if (!controller.login(username, password)) {
                System.out.println("Invalid password. Please try again.");
            }else {
                System.out.println("You are now logged in successfully. \n " +
                        "Sending you to your banking account now");

                break;
            }
        }
        while (true);

        view.userMenu();

    }
}
