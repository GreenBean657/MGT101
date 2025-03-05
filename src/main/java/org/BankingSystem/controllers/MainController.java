package org.BankingSystem.controllers;

import org.BankingSystem.models.Human;
import org.BankingSystem.views.UserLoginView;

public class MainController {

    public void run() {
        SQLController.loadDB();
        UserLoginView.loginScreen();
    }
}
