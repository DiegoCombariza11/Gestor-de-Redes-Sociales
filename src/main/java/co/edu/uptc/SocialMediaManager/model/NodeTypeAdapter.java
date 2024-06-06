package co.edu.uptc.SocialMediaManager.model;
import co.edu.uptc.SocialMediaManager.model.Node;
import co.edu.uptc.SocialMediaManager.model.Post;
import co.edu.uptc.SocialMediaManager.model.User;
import com.google.gson.*;
import java.lang.reflect.Type;

public class NodeTypeAdapter implements JsonDeserializer<Node<Object>> {

    @Override
    public Node<Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement dataElement = jsonObject.get("data");
        Object data;

        if (dataElement.isJsonObject()) {
            JsonObject dataObject = dataElement.getAsJsonObject();
            if (dataObject.has("username")) { // Assuming User objects have a 'username' field
                data = context.deserialize(dataElement, User.class);
            } else if (dataObject.has("content")) { // Assuming Post objects have a 'content' field
                data = context.deserialize(dataElement, Post.class);
            } else {
                data = context.deserialize(dataElement, Object.class);
            }
        } else {
            data = dataElement.getAsString(); // Assuming the root data is a String
        }

        Node<Object> node = new Node<>(data);
        JsonArray childrenArray = jsonObject.getAsJsonArray("children");
        for (JsonElement childElement : childrenArray) {
            Node<Object> child = context.deserialize(childElement, Node.class);
            node.addChild(child);
        }
        return node;
    }
}

