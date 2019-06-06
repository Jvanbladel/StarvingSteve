  package starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class SettingsPane extends GraphicsPane{

	
	private MainApplication program;
	private GImage bg, sound, bio, exit; 
	private GParagraph SETTINGS;
	private Timer playerAnimationTimer;
	
	private GImage playerImage;
	private Player p;
	private ArrayList<GImage> floor;
	
	
	public SettingsPane(MainApplication program)
	{
		super();
		this.program = program;
		drawToScreen();
		
		p = new Player(program);
		p.changePlayerState(PlayerStates.IDLE);
		playerImage = new GImage(p.getImage());
		playerImage.setSize(400, 400);
		playerImage.setLocation(125, 200);
		
		floor = new ArrayList<GImage>();
		for(int i = 0; i < 16; i++)
		{
			GImage block = new GImage("../media/images/middle.png");
			block.setSize(50, 50);
			block.setLocation(0 + i*50, 550);
			floor.add(block);
		}
		
		playerAnimationTimer = new Timer(50, null);
		
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
	
	
	private void drawToScreen()
	{
		bg = new GImage("../media/images/BG.png");
		bg.setSize(800, 600);
		SETTINGS = new GParagraph("Settings",50, 100);
		SETTINGS.setFont("Arial-Bold-72");
		sound = new GImage("../media/Buttons/sound1.png");
		sound.setLocation(550, 75);
		bio = new GImage("../media/Buttons/bio1.png");
		bio.setLocation(550, 250);
		exit = new GImage("../media/Buttons/back1.png");
		exit.setLocation(550, 425);
	}
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(bg);
		program.add(sound); 
		program.add(SETTINGS);
		program.add(bio);
		program.add(exit); 
		
		program.add(playerImage);
		
		for(int i = 0; i< floor.size(); i++)
		{
			program.add(floor.get(i));
		}
		playerAnimationTimer.start();
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(bg);
		program.remove(sound);
		program.remove(SETTINGS);
		program.remove(bio);
		program.remove(exit);
		
		program.remove(playerImage);
		
		for(int i = 0; i< floor.size(); i++)
		{
			program.remove(floor.get(i));
		}
		playerAnimationTimer.stop();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == exit)
		{
			program.playSound("click");
			program.switchToMenu();
		}
		else if(obj == bio)
		{
			program.playSound("click");
			program.switchToAboutUs();
		}
		else if(obj == sound)
		{
			program.playSound("click");
			program.toggleSound();
			if(program.isSoundOn())
				sound.setImage("Buttons/sound2.png");
			else
				sound.setImage("Buttons/sound4.png");
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == exit) 
		{
			exit.setImage("Buttons/back2.png");
		}
		else if (obj == bio) 
		{
			bio.setImage("Buttons/bio2.png");
		}
		else if (obj == sound) 
		{
			if(program.isSoundOn())
				sound.setImage("Buttons/sound2.png");
			else
				sound.setImage("Buttons/sound4.png");
		}
		else 
		{
			exit.setImage("Buttons/back1.png");
			bio.setImage("Buttons/bio1.png");
			
			if(program.isSoundOn())
				sound.setImage("Buttons/sound1.png");
			else
				sound.setImage("Buttons/sound3.png");
		}
	}

}
