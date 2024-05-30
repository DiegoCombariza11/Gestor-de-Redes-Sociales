package co.edu.uptc.SocialMediaManager.test;

import co.edu.uptc.SocialMediaManager.controller.Controller;
import co.edu.uptc.SocialMediaManager.model.SocialMedia;
import co.edu.uptc.SocialMediaManager.model.User;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Controller c = new Controller();
        User juan = new User("Juan", "asdasd@", "123", "juan");
        c.setUserLogged(juan);
        SocialMedia facebook = new SocialMedia("Facebook");
        c.setSocialMedia(facebook);
        c.createPost("Hola", "Facebook", LocalDate.of(2021, 1, 1));
        c.createPost("Adios", "Facebook", LocalDate.of(2021, 12, 1));
        SocialMedia x = new SocialMedia("X");
        c.setSocialMedia(x);
        c.createPost("Hola", "X", LocalDate.of(2021, 1, 1));
        c.createPost("Adios", "X", LocalDate.of(2021, 12, 1));
        System.out.println(c.getUserLogged().toString());
    }
}
