package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;

public class GameOverPane extends GraphicsPane {

	private MainApplication program;
	private String score = "0";
	private GParagraph message;
	private GButton yes;
	private GButton no;
	
	public GameOverPane(MainApplication program) {
		super();
		this.program = program;
	}
	
	public void setScore(double score) {
		this.score = Double.toString(score);
	}
	
	private void makeGameOverMessage() {
		message = new GParagraph("GAME OVER\nScore: " + score + "\nPlay Again?", 0, 0);
		message.setFont("Arial-Bold-32");
		message.move((MainApplication.WINDOW_WIDTH - message.getWidth()) / 2, 200);
		yes = new GButton("Yes", 150, 300, 200, 100, Color.GREEN);
		no = new GButton("No", 400, 300, 200, 100, Color.RED);
	}
	
	@Override
	public void showContents() {
		makeGameOverMessage();
		program.add(message);
		program.add(yes);
		program.add(no);
	}

	@Override
	public void hideContents() {
		program.remove(message);
		program.remove(yes);
		program.remove(no);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == yes)
			program.startNewGame();
		if(obj == no)
			program.switchToMenu();
	}

}
