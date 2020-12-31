package engine.strategies;

import engine.objects.ship.Action;
import engine.objects.ship.ShipStatus;
import engine.objects.ship.ShipStrategy;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


public class Broken implements ShipStrategy {

	@Override
	public Queue<Action> getActions(ShipStatus status) {
		 
	 		return new ArrayBlockingQueue<Action>(5);
	}

	@Override
	public String getName() {
		return "broken";
	}

}
