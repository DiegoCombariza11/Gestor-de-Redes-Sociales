package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Interaction;
import co.edu.uptc.SocialMediaManager.model.Node;
import co.edu.uptc.SocialMediaManager.model.Post;
import co.edu.uptc.SocialMediaManager.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Controller {
    private NTree<Object> socialMedia;
    private PersistenceController pc;

    public Controller(String socialMedia) {
        this.socialMedia = new NTree<>(socialMedia);
        pc = new PersistenceController();
    }

    public Controller() {
        this.socialMedia = new NTree<>();
        pc = new PersistenceController();
    }

    public void addUser(User user) {
        socialMedia.add(user, socialMedia.getRoot());
        writeSocialMedia();
    }

    public void createPost(String content, String date, User user) {
        if (socialMedia != null) {
            Post p = new Post(content, date);
            Node<Object> userNode = findUserRecursive(socialMedia.getRoot(), user.getUsername(), user.getPassword());
            if (userNode != null) {
                User c = (User) userNode.getData();
                if (c != null) {
                    c.addPost(p);
                    writeSocialMedia();
                } else {
                    System.out.println("User data is null");
                }
            } else {
                System.out.println("User not found");
            }
        }
    }

    private Node<Object> findUserRecursive(Node<Object> node, String username, String password) {
        if (node == null) {
            return null;
        }
        if (node.getData() instanceof User) {
            User user = (User) node.getData();
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return node;
            }
        }
        for (Node<Object> child : node.getChildren()) {
            Node<Object> result = findUserRecursive(child, username, password);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public void findSocialMedia(String socialMedia) {
        List<NTree<Object>> aux = pc.readFile("SocialMedia");
        if (aux != null) {
            for (NTree<Object> tree : aux) {
                Node<Object> rootNode = tree.getRoot();
                if (rootNode.getData() instanceof String) {
                    String existingTreeName = (String) rootNode.getData();
                    if (existingTreeName.equals(socialMedia)) {
                        this.socialMedia = tree;
                        break;
                    }
                }
            }
        }
    }

    public void writeSocialMedia() {
        List<NTree<Object>> aux = pc.readFile("SocialMedia");
        boolean updated = false;
        if (aux != null) {
            for (NTree<Object> tree : aux) {
                Node<Object> rootNode = tree.getRoot();
                if (rootNode.getData() instanceof String) {
                    String existingTreeName = (String) rootNode.getData();
                    String currentTreeName = (String) socialMedia.getRoot().getData();
                    if (existingTreeName.equals(currentTreeName)) {
                        tree.setRoot(socialMedia.getRoot());
                        updated = true;
                        break;
                    }
                }
            }
            if (!updated) {
                aux.add(socialMedia);
            }
        } else {
            aux = new ArrayList<>();
            aux.add(socialMedia);
        }
        pc.writeFile("SocialMedia", aux);
    }

    public void reacted(String post, User user, String date) {
        Node<Object> aux = findUserRecursive(socialMedia.getRoot(), user.getUsername(), user.getPassword());
        User u = (User) aux.getData();
        if (u != null && u.getPosts() != null) {
            Post postAux = findPost(u, post);
            if (postAux != null) {
                postAux.setLikes(postAux.getLikes() + 1);
                postAux.addInteraction(user, date);
                writeSocialMedia();
            }
        }
    }

    public Post getPost(String post, User user) {
        Node<Object> aux = findUserRecursive(socialMedia.getRoot(), user.getUsername(), user.getPassword());
        if (aux == null) {
            return null;
        }
        User u = (User) aux.getData();
        if (u != null && u.getPosts() != null) {
            return findPost(u, post);
        }
        return null;
    }

    public Post findPost(User user, String content) {
        ArrayList<Post> aux = user.getPosts();
        for (Post post : aux) {
            if (post.getContent().toLowerCase().contains(content.toLowerCase())) {
                return post;
            }
        }
        return null;
    }

    public void addFriend(User user, User friend) {
        Node<Object> aux = findUserRecursive(socialMedia.getRoot(), user.getUsername(), user.getPassword());
        User u = (User) aux.getData();
        if (u != null) {
            u.addFriend(friend.getUsername());
            writeSocialMedia();
        }
    }
    public List<Interaction> getInteractionsOfPost(String username,String password, String socialMediaName, String content) {
        // Paso 1: Buscar la red social
        findSocialMedia(socialMediaName);

        // Paso 2: Buscar el usuario dentro de la red social
        Node<Object> userNode = findUserRecursive(socialMedia.getRoot(), username,password);
        if (userNode == null || !(userNode.getData() instanceof User)) {
            System.out.println("Usuario no encontrado.");
            return new ArrayList<>(); // Devuelve una lista vacía si no se encuentra el usuario
        }

        User user = (User) userNode.getData();

        // Paso 3: Buscar la publicación dentro de las publicaciones del usuario
        Post post = findPost(user, content);
        if (post == null) {
            System.out.println("Publicación no encontrada.");
            return new ArrayList<>(); // Devuelve una lista vacía si no se encuentra la publicación
        }

        // Paso 4: Recuperar y devolver las interacciones de la publicación
        return post.getInteractions();
    }
}
