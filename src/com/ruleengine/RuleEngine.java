package com.ruleengine;

import java.util.Map;
import java.util.List;

public class RuleEngine {

    public static boolean evaluate_rule(ASTNode node, Map<String, Object> data) {
        if (node == null) {
            return false;
        }

        if (node.isLeaf()) {
            if (data.containsKey(node.value)) {
                Object value = data.get(node.value);
                if (value == null) {
                    throw new IllegalArgumentException("Value for field " + node.value + " is null");
                }
                return Boolean.parseBoolean(value.toString()); // Assuming boolean data
            } else {
                throw new IllegalArgumentException("No value found in data for field: " + node.value);
            }
        }

        Integer left = data.containsKey(node.left.value) ? 
                        Integer.parseInt(data.get(node.left.value).toString()) : null;
        String rightStr = node.right.value; // Right side is a string representation of a number

        if (left == null) {
            throw new IllegalArgumentException("No value found in data for field: " + node.left.value);
        }

        switch (node.value) {
            case ">":
                return left > Integer.parseInt(rightStr);
            case ">=":
                return left >= Integer.parseInt(rightStr);
            case "<":
                return left < Integer.parseInt(rightStr);
            case "<=":
                return left <= Integer.parseInt(rightStr);
            case "=":
                return left.equals(Integer.parseInt(rightStr));
            default:
                break;
        }

        boolean leftEval = evaluate_rule(node.left, data);
        boolean rightEval = evaluate_rule(node.right, data);

        if (node.value.equals("AND")) {
            return leftEval && rightEval;
        } else if (node.value.equals("OR")) {
            return leftEval || rightEval;
        }

        return false; // Default case
    }

    // New method to create a rule from a string
    public static ASTNode create_rule(String rule) {
        String[] parts = rule.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Rule format is invalid. Expected format: 'field operator value'");
        }

        ASTNode leftNode = new ASTNode(parts[0]); // The field name (e.g., "age")
        ASTNode operatorNode = new ASTNode(parts[1]); // The operator (e.g., ">")
        ASTNode rightNode = new ASTNode(parts[2]); // The value (e.g., "30")

        operatorNode.left = leftNode; // Set left child to the field name
        operatorNode.right = rightNode; // Set right child to the value

        return operatorNode; // Return the root of the AST
    }

    // New method to combine multiple rules
    public static ASTNode combine_rules(List<String> rules) {
        if (rules.isEmpty()) {
            throw new IllegalArgumentException("No rules provided to combine.");
        }

        ASTNode rootNode = create_rule(rules.get(0)); // Start with the first rule
        for (int i = 1; i < rules.size(); i++) {
            ASTNode nextRuleNode = create_rule(rules.get(i)); // Create AST for the next rule
            ASTNode andNode = new ASTNode("AND"); // Create an AND node
            andNode.left = rootNode; // Left child is the current combined node
            andNode.right = nextRuleNode; // Right child is the new rule node
            rootNode = andNode; // Update the root node to the new AND node
        }
        return rootNode; // Return the combined AST
    }
}
