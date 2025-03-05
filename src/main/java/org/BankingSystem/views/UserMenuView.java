package org.BankingSystem.views;

import org.BankingSystem.controllers.SQLController;
import org.BankingSystem.models.Human;

import java.io.BufferedReader;
import java.io.IOException;
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

        BufferedReader bReader = new BufferedReader(new java.io.InputStreamReader(System.in));
        do {
            Scanner menuChoice = new Scanner(System.in);

            System.out.println("-------Welcome to Banking System Menu------- \n "
                    + "--Please Select The Action you want to do-- \n" +
                    "-- 1. See current bank info \n" +
                    //TODO Shows info like, balance, current financial goal, and transaction history
                    "-- 2. Set Financial Goal \n " +
                    //TODO Sets the persons Financial goal, and adds it to database, also allows them to change it if one already exists
                    "-- 3. Add money to account  \n" +
//TODO Allows them to add money into a savings account for them to start getting interest or into their normal checking account
                    "-- 4. Log Out ");
            //TODO Logs them out and puts them back into the register/login screen

            switch (menuChoice.nextInt()) {
                case 1 -> {

                }

                case 2 -> {

                }
                case 3 -> {
                    Scanner amtToAdd = new Scanner(System.in);
                    assert loadedHuman != null;
                    float balance = loadedHuman.getBalance();
                    System.out.print("Enter the amount you want to add as a decimal: ");
                    float newBalance = balance + amtToAdd.nextFloat();
                    loadedHuman.setBalance(newBalance);
                }
                default -> {
                    System.out.println("Invalid choice");
                    }
            }

            try {
                String line =  bReader.readLine();
                int choice = Integer.parseInt(line);
                switch (choice) {
                    case (2) -> {
                        assert loadedHuman != null;
                        UserGoalView.goalSet(loadedHuman);
                    }
                    case (4) -> {
                        System.out.println("Exiting program. Goodbye!");
                        assert loadedHuman != null;
                        SQLController.write(loadedHuman);
                        System.exit(0);
                    }
                }
            } catch (NumberFormatException | IOException ignored) {}

        } while (true);
    }


}
