package engine.world;

import engine.objects.ship.Team;
import org.trollheim.commons.math.Vector2d;

import java.util.List;


public interface View {

	void initWorld(float width, float height);

	void endOfRound();

	void shipDestroyed(String name, Team team, Vector2d position);

	void shootLaser(String name, Team team, Vector2d position, Vector2d vector);

	void move(String name, Team team, Vector2d position, Vector2d vector);

	void endOfGame(String winner, List<String> survived);

	void initShip(String name, Team team, float x, float y);
}
