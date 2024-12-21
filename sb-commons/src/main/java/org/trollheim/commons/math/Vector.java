package org.trollheim.commons.math;

import java.util.Arrays;

public abstract class Vector {
	// TODO rename to abstract vector
	protected Vector(float... vector) {

		this.vector = new float[vector.length];
		float tmp = 0;
		for (int i = 0; i < vector.length; i++) {
			this.vector[i] = vector[i];
			tmp += (vector[i] * vector[i]);
		}
		length = (float) Math.sqrt(tmp);
	}

	protected final float vector[];
	private final float length;

	public void add(Vector v) {
		int length = vector.length;
		if (v.vector.length != length) {
			throw new RuntimeException("Dimentions does not match");
		}

		for (int i = 0; i < length; i++) {
			vector[i] += v.vector[i];
		}
	}

	public void sub(Vector v) {
		int length = vector.length;
		if (v.vector.length != length) {
			throw new RuntimeException("Dimentions does not match");
		}

		for (int i = 0; i < length; i++) {
			vector[i] -= v.vector[i];
		}
	}

	public void multiply(float scalar) {
		int length = vector.length;

		for (int i = 0; i < length; i++) {
			vector[i] *= scalar;
		}
	}

	public float getLength() {
		return length;

	}

	private void multiply(Vector v) {
		// TODO consider to return vectro or matrix
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Vector vector1 = (Vector) o;

		if (Float.compare(vector1.length, length) != 0) return false;
		return Arrays.equals(vector, vector1.vector);
	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(vector);
		result = 31 * result + (length != +0.0f ? Float.floatToIntBits(length) : 0);
		return result;
	}
}
