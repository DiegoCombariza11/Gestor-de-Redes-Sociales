package co.edu.uptc.SocialMediaManager.model;

import co.edu.uptc.SocialMediaManager.controller.NTree;
import com.google.gson.annotations.Expose;

import java.util.Objects;

public class SocialMedia {
    private String name;
    private NTree<User> users;

    public SocialMedia(String name) {
        this.name = name;
        this.users = new NTree<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NTree<User> getUsers() {
        return users;
    }
    public void addUser(User user) {
        this.users.add(user, this.users.getRoot());
    }
    public void addPost(User user, Post post) {
        Node<User> userNode = findUserNode(this.users.getRoot(), user);
        if (userNode != null) {
            userNode.getData().addPost(post);
        }
    }
    private Node<User> findUserNode(Node<User> currentNode, User user) {
        if (currentNode == null) return null;
        if (currentNode.getData().equals(user)) return currentNode;

        for (Node<User> child : currentNode.getChildren()) {
            Node<User> foundNode = findUserNode(child, user);
            if (foundNode != null) return foundNode;
        }
        return null;
    }
    private Node<User> findParentNode(Node<User> currentNode, Node<User> targetNode) {
        if (currentNode == null) return null;
        if (currentNode.getChildren().contains(targetNode)) return currentNode;

        for (Node<User> child : currentNode.getChildren()) {
            Node<User> parentNode = findParentNode(child, targetNode);
            if (parentNode != null) return parentNode;
        }
        return null;
    }
   
    @Override
    public String toString() {
        return "SocialMedia{" +
                "name='" + name + '\'' +
                ", users=" + users.printNode(this.users.getRoot(),"",true) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocialMedia that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
