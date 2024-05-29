package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Node;

public class NTree<T> {
    private Node<T> root;

    public NTree() {
        this.root = null;
    }
    public void add(Object value, Object parent) {
        if (root == null) {
            root = new Node(value);
        } else {
            Node node = findNode(root, parent);
            if (node != null) {
                node.setChildren(new Node(value));
            }
        }
    }

    public Node getRoot() {
        return root;
    }

    public Node findNode(Node node, Object value) {
        if (node.getValue().equals(value)) {
            return node;
        } else {
            for (Object child : node.getChildren()) {
                Node result = findNode((Node)child, value);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
    public String printNode(Node node, int level) {
        String aux="";
        for (int i = 0; i < level; i++) {
            aux+="  ";
        }
        aux+=node.getValue()+"\n";
        for (Object child : node.getChildren()) {
            aux+=printNode((Node)child, level + 1);
        }
        return aux;
    }
}
