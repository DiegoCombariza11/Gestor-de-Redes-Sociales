package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Interaction;
import co.edu.uptc.SocialMediaManager.model.Post;
import co.edu.uptc.SocialMediaManager.model.User;
import co.edu.uptc.SocialMediaManager.service.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class InteractionController {
    private final Controller controller;
    public InteractionController(Controller controller) {
        this.controller = controller;
    }
    @PostMapping("/interactions")
    public List<Interaction> getInteractions(@RequestBody Map<String, String> payload) {
        String socialMedia = payload.get("socialMedia");
        String user = payload.get("user");
        String password = payload.get("password");
        String content = payload.get("content");
        return controller.getInteractionsOfPost(user,password,socialMedia,content);
    }
    @PostMapping("/average")
    public String getAverage(@RequestBody Map<String, String> payload) {
        String socialMedia = payload.get("socialMedia");
        String user = payload.get("user");
        String password = payload.get("password");
        String content = payload.get("content");
        return controller.averageLikes(socialMedia,user,password,content);
    }
    @PostMapping("/timePost")
    public List<Post> getTimePost(@RequestBody Map<String, String> payload) {
        String socialMedia = payload.get("socialMedia");
        String user = payload.get("user");
        String password = payload.get("password");
        return controller.timePost(socialMedia,user,password);
    }
}
