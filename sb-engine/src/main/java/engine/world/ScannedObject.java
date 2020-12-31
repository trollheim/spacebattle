package engine.world;


import org.trollheim.commons.math.Vector2d;

public class ScannedObject {
	public ScannedObject(Type type, Vector2d position) {
		super();
		this.type = type;
		this.position = position;
	}

	public Type getType() {
		return type;
	}

	public Vector2d getPosition() {
		return position;
	}

	public static enum Type {
		Friend, Foe, Neutral
	}

	private final Type type;
	private final Vector2d position;
}
