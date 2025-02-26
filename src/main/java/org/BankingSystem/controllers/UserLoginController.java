package org.BankingSystem.controllers;

import org.BankingSystem.models.Human;

import java.util.HashMap;

public class UserLoginController {
    private static HashMap<String, String> users = new HashMap<>();

<<<<<<< Updated upstream
=======
    //TODO make it so the username and passwords are stored into the database
    // and make it so they are re added to the HashMap when the application is ran

    public UserLoginController() {
        Human[] humans = SQLController.loadAll();
        for (Human human : humans) {
            users.put(human.getLogin()[0], human.getLogin()[1]);
        }
    }


>>>>>>> Stashed changes
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
        SQLController.insertNewUser(username, 0, password, "No history provided.", "No set goals provided.", SQLController.loadAll().length+1);

    }
}
