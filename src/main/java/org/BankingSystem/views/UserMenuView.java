package org.BankingSystem.views;

import org.BankingSystem.controllers.SQLController;
import org.BankingSystem.models.Human;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class UserMenuView {



    public void userMenu(String username, String password) {


        Human[] human = SQLController.loadAll();

        Human loadedHuman = null;

        for (Human h : human) {
            String[] login = h.getLogin();
            if (Objects.equals(login[0], username) && Objects.equals(login[1], password)) {
                loadedHuman = h;
            }
        }

        if (loadedHuman == null) {
            throw new InputMismatchException("User not found");
        }

        BufferedReader bReader = new BufferedReader(new java.io.InputStreamReader(System.in));
        boolean exit = false;
        do {
            System.out.println("-------Welcome to Banking System Menu------- \n "
                    + "--Please Select The Action you want to do-- \n" +
                    "-- 1. See current bank info \n" +
                    //TODO Shows info like, balance, current financial goal, and transaction history
                    "-- 2. Set Financial Goal \n " +
                    //TODO Sets the persons Financial goal, and adds it to database, also allows them to change it if one already exists
                    "-- 3. Add money to account  \n" +
                    "-- 4. Log Out ");
            //TODO Logs them out and puts them back into the register/login screen

            if (exit) {
                break;
            }
            try {
                String line =  bReader.readLine();
                int choice = Integer.parseInt(line);
                switch (choice) {
                    case (1) -> {
                        new BankInfoView().run(loadedHuman);
                    }

                    case (2) -> {
                        UserGoalView.goalSet(loadedHuman);
                    }
                    case (3) -> {
                        Scanner savingsOrChecking = new Scanner(System.in);
                        Scanner amtToAdd = new Scanner(System.in);
                        System.out.print("Enter the account you'd like to add to (checking or savings): ");
                        switch (savingsOrChecking.nextLine()) {
                            case "checking":
                                float balance = loadedHuman.getBalance();
                                System.out.print("Enter the amount you want to add as a decimal: ");
                                float newBalance = balance + amtToAdd.nextFloat();
                                loadedHuman.setBalance(newBalance);
                                break;
                            case "savings":
                                float savings = loadedHuman.getSavingsBalance();
                                System.out.print("Enter the amount you want to add as a decimal: ");
                                float newSavings = savings + amtToAdd.nextFloat();
                                loadedHuman.setSavingsBalance(newSavings);
                                break;
                        }
                    }
                    case (4) -> {
                        exit = true;
                        System.out.println("Exiting program. Goodbye!");
                        SQLController.write(loadedHuman);
                        System.exit(0);

                    }
                }
            } catch (NumberFormatException | IOException | InputMismatchException ignored){}

        } while (true);
    }
}
