# Rule Engine

A Java application that enables users to create and evaluate rules using a simple string format. It employs an abstract syntax tree (AST) for logical operations (AND, OR) and provides error handling for invalid rules. Comprehensive JUnit tests ensure functionality and reliability.

## Features

- **User-Friendly Rule Management**: Easily create and manage rules using a string format.
- **Logical Operation Support**: Evaluate rules with logical operations such as AND and OR.
- **Robust Error Handling**: Handle invalid rules gracefully with clear error messages.
- **Comprehensive Unit Testing**: Ensure functionality and reliability with JUnit tests.

## Usage

To use the Rule Engine, you can create rules and evaluate them based on provided data. Below is an example of how to create and evaluate a rule:

```java
// Example usage
ASTNode rule = RuleEngine.create_rule("age > 30");
Map<String, Object> data = new HashMap<>();
data.put("age", 35);
boolean result = RuleEngine.evaluate_rule(rule, data);
System.out.println("Rule Evaluation Result: " + result);
