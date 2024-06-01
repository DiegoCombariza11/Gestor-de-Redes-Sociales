package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuthService authService;



    @GetMapping("/")
    public ResponseEntity<Void> home(){
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/pages/Login.html"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
    /*
    @GetMapping("/test")
    public RespondeEntity<Void> test() {
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(URI.create("/pages/Home.html"));
       return new RespondeEntity<>(headers, HttpStatus.FOUND);
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
    public ResponseEntity<Void> loginPost(@RequestParam String username, @RequestParam String password, @RequestParam String socialNetWork) {

        boolean isValid = authService.validateUser(username, password, socialNetWork);

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Red social: "+ socialNetWork);
        if(isValid){
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/pages/Home.html"));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }





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
