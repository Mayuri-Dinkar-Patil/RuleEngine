package com.ruleengine;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleEngineTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCreateRuleInvalidFormat() {
        RuleEngine.create_rule("invalid_rule_format");
    }

    @Test
    public void testCreateRuleValidFormat() {
        ASTNode node = RuleEngine.create_rule("age > 30");
        assertEquals(">", node.value);
        assertEquals("age", node.left.value);
        assertEquals("30", node.right.value);
    }

    @Test
    public void testCombineRules() {
        List<String> rules = List.of("age > 30", "salary >= 50000");
        ASTNode combined = RuleEngine.combine_rules(rules);
        assertEquals("AND", combined.value);
        assertEquals(">", combined.left.value);
        assertEquals("age", combined.left.left.value);
        assertEquals("30", combined.left.right.value);
        assertEquals(">=", combined.right.value);
    }

    @Test
    public void testEvaluateRuleTrue() {
        ASTNode rule = RuleEngine.create_rule("age > 30");
        Map<String, Object> data = new HashMap<>();
        data.put("age", 35);
        assertTrue(RuleEngine.evaluate_rule(rule, data));
    }

    @Test
    public void testEvaluateRuleFalse() {
        ASTNode rule = RuleEngine.create_rule("age < 30");
        Map<String, Object> data = new HashMap<>();
        data.put("age", 35);
        assertFalse(RuleEngine.evaluate_rule(rule, data));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateRuleNoData() {
        ASTNode rule = RuleEngine.create_rule("age > 30");
        Map<String, Object> data = new HashMap<>(); // No data provided
        RuleEngine.evaluate_rule(rule, data);
    }
}
