package co.edu.uptc.SocialMediaManager.model;

import co.edu.uptc.SocialMediaManager.controller.NTree;

public class SocialMedia {
    private String name;
    private NTree<Post> posts;
    private NTree<User> interactions;
    private NTree<User> Friends;

    public SocialMedia(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUser(String user) {
        this.name = user;
    }

    public NTree getPosts() {
        return posts;
    }

    public void setPosts(Post posts) {
        this.posts.add(posts,this.posts.getRoot());
    }

    public NTree getInteractions() {
        return interactions;
    }

    public void setInteractions(User interactions) {
        this.interactions.add(interactions,this.interactions.getRoot());
    }

    public NTree getFriends() {
        return Friends;
    }

    public void setFriends(User friends) {
        this.Friends.add(friends,this.Friends.getRoot());
    }
}
