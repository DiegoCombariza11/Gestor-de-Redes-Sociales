package co.edu.uptc.SocialMediaManager.model;

import co.edu.uptc.SocialMediaManager.controller.BinaryTree;
import co.edu.uptc.SocialMediaManager.controller.NTree;
import com.google.gson.annotations.Expose;

import java.util.Comparator;
import java.util.Objects;

public class User {
    private String name;
    private String email;
    private String password;
    private String username;
    private BinaryTree<Post> posts;
    private BinaryTree<String> friends;
    public User(String name, String email, String password, String username) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.posts = new BinaryTree<>(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return o1.getLikes()-o2.getLikes();
            }
        });
        this.friends=new BinaryTree<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public BinaryTree<String> getFriends() {
        return friends;
    }

    public void setFriends(BinaryTree<String> friends) {
        this.friends = friends;
    }

    public BinaryTree getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
    public void addFrined(String userName) {
        this.friends.add(userName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", posts=" + (posts != null ? posts.printTree(posts.getRoot()) : "null") +
                ", friends=" + (friends != null ? friends.printTree(friends.getRoot()) : "null") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, username);
    }
}
