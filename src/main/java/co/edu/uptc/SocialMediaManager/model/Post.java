package co.edu.uptc.SocialMediaManager.model;

import co.edu.uptc.SocialMediaManager.controller.NTree;
import com.google.gson.annotations.Expose;

public class Post {
    private String content;
    private String date;
    private transient User user;
    private NTree<User> interactions;

    public Post(String content, String date, User user) {
        this.content = content;
        this.date = date;
        this.user = user;
        this.interactions=new NTree<>();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NTree<User> getInteractions() {
        return interactions;
    }

    @Override
    public String toString() {
        return "Post{" +
                "content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

    public void setInteractions(NTree<User> interactions) {
        this.interactions = interactions;
    }

    public void addInteraction(User user) {
        this.interactions.add(user, this.interactions.getRoot());
    }
}
