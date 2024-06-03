package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Node;
import co.edu.uptc.SocialMediaManager.model.Post;
import co.edu.uptc.SocialMediaManager.model.SocialMedia;
import co.edu.uptc.SocialMediaManager.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Controller {
    private User userLogged;
    private SocialMedia socialMediaLogged;
    private PersistenceController pc;

    public Controller() {
        pc = new PersistenceController();
        this.userLogged = null;
        socialMediaLogged = null;
    }

    public void createPost(String content, String date) {
        if (userLogged != null && socialMediaLogged != null) {
            Post p = new Post(content, date);
            Node<User> userNode = findUserRecursive(socialMediaLogged.getUsers().getRoot(), userLogged.getUsername(), userLogged.getPassword());
            if (userNode != null) {
                userNode.getData().addPost(p);
                writeSocialMedia();
            }
        }
    }
    public boolean createPost1(String content, String date) {
        if (userLogged != null && socialMediaLogged != null) {
            Post p = new Post(content, date);
            Node<User> userNode = findUserRecursive(socialMediaLogged.getUsers().getRoot(), userLogged.getUsername(), userLogged.getPassword());
            if (userNode != null) {
                userNode.getData().addPost(p);
                writeSocialMedia();
                return true;
            }
        }
        return false;
    }

    public void setSocialMediaLogged(SocialMedia socialMediaLogged) {
        this.socialMediaLogged = socialMediaLogged;
    }

    private Node<User> findUserRecursive(Node<User> node, String username, String password) {
        if (node == null) {
            return null;
        }

        User user = node.getData();
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return node;
        }

        for (Node<User> child : node.getChildren()) {
            Node<User> result = findUserRecursive(child, username, password);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    public void writeSocialMedia() {
        List<SocialMedia> aux=pc.readFile("SocialMedia");
        if(aux!=null){
            for (SocialMedia sm:aux) {
                if(sm.equals(socialMediaLogged)){
                    aux.remove(sm);
                    break;
                }
            }
            aux.add(socialMediaLogged);
            pc.writeFile("SocialMedia", aux);
        }else {
            ArrayList<SocialMedia> c =new ArrayList<>();
            c.add(socialMediaLogged);
            pc.writeFile("SocialMedia", c);
        }
    }

    public List<SocialMedia> getSocialPersistence() {
        return pc.readFile("SocialMedia");
    }

    public void reacted(String post, User user) {
        if (userLogged != null && userLogged.getPosts() != null) {
            Post aux = (Post) userLogged.getPosts().findValue(userLogged.getPosts().getRoot(), post);
            if (aux != null) {
                aux.setLikes(aux.getLikes() + 1);
                aux.addInteraction(user);
                writeSocialMedia();
            }
        }
    }
    public void addFriend(User user) {
        if(userLogged!=null){
            userLogged.addFrined(user.getUsername());
            writeSocialMedia();
        }
    }

    public User getUserLogged() {
        return userLogged;
    }

    public SocialMedia getSocialMediaLogged() {
        return socialMediaLogged;
    }

    public void setUserLogged(User userLogged) {
        this.userLogged = userLogged;
    }

}
