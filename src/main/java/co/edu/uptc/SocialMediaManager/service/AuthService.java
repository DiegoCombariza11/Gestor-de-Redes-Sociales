package co.edu.uptc.SocialMediaManager.service;

import co.edu.uptc.SocialMediaManager.controller.PersistenceController;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class AuthService {

    private final PersistenceController persistenceController;

    public AuthService(PersistenceController persistenceController) {
        this.persistenceController = persistenceController;
    }

    public boolean validateUser(String username, String password, String socialNetwork) {
        try {
            Optional<JsonNode> optionalRootNode = persistenceController.readJsonFile("SocialMedia");
            if (!optionalRootNode.isPresent()) {
                System.out.println("Error al leer el archivo JSON");
                return false;
            }

            JsonNode rootNode = optionalRootNode.get();
            JsonNode socialNode = null;

            for (JsonNode node : rootNode) {
                if (node.has("root") && node.get("root").has("data") && node.get("root").get("data").asText().equals(socialNetwork)) {
                    socialNode = node.get("root");
                    break;
                }
            }

            if (socialNode == null) {
                System.out.println("Red social no encontrada");
                return false;
            }

            JsonNode usersNode = socialNode.path("children");
            if (usersNode.isMissingNode()) {
                System.out.println("Usuarios no encontrados");
                return false;
            }

            for (JsonNode userNode : usersNode) {
                if (userExists(userNode, username, password)) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            System.out.println("Error al procesar la solicitud");
            e.printStackTrace();
            return false;
        }
    }

    private boolean userExists(JsonNode userNode, String username, String password) {
        JsonNode dataNode = userNode.path("data");

        if (dataNode.path("username").asText().equals(username) && dataNode.path("password").asText().equals(password)) {
            return true;
        }

        Iterator<JsonNode> children = userNode.path("children").elements();
        while (children.hasNext()) {
            if (userExists(children.next(), username, password)) {
                return true;
            }
        }

        return false;
    }
}
