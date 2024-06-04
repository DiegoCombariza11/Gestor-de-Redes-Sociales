package co.edu.uptc.SocialMediaManager.test;

import co.edu.uptc.SocialMediaManager.controller.Controller;
import co.edu.uptc.SocialMediaManager.model.SocialMedia;
import co.edu.uptc.SocialMediaManager.model.User;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Controller c = new Controller();

        // Create users
        User juan = new User("Juan", "asdasd@", "123", "juan");
        User pedro = new User("Pedro", "asdasd@", "123", "pedro");

        // Create social media platforms
        SocialMedia facebook = new SocialMedia("Facebook");
        SocialMedia twitter = new SocialMedia("Twitter");
        // Add users to social media platforms
        c.setSocialMediaLogged(facebook);
        c.getSocialMediaLogged().addUser(juan);
        c.setUserLogged(juan);
        c.createPost("Hola", LocalDate.of(2021, 1, 1).toString());
        c.createPost("Adios", LocalDate.of(2021, 12, 1).toString());
        c.addFriend(pedro);
        c.setSocialMediaLogged(facebook);
        c.getSocialMediaLogged().addUser(pedro);

        // Log in as Juan and create posts
        c.setUserLogged(pedro);
        c.createPost("Hola", LocalDate.of(2021, 1, 1).toString());
        c.createPost("Adios", LocalDate.of(2021, 12, 1).toString());

        // Save Facebook data

        // Log in as Pedro and create posts
        c.setUserLogged(pedro);
        c.createPost("Hola", LocalDate.of(2021, 1, 1).toString());

        // Juan reacts to Pedro's post on Twitter
        c.reacted("Hola", juan,LocalDate.of(2021, 1, 10).toString());
        c.addFriend(juan);
        // Save Twitter data

        // Print persisted social media data
        List<SocialMedia> persistedData = c.getSocialPersistence();
        for (SocialMedia sm : persistedData) {
            System.out.println(sm);
        }
    }
}
