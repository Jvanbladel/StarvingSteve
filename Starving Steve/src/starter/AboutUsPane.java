package starter;

import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class AboutUsPane extends GraphicsPane{

	
	private MainApplication program;
	
	public AboutUsPane(MainApplication program)
	{
		super();
		this.program = program;
		drawToScreen();
	}
	
	private GLabel aboutUs, us;
	
	private GImage jessica;
	
	private void drawToScreen()
	{
		aboutUs = new GLabel("About");
		aboutUs.setLocation(280, 75);
		aboutUs.setFont("Arial-Bold-72");
		
		us = new GLabel("Us");
		us.setLocation(350, 145);
		us.setFont("Arial-Bold-72");
		
		jessica = new GImage("../media/images/jessica.png");
		jessica.setLocation(600, 245);
		jessica.setSize(100, 200);
	}
	
	@Override
	public void showContents() {
		program.add(aboutUs);
		program.add(us);
		program.add(jessica);
	}

	@Override
	public void hideContents() {
		program.remove(aboutUs);
		program.remove(us);
		program.remove(jessica);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		//if(obj == item selected)
				//do an action
	}

}
