package starter;

import java.util.ArrayList;

public class Player {
	
	private final static int HEIGHT = 100;
	private final static int WIDTH = 50;
	
	private double x; 
	private double y;
	private boolean canDoubleJump;
	private boolean doneFirstJump;
	
	private int imageNumb;
	private PlayerStates state;
	
	public Player()
	{
		this.x = 4.0;
		this.y = 10.0;
		this.doneFirstJump = false;
		this.canDoubleJump = true;
		this.state = PlayerStates.IDLE;
		this.imageNumb = 1;
	}
	
	public boolean jump()
	{
		if(!doneFirstJump)
		{
			changePlayerState(PlayerStates.JUMPING);
			this.doneFirstJump = true;
			return true;
		}
		else if(canDoubleJump)
		{
			changePlayerState(PlayerStates.JUMPING);
			this.canDoubleJump = false;
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
			if(y >= 12)
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
	
}
