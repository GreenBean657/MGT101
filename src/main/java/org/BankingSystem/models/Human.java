package org.BankingSystem.models;

import static org.BankingSystem.models.TypeOfTransaction.OTHER;

public class Human {
    int position;
    String name;
    float balance;
    String password;
    TransactionHistory[] transHistory = new TransactionHistory[5];
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

    public String[] getLogin() {
        return new String[]{name, password};
    }

    public Object[][] getData() {
        return new Object[][]{
                {position,balance, name, password},
                history,
                setGoals
        };
    }
    public void historyTemplate(){
        for (int i = 0; i < transHistory.length; i++) {
            transHistory[i] = new TransactionHistory("Filler","1/1/20XX", 9.99f, OTHER);
            balance = balance - transHistory[i].amount;
        }
        for (int i = 0; i < 5; i++) {
            if(history[i] == null) {
                history[i] = transHistory[i].toString();
            }
            System.out.println(history[i]);
        }
    }
}
