package starter;

public enum PlayerStates {

	IDLE, RUNNING, JUMPING, DEAD;
	
	public final static String FP= "../media/player/";
	public final static String EXT= ".png";
	
	
	public String getImagePath(int x)
	{
		switch(this) 
		{
		case IDLE: return FP+"Idle"+x+EXT;
		case RUNNING: return FP+"Run"+x+EXT;
		case JUMPING: return FP+"Jump"+x+EXT;
		case DEAD: return FP+"Dead"+x+EXT;
		}	
		return "";
	}
}
