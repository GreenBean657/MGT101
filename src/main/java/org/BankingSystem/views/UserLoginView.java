package org.BankingSystem.views;

import org.BankingSystem.controllers.UserLoginController;

import java.util.Scanner;

public class UserLoginView {

    private static Scanner scanner = new Scanner(System.in);
    static UserLoginController controller = new UserLoginController();

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
}
