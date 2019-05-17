package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;

public class GamePane extends GraphicsPane 
{
	private MainApplication program; 
	
	public GamePane(MainApplication app) 
	{
		super();
		program = app;
	}
	
	@Override
	public void showContents() 
	{
	
	}
	
	@Override
	public void hideContents() 
	{

	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
	}

}
