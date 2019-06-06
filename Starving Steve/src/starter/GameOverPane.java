package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.util.JTFTools;

public class GameOverPane extends GraphicsPane {

	private MainApplication program;
	private int score;
	private GLabel title;
	private GParagraph message;

	private GImage bg, yes, no, steve;
	
	public GameOverPane(MainApplication program, int score) {
		super();
		this.program = program;
		this.score = score;
		
		bg = new GImage("../media/images/BG.png");
		bg.setSize(800, 600);
		title = new GLabel("Steve Starved!");
		message = new GParagraph("Score: " + score + "\n\nPlay Again?", 0, 0);
		if (JTFTools.findFontFamily("Kristen ITC") != null) {
			title.setFont("Kristen ITC-Bold-72");
			message.setFont("Kristen ITC-Bold-36");
		}
		title.move((MainApplication.WINDOW_WIDTH - title.getWidth()) / 2, 100);
		message.move((MainApplication.WINDOW_WIDTH - message.getWidth()) / 2, 200);
		/*yes = new GImage("../media/Buttons/yes1.png");
		no = new GImage("../media/Buttons/no1.png");
		yes.move(200, 380);
		no.move(400, 380);*/
	}
	
	/*public void setScore(int score) {
		this.score = Double.toString(score);
	}*/
	
	private void makeGameOverMessage() {
		
		bg = new GImage("../media/images/BG.png");
		bg.setSize(800, 600);
		
		steve = new GImage("../player/dead14.png");
		steve.setSize(300, 300);
		steve.setLocation(225, 325);
		
		yes = new GImage("../media/Buttons/yes1.png");
		yes.setLocation(200, 350);
		no = new GImage("../media/Buttons/no1.png");
		no.setLocation(400, 350);
	}
	
	@Override
	public void showContents() {

		makeGameOverMessage();
		program.add(bg);
		program.add(title);
		program.add(message);
		program.add(steve);
		program.add(yes);
		program.add(no);
		
	}

	@Override
	public void hideContents() {
		program.remove(bg);
		program.remove(steve);
		program.remove(title);
		program.remove(message);
		program.remove(yes);
		program.remove(no);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == yes)
		{
			program.playSound("click");
			program.switchToTutorial();
		}
		if(obj == no)
		{
			program.playSound("click");
			program.switchToMenu();
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == no) 
		{
			no.setImage("Buttons/no2.png");
		}
		else if (obj == yes) 
		{
			yes.setImage("Buttons/yes2.png");
		}
		else 
		{
			yes.setImage("Buttons/yes1.png");
			no.setImage("Buttons/no1.png");
		}
	}

}
