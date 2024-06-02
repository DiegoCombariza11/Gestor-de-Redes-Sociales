package co.edu.uptc.SocialMediaManager.model;

import co.edu.uptc.SocialMediaManager.controller.NTree;
import com.google.gson.annotations.Expose;

import java.util.Objects;

public class SocialMedia {
    private String name;
    private NTree<Post> posts;
    private NTree<User> interactions;
    private NTree<User> friends;

    public SocialMedia(String name) {
        this.name = name;
        this.posts = new NTree<>(new Post("Posts","",null));
        this.interactions = new NTree<>();
        this.friends = new NTree<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NTree<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        if (posts.getRoot() == null) {
            posts = new NTree<>(post);
        } else {
            posts.add(post, posts.getRoot());
        }
    }

    public NTree<User> getInteractions() {
        return interactions;
    }

    public void addInteraction(User user) {
        this.interactions.add(user, this.interactions.getRoot());
    }

    public NTree<User> getFriends() {
        return friends;
    }

    public void addFriend(User user) {
        this.friends.add(user, this.friends.getRoot());
    }

    @Override
    public String toString() {
        return "SocialMedia{" +
                "name='" + name + '\'' +
                ", posts=" + posts.printNode(this.posts.getRoot(),"",true) +
                ", interactions=" + interactions.printNode(this.interactions.getRoot(),"",true) +
                ", friends=" + friends.printNode(this.friends.getRoot(),"",true) +
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
