package starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program; 
	
	private GImage title, background;
	private GButton play, settings;
	
	Player p;
	GImage playerImage;
	Timer playerAnimationTimer;

	
	public MenuPane(MainApplication app) {
		super();
		program = app;
		
		
		play = new GButton("Play", 550, 25, 200, 100, Color.GREEN);
		settings = new GButton("Settings", 550, 150, 200, 100, Color.YELLOW); 
		title = new GImage("../media/images/STARVING STEVE 3.png");
		title.setLocation(0, 9);
		title.setSize(550, 200);
		
		//background.setLocation(0, 9);
		//background.setSize(550, 200);
		
		
		
		//title.setFont("Arial-Bold-72");
		
		p = new Player();
		p.changePlayerState(PlayerStates.IDLE);
		playerImage = new GImage(p.getImage());
		playerImage.setSize(400, 400);
		playerImage.setLocation(125, 200);
		
		playerAnimationTimer = new Timer(100, null);
		
		playerAnimationTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				GImage newPlayerImage = new GImage(p.getImage());
				newPlayerImage.setLocation(125, 200);
				newPlayerImage.setSize(400, 400);
				
				program.remove(playerImage);
				program.add(newPlayerImage);
				playerImage = newPlayerImage;
			}
		});
		
	}

	@Override
	public void showContents() {
		program.add(play);
		program.add(title);
		program.add(settings);
		program.add(playerImage);
		//program.add(background);
		playerAnimationTimer.start();
	}

	@Override
	public void hideContents() {
		program.remove(play);
		program.remove(title);
		program.remove(settings);
		program.remove(playerImage);
		//program.remove(background);
		playerAnimationTimer.stop();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == play)
			program.startNewGame();
		else if(obj == settings)
			program.changeToSettings();
		else if(obj == playerImage)
			program.switchToAboutUs();
		
	}
}//test

