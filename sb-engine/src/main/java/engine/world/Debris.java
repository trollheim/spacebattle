package engine.world;


public class Debris implements WorldObject{

	
	public Debris(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	final private float x,y;
	@Override
	public float getX() { 
		return x;
	}

	@Override
	public float getY() { 
		return y;
	}

	
}
