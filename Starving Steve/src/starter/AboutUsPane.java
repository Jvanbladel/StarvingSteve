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
	
	private GImage bg, back;
	private GImage jessica, jason, devin, abdullah;
	
	private void drawToScreen()
	{
		bg = new GImage("../media/images/BG.png");
		bg.setSize(800, 600);
		
		aboutUs = new GLabel("About");
		aboutUs.setLocation(280, 75);
		aboutUs.setFont("Arial-Bold-72");
		
		us = new GLabel("Us");
		us.setLocation(350, 145);
		us.setFont("Arial-Bold-72");
		
		jessica = new GImage("../media/images/jessica.png");
		jessica.setLocation(620, 200);
		jessica.setSize(100, 200);
		
		jason = new GImage("../media/images/jessica.png");
		jason.setLocation(440, 200);
		jason.setSize(100, 200);
		
		devin = new GImage("../media/images/jessica.png");
		devin.setLocation(260, 200);
		devin.setSize(100, 200);
		
		abdullah = new GImage("../media/images/jessica.png");
		abdullah.setLocation(80, 200);
		abdullah.setSize(100, 200);
		
		back = new GImage("../media/Buttons/back1.png");
		back.setLocation(300, 450);
	}
	
	@Override
	public void showContents() {
		program.add(bg);
		program.add(aboutUs);
		program.add(us);
		program.add(jessica);
		program.add(jason);
		program.add(devin);
		program.add(abdullah);
		program.add(back);
	}

	@Override
	public void hideContents() {
		program.remove(bg);
		program.remove(aboutUs);
		program.remove(us);
		program.remove(jessica);
		program.remove(jason);
		program.remove(devin);
		program.remove(abdullah);
		program.remove(back);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == back)
		{
			program.playSound("click");
			program.changeToSettings();
		}
				
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == back) 
		{
			back.setImage("Buttons/back2.png");
		}
		else
		{
			back.setImage("Buttons/back1.png");
		}
	}

}
