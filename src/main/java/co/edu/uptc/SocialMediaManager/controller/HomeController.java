package co.edu.uptc.SocialMediaManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/pages/Login.html";
    }
    @GetMapping("/home")
    public String test() {
        return "redirect:/pages/Home.html";
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/pages/Login.html";
    }

}
