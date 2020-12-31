package org.trollheim.commons.math;

public  class Point {
	protected final float point[];
	
	protected Point(float... point) {

		this.point = new float[point.length];
		for (int i = 0; i < point.length; i++) {
			this.point[i] = point[i];
		}
	}
	
	protected float[] findVector(Point p){
	 int length = point.length;
		
		if ( length != p.point.length){
		 throw new IllegalArgumentException("Dimensions mismatch");
	 }
	 
	 float vector[] = new float[length];
	 for (int i=0;i<length;i++){
		 vector[i] = p.point[i] - point[i];
	 }
	 return vector;
	}

	 public Point shift(Point p){
		return new Point(p.findVector(this));
	 }
	
}
