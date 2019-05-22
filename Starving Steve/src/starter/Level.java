package starter;

import java.util.ArrayList;

import acm.util.RandomGenerator;

public class Level {
	
	private Player p;
	private ArrayList<Obstacle> obstacleList;
	private RandomGenerator rgen;
	
	public Level()
	{
		this.rgen = RandomGenerator.getInstance();
		p = new Player();
		p.changePlayerState(PlayerStates.RUNNING);
		obstacleList = new ArrayList<Obstacle>();
		drawStartOfLevel();
	}
	
	private void drawStartOfLevel() 
	{
		Obstacle base = new Obstacle(0,10, 20, 1);
		base.setGeneration(true);
		obstacleList.add(base);
		
		
		Obstacle platform = new Obstacle(23, 7, 5, 1);
		base.setGeneration(false);
		obstacleList.add(platform);
		lastPlatform = platform;
	}

	public Player getPlayer()
	{
		return p;
	}
	
	public void move()
	{
		for(int i = 0; i < obstacleList.size();i++)
		{
			obstacleList.get(i).move();
		}
	}
	
	public Obstacle generateNewPlatform(double x)
	{
		Obstacle newP = new Obstacle(x);
		return newP;
	}
	
	public ArrayList<Obstacle> getObstacles()
	{
		return obstacleList;
	}
	
	public Obstacle deleteOldPlatforms()
	{
		for(int i = 0; i < obstacleList.size(); i++)
		{
			if(obstacleList.get(i).delete())
			{
				Obstacle delete = obstacleList.get(i);
				obstacleList.remove(i);
				return delete;
			}
		}
		return null;
	}
	
	private Obstacle lastPlatform;
	
	public Obstacle addPlatform()
	{
		if(lastPlatform.getEndOfPlatform() < 20)
		{
			Obstacle newPlatform = generateNewPlatform(
					lastPlatform.getEndOfPlatform()+rgen.nextInt(3,6));
			obstacleList.add(newPlatform);
			lastPlatform = newPlatform;
			return newPlatform;
		}
		return null;
	}
	
	public boolean jump()
	{
		return p.jump();
	}
	
	public String getPlayerImage()
	{
		return p.getImage();
	}
	
	public void changePlayerState()
	{
		p.updatePlayer(obstacleList);
	}
	
	public void gravity()
	{
		p.movePlayer(0, .30);
	}
	
	public void jumping()
	{
		p.movePlayer(0, -.35);
	}
}
