package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class TutorialPane extends GraphicsPane {

	private MainApplication program;
	private GLabel title;
	private GParagraph message;
	private GButton play;
	private GImage unchecked;
	private GImage checked;
	private boolean isChecked;

	public TutorialPane(MainApplication program) {
		super();
		this.program = program;
		makeTutorialScreen();
	}

	private void makeTutorialScreen() {
		title = new GLabel("Tutorial");
		title.setFont("Arial-Bold-32");
		title.move((MainApplication.WINDOW_WIDTH - title.getWidth()) / 2, 100);
		message = new GParagraph("Objective:\nJump between platforms while collecting power-ups. Progress as far as possible.\nCollect a full course meal to spawn a super power-up.", 0, 0);
		message.move((MainApplication.WINDOW_WIDTH - message.getWidth()) / 2, 110 + title.getHeight());
		play = new GButton("Play", 0, 0, 400, 60, Color.GREEN);
		play.move((MainApplication.WINDOW_WIDTH - play.getWidth()) / 2, 400);
		unchecked = new GImage("../media/images/checkbox-unchecked.png");
		checked = new GImage("../media/images/checkbox-checked.png");
		unchecked.move((MainApplication.WINDOW_WIDTH - unchecked.getWidth()) / 2, 470);
		checked.move((MainApplication.WINDOW_WIDTH - checked.getWidth()) / 2, 470);
		unchecked.setVisible(!isChecked);
		checked.setVisible(isChecked);
	}

	@Override
	public void showContents() {
		program.add(title);
		program.add(message);
		program.add(play);
		program.add(unchecked);
		program.add(checked);
	}

	@Override
	public void hideContents() {
		program.remove(title);
		program.remove(message);
		program.remove(play);
		program.remove(unchecked);
		program.remove(checked);
		program.showTutorial = !isChecked;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == play)
			program.startNewGame();
		if (obj == unchecked || obj == checked) {
			isChecked = !isChecked;
			unchecked.setVisible(!isChecked);
			checked.setVisible(isChecked);
		}

	}

}
