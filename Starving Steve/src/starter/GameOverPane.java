package starter;

import java.awt.event.MouseEvent;

import acm.graphics.GImage;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.util.JTFTools;

public class GameOverPane extends GraphicsPane {

	private MainApplication program;
	private int score;
	private GLabel title;
	private GLabel scoreMsg;
	private GLabel playAgainMsg;

	private GImage bg, yes, no, steve;
	
	public GameOverPane(MainApplication program, int score) {
		super();
		this.program = program;
		this.score = score;
		
		bg = new GImage("../media/images/BG.png");
		bg.setSize(800, 600);
		title = new GLabel("Steve Starved!");
		scoreMsg = new GLabel("Score: " + score, 0, 0);
		playAgainMsg = new GLabel("Play Again?", 0, 0);
		if (JTFTools.findFontFamily("Kristen ITC") != null) {
			title.setFont("Kristen ITC-Bold-72");
			scoreMsg.setFont("Kristen ITC-Bold-36");
			playAgainMsg.setFont("Kristen ITC-Bold-36");
		} else {
			title.setFont("Arial-Bold-72");
			scoreMsg.setFont("Arial-Bold-36");
			playAgainMsg.setFont("Arial-Bold-36");
		}
		title.move((MainApplication.WINDOW_WIDTH - title.getWidth()) / 2, 100);
		scoreMsg.move((MainApplication.WINDOW_WIDTH - scoreMsg.getWidth()) / 2, 200);
		playAgainMsg.move((MainApplication.WINDOW_WIDTH - playAgainMsg.getWidth()) / 2, 300);
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
		yes.setLocation(200, 340);
		no = new GImage("../media/Buttons/no1.png");
		no.setLocation(400, 340);
	}
	
	@Override
	public void showContents() {

		makeGameOverMessage();
		program.add(bg);
		program.add(title);
		program.add(scoreMsg);
		program.add(playAgainMsg);
		program.add(steve);
		program.add(yes);
		program.add(no);
		
	}

	@Override
	public void hideContents() {
		program.remove(bg);
		program.remove(steve);
		program.remove(title);
		program.remove(scoreMsg);
		program.remove(playAgainMsg);
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
