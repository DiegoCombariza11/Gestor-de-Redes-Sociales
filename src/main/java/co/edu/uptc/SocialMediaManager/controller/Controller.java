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
        Node foundNode = findSocialMedia(this.userLogged.getSocialMediaNTree().getRoot(), media);
        if (foundNode != null && foundNode.getValue() instanceof SocialMedia) {
            SocialMedia socialMedia = (SocialMedia) foundNode.getValue();
            socialMedia.setPosts(new Post(content, date, this.userLogged));
        } else {
            // Handle the case when the node is not found, e.g., log an error or throw an exception
        }
    }

    public Node findSocialMedia(Node node, String value) {
        if (node == null) {
            return null;
        }
        if (node.getValue() instanceof SocialMedia) {
            SocialMedia s = (SocialMedia) node.getValue();
            if (s.getName().equals(value)) {
                return node;
            }
        }
        for (Object child : node.getChildren()) {
            Node result = findSocialMedia((Node) child, value);
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
        this.userLogged.setSocialMediaNTree(s, this.userLogged);
    }
}
