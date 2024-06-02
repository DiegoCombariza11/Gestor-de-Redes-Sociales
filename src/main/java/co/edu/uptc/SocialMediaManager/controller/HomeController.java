package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

    private final AuthService authService;

    public HomeController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/")
    public ResponseEntity<Void> home() {
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


    @PostMapping("/login")
    public ResponseEntity<Void> loginPost(@RequestBody Map<String, String> payload) {

        String username = payload.get("username");
        String password = payload.get("password");
        String socialNetwork = payload.get("socialNetwork");


        boolean isValid = authService.validateUser(username, password, socialNetwork);

        System.out.println("Datos recibidos en del cliente: ");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Red social: " + socialNetwork);
        if (isValid) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/pages/Home.html"));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }


}
