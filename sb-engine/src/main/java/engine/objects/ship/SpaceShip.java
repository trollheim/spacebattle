package engine.objects.ship;


import engine.world.WorldObject;

public class SpaceShip implements WorldObject {


	public SpaceShip(float x, float y, ShipStrategy shipStrategy, Team team) {
		super();
		this.name = shipStrategy.getName();
		this.x = x;
		this.y = y;
		this.shipStrategy = shipStrategy;
		this.team = team;
	}

	private float x, y;
	private int totalEnergy = 100, totalHp = 100;
	private int availableEnergy = totalEnergy, hitPoints = totalHp;
	private final String name;
	private final Team team;
	private final ShipStrategy shipStrategy;

	public boolean energyCheck(int energy) {
		if (energy < availableEnergy) {
			return true;
		}
		availableEnergy -= (energy - availableEnergy);
		return availableEnergy > 50;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void move(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public Team getTeam() {
		return team;
	}

	public ShipStrategy getShipStrategy() {
		return shipStrategy;
	}

	public boolean checkHitPoints() {
		return hitPoints > 0;
	}

	public void reduceHitPoints(int hitPoints) {
		totalHp-=hitPoints*0.2;
		this.hitPoints -= hitPoints;
	}

	public String getName() {
		return  name;
	}

	public int getHp() {
		return hitPoints;
	}


	public int getTotalEnergy() {
		return totalEnergy;
	}

	
	public int getTotalHp() {
		return totalHp;
	}

	public int getAvailableEnergy() {
		return availableEnergy;
	}

	public void repaired(int repaired) {
		hitPoints+=repaired;
		if (hitPoints>totalHp){
			hitPoints=totalHp;
		}
		
	}

	
}
