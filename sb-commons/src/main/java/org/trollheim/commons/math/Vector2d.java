package org.trollheim.commons.math;

public class Vector2d extends Vector {

	public Vector2d(float x, float y) {
		super(x, y);
	}

	public float getX() {
		return vector[0];
	}

	public float getY() {
		return vector[1];
	}

	public void rotateDeg(float angle) {
		float sin = (float) Math.sin(Math.toRadians(angle));
		float cos = (float) Math.cos(Math.toRadians(angle));
		if (Math.abs(cos)<1E-6) cos =0;
		if (Math.abs(sin)<1E-6) sin =0;
		float x = vector[0], y = vector[1];
		vector[0] = x * cos - y * sin;
		vector[1] = x * sin + y * cos;
	}
}
