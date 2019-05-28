package starter;

import java.util.ArrayList;

import acm.util.RandomGenerator;

public class Obstacle {
	
	private RandomGenerator rgen;
	
	private double x;
	private double y;
	private double height;
	private int width;
	private boolean generatedNextBlock;
	
	private ArrayList<Block> blocks;
	
	public Obstacle(double x, PowerUp p)
	{
		this.rgen = RandomGenerator.getInstance();
		this.x = x;
		this.y = rgen.nextInt(4, 10);
		if(this.y == p.getY())
		{
			this.y += 1;
		}
		this.height = .4;
		this.width = rgen.nextInt(4, 10);
		
		this.blocks = new ArrayList<Block>();
		this.blocks.add(new Block("../media/images/left.png",x,y));
		for(int i = 1;i<width-1;i++)
		{
			this.blocks.add(new Block("../media/images/middle.png",x+i,y));
		}
		this.blocks.add(new Block("../media/images/right.png",x+width-1,y));
		this.generatedNextBlock = false;
	}
	
	public Obstacle(double x, double y, int width, int height)
	{
		this.rgen = RandomGenerator.getInstance();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		
		this.blocks = new ArrayList<Block>();
		this.blocks.add(new Block("../media/images/left.png",x,y));
		for(int i = 1;i<width-1;i++)
		{
			this.blocks.add(new Block("../media/images/middle.png",x+i,y));
		}
		this.blocks.add(new Block("../media/images/right.png",x+width-1,y));
		this.generatedNextBlock = false;
	}
	
	public void setGeneration(boolean generation)
	{
		this.generatedNextBlock = generation;
	}
	
	public static final double DELTAX= .1;
	
	public void move()
	{
		this.x = x-DELTAX;
		for(int i = 0; i < blocks.size(); i++)
			blocks.get(i).setLocation(blocks.get(i).getX()-DELTAX, 
					(blocks.get(i).getY()));
		
	}
	
	public ArrayList<Block> getBlocks()
	{
		return blocks;
	}
	
	public boolean delete()
	{
		return x < -50;
	}
	
	public double getEndOfPlatform()
	{
		return (x+ width);
	}
	
	public boolean canSpawn()
	{
		return !generatedNextBlock;
	}
	
	public boolean isPlayerTouching(double playerx, double playery)
	{	
		return(playerx >= this.x && playerx <= (this.x+this.width)
				&& playery >= this.y && playery <= (this.y+height));
	}

}
