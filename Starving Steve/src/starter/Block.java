package starter;

public class Block {

	private String filePath;
	private double x;
	private double y;
	
	public Block(String filePath, double x, double y)
	{
		this.filePath = filePath;
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public String getFP()
	{
		return filePath;
	}
}
