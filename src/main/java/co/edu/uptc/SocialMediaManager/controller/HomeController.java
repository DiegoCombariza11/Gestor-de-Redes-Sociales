package co.edu.uptc.SocialMediaManager.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "redirect:/pages/Login.html";
    }
    /*
    @GetMapping("/test")
    public String test() {
        return "redirect:/pages/Home.html";
    }

     */
    @PostMapping("/test")
public Map<String, List<Map<String, String>>> testPost(@RequestBody Map<String, List<Map<String, String>>> payload) {
    return payload;
}

    @GetMapping("/login")
    public String loginV() {
        return "redirect:/pages/Login.html";
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginPost(@RequestParam String username, @RequestParam String password) {
        // Toca validar el usaurio

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/pages/Home.html"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

/*
    @PostMapping("/pages/Login.html")
    public String login() {
        return "redirect:/pages/Home.html";
        //RedirectView("/pages/home").getUrl();
    }

 */
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
