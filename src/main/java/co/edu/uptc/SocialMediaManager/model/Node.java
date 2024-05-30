package co.edu.uptc.SocialMediaManager.model;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T data;
    private List<Node<T>> children;

    public Node(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void addChild(Node<T> child) {
        this.children.add(child);
    }
}
