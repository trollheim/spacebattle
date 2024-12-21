package engine.simulation;



import engine.objects.ship.*;
import engine.world.*;
import org.trollheim.commons.math.Distance;
import org.trollheim.commons.math.Vector2d;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


public class Simulation {

	public Simulation(View view) {
		super();
		this.view = view;
	}

	private static class CallableWrapper implements Callable<Queue<Action>> {
		public CallableWrapper(ShipStrategy strategy,
							   final ShipStatus shipStatus) {
			super();
			this.strategy = strategy;
			this.shipStatus = shipStatus;
		}

		private final ShipStrategy strategy;
		private final ShipStatus shipStatus;

		@Override
		public Queue<Action> call() throws Exception {

			return strategy.getActions(shipStatus);
		}

	}

	World world = new World();
	private final Map<SpaceShip, Map<Systems, Action>> powerMap = new HashMap<>();
	private final View view;
	private final ExecutorService executor = Executors
			.newSingleThreadExecutor();

	public void runSimulation(List<ShipStrategy> strategies) {
		view.initWorld(world.WORLD_SIZE,world.WORLD_SIZE);
		// loadStrategies

		for (int i = 0; i < strategies.size(); i++) {
			boolean teamRed = i % 2 == 0;
			int shipIndex = i / 2;
			float y = 50 + ((shipIndex % 2 == 0 ? -1 : 1) * shipIndex * 10);
			SpaceShip ship = new SpaceShip(teamRed ? 10 : 90, y,
					strategies.get(i), teamRed ? Team.RED : Team.BLUE);
			world.addWorldObjects(ship);
			view.initShip(ship.getName(), ship.getTeam(), ship.getX(),
					ship.getY());
		}

		// create ship for each strategy
		// init scanner data
		int i = 0;
		int red, blue;

		do {
			red = 0;
			blue = 0;
			round();
			i++;
			for (WorldObject object : world.getWorldObjects()) {
				if (object instanceof SpaceShip) {
					SpaceShip ship = (SpaceShip) object;
					if (ship.getTeam() == Team.BLUE) {
						blue++;

					} else {
						red++;
					}
				} else {

				}
			}

			System.out.println("round " + i + " " + red + " " + blue + " "
					+ world.getWorldObjects().size());
			view.endOfRound();

		} while (i < world.NUMBER_ROUNDS && (red != 0 && blue != 0));// TODO
																		// change
		// to
		System.out.println("round " + i);
		String winner = "";
		if (red < blue) {
			winner = "blue";
		} else if (red > blue) {
			winner = "red";
		}
		List<String> survived = world.getWorldObjects().stream()
				.filter((object) -> object instanceof SpaceShip)
				.map(ship -> ((SpaceShip) ship).getName())
				.collect(Collectors.toList());


		executor.shutdown();
		view.endOfGame(winner, survived);
		// check
		// is there a
		// winner;

	}

	private void round() {

		for (int i = 0; i < world.getWorldObjects().size(); i++) {
			WorldObject object = world.getWorldObjects().get(i);
			if (object instanceof SpaceShip) {
				SpaceShip ship = (SpaceShip) object;

				List<ScannedObject> scannersResult = resolveScanners(ship);

				ShipStatus shipStatus = new ShipStatus(
						ship.getAvailableEnergy(), ship.getTotalHp(),
						ship.getHp(), scannersResult);
				Future<Queue<Action>> future = executor
						.submit(new CallableWrapper(ship.getShipStrategy(),
								shipStatus));
				Queue<Action> queue;
				try {
					queue = future.get(5, TimeUnit.SECONDS);

				} catch (TimeoutException | InterruptedException
						| ExecutionException e) {
					future.cancel(true);
					view.shipDestroyed(ship.getName(), ship.getTeam(),
							new Vector2d(ship.getX(), ship.getY()));
					world.getWorldObjects().set(i,
							new Debris(ship.getX(), ship.getY()));

					continue;
				}
				Map<Systems, Action> actions = new HashMap<Systems, Action>();
				int totalPower = 0;
				for (Action action : queue) {
					if (action.getOptional() instanceof Vector2d) {
						Vector2d vector = (Vector2d) action.getOptional();
						vector.rotateDeg(ship.getTeam() == Team.RED ? -90 : 90);
					}

					totalPower += action.getPower();
					actions.put(action.getSystem(), action);
				}

				if (!ship.energyCheck(totalPower)) {
					// ship exploded
					view.shipDestroyed(ship.getName(), ship.getTeam(),
							new Vector2d(ship.getX(), ship.getY()));
					world.getWorldObjects().set(i,
							new Debris(ship.getX(), ship.getY()));

				}
				powerMap.put(ship, actions);
			}
		}

		// resolveShoot
		world.getWorldObjects().stream().filter(o -> o instanceof SpaceShip)
				.forEach(ship -> resolveShoot((SpaceShip) ship));

		// resolveMove
		world.getWorldObjects().stream().filter(o -> o instanceof SpaceShip)
				.forEach(ship -> resolveMove((SpaceShip) ship));

		// checkCollision
		// removeDestroyedOnes
		for (int i = 0; i < world.getWorldObjects().size(); i++) {
			WorldObject object = world.getWorldObjects().get(i);
			if (object instanceof SpaceShip) {

				SpaceShip ship = (SpaceShip) object;

				if (!ship.checkHitPoints()) {
					world.getWorldObjects().set(i,
							new Debris(ship.getX(), ship.getY()));
					view.shipDestroyed(ship.getName(), ship.getTeam(),
							new Vector2d(ship.getX(), ship.getY()));
					continue;
				}

				for (int j = 0; j < world.getWorldObjects().size(); j++) {

					WorldObject object2 = world.getWorldObjects().get(j);
					if (object == object2) {
						continue;
					}

					float distance = Distance.euclidian(ship.getX(),
							ship.getY(), object2.getX(), object2.getY());

					if (distance < world.SHIP_SIZE * 2) {
						world.getWorldObjects().set(i,
								new Debris(ship.getX(), ship.getY()));
						view.shipDestroyed(ship.getName(), ship.getTeam(),
								new Vector2d(ship.getX(), ship.getY()));
						if (object2 instanceof SpaceShip) {
							SpaceShip ship2 = (SpaceShip) object2;
							view.shipDestroyed(ship2.getName(),
									ship2.getTeam(), new Vector2d(ship2.getX(),
											ship2.getY()));
							world.getWorldObjects().set(j,
									new Debris(object2.getX(), object2.getY()));

						}

						break;
					}

				}

			}

		}
		// resolve repair
		world.getWorldObjects().stream().filter(o -> o instanceof SpaceShip)
				.forEach(ship -> resolveRepair((SpaceShip) ship));
		;

	}

	private Action getActionForShip(SpaceShip ship, Systems system) {
		Map<Systems, Action> map = powerMap.get(ship);
		if (map != null) {
			return map.get(system);

		}
		return null;
	}

	private void resolveShoot(SpaceShip ship) {
		Action action = getActionForShip(ship, Systems.Weapons);
		if (action == null) {
			return;
		}
		Vector2d vector = (Vector2d) action.getOptional();
		if (vector.getLength() > world.LASER_DISTANCE) {
			//TODO normalize vector
			return;
		}

		view.shootLaser(ship.getName(), ship.getTeam(),
				new Vector2d(ship.getX(), ship.getY()), vector);

		for (WorldObject object : world.getWorldObjects()) {
			if (!(object instanceof SpaceShip)) {
				continue;

			}
			// TODO isOnLine (use cosine distance?)
			if (ship.getX() + vector.getX() == object.getX()
					&& ship.getY() + vector.getY() == object.getY()) {
				SpaceShip enemyShip = (SpaceShip) object;
				Action shieldAction = getActionForShip(enemyShip,
						Systems.Shields);
				int difference = action.getPower()
						- (shieldAction == null ? 0 : shieldAction.getPower());
				if (difference > 0) {

					enemyShip.reduceHitPoints(difference);
				}
				// TODO view
				break;
			}

		}

	}

	private void resolveRepair(SpaceShip ship) {
		Action action = getActionForShip(ship, Systems.Repair);
		if (action == null) {
			return;
		}
		int repaired = action.getPower() / 10;
		ship.repaired(repaired);

	}

	private void resolveMove(SpaceShip ship) {
		Action action = getActionForShip(ship, Systems.Engines);
		if (action == null) {
			return;
		}
		float modifier = action.getPower() / 100f;
		float maxDistance = modifier * world.ENGINE_DISTANCE;

		Vector2d vector = (Vector2d) action.getOptional();
		if (vector.getLength() > maxDistance) {
			// find new vector;
			float x = vector.getX() * maxDistance / vector.getLength();

			float y = vector.getY() * maxDistance / vector.getLength();
			vector = new Vector2d(x, y);
		}
		view.move(ship.getName(), ship.getTeam(), new Vector2d(ship.getX(),
				ship.getY()), vector);
		ship.move(vector.getX(), vector.getY());

	}

	private List<ScannedObject> resolveScanners(SpaceShip ship) {

		List<ScannedObject> list = new ArrayList<>();

		float modifier = 0;
		Action action = getActionForShip(ship, Systems.Scanners);
		if (action != null) {
			modifier = action.getPower();
		}

		float scannerRange = 2 + 0.1f * modifier;
		for (WorldObject object : world.getWorldObjects()) {
			float distance = Distance.euclidian(ship.getX(), ship.getY(),
					object.getX(), object.getY());
			// System.out.println(ship.getName()+"::: scanning " +
			// scannerRange+"  object in range "+distance);
			if (distance > scannerRange) {
				continue;
			}
			final ScannedObject.Type type;
			Vector2d vector = new Vector2d(object.getX() - ship.getX(),
					object.getY() - ship.getY());
			if (object instanceof SpaceShip) {
				if (ship.getTeam() == ((SpaceShip) object).getTeam()) {
					type = ScannedObject.Type.Friend;
				} else {
					type = ScannedObject.Type.Foe;
				}
			} else {
				type = ScannedObject.Type.Neutral;
			}

			vector.rotateDeg(ship.getTeam() == Team.RED ? 90 : -90);
			ScannedObject scanned = new ScannedObject(type, vector);
			list.add(scanned);
		}

		return list;
	}

}