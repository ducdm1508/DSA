package org.example.transactionmanager.Model;

import org.example.transactionmanager.Entity.Transaction;

public class BinaryTree {
    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(Transaction transaction) {
        root = insertRec(root, transaction);
    }

    private Node insertRec(Node root, Transaction transaction) {
        if (root == null) {
            root = new Node(transaction);
            return root;
        }

        if (transaction.getAmount() < root.transaction.getAmount()) {
            root.left = insertRec(root.left, transaction);
        } else if (transaction.getAmount() > root.transaction.getAmount()) {
            root.right = insertRec(root.right, transaction);
        } else {
            root.left = insertRec(root.left, transaction);
        }

        return root;
    }

    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.transaction);
            inOrderRec(root.right);
        }
    }

    public Node getRoot() {
        return root;
    }
}
