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
            if (node.has("name") && node.get("name").asText().equals(socialNetwork)) {
                socialNode = node;
                break;
            }
        }

        if (socialNode == null) {
            System.out.println("Red social no encontrada");
            return false;
        }

       JsonNode userNode = socialNode.path("users").path("root");
        if (userNode.isMissingNode()) {
            System.out.println("Usuario no encontrado");
            return false;
        }

        return userExists(userNode, username, password);
    } catch (Exception e) {
        System.out.println("Error al procesar la solicitud");
        return false;
    }
}

    private boolean userExists(JsonNode userNode, String username, String password) {

        if (userNode.path("data").path("username").asText().equals(username) && userNode.path("data").path("password").asText().equals(password)) {
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


//    public boolean validateUser(String username, String password, String socialNetWork) {
//        System.out.println("Datos recibidos en el servicio: ");
//        System.out.println("Username: " + username);
//        System.out.println("Contraseña: "+password);
//        System.out.println("Red social: "+socialNetWork);
//
//        return (existUser(username, password, socialNetWork));
//    }
//
//    public boolean existUser(String username, String password, String socialNetWork) {
//        if (username.equals("admin") && password.equalsIgnoreCase("admin") && (socialNetWork.equalsIgnoreCase("Facebook")| socialNetWork.equalsIgnoreCase("X")| socialNetWork.equals("X")| socialNetWork.equals("Instagram"))){
//            System.out.println("usuario admin existente");
//            return true;
//        } else {
//            System.out.println("usuario no existente");
//            return false;
//        }
//
//    }

}
