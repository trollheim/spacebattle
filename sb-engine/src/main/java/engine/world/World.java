package engine.world;

import java.util.ArrayList;
import java.util.List;

public class World {

	public final float WORLD_SIZE = 100;
	public final float LASER_DISTANCE = 5;
	public final float ENGINE_DISTANCE = 2;
	public final float SCANNERS_DISTANCE = 12;
	public final float NUMBER_ROUNDS = 1000;
	public final float SHIP_SIZE = 0.2f;

	private List<WorldObject> worldObjects = new ArrayList<WorldObject>();

	public List<WorldObject> getWorldObjects() {
		return worldObjects;
	}

	public void addWorldObjects(WorldObject wordObject) {
		this.worldObjects.add(wordObject);
	}
	 
	
	
	

}
