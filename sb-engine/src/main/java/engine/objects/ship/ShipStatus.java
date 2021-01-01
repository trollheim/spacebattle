package engine.objects.ship;

import java.util.List;
import java.util.stream.Collectors;

import engine.world.ScannedObject;
import org.trollheim.commons.utils.Stringifyable;

public class ShipStatus implements Stringifyable {
	private final String json;

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
		json = new StringBuilder()
				.append("{")
				.append("\"allowedEnergy\" : ").append(allowedEnergy).append(", ")
				.append("\"maximumHitPoints\" : ").append(maximumHitPoints).append(", ")
				.append("\"hitPoints\" : ").append(hitPoints).append(", ")
				.append("\"scannerReadings\" : ").append(scannerReadings.stream().map(ScannedObject::asString).collect(Collectors.joining(", ","[","]")))
		.append("}").toString();
	}

	public List<ScannedObject> getScannerReadings() {
		return scannerReadings;
	}

	private final int allowedEnergy;
	private final int maximumHitPoints;
	private final int hitPoints;
	private final List<ScannedObject> scannerReadings;

	@Override
	public String asString() {
		return json;
	}
}
