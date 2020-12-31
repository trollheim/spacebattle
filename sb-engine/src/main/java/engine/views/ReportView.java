package engine.views;

import engine.objects.ship.Team;
import engine.world.View;
import org.trollheim.commons.math.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportView implements View {

	private static final String NL = "\n";
	private List<StringBuilder> pages = new ArrayList<>();

	public List<String> getPages() {
		return pages.stream().map(StringBuilder::toString).collect(Collectors.toList());
	}

	private StringBuilder getLastPage(){
		return pages.get(pages.size()-1);
	}

	private int round = 1;

	@Override
	public void initWorld(float width, float height) {
		pages.add(new StringBuilder());
		getLastPage().append("Starting round ").append(round).append(NL).append(NL);

	}

	@Override
	public void endOfRound() {
		System.out.println("endOfRound " + pages.size() + " "+round);
		pages.add(new StringBuilder());
		round++;
		getLastPage().append("Starting round ").append(round).append(NL).append(NL);
	}

	@Override
	public void shipDestroyed(String name, Team team, Vector2d position) {
		getLastPage().append(team + ":::" + name + "is destroyed").append(NL);

	}

	@Override
	public void shootLaser(String name, Team team, Vector2d position,
			Vector2d vector) {
		getLastPage().append(team + ":::" + name + " shoots " + vector.getX()
				+ " " + vector.getY()).append(NL);

	}

	@Override
	public void move(String name, Team team, Vector2d position, Vector2d vector) {
		getLastPage().append(team + ":::" + name + " moves by " + vector.getX()
				+ " " + vector.getY()).append(NL);

	}

	@Override
	public void endOfGame(String winner, List<String> survived) {
		if (winner != null && !winner.isEmpty()) {
			getLastPage().append(winner + " won game").append(NL);
		} else {
			getLastPage().append("Drawn").append(NL);
		}

		for (String ship : survived) {
			getLastPage().append(" :::" + ship).append(NL);
		}

	}

	@Override
	public void initShip(String name, Team team, float x, float y) {
		getLastPage().append(team + ":::Init ship " + name + "@" + x + "," + y).append(NL);

	}

}
