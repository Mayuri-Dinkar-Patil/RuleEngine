package com.ruleengine;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RuleEngine ruleEngine = new RuleEngine();

        // Rule string examples
        String rule1 = "age > 30";
        String rule2 = "salary > 50000";

        // Step 1: Parse individual rules into ASTs
        ASTNode astRule1 = ruleEngine.create_rule(rule1);
        ASTNode astRule2 = ruleEngine.create_rule(rule2);

        // Step 2: Combine multiple rules into one AST using combine_rules
        ASTNode combinedAST = ruleEngine.combine_rules(List.of(rule1, rule2));

        // Step 3: Test data (representing a user's attributes)
        Map<String, Object> data = new HashMap<>();
        data.put("age", 35);
        data.put("salary", 60000);

        // Step 4: Evaluate the combined rules against the data
        boolean result = ruleEngine.evaluate_rule(combinedAST, data);

        // Output the evaluation result
        System.out.println("Evaluation result: " + result);  // Expected output: true
    }
}
