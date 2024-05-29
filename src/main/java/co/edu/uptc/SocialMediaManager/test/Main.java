package co.edu.uptc.SocialMediaManager.test;

import co.edu.uptc.SocialMediaManager.controller.Controller;
import co.edu.uptc.SocialMediaManager.model.SocialMedia;
import co.edu.uptc.SocialMediaManager.model.User;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Controller c=new Controller();
        c.setUserLogged(new User("Juan", "asdasd@", "123", "juan"));
        c.setSocialMedia(new SocialMedia("Facebook"));
        c.createPost("Hola", "Facebook", LocalDate.ofEpochDay(2021-01-01));
        c.createPost("Adios", "Facebook", LocalDate.ofEpochDay(2021-12-01));
        System.out.println(c.getUserLogged().toString());
    }
}
