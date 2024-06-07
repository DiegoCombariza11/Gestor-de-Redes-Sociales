package co.edu.uptc.SocialMediaManager.controller;


import co.edu.uptc.SocialMediaManager.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.edu.uptc.SocialMediaManager.service.Controller;

import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    private final Controller controller;

    public PostController(Controller controller) {
        this.controller = controller;
    }

    @PostMapping("/api/posts")
    public ResponseEntity<List<Post>> getPostsByUsername(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        String socialMediaName = payload.get("socialMediaName");

//        if (username == null || password == null || socialMediaName == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

        List<Post> posts = controller.getPostsByUsername(username, password);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
