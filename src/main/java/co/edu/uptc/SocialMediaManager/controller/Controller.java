package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Node;
import co.edu.uptc.SocialMediaManager.model.Post;
import co.edu.uptc.SocialMediaManager.model.SocialMedia;
import co.edu.uptc.SocialMediaManager.model.User;

import java.util.List;

public class Controller {
    private User userLogged;
    private PersistenceController pc;
    public Controller() {
        pc=new PersistenceController();
        this.userLogged = null;
    }

    public void createPost(String content, String media, String date) {
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
    public void writeUser(User user){
        pc.writeFile("users",user);
    }
    public List<User> getUsersPersistence(){
        return pc.readFile("users");
    }
    public void reacted(String post, String media, User user) {
        Node<SocialMedia> foundNode = findSocialMedia(this.userLogged.getSocialMediaNTree().getRoot(), media);
        if (foundNode != null) {
            Post p = findPost(foundNode, post);
            if (p != null) {
                System.out.println("Entro");
                p.addInteraction(user);
            } else {
                System.err.println("Post not found: " + post);
            }
        } else {
            System.err.println("Social media not found: " + media);
        }
    }
    public Post findPost(Node<SocialMedia> node, String value) {
        if (node == null) {
            return null;
        }
        SocialMedia s = node.getData();
        if(s.getPosts().getRoot().getData().getContent().equals(value.trim())){
            return s.getPosts().getRoot().getData();
        }
        for (Node<Post> post : s.getPosts().getRoot().getChildren()) {
            if (post.getData().getContent().equals(value.trim())) {
                return post.getData();
            }
        }
        for (Node<SocialMedia> child : node.getChildren()) {
            Post result = findPost(child, value);
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
