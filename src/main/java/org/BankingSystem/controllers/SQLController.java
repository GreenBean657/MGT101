package org.BankingSystem.controllers;

import org.BankingSystem.models.Human;

import java.sql.*;


public class SQLController {

    private static final String path = "jdbc:sqlite:mainDB.sqlite";

    private static Connection conn = null;


    public static void loadDB() {
        System.out.println("TRYING");
        try {
           conn = DriverManager.getConnection(path);

           Statement stmt = conn.createStatement();
           //String query = "INSERT INTO users (name) VALUES ('John')";
           //stmt.executeUpdate(query);
            //PreparedStatement pstmt = conn.prepareStatement(query);
            //pstmt.executeUpdate();
            //System.out.println("DONE");

        } catch (SQLException exception) {
           System.out.println(exception.getMessage());
        }
    }

    public static void write(Human human) {

        Object[][] data = human.getData();
        int position;
        float balance;
        String name;
        String password;

        String[] history;
        String[] setGoals;
        try {
            position = (int) data[0][0];
            balance = (float) data[0][1];
            name = (String) data[0][2];
            password = (String) data[0][3];

            history = (String[]) data[1];
            setGoals = (String[]) data[2];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("Corrupted Data detected!");
        }

        if (position < 0) throw new IllegalArgumentException("Position cannot be negative!");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty!");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password cannot be null or empty!");
        if (balance < 0) throw new IllegalArgumentException("Balance cannot be negative!");

        if (history == null || history.length == 0) history = new String[]{"No history provided."};
        if (setGoals == null || setGoals.length == 0) setGoals = new String[]{"No set goals provided."};

        RunAndReplace(history);
        RunAndReplace(setGoals);

        StringBuilder builder = new StringBuilder();
        for (String s : history) {
            builder.append(s).append('`');
        }

        String finalHistory = builder.toString();
        builder = new StringBuilder();

        for (String s : setGoals) {
            builder.append(s);
        }

        String finalSetGoals = builder.toString();

        String query = "UPDATE users SET name = ?, balance = ?, password = ?, history = ?, setGoals = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);               // Set name
            pstmt.setFloat(2, balance);             // Set balance
            pstmt.setString(3, password);           // Set password
            pstmt.setString(4, finalHistory);       // Set history
            pstmt.setString(5, finalSetGoals);      // Set setGoals
            pstmt.setInt(6, position);              // Set position

            pstmt.executeUpdate(); // Execute the update
        } catch (SQLException exception) {
            exception.printStackTrace(); // Handle SQL exceptions
        }
    }

    private static void RunAndReplace(String[] object) {
        for (int i = 0; i < object.length; i++) {
            for (int j = 0; j < object[i].length(); j++) {
                if (object[i].charAt(j) == '`') {
                    object[i] = String.valueOf(';');
                }
            }
        }
    }

}
