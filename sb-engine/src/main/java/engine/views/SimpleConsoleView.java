package engine.views;

import engine.objects.ship.Team;
import engine.world.View;
import org.trollheim.commons.math.Vector2d;

import java.util.List;

public class SimpleConsoleView implements View {

	@Override
	public void initWorld(float width, float height) {
		System.out.println("init new world = " + width + "x" + height);

	}

	@Override
	public void endOfRound() {
		// TODO Auto-generated method stub

	}

	@Override
	public void shipDestroyed(String name, Team team, Vector2d position) {
		System.out.println(team + ":::" + name + "is destroyed");

	}

	@Override
	public void shootLaser(String name, Team team, Vector2d position,
			Vector2d vector) {
		System.out.println(team + ":::" + name + " shoots " + vector.getX()
				+ " " + vector.getY());

	}

	@Override
	public void move(String name, Team team, Vector2d position, Vector2d vector) {
		System.out.println(team + ":::" + name + " moves by " + vector.getX()
				+ " " + vector.getY());

	}

	@Override
	public void endOfGame(String winner, List<String> survived) {
		if (winner != null && !winner.isEmpty()) {
			System.out.println(winner + " won game");
		} else {
			System.out.println("Drawn");
		}
		System.out.println("survived ships : ");
		for (String ship : survived) {
			System.out.println(" :::" + ship);
		}

	}

	@Override
	public void initShip(String name, Team team, float x, float y) {
		System.out.println(team + ":::Init ship " + name + "@" + x + "," + y);

	}

}
