package co.edu.uptc.SocialMediaManager.model;

import co.edu.uptc.SocialMediaManager.controller.NTree;

import java.util.ArrayList;

public class Post  {
    private String content;
    private String date;
    private ArrayList<Interaction> interactions;
    private int likes;
    public Post(String content, String date) {
        this.content = content;
        this.date = date;
        this.interactions=new ArrayList<>();
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

    public ArrayList<Interaction> getInteractions() {
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

    public void setInteractions(ArrayList<Interaction> interactions) {
        this.interactions = interactions;
    }

    public void addInteraction(User user, String date) {
        this.interactions.add(new Interaction(date, user.getName()));
    }
}
