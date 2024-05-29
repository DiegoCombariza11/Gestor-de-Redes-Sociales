package co.edu.uptc.SocialMediaManager.model;

import java.util.ArrayList;

public class Node<T> {
    private T value;
    private ArrayList<Node> children;

    public Node(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(Node children) {
        this.children.add(children);
    }
}
