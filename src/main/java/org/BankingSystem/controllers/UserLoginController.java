package org.BankingSystem.controllers;

import java.util.HashMap;

public class UserLoginController {
    private static HashMap<String, String> users = new HashMap<>();

    public boolean registerUser(String username) {

        if (username.isBlank()) {
            return false;
        } else {
        if (users.containsKey(username)) {
            return false;
        }else{
            return true;
        }
        }
    }

    public boolean registerPassword(String password) {
        return (password.length() >= 3);
    }

    public void register(String username, String password) {
        users.put(username, password);


    }
}
