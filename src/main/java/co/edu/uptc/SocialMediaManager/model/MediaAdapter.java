package co.edu.uptc.SocialMediaManager.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class MediaAdapter implements JsonSerializer<SocialMedia> {
    @Override
    public JsonElement serialize(SocialMedia src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", src.getName());
        JsonElement users = context.serialize(src.getUsers());
        jsonObject.add("users", users);

        return jsonObject;
    }
}
