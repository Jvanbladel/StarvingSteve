package starter;

import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.util.JTFTools;

public class AboutUsPane extends GraphicsPane{

	
	private MainApplication program;
	
	public AboutUsPane(MainApplication program)
	{
		super();
		this.program = program;
		drawToScreen();
	}
	
	private GImage ABOUTUS;
	
	private GImage bg, back;
	private GImage jessica, jason, devin, abdullah;
	private GParagraph j1, j2, d, a;
	
	private void drawToScreen()
	{
		bg = new GImage("../media/images/BG.png");
		bg.setSize(800, 600);
		
		ABOUTUS = new GImage("../media/images/BIO.png");
		ABOUTUS.setLocation(160, 15);
		ABOUTUS.setSize(460, 150);
		
		jessica = new GImage("../media/images/jessica.png");
		jessica.setLocation(620, 200);
		jessica.setSize(100, 200);
		
		j1 = new GParagraph("Product Owner\n Jessica Huang",610, 400);
		
		jason = new GImage("../media/images/jason.png");
		jason.setLocation(440, 200);
		jason.setSize(100, 200);
		
		j2 = new GParagraph("Lead Programmer\nJason Van Bladel", 422.5, 400);
		
		devin = new GImage("../media/images/devin.png");
		devin.setLocation(260, 200);
		devin.setSize(100, 200);
		
		d = new GParagraph("Evaluation Lead\n  Devin French", 247.5, 400);
		
		abdullah = new GImage("../media/images/abdulla.png");
		abdullah.setLocation(80, 200);
		abdullah.setSize(100, 200);
		
		a = new GParagraph("      Programmer\nAbdullah Alrookan", 60, 400);
		
		back = new GImage("../media/Buttons/back1.png");
		back.setLocation(300, 450);
		
		if (JTFTools.findFontFamily("Kristen ITC") != null) {
			j1.setFont("Kristen ITC-Bold-14");
			j2.setFont("Kristen ITC-Bold-14");
			d.setFont("Kristen ITC-Bold-14");
			a.setFont("Kristen ITC-Bold-14");
			//aboutUs.setFont("Kristen ITC-Bold-72");
			//us.setFont("Kristen ITC-Bold-72");
			
		} else {
			j1.setFont("Arial-Bold-14");
			j2.setFont("Arial-Bold-14");
			d.setFont("Arial-Bold-14");
			a.setFont("Arial-Bold-14");
			//aboutUs.setFont("Arial-Bold-72");
			//us.setFont("Arial-Bold-72");
		}
	}
	
	@Override
	public void showContents() {
		program.add(bg);
		program.add(ABOUTUS);
		program.add(jessica);
		program.add(jason);
		program.add(devin);
		program.add(abdullah);
		program.add(back);
		program.add(j1);
		program.add(j2);
		program.add(d);
		program.add(a);
	}

	@Override
	public void hideContents() {
		program.remove(bg);
		program.remove(ABOUTUS);
		program.remove(jessica);
		program.remove(jason);
		program.remove(devin);
		program.remove(abdullah);
		program.remove(back);
		program.remove(j1);
		program.remove(j2);
		program.remove(d);
		program.remove(a);
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
