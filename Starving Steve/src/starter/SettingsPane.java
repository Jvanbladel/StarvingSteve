  package starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class SettingsPane extends GraphicsPane{

	
	private MainApplication program;
	private GButton Sound, Bio, Exit; 
	//private GParagraph ; 
	Player p;
	GImage playerImage, SETTINGS;
	Timer playerAnimationTimer;
	
	public SettingsPane(MainApplication program)
	{
		super();
		this.program = program;
		drawToScreen();
	}
	
	private GLabel name;
	private String labelName;
	
	private void drawToScreen()
	{
		SETTINGS = new GImage("../media/images/SETTINGS.png"); //",50, 100);
		SETTINGS.setLocation(0, 9);
		SETTINGS.setSize(480, 150);
		//labelName = "steve will be here";
		//name = new GLabel(labelName);
		//name.setLocation(75, 400);
		//name.setFont("Arial-Bold-32");
		p = new Player();
		p.changePlayerState(PlayerStates.IDLE);
		playerImage = new GImage(p.getImage());
		playerImage.setSize(400, 400);
		playerImage.setLocation(125, 200);
		Sound = new GButton("Sound", 550, 25, 200, 100, Color.YELLOW);
		Bio = new GButton("Bio", 550, 200, 200, 100, Color.YELLOW);
		Exit = new GButton("Exit", 550, 400, 200, 100, Color.YELLOW);
		
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
		// TODO Auto-generated method stub
		//program.add(name);
		program.add(Sound); 
		program.add(SETTINGS);
		program.add(Bio);
		program.add(Exit); 
		program.add(playerImage);
		playerAnimationTimer.start();
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		//program.remove(name);
		program.remove(Sound);
		program.remove(SETTINGS);
		program.remove(Bio);
		program.remove(Exit);
		program.remove(playerImage);
		playerAnimationTimer.stop();
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == Exit)
		{
			program.switchToMenu();
		}
		//if(obj == bio)
		//{
			//program.switchToBio();
	}

}
