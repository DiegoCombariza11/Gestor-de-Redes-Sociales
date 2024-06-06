package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Interaction;
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
        return controller.getInteractionsOfPost(user,password,socialMedia,"Hola");
    }
}
