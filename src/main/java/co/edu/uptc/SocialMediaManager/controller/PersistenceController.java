package co.edu.uptc.SocialMediaManager.controller;

import co.edu.uptc.SocialMediaManager.model.SocialMedia;
import co.edu.uptc.SocialMediaManager.model.User;
import co.edu.uptc.SocialMediaManager.model.UserAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

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
            wr = new PrintWriter(new FileWriter(direction + file + extension, true));
            wr.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean writeFile(String name, Object obj) {
        file = new File(name);
        gson = new GsonBuilder()
                .registerTypeAdapter(User.class, new UserAdapter()).setPrettyPrinting()
                .create();
        String json = gson.toJson(obj);
        try {
            wr = new PrintWriter(new FileWriter(direction + file + extension, true));
            if (isNotEmptyFile(name)) {
                deleteCorchete(file);
                wr.write(",\n");
            } else {
                wr.write("[\n");
            }
            wr.write(json);
            wr.write("\n]");
            wr.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean deleteCorchete(File n) {
        try {
            RandomAccessFile raf = new RandomAccessFile(direction + n + extension, "rw");
            Long size = raf.length();
            raf.setLength(size - 1);
            raf.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean isNotEmptyFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(direction + filename + extension))) {
            if (reader.readLine() != null) {
                reader.close();
                return true;
            } else {
                reader.close();
                return false;
            }
        }
    }

    public List<User> readFile(String name) {
        file = new File(name);
        List<User> people = null;
        gson = new GsonBuilder()
                .registerTypeAdapter(User.class, new UserAdapter()).setPrettyPrinting()
                .create();
        try {
            bf = new BufferedReader(new FileReader(direction + file + extension));
            Type personListType = new TypeToken<List<User>>() {
            }.getType();
            people = gson.fromJson(bf, personListType);
            bf.close();
        } catch (IOException e) {
            return people;
        }
        return people;
    }
}
