package org.trollheim.game.spacebattle.newengine;

import org.trollheim.commons.math.Distance;
import org.trollheim.commons.math.Point;
import org.trollheim.game.spacebattle.newengine.elements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Simulation {

    public static final float DEFAULT_SCANNER_RANGE = 10;

    World world;
    Set<Team> teams;


    public void runSimulation(){
        List<WorldObject> objects = world.getObjects();
        List<Ship> ships = objects.parallelStream().filter(o -> o instanceof  Ship).map(o-> (Ship) o).collect(Collectors.toList());
        do{
        for (Ship ship :ships){
            Scan scan = getScan(ship.getPosition(), DEFAULT_SCANNER_RANGE * (100 + ship.getBoost().getScan()) / 100f, objects);
            List<Action> commands = ship.getShipStrategy().execute(scan);

        }


        handleCollisions(objects);        
        }while (isFinished(ships));

    }

    private Scan getScan(Point position, float range, List<WorldObject> objects) {
        List<DetectedObjects> detectedObjects = objects.stream().filter(o -> Distance.euclidian(position, o.getPosition()) <= range).map(o -> new DetectedObjects(o)).collect(Collectors.toList());
        detectedObjects.forEach(o->o.setPosition(position.shift(o.getPosition())));
        //TODO
        return new Scan(detectedObjects);
    }

    protected void handleCollisions(List<WorldObject> objects) {
        List<WorldObject> destroy = new ArrayList<>();
        for (WorldObject first: objects){
            for (WorldObject second: objects){
                if (first==second){
                    continue;
                }
                if (first instanceof Debris && second instanceof Debris){
                    continue;
                }


                float distance = Distance.euclidian(first.getPosition(), second.getPosition());
                if (distance <= first.getSize()+second.getSize()){
                    destroy.add(first);
                    destroy.add(second);
                }
            }
        }

     destroy.forEach( world::destroyObject);
    }

    private boolean isFinished(List<Ship> objects) {
        if (objects.isEmpty()){
            return true;
        }
        Set<Team> teams = objects.stream().map(Ship::getTeam).collect(Collectors.toSet());
        return teams.size()<2;
    }
}
