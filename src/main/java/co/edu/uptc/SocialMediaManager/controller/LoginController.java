package co.edu.uptc.SocialMediaManager.controller;
import co.edu.uptc.SocialMediaManager.service.AuthService;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
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
        System.out.println("Red social: "+ socialNetwork);
        if(isValid){
            ResponseCookie userCookie = ResponseCookie.from("user", username)
                    .maxAge(24 * 3600)
                    .httpOnly(false)
                    .path("/")
                    .build();

            ResponseCookie socialMediaCookie = ResponseCookie.from("socialMedia", socialNetwork)
                    .maxAge(24 * 3600)
                    .httpOnly(false)
                    .path("/")
                    .build();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, userCookie.toString());
            headers.add(HttpHeaders.SET_COOKIE, socialMediaCookie.toString());
            headers.setLocation(URI.create("/pages/Home.html"));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}

