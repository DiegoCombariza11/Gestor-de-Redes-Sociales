package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Post;
import co.edu.uptc.SocialMediaManager.model.User;
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

    private final Controller controller;
    public HomeController(Controller controller) {
        this.controller = controller;
    }

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

    @PostMapping("/post")
    public ResponseEntity<Void> createPost(@RequestBody Post post){
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/pages/Home.html"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/getPost")
    public ResponseEntity<Post> getPost(@RequestBody Map<String, String> payload){
        String socialMedia = payload.get("socialMedia");
        String user = payload.get("user");
        String password = payload.get("password");
        String post = payload.get("post");
        controller.findSocialMedia(socialMedia);
        return new ResponseEntity<>(controller.getPost(post,new User("","",password,user)), HttpStatus.OK);
    }

}
