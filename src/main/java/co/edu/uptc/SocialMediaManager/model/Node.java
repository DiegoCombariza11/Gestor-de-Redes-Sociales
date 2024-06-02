package co.edu.uptc.SocialMediaManager.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T data;
    private List<Node<T>> children;
    private Node<T> left;
    private Node<T> right;
    public Node(T data) {
        this.data = data;
        this.children = new ArrayList<>();
        this.left=null;
        this.right=null;
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void addChild(Node<T> child) {
        this.children.add(child);
    }
}
