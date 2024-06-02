package co.edu.uptc.SocialMediaManager.controller;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;


public class LoginController {

    private final PersistenceController persistenceController = new PersistenceController();

    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password, @RequestParam String socialNetwork) {
        try {
            Optional<JsonNode> optionalRootNode = persistenceController.readJsonFile("users");
            if (!optionalRootNode.isPresent()) {
                return ResponseEntity.status(500).body("Error al leer el archivo JSON");
            }

            JsonNode rootNode = optionalRootNode.get();
            JsonNode socialNode = rootNode.path(socialNetwork);
            if (socialNode.isMissingNode() || !socialNode.isArray()) {
                return ResponseEntity.status(404).body("Red social no encontrada");
            }

            boolean userFound = false;
            Iterator<JsonNode> users = socialNode.elements();
            while (users.hasNext()) {
                JsonNode userNode = users.next();
                if (userNode.path("username").asText().equals(username) && userNode.path("password").asText().equals(password)) {
                    userFound = true;
                    break;
                }
            }

            if (!userFound) {
                return ResponseEntity.status(401).body("Usuario y/o Contraseña inválidas");
            }

            return ResponseEntity.ok("Inicio de sesión exitoso");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al procesar la solicitud");
        }
    }
}

