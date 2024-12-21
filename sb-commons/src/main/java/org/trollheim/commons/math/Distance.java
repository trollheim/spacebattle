package org.trollheim.commons.math;

public class Distance {

	public static float euclidian(float x1, float y1, float x2, float y2) {
		float a = x1-x2,b = y1-y2;
		
		return (float) Math.sqrt(a*a+b*b);
	}    
	
	public static float euclidian(Point a,Point b) {
		if (a.point.length!= b.point.length){
			throw new IllegalArgumentException("Invalid points");
		}
		
		float sum = 0;
		for (int i=0;i<a.point.length;i++){
			float diff = (a.point[i]-b.point[i]);
			sum+= (diff*diff) ;
		}

		return (float) Math.sqrt(sum);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      


	public static float manhattan(float x1, float y1, float x2, float y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2); 
	}
}
