package engine.strategies;

import engine.objects.ship.Action;
import engine.objects.ship.ShipStatus;
import engine.objects.ship.ShipStrategy;
import engine.world.ScannedObject;
import org.trollheim.commons.math.Vector2d;

import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;



public class Speeder implements ShipStrategy {
	private final static int FULL = 100;
	private final static int OVERIDE = 120;
	private final static int REST = 80;

	boolean overide = false;
	boolean actionScannner = false;

	@Override
	public Queue<Action> getActions(ShipStatus status) {
		List<ScannedObject> scannedObjects = status.getScannerReadings();
		Queue<Action> queue = new ArrayBlockingQueue<Action>(5);
		ScannedObject closest = findClosestEnemy(scannedObjects);

		if (closest == null) {
			if (actionScannner) {
				queue.add(Action.setScanners(FULL));
			} else {

				queue.add(Action.Move(new Vector2d(0, 5), 10));
			}
			actionScannner = !actionScannner;
		} else {
			if (closest.getPosition().getLength() < 5) {

				if (overide) {

					queue.add(Action.setShield(70));
					overide = false;
				} else {

					queue.add(Action.Attack(closest.getPosition(), FULL));
					queue.add(Action.setShield(30));

					overide = true;
				}
			} else {

				if (!overide) {

					queue.add(Action.Move(closest.getPosition(), 10));
					queue.add(Action.setScanners(30));
				} else {
					overide = false;
				}
				queue.add(Action.setShield(60));

			}

		}

		return queue;
	}

	@Override
	public String getName() {
		return "Speeder";
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
