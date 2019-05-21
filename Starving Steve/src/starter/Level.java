package starter;

import java.util.ArrayList;

public class Level {
	
	private Player p;
	private ArrayList<Obstacle> obstacleList;
	
	public Level()
	{
		p = new Player();
		obstacleList = new ArrayList<Obstacle>();
		drawStartOfLevel();
	}
	
	private void drawStartOfLevel() 
	{
		Obstacle base = new Obstacle(0,10, 20, 1);
		obstacleList.add(base);
	}

	public Player getPlayer()
	{
		return p;
	}
	
	public void move()
	{
		
	}
	
	public ArrayList<Obstacle> getObstacles()
	{
		return obstacleList;
	}
	
	
	
}
