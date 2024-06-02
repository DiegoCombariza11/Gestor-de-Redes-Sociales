package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Node;
import co.edu.uptc.SocialMediaManager.model.Post;

import java.util.Comparator;

public class BinaryTree<T> {
    private Node<T> root;
    private transient Comparator<T> comparator;

    public BinaryTree(Comparator<T> comparator) {
        root = null;
        this.comparator = comparator;
    }

    public BinaryTree() {
        root = null;
        comparator = null;
    }

    public void add(T data) {
        this.root = addRecursive(root, data);
    }

    public Node addRecursive(Node<T> node, T data) {
        if (node == null) {
            return new Node(data);
        }
        if (comparator.compare(data, node.getData()) < 0) {
            node.setLeft(addRecursive(node.getLeft(), data));
        } else if (comparator.compare(data, node.getData()) > 0) {
            node.setRight(addRecursive(node.getRight(), data));
        }
        return node;
    }

    public Node<T> getRoot() {
        return root;
    }

    public String printTree(Node<T> node) {
        if (node == null) {
            return "";
        }
        return printTree(node.getLeft()) + node.getData() + "\n" + printTree(node.getRight());
    }

    public void delete(T data) {
        root = deleteRecursive(root, data);
    }

    public Node<T> deleteRecursive(Node<T> node, T data) {
        if (node == null) {
            return null;
        }
        if (comparator.compare(data, node.getData()) == 0) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            T smallestValue = findSmallestValue(node.getRight());
            node.setData(smallestValue);
            node.setRight(deleteRecursive(node.getRight(), smallestValue));
            return node;
        }

        if (comparator.compare(data, node.getData()) < 0) {
            node.setLeft(deleteRecursive(node.getLeft(), data));
            return node;
        }
        node.setRight(deleteRecursive(node.getRight(), data));
        return node;
    }

    public T findSmallestValue(Node<T> root) {
        return root.getLeft() == null ? root.getData() : findSmallestValue(root.getLeft());
    }

    public T findValue(Node<T> node, String content) {
        if (node == null) {
            return null;
        }
        Post p = (Post) node.getData();
        if (p.getContent().toLowerCase().contains(content.toLowerCase())) {
            return node.getData();
        }
        T leftSearchResult = findValue(node.getLeft(), content);
        if (leftSearchResult != null) {
            return leftSearchResult;
        }
        return findValue(node.getRight(), content);
    }
}
