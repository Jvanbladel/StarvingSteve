package starter;

import java.util.ArrayList;

public class Obstacle {
	
	private double x;
	private double y;
	private int height;
	private int width;
	
	private ArrayList<Block> blocks;
	
	public Obstacle(double x, double y, int width, int height)
	{
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
	}
	
	public boolean move(int deltaX)
	{
		this.x = x-deltaX;
		return(x < -50);
	}
	
	public ArrayList<Block> getBlocks()
	{
		return blocks;
	}
	

}
