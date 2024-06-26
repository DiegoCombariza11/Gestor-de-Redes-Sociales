package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Post;
import co.edu.uptc.SocialMediaManager.model.User;
import co.edu.uptc.SocialMediaManager.service.Controller;
import jakarta.servlet.http.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {


    private final Controller controller;


    public HomeController(Controller controller) {
        this.controller = controller;
    }

    @GetMapping("/")
    public ResponseEntity<Void> home() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/pages/Login.html"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/posts")
    public ResponseEntity<List<Post>> getPostsByUsername(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        String socialMediaName = payload.get("socialMediaName");

        if (username == null || password == null || socialMediaName == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        controller.findSocialMedia(socialMediaName);
        List<Post> posts = controller.getPostsByUsername(username, password);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/friends")
    public ResponseEntity<List<String>> getFriends(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        String socialMediaName = payload.get("socialMediaName");

        List<String> friends = controller.getFriends(socialMediaName, username, password);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }


@PostMapping("/selectPost")
public ResponseEntity<Void> selectPost(@RequestBody Map<String, String> payload) {
    String postContent = payload.get("content");

    ResponseCookie postCookie = ResponseCookie.from("post", postContent)
            .maxAge(24 * 3600)
            .httpOnly(false)
            .path("/")
            .build();


    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.SET_COOKIE, postCookie.toString());
    headers.setLocation(URI.create("/pages/Statistics.html"));
    return new ResponseEntity<>(headers, HttpStatus.FOUND);
}




    @PostMapping("/getPost")
    public ResponseEntity<Post> getPost(@RequestBody Map<String, String> payload) {
        String socialMedia = payload.get("socialMedia");
        String user = payload.get("user");
        String password = payload.get("password");
        String post = payload.get("post");
        controller.findSocialMedia(socialMedia);
        return new ResponseEntity<>(controller.getPost(post, new User("", "", password, user)), HttpStatus.OK);
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