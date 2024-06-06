package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.Node;
import co.edu.uptc.SocialMediaManager.model.NodeTypeAdapter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PersistenceController {
    private BufferedReader bf;
    private File file;
    private Gson gson;
    private PrintWriter wr;
    public static final String direction = "src/main/java/co/edu/uptc/SocialMediaManager/persistence/";
    public static final String extension = ".json";

    public boolean createFile(String name) {
        file = new File(direction + name + extension);
        try {
            wr = new PrintWriter(new FileWriter(file));
            wr.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean writeFile(String name, List<NTree<Object>> trees) {
        file = new File(direction + name + extension);
        gson = new GsonBuilder()
                .registerTypeAdapter(Node.class, new NodeTypeAdapter())
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(trees);
        try {
            wr = new PrintWriter(new FileWriter(file));
            wr.write(json);
            wr.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<NTree<Object>> readFile(String name) {
        file = new File(direction + name + extension);
        gson= new GsonBuilder()
                .registerTypeAdapter(Node.class, new NodeTypeAdapter())
                .setPrettyPrinting()
                .create();
        List<NTree<Object>> trees = new ArrayList<>();
        try {
            bf = new BufferedReader(new FileReader(file));
            Type treeListType = new TypeToken<List<NTree<Object>>>() {}.getType();
            trees = gson.fromJson(bf, treeListType);
            bf.close();
        } catch (IOException e) {
        }
        return trees;
    }

    public Optional<JsonNode> readJsonFile(String filename) {
        try {
            File jsonFile = new File(direction + filename + extension);
            ObjectMapper objectMapper = new ObjectMapper();
            return Optional.of(objectMapper.readTree(jsonFile));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}
