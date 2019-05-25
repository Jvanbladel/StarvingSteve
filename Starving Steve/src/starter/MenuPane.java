package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program; 
	
	private GParagraph title;
	private GButton play, settings;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		
		play = new GButton("Play", 550, 25, 200, 100, Color.GREEN);
		settings = new GButton("Settings", 550, 150, 200, 100, Color.YELLOW);
		title = new GParagraph("Starving\nSteve",50, 100);
		title.setFont("Arial-Bold-72");
		
	}

	@Override
	public void showContents() {
		program.add(play);
		program.add(title);
		program.add(settings);
	}

	@Override
	public void hideContents() {
		program.remove(play);
		program.remove(title);
		program.remove(settings);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == play)
			program.startNewGame();
		if(obj == settings)
			program.changeToSettings();
	}
}//test

