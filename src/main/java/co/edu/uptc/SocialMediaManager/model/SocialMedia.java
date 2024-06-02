package co.edu.uptc.SocialMediaManager.model;

import co.edu.uptc.SocialMediaManager.controller.NTree;
import com.google.gson.annotations.Expose;

import java.util.Objects;

public class SocialMedia {
    private String name;
    private NTree<User> users;

    public SocialMedia(String name) {
        this.name = name;
        this.users=new NTree<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NTree<User> getUsers() {
        return users;
    }
    public void addUser(User user) {
        this.users.add(user, this.users.getRoot());
    }

    @Override
    public String toString() {
        return "SocialMedia{" +
                "name='" + name + '\'' +
                ", users=" + users.printNode(this.users.getRoot(),"",true) +
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