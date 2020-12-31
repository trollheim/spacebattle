package engine.strategies;

import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

import engine.objects.ship.Action;
import engine.objects.ship.ShipStatus;
import engine.objects.ship.ShipStrategy;
import engine.world.ScannedObject;

public class Lurker implements ShipStrategy{

	 private final static int FULL = 100;
	// private final static int OVERIDE = 120;
	 //private final static int REST = 80;
	 
	 boolean overide = false;
	
	

	@Override
	public Queue<Action> getActions(ShipStatus status) {
		  Queue<Action> queue = new ArrayBlockingQueue<Action>(5);
		  
		  List<ScannedObject> scannedObjects = status.getScannerReadings();
		ScannedObject closest = findClosestEnemy(scannedObjects );
		  if (closest != null)
		  System.out.println("lurker::Enemy in range " + closest.getPosition().getLength());
		if (closest == null ||closest.getPosition().getLength()>5 ){
					
			queue.add(Action.setScanners(  FULL));
			overide = false;
		}else{
			queue.add(Action.Attack(closest.getPosition(), FULL));
		}
		return queue;
	}

	@Override
	public String getName() {
		return "Lurker";
	}

	private ScannedObject findClosestEnemy(List<ScannedObject> scannedObjects) {
		List<ScannedObject> enemies = scannedObjects.stream()
				.filter(object -> object.getType() == ScannedObject.Type.Foe)
				.collect(Collectors.toList());
		if (enemies.isEmpty()) {
			return null;
		}

		Optional<ScannedObject> closest = enemies.stream().min(
				(a, b) -> Float.compare(a.getPosition().getLength(), b
						.getPosition().getLength()));

		return closest.get();
	}



}
