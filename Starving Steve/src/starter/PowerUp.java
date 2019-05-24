package starter;

import acm.util.RandomGenerator;

public class PowerUp {
	
	
	private double x,y;
	private int gap;
	private int size;
	
	private PowerUpType p;
	private RandomGenerator rgen;
	private boolean generatedNextBlock, collected;
	
	public PowerUp(double x, double y)
	{
		this.rgen = RandomGenerator.getInstance();
		this.gap = rgen.nextInt(0, 10);
		this.x = x;
		this.y = y;
		this.size = 1;
		this.p = PowerUpType.APPLE.createRandomPowerUp();
		this.generatedNextBlock = false;
		this.collected = false;
	}
	
	public String getFP()
	{
		return p.getImagePath();
	}
	
	public void move(double x, double y)
	{
		this.x += x;
		this.y += y;
	}
	
	public static final double DELTAX= .1;
	public void move()
	{
		this.x = x-DELTAX;
	}
	
	public boolean hitPlayer(double playerx, double playery)
	{
		//System.out.println("Test");
		return(playerx >= this.x && playerx <= (this.x+this.size)
				&& playery >= this.y && playery <= (this.y+size));
	}
	
	public PowerUpType getPowerUp()
	{
		return p;
	}
	
	public boolean delete()
	{
		return x < -50;
	}
	
	public void setGeneration(boolean generation)
	{
		this.generatedNextBlock = generation;
	}
	
	public double getEndOfPowerUp()
	{
		return (x+ size);
	}
	
	public boolean canSpawn()
	{
		return !generatedNextBlock;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public boolean getCollected()
	{
		return collected;
	}
	
	public int Collected()
	{
		collected = true;
		return p.getEnergy();
	}
}
