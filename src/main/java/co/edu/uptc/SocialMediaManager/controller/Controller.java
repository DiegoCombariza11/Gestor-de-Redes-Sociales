package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Node;
import co.edu.uptc.SocialMediaManager.model.Post;
import co.edu.uptc.SocialMediaManager.model.SocialMedia;
import co.edu.uptc.SocialMediaManager.model.User;

import java.time.LocalDate;

public class Controller {
    private User userLogged;

    public Controller() {
        this.userLogged = null;
    }

    public void createPost(String content, String media, LocalDate date) {
        Node<SocialMedia> foundNode = findSocialMedia(this.userLogged.getSocialMediaNTree().getRoot(), media);
        if (foundNode != null) {
            SocialMedia socialMedia = foundNode.getData();
            socialMedia.addPost(new Post(content, date, this.userLogged));
        } else {
            System.err.println("Social media not found: " + media);
        }
    }

    public Node<SocialMedia> findSocialMedia(Node<SocialMedia> node, String value) {
        if (node == null) {
            return null;
        }
        SocialMedia s = node.getData();
        if (s.getName().equals(value)) {
            return node;
        }
        for (Node<SocialMedia> child : node.getChildren()) {
            Node<SocialMedia> result = findSocialMedia(child, value);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public User getUserLogged() {
        return userLogged;
    }

    public void setUserLogged(User userLogged) {
        this.userLogged = userLogged;
    }

    public void setSocialMedia(SocialMedia s) {
        this.userLogged.addSocialMedia(s);
    }
}
