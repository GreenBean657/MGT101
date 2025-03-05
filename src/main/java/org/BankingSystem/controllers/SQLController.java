package org.BankingSystem.controllers;

import org.BankingSystem.models.Human;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLController {

    private static final String path = "jdbc:sqlite:mainDB.sqlite";
    private static Connection conn = null;

    public static void loadDB() {
        System.out.println("Connecting to database...");
        try {
            conn = DriverManager.getConnection(path);
            System.out.println("Database connection established successfully");
        } catch (SQLException exception) {
            System.out.println("Database connection error: " + exception.getMessage());
        }
    }

    /**
     * Load all humans from the database
     * @return Array of Human objects
     */
    public static Human[] loadAll() {
        String query = "SELECT * FROM users";
        List<Human> humans = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float balance = rs.getFloat("balance");
                float savingsBalance = rs.getFloat("savings");
                String password = rs.getString("password");
                String financialHistory = rs.getString("history");
                String setGoals = rs.getString("setGoals");

                String[] adjHistory = financialHistory.split("`");
                String[] adjGoals = setGoals.split("`");

                Human human = new Human(id, name, balance, savingsBalance, password, adjHistory, adjGoals);
                humans.add(human);
            }

            System.out.println("Loaded " + humans.size() + " users from database");
            return humans.toArray(new Human[0]);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading all users", e);
        }
    }

    public static Human load(int position) {
        // Ensure consistent table name (changed from "Human" to "users" to match write method)
        String query = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, position);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Extract data from the result set
                    String name = rs.getString("name");
                    float balance = rs.getFloat("balance");
                    float savingsBalance = rs.getFloat("savingsBalance");
                    String password = rs.getString("password");
                    String financialHistory = rs.getString("history");
                    // Ensure consistent field name (changed from "setgoals" to "setGoals")
                    String setGoals = rs.getString("setGoals");
                    int thisposition = rs.getInt("id"); // Changed from "position" to "id" to match query

                    String[] adjHistory = financialHistory.split("`");
                    String[] adjGoals = setGoals.split("`");

                    return new Human(thisposition, name, balance, savingsBalance, password, adjHistory, adjGoals);
                } else {
                    System.out.println("No user found with ID: " + position);
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading user with ID: " + position, e);
        }
    }

    /**
     * Write a human class to the database
     * @param human The human class
     */
    public static void write(Human human) {
        Object[][] data = human.getData();
        int position;
        float balance;
        float savingsBalance;
        String name;
        String password;
        String[] history;
        String[] setGoals;

        try {
            position = (int) data[0][0];
            balance = (float) data[0][1];
            savingsBalance = (float) data[0][2];
            name = (String) data[0][3];
            password = (String) data[0][4];
            history = (String[]) data[1];
            setGoals = (String[]) data[2];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("Corrupted Data detected!");
        }

        if (position < 0) throw new IllegalArgumentException("Position cannot be negative!");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty!");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password cannot be null or empty!");
        if (balance < 0) throw new IllegalArgumentException("Balance cannot be negative!");
        if (savingsBalance < 0) throw new IllegalArgumentException("Savings balance cannot be negative!");

        if (history == null || history.length == 0) history = new String[]{"No history provided."};
        if (setGoals == null || setGoals.length == 0) setGoals = new String[]{"No set goals provided."};

        // Fix character replacement in arrays
        sanitizeBackticks(history);
        sanitizeBackticks(setGoals);

        // Join history array elements with backtick delimiter
        StringBuilder historyBuilder = new StringBuilder();
        for (String s : history) {
            historyBuilder.append(s).append('`');
        }
        String finalHistory = historyBuilder.toString();

        // Join setGoals array elements with backtick delimiter
        StringBuilder goalsBuilder = new StringBuilder();
        for (String s : setGoals) {
            goalsBuilder.append(s).append('`');
        }
        String finalSetGoals = goalsBuilder.toString();

        String query = "UPDATE users SET name = ?, balance = ?, savingsBalance = ?, password = ?, history = ?, setGoals = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setFloat(2, balance);
            pstmt.setFloat(3, savingsBalance);
            pstmt.setString(4, password);
            pstmt.setString(5, finalHistory);
            pstmt.setString(6, finalSetGoals);
            pstmt.setInt(7, position);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                // No rows updated, might need to insert instead
                insertNewUser(name, balance, savingsBalance, password, finalHistory, finalSetGoals, position);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error updating user with ID: " + position, exception);
        }
    }

    public static void insertNewUser(String name, float balance, float savingsBalance, String password,
                                      String history, String setGoals, int position) {
        String query = "INSERT INTO users (id, name, balance, savingsBalance, password, history, setGoals) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, position);
            pstmt.setString(2, name);
            pstmt.setFloat(3, balance);
            pstmt.setFloat(4, savingsBalance);
            pstmt.setString(5, password);
            pstmt.setString(6, history);
            pstmt.setString(7, setGoals);
            pstmt.executeUpdate();
            System.out.println("New user inserted with ID: " + position);
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error inserting new user with ID: " + position, exception);
        }
    }

    private static void sanitizeBackticks(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                // Replace backticks with semicolons
                array[i] = array[i].replace('`', ';');
            }
        }
    }
}