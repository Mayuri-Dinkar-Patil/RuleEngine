package com.ruleengine;

public class ASTNode {
    String value;  // The value of the node (e.g., "AND", ">", "age")
    ASTNode left;  // Left child node
    ASTNode right; // Right child node

    public ASTNode(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // Method to check if the node is a leaf (i.e., has no children)
    public boolean isLeaf() {
        return left == null && right == null;
    }
}



