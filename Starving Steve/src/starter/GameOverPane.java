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
	private GImage bg;
	private GImage yes;
	private GImage no;
	
	public GameOverPane(MainApplication program, int score) {
		super();
		this.program = program;
		this.score = score;
		
		bg = new GImage("../media/images/BG.png");
		bg.setSize(800, 600);
		title = new GLabel("Game Over");
		message = new GParagraph("Score: " + score + "\n\nPlay Again?", 0, 0);
		if (JTFTools.findFontFamily("Kristen ITC") != null) {
			title.setFont("Kristen ITC-48");
			message.setFont("Kristen ITC-36");
		}
		title.move((MainApplication.WINDOW_WIDTH - title.getWidth()) / 2, 150);
		message.move((MainApplication.WINDOW_WIDTH - message.getWidth()) / 2, 250);
		yes = new GImage("../media/Buttons/yes1.png");
		no = new GImage("../media/Buttons/no1.png");
		yes.move(200, 380);
		no.move(400, 380);
	}
	
	/*public void setScore(int score) {
		this.score = Double.toString(score);
	}*/
	
	@Override
	public void showContents() {
		program.add(bg);
		program.add(title);
		program.add(message);
		program.add(yes);
		program.add(no);
	}

	@Override
	public void hideContents() {
		program.remove(bg);
		program.remove(title);
		program.remove(message);
		program.remove(yes);
		program.remove(no);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == yes)
			program.switchToTutorial();
		if(obj == no)
			program.switchToMenu();
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == yes) 
		{
			yes.setImage("../media/Buttons/yes2.png");
		}
		else if (obj == no)
		{
			no.setImage("../media/Buttons/no2.png");
		}
		else 
		{
			yes.setImage("../media/Buttons/yes1.png");
			no.setImage("../media/Buttons/no1.png");
		}
	}

}
