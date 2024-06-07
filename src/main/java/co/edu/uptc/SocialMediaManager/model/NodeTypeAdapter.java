package co.edu.uptc.SocialMediaManager.model;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NodeTypeAdapter implements JsonDeserializer<Node<Object>> {

    @Override
    public Node<Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement dataElement = jsonObject.get("data");
        Object data;

        if (dataElement.isJsonObject()) {
            JsonObject dataObject = dataElement.getAsJsonObject();
            if (dataObject.has("username")) {
                // Deserializar User
                data = context.deserialize(dataElement, User.class);
            } else if (dataObject.has("content")) {
                // Deserializar Post
                Type interactionListType = new TypeToken<List<Interaction>>() {}.getType();
                List<Interaction> interactions = context.deserialize(dataObject.get("interactions"), interactionListType);
                Post post = context.deserialize(dataElement, Post.class);
                post.setInteractions((ArrayList<Interaction>) interactions);
                data = post;
            } else if (dataObject.has("date") && dataObject.has("user")) {
                // Deserializar Interaction
                data = context.deserialize(dataElement, Interaction.class);
            } else if (dataObject.has("likes")) {
                // Deserializar Likes
                data = context.deserialize(dataElement, Post.class);
            } else if (dataObject.has("friends")) {
                // Deserializar User con friends
                Type friendsListType = new TypeToken<List<String>>() {}.getType();
                List<String> friends = context.deserialize(dataObject.get("friends"), friendsListType);
                User user = context.deserialize(dataElement, User.class);
                user.setFriends((ArrayList<String>) friends);
                data = user;
            } else {
                data = context.deserialize(dataElement, Object.class);
            }
        } else {
            data = dataElement.getAsString();
        }

        Node<Object> node = new Node<>(data);
        JsonArray childrenArray = jsonObject.getAsJsonArray("children");
        if (childrenArray != null) {
            for (JsonElement childElement : childrenArray) {
                Node<Object> child = context.deserialize(childElement, new TypeToken<Node<Object>>() {}.getType());
                node.addChild(child);
            }
        }
        return node;
    }
}