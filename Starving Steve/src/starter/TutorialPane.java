package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class TutorialPane extends GraphicsPane {

	private MainApplication program;
	private GImage tutorial;
	private GImage play;
	private GImage unchecked;
	private GImage checked;
	private GImage checkboxMessage;
	private GImage bg;
	private boolean isChecked;

	public TutorialPane(MainApplication program) {
		super();
		this.program = program;
		makeTutorialScreen();
	}

	private void makeTutorialScreen() {
		bg = new GImage("../media/images/BG.png");
		bg.setSize(800, 600);
		tutorial = new GImage("../media/images/tutorial.png");
		tutorial.setSize(800, 600);
		play = new GImage("../media/Buttons/play-lg1.png");
		play.move((MainApplication.WINDOW_WIDTH - play.getWidth()) / 2, 400);
		checkboxMessage = new GImage("../media/images/do-not-show-again.png");
		unchecked = new GImage("../media/Buttons/checkbox3.png");
		checked = new GImage("../media/Buttons/checkbox1.png");
		checkboxMessage.setSize(300, 18);
		unchecked.setSize(30, 30);
		checked.setSize(30, 30);
		unchecked.move(play.getX() - 45, 520);
		checked.move(play.getX() - 45, 520);
		checkboxMessage.move(checked.getX() + checked.getWidth() + 10, checked.getY() + checkboxMessage.getHeight() / 2);
		unchecked.setVisible(!isChecked);
		checked.setVisible(isChecked);
	}

	@Override
	public void showContents() {
		program.add(bg);
		program.add(tutorial);
		program.add(play);
		program.add(unchecked);
		program.add(checked);
		program.add(checkboxMessage);
	}

	@Override
	public void hideContents() {
		program.remove(bg);
		program.remove(tutorial);
		program.remove(play);
		program.remove(unchecked);
		program.remove(checked);
		program.remove(checkboxMessage);
		program.showTutorial = !isChecked;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == play)
		{
			program.playSound("click");
			program.startNewGame();
		}
		if (obj == unchecked || obj == checked) {
			program.playSound("click");
			isChecked = !isChecked;
			unchecked.setVisible(!isChecked);
			checked.setVisible(isChecked);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == play) 
		{
			play.setImage("../media/Buttons/play-lg2.png");
		}
		else if (obj == unchecked || obj == checked)
		{
			unchecked.setImage("../media/Buttons/checkbox4.png");
			checked.setImage("../media/Buttons/checkbox2.png");
			unchecked.setSize(30, 30);
			checked.setSize(30, 30);
		}
		else 
		{
			play.setImage("../media/Buttons/play-lg1.png");
			unchecked.setImage("../media/Buttons/checkbox3.png");
			checked.setImage("../media/Buttons/checkbox1.png");
			unchecked.setSize(30, 30);
			checked.setSize(30, 30);
		}
	}

}
