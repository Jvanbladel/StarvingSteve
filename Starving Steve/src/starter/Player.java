package starter;

public class Player {
	
	private final static int HEIGHT = 100;
	private final static int WIDTH = 50;
	
	private int x; 
	private int y;
	private boolean canDoubleJump;
	
	public Player()
	{
		this.x = 0;
		this.y = 0;
		canDoubleJump = false;
	}
	
	public boolean jump()
	{
		System.out.println("Jump");
		return true;
	}
}
