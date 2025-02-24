package org.BankingSystem.models;

public class Human {
    int position;
    String name;
    float balance;
    String password;
    String[] history;
    String[] setGoals;


    public Human(int position, String name, float balance, String password, String[] history, String[] setGoals) {
        this.position = position;
        this.name = name;
        this.balance = balance;
        this.password = password;
        this.history = history;
        this.setGoals = setGoals;
    }

    public Object[][] getData() {
        return new Object[][]{
                {position,balance, name, password},
                history,
                setGoals
        };
    }
}
