package com.ruleengine;

public class Rule {
    private String condition;
    private String action;

    // Constructor
    public Rule(String condition, String action) {
        this.condition = condition;
        this.action = action;
    }

    // Getter for condition
    public String getCondition() {
        return condition;
    }

    // Getter for action
    public String getAction() {
        return action;
    }

    // Method to evaluate the rule (replace this with actual evaluation logic)
    public boolean evaluate() {
        // Dummy evaluation for now
        return true;
    }

    // Method to perform the action
    public void performAction() {
        System.out.println("Performing action: " + action);
    }

    @Override
    public String toString() {
        return "Rule [condition=" + condition + ", action=" + action + "]";
    }
}
