package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program; 
	
	private GParagraph title;
	private GButton play;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		
		play = new GButton("Play", 550, 25, 200, 100, Color.GREEN);
		title = new GParagraph("Starving\nSteve",50, 100);
		title.setFont("Arial-Bold-72");
		
	}

	@Override
	public void showContents() {
		program.add(play);
		program.add(title);
	}

	@Override
	public void hideContents() {
		program.remove(play);
		program.remove(title);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == play)
			program.startNewGame();
	}
}//test

