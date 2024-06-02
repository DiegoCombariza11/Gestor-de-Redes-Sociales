package co.edu.uptc.SocialMediaManager.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class UserAdapter implements JsonSerializer<User> {

    @Override
    public JsonElement serialize(User user, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", user.getName());
        jsonObject.addProperty("email", user.getEmail());
        jsonObject.addProperty("password", user.getPassword());
        jsonObject.addProperty("username", user.getUsername());
        JsonElement socialMediaNTree = context.serialize(user.getSocialMediaNTree());
        jsonObject.add("socialMediaNTree", socialMediaNTree);

        return jsonObject;
    }
}
