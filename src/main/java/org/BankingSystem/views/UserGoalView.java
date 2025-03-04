package org.BankingSystem.views;

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
            System.out.print(loadedGoal + " ");
        }
    }
}
