package org.BankingSystem.controllers;

import org.BankingSystem.models.Human;
import org.BankingSystem.views.UserLoginView;

public class MainController {

    public void run() {


        SQLController.loadDB();
        SQLController.write(new Human(1, "Bob", 9, "1235", new String[]{}, new String[]{}));
        Human human = SQLController.load(1);
        assert human != null;
        System.out.println(human.toString());
        UserLoginView.loginScreen();
    }
}
