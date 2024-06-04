package co.edu.uptc.SocialMediaManager.controller;
import co.edu.uptc.SocialMediaManager.model.*;
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
        }else{
            return node;
        }
        return node;
    }

    public Node<T> getRoot() {
        return root;
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

    public T find(T data) {
        return findRecursive(root, data);
    }

    private T findRecursive(Node<T> current, T data) {
        if (current == null) {
            return null;
        }

        if (comparator.compare(data, current.getData()) == 0) {
            return current.getData();
        }

        return comparator.compare(data, current.getData()) < 0
                ? findRecursive(current.getLeft(), data)
                : findRecursive(current.getRight(), data);
    }

    public String printTree (Node<T>node) {
        if (node == null) {
            return "";
        }
        return printTree(node.getLeft()) + " " + node.getData().toString() + " " + printTree(node.getRight());
    }
    public T findPost(T post) {
        return find(post);
    }
}
