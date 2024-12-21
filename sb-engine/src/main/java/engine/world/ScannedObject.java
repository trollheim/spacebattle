package engine.world;


import org.trollheim.commons.math.Vector2d;
import org.trollheim.commons.utils.Stringifyable;

public class ScannedObject implements Stringifyable {


    public ScannedObject(Type type, Vector2d position) {
        super();
        this.type = type;
        this.position = position;
        this.json = new StringBuilder().append("{").append("\"type\":\"").append(type.name().toLowerCase()).append("\", ")
				.append("\"position\": { \"x\" :  ").append(position.getX()).append(", \"y\" : ").append(position.getY()).append("}")
				.append("}").toString();
    }

    public Type getType() {
        return type;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String asString() {

        return json;
    }

    public enum Type {
        Friend, Foe, Neutral
    }

    private final String json;
    private final Type type;
    private final Vector2d position;
}
