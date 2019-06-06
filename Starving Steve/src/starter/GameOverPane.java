package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class GameOverPane extends GraphicsPane {

	private MainApplication program;
	private int score;
	private GParagraph message;
	private GImage bg, yes, no, steve;
	
	public GameOverPane(MainApplication program, int score) {
		super();
		this.program = program;
		this.score = score;
	}
	
	/*public void setScore(int score) {
		this.score = Double.toString(score);
	}*/
	
	private void makeGameOverMessage() {
		message = new GParagraph("STEVE STARVED\nScore: " + score + "\nPlay Again?", 0, 0);
		message.setFont("Arial-Bold-32");
		message.move((MainApplication.WINDOW_WIDTH - message.getWidth()) / 2, 200);
		
		bg = new GImage("../media/images/BG.png");
		bg.setSize(800, 600);
		
		steve = new GImage("../player/dead14.png");
		steve.setSize(300, 300);
		steve.setLocation(225, 300);
		
		yes = new GImage("../media/Buttons/yes1.png");
		yes.setLocation(150, 300);
		no = new GImage("../media/Buttons/no1.png");
		no.setLocation(450, 300);
	}
	
	@Override
	public void showContents() {
		makeGameOverMessage();
		program.add(bg);
		program.add(message);
		program.add(steve);
		program.add(yes);
		program.add(no);
		
	}

	@Override
	public void hideContents() {
		program.remove(bg);
		program.remove(steve);
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
