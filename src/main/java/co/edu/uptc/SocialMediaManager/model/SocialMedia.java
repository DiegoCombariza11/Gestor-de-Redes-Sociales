package co.edu.uptc.SocialMediaManager.model;

import co.edu.uptc.SocialMediaManager.controller.NTree;

import java.util.Objects;

public class SocialMedia {
    private String name;
    private NTree<Post> posts;
    private NTree<User> interactions;
    private NTree<User> friends;

    public SocialMedia(String name) {
        this.name = name;
        this.posts = new NTree<>();
        this.interactions = new NTree<>();
        this.friends = new NTree<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String user) {
        this.name = user;
    }

    public NTree getPosts() {
        return posts;
    }

    public void setPosts(Post post) {
    if (this.posts.getRoot() == null) {
        this.posts.add(post, this.posts.getRoot());
    } else {
        this.posts.add(post, this.posts.getRoot());
    }
}

    public NTree getInteractions() {
        return interactions;
    }

    public void setInteractions(User interactions) {
        this.interactions.add(interactions, this.interactions.getRoot());
    }

    public NTree getFriends() {
        return friends;
    }

    public void setFriends(User friends) {
        this.friends.add(friends, this.friends.getRoot());
    }

    @Override
    public String toString() {
        String postsString = posts.getRoot() == null ? "No posts" : posts.toString();
        String interactionsString = interactions.getRoot() == null ? "No interactions" : interactions.printNode(interactions.getRoot(), 0);
        String friendsString = friends.getRoot() == null ? "No friends" : friends.printNode(friends.getRoot(), 0);
        return "SocialMedia{" +
                "name='" + name + '\'' +
                ", posts=" + postsString +
                ", interactions=" + interactionsString +
                ", Friends=" + friendsString +
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
        return Objects.hashCode(name);
    }
}
