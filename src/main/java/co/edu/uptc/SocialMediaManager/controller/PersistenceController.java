package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.SocialMedia;
import co.edu.uptc.SocialMediaManager.model.User;
import co.edu.uptc.SocialMediaManager.model.MediaAdapter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
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
        file = new File(name);
        try {
            wr = new PrintWriter(new FileWriter(direction + file + extension));
            wr.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean writeFile(String name, Object obj) {
        file = new File(name);
        gson = new GsonBuilder()
                .registerTypeAdapter(SocialMedia.class, new MediaAdapter()).setPrettyPrinting()
                .create();
        String json = gson.toJson(obj);
        try {
            wr = new PrintWriter(new FileWriter(direction + file + extension));
            wr.write(json);
            wr.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public List<SocialMedia> readFile(String name) {
        file = new File(name);
        List<SocialMedia> reds = null;
        gson = new GsonBuilder()
                .registerTypeAdapter(MediaAdapter.class, new MediaAdapter()).setPrettyPrinting()
                .create();
        try {
            bf = new BufferedReader(new FileReader(direction + file + extension));
            Type personListType = new TypeToken<List<SocialMedia>>() {
            }.getType();
            reds = gson.fromJson(bf, personListType);
            bf.close();
        } catch (IOException e) {
            return reds;
        }
        return reds;
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