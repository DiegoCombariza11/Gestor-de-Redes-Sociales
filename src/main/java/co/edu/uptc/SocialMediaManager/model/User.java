package co.edu.uptc.SocialMediaManager.model;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String name;
    private String email;
    private String password;
    private String username;
    private ArrayList<Post> posts;
    private ArrayList<String> friends;
    private int stars; 

    public User(String name, String email, String password, String username) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.posts = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.stars = 0; 
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
          
    }

    public void removePost(Post post) {
        this.posts.remove(post);
        
    }

    public void editPost(Post post, String newContent) {
        for (Post p : posts) {
            if (p.equals(post)) {
                p.setContent(newContent);
                break;
            }
        }
        
    }

    public void addFriend(String userName) {
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

    public int getStars() {
        return stars;
    }

    

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", posts=" + posts +
                ", friends=" + friends +
                ", stars=" + stars + 
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
