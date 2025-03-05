package org.BankingSystem.views;

import org.BankingSystem.controllers.SQLController;
import org.BankingSystem.models.Human;

import static org.BankingSystem.views.UserLoginView.scanner;

public class UserGoalView {

    public static void goalSet(Human loadedhuman){

        System.out.print("Enter your financial goal\n");
        String goal = scanner.nextLine();

        String[] goals = loadedhuman.getGoals();
        String[] newGoals = new String[goals.length+1];
        System.arraycopy(goals, 0, newGoals, 0, goals.length);
        newGoals[goals.length] = goal;

        loadedhuman.setGoals(newGoals);

        for (String loadedGoal : newGoals) {
            System.out.print(loadedGoal + " \n");
        }

        System.out.println("Would like to view all your goals \n" + "1. Yes\n" + "2. No \n");
       do {
           String answer = scanner.nextLine();
           if (answer.equals("1")) {
               userGoalView();
           } else if (answer.equals("2")) {
               System.out.println("opening menuView again\n");
           } else {
               System.out.println("Invalid answer, please try again\n");
           }
       }while (true);
    }

    public static void userGoalView(){


        Human[] humans = SQLController.loadAll();
        for (Human human : humans) {
            System.out.print(human.getGoals()[0] + " ");
        }

    }
}
