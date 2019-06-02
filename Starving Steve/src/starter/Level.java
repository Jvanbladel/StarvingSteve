package starter;

import java.util.ArrayList;

import acm.util.RandomGenerator;

public class Level {
	
	private Player p;
	private ArrayList<Obstacle> obstacleList;
	private ArrayList<PowerUp> powerupList;
	private RandomGenerator rgen;
	
	public Level()
	{
		this.rgen = RandomGenerator.getInstance();
		p = new Player();
		p.changePlayerState(PlayerStates.RUNNING);
		obstacleList = new ArrayList<Obstacle>();
		powerupList = new ArrayList<PowerUp>();
		drawStartOfLevel();
	}
	
	private void drawStartOfLevel() 
	{
		Obstacle base = new Obstacle(-1,11, 21, 1);
		base.setGeneration(true);
		obstacleList.add(base);
		
		
		Obstacle platform = new Obstacle(23, 8, 5, 1);
		base.setGeneration(false);
		obstacleList.add(platform);
		lastPlatform = platform;
		
		PowerUp firstPowerUp = new PowerUp(25, 6);
		powerupList.add(firstPowerUp);
		lastPowerUp = firstPowerUp;
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
		
		for(int i = 0; i < powerupList.size(); i++)
		{
			powerupList.get(i).move();
		}
	}
	
	public Obstacle generateNewPlatform(double x)
	{
		Obstacle newP = new Obstacle(x, lastPowerUp);
		return newP;
	}
	
	public PowerUp generateNewPowerUp(double x, double y)
	{
		PowerUp output = new PowerUp(x, y);
		return output;
	}
	
	public ArrayList<Obstacle> getObstacles()
	{
		return obstacleList;
	}
	
	public ArrayList<PowerUp> getPowerUps()
	{
		return powerupList;
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
	
	public PowerUp deleteOldPowerUps()
	{
		for(int i = 0; i < powerupList.size(); i++)
		{
			if(powerupList.get(i).delete() || powerupList.get(i).getCollected())
			{	
				PowerUp delete = powerupList.get(i);
				powerupList.remove(i);
				return delete;
			}
		}
		return null;
	}
	
	private Obstacle lastPlatform;
	private PowerUp lastPowerUp;
	
	public Obstacle addPlatform()
	{
		if(lastPlatform.getEndOfPlatform() < 20)
		{
			Obstacle newPlatform = generateNewPlatform(
					lastPlatform.getEndOfPlatform()+rgen.nextInt(2,5));
			obstacleList.add(newPlatform);
			lastPlatform = newPlatform;
			return newPlatform;
		}
		return null;
	}
	
	public PowerUp addPowerUp()
	{
		if(lastPowerUp.getEndOfPowerUp() < 21)
		{
			PowerUp newPowerUp = generateNewPowerUp(
					lastPowerUp.getEndOfPowerUp()+rgen.nextInt(10,15), rgen.nextInt(1,9));
			
			powerupList.add(newPowerUp);
			lastPowerUp = newPowerUp;
			return newPowerUp;
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
	
	public void collectPowerUp()
	{
		p.checkPowerUpCollision(powerupList);
	}
	
	public void gravity()
	{
		p.movePlayer(0, .30);
	}
	
	public void jumping()
	{
		p.movePlayer(0, -.35);
	}
	
	public PowerUp spawnSuperPowerUp()
	{
		PowerUp superPowerUp = new PowerUp();
		powerupList.add(superPowerUp);
		return superPowerUp;
	}
	
	public int getScore()
	{
		return p.getScore();
	}
}
