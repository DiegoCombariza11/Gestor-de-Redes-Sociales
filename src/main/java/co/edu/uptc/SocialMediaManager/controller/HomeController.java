package co.edu.uptc.SocialMediaManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

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



    @PostMapping("/login")
    public String login() {
        return new RedirectView("/home").getUrl();
                //"redirect:/pages/Home.html";
    }
/*
    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return new RedirectView("/home");
        } else {
            return new RedirectView("/login");
        }
    }

     */
}
