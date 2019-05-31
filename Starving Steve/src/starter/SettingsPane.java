  package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class SettingsPane extends GraphicsPane{

	
	private MainApplication program;
	private GButton sound, bio, exit; 
	private GParagraph SETTINGS; 
	
	public SettingsPane(MainApplication program)
	{
		super();
		this.program = program;
		drawToScreen();
	}
	
	private GLabel name;
	private String labelName;
	
	private void drawToScreen()
	{
		SETTINGS = new GParagraph("Settings",50, 100);
		SETTINGS.setFont("Arial-Bold-72");
		labelName = "steve will be here";
		name = new GLabel(labelName);
		name.setLocation(75, 400);
		name.setFont("Arial-Bold-32");
		sound = new GButton("sound", 550, 25, 200, 100, Color.YELLOW);
		bio = new GButton("bio", 550, 200, 200, 100, Color.YELLOW);
		exit = new GButton("exit", 550, 400, 200, 100, Color.YELLOW);
	}
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(name);
		program.add(sound); 
		program.add(SETTINGS);
		program.add(bio);
		program.add(exit); 
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(name);
		program.remove(sound);
		program.remove(SETTINGS);
		program.remove(bio);
		program.remove(exit);
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == exit)
		{
			program.switchToMenu();
		}
		//if(obj == item selected)
				//do an action
	}

}
