package co.edu.uptc.SocialMediaManager.test;

import co.edu.uptc.SocialMediaManager.service.Controller;
import co.edu.uptc.SocialMediaManager.model.User;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Controller c = new Controller("X");
//
//        // Create users
//        User juan = new User("Juan", "asdasd@", "123", "juan");
//        User pedro = new User("Pedro", "asdasd@", "123", "pedro");
//
//        // Add users to social media platforms
//        c.addUser(juan);
//        c.createPost("Hola", LocalDate.of(2021, 1, 1).toString(), juan);
//        c.createPost("Adios", LocalDate.of(2021, 12, 1).toString(), juan);
//        c.addFriend(juan, pedro);
//        c.addUser(pedro);
//
//        // Log in as Pedro and create posts
//        c.createPost("Hola", LocalDate.of(2021, 1, 1).toString(), pedro);
//        c.createPost("Adios", LocalDate.of(2021, 12, 1).toString(), pedro);
//
//        // Juan reacts to Pedro's post
//        c.reacted("Hola", juan, LocalDate.of(2021, 1, 10).toString());
//        c.addFriend(pedro, juan);
//        c.addFriend(pedro, new User("carlos", "asdasd@", "123", "carlos"));
//        c.addFriend(pedro, new User("diego", "asdasd@", "123", "diego"));
//        c.addFriend(pedro, new User("luis", "asdasd@", "123", "luis"));
//        c.addFriend(pedro, new User("jose", "asdasd@", "123", "jose"));
//        c.addFriend(pedro, new User("maria", "asdasd@", "123", "maria"));
//        c.addFriend(pedro, new User("laura", "asdasd@", "123", "laura"));
//        c=new Controller();
//        c.findSocialMedia("X");
        System.out.println(c.getPost("Hola", new User("Juan", "asdasd@", "123", "juan")));

    }
}
