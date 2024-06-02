package co.edu.uptc.SocialMediaManager.model;

import co.edu.uptc.SocialMediaManager.controller.NTree;
import com.google.gson.annotations.Expose;

import java.util.Comparator;

public class Post  {
    private String content;
    private String date;
    private NTree<User> interactions;
    private int likes;
    public Post(String content, String date) {
        this.content = content;
        this.date = date;
        this.interactions=new NTree<>();
        this.likes=0;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setInteractions(NTree<User> interactions) {
        this.interactions = interactions;
    }

    public void addInteraction(User user) {
        this.interactions.add(user, this.interactions.getRoot());
    }
}