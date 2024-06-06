package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Interaction;
import co.edu.uptc.SocialMediaManager.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InteractionController {
    private final Controller controller;
    public InteractionController(Controller controller) {
        this.controller = controller;
    }
    @GetMapping("/interactions")
    public List<Interaction> getInteractions() {
        return controller.getInteractionsOfPost("pedro","123","Facebook","Hola");
    }
}
