package starter;

import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class SettingsPane extends GraphicsPane{

	
	private MainApplication program;
	
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
		labelName = "Abdullah";
		name = new GLabel(labelName);
		name.setLocation(75, 400);
		name.setFont("Arial-Bold-32");
	}
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(name);
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(name);
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == name)
		{
			program.switchToMenu();
		}
		//if(obj == item selected)
				//do an action
	}

}
