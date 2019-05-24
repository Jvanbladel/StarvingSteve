package starter;

import java.util.ArrayList;

public class Player {
	
	private final static int HEIGHT = 100;
	private final static int WIDTH = 50;
	
	private final static int MAX_ENERGY = 25;
	
	private double x; 
	private double y;
	private boolean canDoubleJump;
	private boolean doneFirstJump;
	
	private int imageNumb;
	private PlayerStates state;
	private int energy;
	
	private PowerUp[] inventory;
	
	public Player()
	{
		this.x = 4.0;
		this.y = 10.0;
		this.doneFirstJump = false;
		this.canDoubleJump = true;
		this.state = PlayerStates.IDLE;
		this.imageNumb = 1;
		this.energy = 10;
		inventory = new PowerUp[5];
	}
	
	public boolean jump()
	{
		if(state == PlayerStates.DEAD)
		{
			return false;
		}
		if(!doneFirstJump)
		{
			changePlayerState(PlayerStates.JUMPING);
			this.doneFirstJump = true;
			this.energy -=1;
			return true;
		}
		else if(canDoubleJump)
		{
			changePlayerState(PlayerStates.JUMPING);
			this.canDoubleJump = false;
			this.energy -=1;
			return true;
		}
		return false;
	}
	
	public void changePlayerState(PlayerStates state)
	{
		this.state = state;
		this.imageNumb = 1;
	}
	
	public String getImage()
	{
		imageNumb++;
		return state.getImagePath((imageNumb-1)%14 +1);
	}
	
	public PlayerStates updatePlayer(ArrayList<Obstacle> list)
	{
		PlayerStates s;
		if(state != PlayerStates.JUMPING)
		{
			s = PlayerStates.IDLE;
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i).isPlayerTouching(x, y) || list.get(i).isPlayerTouching(x+.5, y) || list.get(i).isPlayerTouching(x+1, y))
				{
					s = PlayerStates.RUNNING;
					resetJumps();
				}
			}
			if(y >= 14)
			{
				s = PlayerStates.DEAD;
			}
		}
		else
		{
			s = PlayerStates.JUMPING;
		}
			
		state = s;
		return s;
	}
	
	public PowerUp checkPowerUpCollision(ArrayList<PowerUp> powerupList)
	{
		for(int i = 0; i < powerupList.size(); i++) 
		{
			if(powerupList.get(i).hitPlayer(x, y) 
					|| powerupList.get(i).hitPlayer(x+.5, y)
					|| powerupList.get(i).hitPlayer(x+1, y)
					|| powerupList.get(i).hitPlayer(x+1, y-.5)
					|| powerupList.get(i).hitPlayer(x + 1, y - 1)
					|| powerupList.get(i).hitPlayer(x + 1, y - 1.5)
					|| powerupList.get(i).hitPlayer(x + 1, y - 2)
					|| powerupList.get(i).hitPlayer(x + .5, y- 2)
					|| powerupList.get(i).hitPlayer(x, y- 2))
			{
				addEnergy(powerupList.get(i).Collected());
				return powerupList.get(i);
			}
		}
		return null;
	}
	
	public PlayerStates getState()
	{
		return state;
	}
	
	public void movePlayer(double x, double y)
	{
		this.x +=x;
		this.y +=y;
	}
	
	public void resetJumps()
	{
		this.canDoubleJump = true;
		this.doneFirstJump = false;
	}
	
	public void addEnergy(int addThis)
	{
		if(this.energy + addThis > MAX_ENERGY)
			energy = MAX_ENERGY;
		else if(this.energy + addThis <= 0)
		{
			energy = 0;
			state = PlayerStates.DEAD;
		}
		else
			energy+= addThis;
	}
	
	public int getEnergy()
	{
		return energy;
	}
	
	public boolean addPowerUpToInventory(PowerUp power)
	{
		if(power.getPowerUp().getFoodType() == FoodType.STARTER && inventory[0] == null)
		{
			inventory[0] = power;
			return true;
		}
		else if(power.getPowerUp().getFoodType() == FoodType.MAINDISH && inventory[1] == null)
		{
			inventory[1] = power;
			return true;
		}
		else if(power.getPowerUp().getFoodType() == FoodType.SIDEDISH && inventory[2] ==  null)
		{
			inventory[2] = power;
			return true;
		}
		else if(power.getPowerUp().getFoodType() == FoodType.DRINK && inventory[3] == null)
		{
			inventory[3] = power;
			return true;
		}
		else if(power.getPowerUp().getFoodType() == FoodType.STARTER && inventory[4] == null)
		{
			inventory[4] = power;
			return true;
		}
		return false;
	}
	
	public PowerUp[] getInventory()
	{
		return inventory;
	}
}
