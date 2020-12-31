package engine.objects.ship;

import java.util.List;

import engine.world.ScannedObject;

public class ShipStatus {
	public int getAllowedEnergy() {
		return allowedEnergy;
	}

	public int getMaximumHitPoints() {
		return maximumHitPoints;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public ShipStatus(int allowedEnergy, int maximumHitPoints, int hitPoints,
			List<ScannedObject> scannerReadings) {
		super();
		this.allowedEnergy = allowedEnergy;
		this.maximumHitPoints = maximumHitPoints;
		this.hitPoints = hitPoints;
		this.scannerReadings = scannerReadings;
	}

	public List<ScannedObject> getScannerReadings() {
		return scannerReadings;
	}

	private final int allowedEnergy;
	private final int maximumHitPoints;
	private final int hitPoints;
	private final List<ScannedObject> scannerReadings;

}
