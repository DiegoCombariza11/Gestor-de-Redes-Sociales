package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Node;
import co.edu.uptc.SocialMediaManager.model.SocialMedia;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NTree<T> implements Serializable {
    @SerializedName("root")
    private Node<T> root;

    public NTree() {
        this.root = null;
    }

    public NTree(T rootData) {
        this.root = new Node<>(rootData);
    }

    public Node<T> getRoot() {
        return root;
    }

    public void add(T data, Node<T> parent) {
        Node<T> newNode = new Node<>(data);
        if (parent == null) {
            if (root == null) {
                root = newNode;
            } else {
                throw new IllegalArgumentException("Root already exists");
            }
        } else {
            parent.addChild(newNode);
        }
    }
    public String printNode(Node<T> node, String prefix, boolean isTail) {
        if(node==null){
            return "esta vacio";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(prefix).append(isTail ? "└── " : "├── ").append(node.getData().toString()).append("\n");
        for (int i = 0; i < node.getChildren().size() - 1; i++) {
            builder.append(printNode(node.getChildren().get(i), prefix + (isTail ? "    " : "│   "), false));
        }
        if (node.getChildren().size() > 0) {
            builder.append(printNode(node.getChildren().get(node.getChildren().size() - 1), prefix + (isTail ? "    " : "│   "), true));
        }
        return builder.toString();
    }
    public void addRoot(T data) {
        if (root == null) {
            root = new Node<>(data);
        } else {
            throw new IllegalArgumentException("Root already exists");
        }
    }
}

