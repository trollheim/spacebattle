package org.trollheim.game.spacebattle.newengine.elements;

import org.trollheim.commons.math.Point;


import java.util.Collections;
import java.util.List;

public class World {

    public World(List<Point> boundries, List<WorldObject> objects) {
        this.boundries = boundries;
        this.objects = objects;
    }

    private final List<Point> boundries;
    private final List<WorldObject> objects;

    public List<Point> getBoundries() {
        return boundries;
    }

    public List<WorldObject> getObjects() {
        return objects;
    }


    public void destroyObject(WorldObject worldObject){
        if (worldObject instanceof Debris){
            return; //already destroyed
        }
        WorldObject debris = new Debris(worldObject.id);
        debris.setPosition(worldObject.getPosition());
        debris.setSize(worldObject.getSize());
        Collections.replaceAll(objects,worldObject,debris);

    }
}
