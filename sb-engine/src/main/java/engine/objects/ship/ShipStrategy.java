package engine.objects.ship;

import java.util.Queue;

public interface ShipStrategy {

	Queue<Action> getActions(ShipStatus status);

    String getName();
}
