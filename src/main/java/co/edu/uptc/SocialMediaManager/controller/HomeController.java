package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/test")
public Map<String, List<Map<String, String>>> testPost(@RequestBody Map<String, List<Map<String, String>>> payload) {
    return payload;
}
  */




}
