package co.edu.uptc.SocialMediaManager.test;

import co.edu.uptc.SocialMediaManager.controller.Controller;
import co.edu.uptc.SocialMediaManager.model.SocialMedia;
import co.edu.uptc.SocialMediaManager.model.User;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Controller c = new Controller();
        User juan = new User("Juan", "asdasd@", "123", "juan");
        User pedro = new User("Pedro", "asdasd@", "123", "pedro");
        c.setUserLogged(juan);
        SocialMedia facebook = new SocialMedia("Facebook");
        c.setSocialMedia(facebook);
        c.createPost("Hola", "Facebook", LocalDate.of(2021, 1, 1).toString());
        c.createPost("Adios", "Facebook", LocalDate.of(2021, 12, 1).toString());
        c.setUserLogged(pedro);
        c.setSocialMedia(facebook);
        c.createPost("Hola", "Facebook", LocalDate.of(2021, 1, 1).toString());
        SocialMedia twitter = new SocialMedia("Twitter");
        c.setSocialMedia(twitter);
        c.createPost("Hola", "Twitter", LocalDate.of(2021, 1, 1).toString());
        c.reacted("Hola", "Twitter", juan);
        c.writeUser(pedro);
        System.out.println(c.getUsersPersistence());
    }
}
