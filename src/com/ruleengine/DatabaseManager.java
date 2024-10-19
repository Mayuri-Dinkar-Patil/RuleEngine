package com.ruleengine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rule_engine_db?useSSL=false";
    private static final String DB_USER = "root";  
    private static final String DB_PASSWORD = "Mayuri@1512";  

    // Method to get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to store a rule in the database
    public static void storeRule(String ruleString) {
        String query = "INSERT INTO rules (rule_string) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ruleString);
            stmt.executeUpdate();
            System.out.println("Rule stored successfully.");
        } catch (SQLException e) {
            System.err.println("Error storing rule: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    // Method to retrieve all rules from the database
    public static void retrieveRules() {
        String query = "SELECT rule_string FROM rules";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Rule: " + rs.getString("rule_string"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving rules: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test storing a rule
        storeRule("((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)");
        
        // Test retrieving rules
        System.out.println("Retrieving rules from the database:");
        retrieveRules();
    }
}
